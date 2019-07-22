package com.zyg.controller;

import com.zyg.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: zhaoyuanguang1573@163.com
 * @Data: 2019/7/22 19:40
 * @Content:
 */
@RestController
@RequestMapping("/file")
public class UploadController {
    @Value("${FILE_SERVER_URL}")
    private String file_server_url;

    @RequestMapping("/upload")
    public String upload(@RequestParam MultipartFile file) {
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //得到扩展名
        String extName=originalFilename.substring( originalFilename.lastIndexOf(".")+1);

        try {
            FastDFSClient client=new FastDFSClient("classpath:fdfs_client.conf");
            String fileId = client.uploadFile(file.getBytes(), extName);
            String url=file_server_url+fileId;//图片完整地址
            return url;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
