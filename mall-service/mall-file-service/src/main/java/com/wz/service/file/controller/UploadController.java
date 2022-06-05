package com.wz.service.file.controller;

import com.wz.mall.util.RespResult;
import com.wz.service.file.ceph.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class UploadController {

    @Autowired
    private FileHandler fileHandler;

     @PostMapping("/upload")
     public RespResult upload(MultipartFile file) throws IOException {
        fileHandler.upload(file.getOriginalFilename(),file.getBytes());
        return RespResult.ok();
     }


    @GetMapping("/download/{filename}")
    public RespResult download(@PathVariable String filename, HttpServletResponse response) throws IOException {
        byte[] buffer = fileHandler.download(filename);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(buffer);
        outputStream.flush();
        outputStream.close();
        return RespResult.ok();
    }


}
