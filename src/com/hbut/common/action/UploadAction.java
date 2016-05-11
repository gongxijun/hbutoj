package com.hbut.common.action;

import com.hbut.user.service.UserService;
import com.hbut.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.util.CompressImage;
import com.util.Upload;
import org.apache.struts2.ServletActionContext;

import java.io.File;

public class UploadAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 8183005531295919951L;
    private static final Logger logger = LoggerFactory.getLogger(UploadAction.class);

    private UserService userService;
    private File titlePhoto;

    public File getTitlePhoto() {
        return titlePhoto;
    }

    public void setTitlePhoto(File titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String uploadPhoto() throws Exception {
        try {
            // System.out.println(titlePhoto);
            String username = (String) ActionContext.getContext().getSession()
                    .get("session_username");
            if (null == username || "".equals(username)) {
                return LOGIN;
            }
            User user_ = userService.queryUser(username);
            if (user_ == null) {
                return LOGIN;
            }
            String root = ServletActionContext.getRequest().getRealPath(
                    "/upload");
            String path_orig = root + "\\userphoto\\" + username;
            if (Upload.uploadFile(titlePhoto, path_orig, "photo.jpg") == false) {
                return ERROR;
            }
            CompressImage.Generate(path_orig + "\\photo.jpg", path_orig,
                    "photo_250", 250);// 大头像
            CompressImage.Generate(path_orig + "\\photo.jpg", path_orig,
                    "photo_80", 80);// 小缩略头像
            CompressImage.Generate(path_orig + "\\photo.jpg", path_orig,
                    "photo_50", 50);// 小缩略头像
            // back-up
            /*
             * root = "E:\\OJ_BACKUP\\image"; path_orig =
			 * root+"\\userphoto\\"+username;
			 * if(Upload.uploadFile(titlePhoto,path_orig,"photo.jpg")==false){
			 * return ERROR; }
			 * CompressImage.Generate(path_orig+"\\photo.jpg",path_orig
			 * ,"photo_big",250);//大头像
			 * CompressImage.Generate(path_orig+"\\photo.jpg"
			 * ,path_orig,"photo_small",80);//小缩略头像
			 */
            user_.setAvatar("Y");
            userService.save(user_);
        } catch (Exception e) {
            // TODO: handle exception
            this.addFieldError("tip", "Unknown error.");
            logger.error("更新时出现异常 {}", e);
            return ERROR;
        }
        return SUCCESS;
    }
}
