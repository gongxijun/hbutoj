package com.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Upload {

    public static boolean delete(String path) throws IOException {
        File dest = new File(path);
        if (true == dest.exists()) {
            FileUtils.forceDelete(dest);
        }
        return true;
    }

    public static boolean save(File file, String path) throws IOException {
        try {
            File dest = new File(path);
            if (!dest.getParentFile().exists())
                dest.getParentFile().mkdirs();
            FileUtils.copyFile(file, dest);

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean uploadFile(File file, String path, String fileName) {
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(path + "\\" + fileName);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            fis.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
