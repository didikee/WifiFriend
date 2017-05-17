package com.didikee.wififriend.presenters;

/**
 * Created by didikee on 09/05/2017.
 */

public interface IFileRWerUtil {
    boolean overwrite(String filePath,String content);
    boolean append(String filePath,String content);
    String getContent(String fileFullPath);
}
