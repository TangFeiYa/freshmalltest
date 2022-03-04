package com.freshmall.controller;


import com.freshmall.utils.FastDFSClientUtils;
import com.freshmall.utils.MessageKindEditor;
import com.freshmall.utils.ResultCode;
import com.freshmall.utils.ResultCommon;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
@Slf4j
public class UploadController {

    private final static String FILE_SERVER_URL = "http://101.43.242.192:8080/";// 文件服务器地址

    @RequestMapping("/uploadImg")
    public ResultCommon uploadImg(@RequestParam("file") MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        if(StringUtils.isEmpty(originalFilename)){
            return ResultCommon.fail(ResultCode.FAIL);
        }else{
            //扩展名
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            try {
//             2、创建一个 FastDFS 的客户端
                FastDFSClientUtils fastDFSClient = new FastDFSClientUtils("classpath:fdfs_client.conf");
//             3、执行上传处理
                String path = fastDFSClient.uploadFile(file.getBytes(), extName);
//             4、拼接返回的 url 和 ip 地址，拼装成完整的 url
                String url = FILE_SERVER_URL + path;
//            log.info("服务器上传成功的完整地址:"+url);
                return ResultCommon.success(ResultCode.SUCCESS,url);
            } catch (Exception e) {
                return ResultCommon.fail(ResultCode.FAIL);
            }
        }
    }

    @RequestMapping("/kindEditorUpload")
    public MessageKindEditor kindEditorUpload(@RequestParam("file") MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        if(StringUtils.isEmpty(originalFilename)){
            return  new MessageKindEditor(1,null,"文件上传异常，文件名为空");
        }else{
            //扩展名
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            try {
                // 2、创建一个 FastDFS 的客户端
                FastDFSClientUtils fastDFSClient = new FastDFSClientUtils("classpath:fdfs_client.conf");
                // 3、执行上传处理
                String path = fastDFSClient.uploadFile(file.getBytes(), extName);
                // 4、拼接返回的 url 和 ip 地址，拼装成完整的 url
                String url = FILE_SERVER_URL + path;
//            log.info("服务器上传成功的完整地址:"+url);
                return new MessageKindEditor(0,url,"");
            } catch (Exception e) {
                return new MessageKindEditor(1,null,"文件上传异常");
            }
        }
    }
}
