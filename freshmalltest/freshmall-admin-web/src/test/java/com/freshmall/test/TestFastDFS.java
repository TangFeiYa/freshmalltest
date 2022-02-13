package com.freshmall.test;

import com.freshmall.utils.FastDFSClientUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

/**
 * 测试文件上传服务器*/


public class TestFastDFS {

    /* * 步骤：
     *      1、使用ClientGlobal的静态方法加载Tracker服务器配置信息（注意：必须为全路径）
     *      2、创建TrackerClient对象
     *      3、使用TrackerClient获取TrackerServer对象
     *      4、创建StorageServer对象，引用为null即可
     *      5、使用TrackerServer和StorageServer构造StorageClient对象
     *      6、使用StorageClient进行文件上传
     *      7、返回的字符串数组为上传文件在服务器中所在位置以及文件名*/


    @Test
    public void fileUpload() throws IOException, MyException {
        //加载连接信息（即FastDFS中tracker服务器IP）
        ClientGlobal.init("E:\\freshmall-git\\freshmalltest\\freshmall-admin-web\\src\\main\\resources\\fdfs_client.conf");
        //创建TrackerClient对象\
        TrackerClient trackerClient = new TrackerClient();
        //获取TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //创建StorageServer对象，引用为空
        StorageServer storageServer = null;
        //使用TrackerServer和StorageServer构造StorageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //使用storageClient上传文件到服务器
        String[] strings = storageClient.upload_file("C:\\test.png", "png", null);
        //上传成功会返回一个字符数组，分别为：文件所在组和文件在组中的位置及名称
        for(String s:strings){
            System.out.println(s);
        }
        // http://172.81.235.217:8080/group1/M00/00/00/rBEACGFCsBmAbcwsAACUABNBz0M365.png
    }

    @Test
    public void fileUploadUtil() throws Exception {
        //使用Tracker服务器信息配置文件位置构造工具类对象
        FastDFSClientUtils util = new FastDFSClientUtils("E:\\freshmall-git\\freshmalltest\\freshmall-admin-web\\src\\main\\resources\\fdfs_client.conf");
        //上传文件到文件服务器
        String s = util.uploadFile("E:\\123.png");
        //打印文件在服务器所在位置
        System.out.print(s);
    }
}
