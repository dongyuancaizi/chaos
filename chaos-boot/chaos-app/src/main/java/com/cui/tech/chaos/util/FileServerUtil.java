package com.cui.tech.chaos.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 服务器文件Util
 */
public class FileServerUtil {
    //服务器暂存文件根目录
    public static final String SERVER_FILE_PATH = "/upload/";

    /**
     * 根据文件名生成文件路径
     *
     * @param fileName
     * @return "{文件类型}/{日期字符串yyyyMMdd}/{当前时间戳}/{文件名}
     */
    public static final String genFilePath(String fileName) {
        String result = null;
        String fileFormat = fileName.substring(fileName.lastIndexOf(".") + 1);
        Date currentDate = new Date();
        String dateFormat = new SimpleDateFormat("yyyyMMdd").format(currentDate);
        String timestamp = currentDate.getTime() + "";
        result = fileFormat + "/" + dateFormat + "/" + timestamp;
        return result;
    }

    /**
     * 从服务器读取文件
     * @param filePath
     * @param fileName
     * @return
     */
    public static final File getFileFromServer(String filePath, String fileName) {
        return new File(SERVER_FILE_PATH + filePath + "/" + fileName);
    }

    /**
     * 将文件保存到服务器
     *
     * @param fileName
     * @param inputStream
     * @throws IOException
     */
    public static final void saveFileInServer(String filePath, String fileName, InputStream inputStream) throws IOException {
        OutputStream outputStream = null;
        try {
            filePath = SERVER_FILE_PATH + filePath;
            //在服务器上创建文件目录
            File directory = new File(filePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            //在服务器上创建文件
            File file = new File(filePath + "/" + fileName);
            file.createNewFile();
            //写入文件数据
            outputStream = new FileOutputStream(file);
            byte temp[] = new byte[1024];
            int size = -1;
            while ((size = inputStream.read(temp)) != -1) {
                outputStream.write(temp, 0, size);
            }
        } finally {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
        }
    }

    /**
     * 将文件从服务器删除
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static final boolean deleteFileFromServer(String filePath, String fileName) {
        File file = new File(SERVER_FILE_PATH + filePath + "/" + fileName);
        if (file.exists()) {
            file.delete();
        }
        return true;
    }
}
