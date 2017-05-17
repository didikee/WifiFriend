package com.didikee.wififriend.presenters;

import android.text.TextUtils;

import com.didikee.wififriend.constant.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * Created by didikee on 09/05/2017.
 */

public class FileRWerUtil implements IFileRWerUtil{
    private String mInnerFilePath;
    private String mFileName;

    public FileRWerUtil(String filePath){
        mFileName = Config.FILE_NAME;
        mInnerFilePath = filePath;
    }

    @Override
    public boolean overwrite(String filePath, String content) {
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(content)){
            return false;
        }
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean append(String filePath, String content) {
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(content)){
            return false;
        }
        try {
            File file = new File(filePath);
            //第二个参数意义是说是否以append方式添加内容
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(content);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getContent(String fileFullPath) {
        String content = "";
        try {
            File file = new File(fileFullPath);
            FileInputStream is = new FileInputStream(file);
            byte[] b = new byte[is.available()];
            int read = is.read(b);
            content = new String(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
