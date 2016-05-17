package com.hbut.admin.action;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.Config;
import com.util.StreamHandler;

import java.util.List;

public class GetFileListAction extends ActionSupport {

    private static final long serialVersionUID = -29181731L;
    private static final Logger logger = LoggerFactory.getLogger(GetFileListAction.class);

    private String type;
    private String path;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private List<String> inputFilesName;

    public List<String> getInputFilesName() {
        return inputFilesName;
    }

    public void setInputFilesName(List<String> inputFilesName) {
        this.inputFilesName = inputFilesName;
    }

    public String fileList() {

        try {
            String[] inN = StreamHandler.getFilesNameList(Config.getValue(type)
                    + path);

            inputFilesName =  Lists.newArrayList();

            if (inN != null)
                for (int i = 0; i < inN.length; i++) {
                    inputFilesName.add(inN[i]);
                }
            return SUCCESS;
        } catch (Exception e) {

            logger.error("fileList 获取配置信息错误", e);
            return "error";
        }

    }

}
