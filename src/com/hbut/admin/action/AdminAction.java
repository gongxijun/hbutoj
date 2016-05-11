package com.hbut.admin.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.Config;
import com.util.PasswordMD5;
import com.util.Upload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@SuppressWarnings("unused")
public class AdminAction extends ActionSupport {


    private final static Logger logger = LoggerFactory.getLogger(AdminAction.class);

    private String opensource;
    private String privilege;
    private String privilege_value;
    private String cmdline;
    private String topic_switch;

    private File file;

    public String getTopic_switch() {
        return topic_switch;
    }

    public void setTopic_switch(String topicSwitch) {
        topic_switch = topicSwitch;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getCmdline() {
        return cmdline;
    }

    public void setCmdline(String cmdline) {
        this.cmdline = cmdline;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getPrivilege_value() {
        return privilege_value;
    }

    public void setPrivilege_value(String privilegeValue) {
        privilege_value = privilegeValue;
    }

    public String getOpensource() {
        return opensource;
    }

    public void setOpensource(String opensource) {
        this.opensource = opensource;
    }

    public boolean getPrivilege(String privilegeString) {
        String s = Config.getValue(privilegeString);
        if (s != null && s.equals("YES")) {
            return true;
        } else
            return false;
    }

    public boolean setPrivilege(String privilege, String value)
            throws IOException {
        if (true == Config.setValue(privilege, value)) {
            return true;
        }
        return false;
    }

    public void outString(String str) {
        try {
            PrintWriter out = ServletActionContext.getResponse().getWriter();
            out.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String switchs() {
        opensource = getPrivilege("OPENSOURCE") == true ? "YES" : "NO";
        topic_switch = getPrivilege("OPENBBS") == true ? "YES" : "NO";

        return "switchs";
    }

    public String changeSwitch() throws IOException {
        String username = (String) ActionContext.getContext().getSession()
                .get("session_username");
        if (username == null) {
            outString(getError("need login!"));
            return null;
        }

        try {
            if (false == setPrivilege(privilege, privilege_value)) {
                JSONObject obj = new JSONObject();
                obj.put("error", 1);
                obj.put("message", "Operate Failed...");
                outString(obj.toJSONString());
                return null;
            }
        } catch (Exception e) {
            // TODO: handle exception
            JSONObject obj = new JSONObject();
            obj.put("error", 1);
            obj.put("message", "Operate Failed...");
            outString(obj.toJSONString());
            logger.error("changeSwitch 内部出错", e);
            return null;
        }
        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("message", "Operate Success...");
        outString(obj.toJSONString());
        return null;
    }

    private String getError(String message) {

        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }

    public String runcmd() {
        /*
         * String[] cmd = cmdline.split(" "); try {
		 * Runtime.getRuntime().exec(cmd); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
        return "admin";
    }

    public String upload() throws Exception {
        try {
            // System.out.println(titlePhoto);
            String username = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (null == username || "".equals(username)) {
                return LOGIN;
            }

            // Struts2 请求 包装过滤器，此处使用struts2的包装过滤器
            MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) ServletActionContext
                    .getRequest();
            if (wrapper.getFileNames("file") == null) {
                ActionContext.getContext().put("tip", "No File...");
                // out.println(getError("请选择文件。"));
                return INPUT;
            }
            // 获得上传的文件名
            String fileName = wrapper.getFileNames("file")[0];
            System.out.println(wrapper.getFileNames("file").length);
            // imgFile
            // 获得文件过滤器
            File file = wrapper.getFiles("file")[0];

            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
                    .toLowerCase();

            // 重构上传文件的名称
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newName = fileName.substring(0, fileName.lastIndexOf("."))
                    + PasswordMD5.MD5(username + df.format(new Date())
                    + new Random().nextInt(1000)) + "." + fileExt;

            String root = ServletActionContext.getRequest().getRealPath(
                    "/upload");
            String path = root + "\\file";
            if (Upload.uploadFile(file, path, newName) == false) {
                ActionContext.getContext().put("tip", "upload faild.");
                return ERROR;
            }

            ActionContext.getContext().put("tip",
                    "upload success , File path is: upload\\file\\" + newName);
        } catch (Exception e) {
            // TODO: handle exception
            ActionContext.getContext().put("tip", "upload faild.");
            logger.error("upload 内部出错", e);
            return ERROR;
        }

        return "admin";
    }
}
