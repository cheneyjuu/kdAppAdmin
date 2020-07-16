package com.chen.web.rest;

import com.chen.domain.UeditorImage;
import com.chen.service.FileService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chen
 */
@RequestMapping("/api/ueditor")
@RestController
public class UEditorFileResource {
    private static final Logger log = LoggerFactory.getLogger(UEditorFileResource.class);

    private final FileService fileService;

    public UEditorFileResource(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "上传图片类型的附件", notes = "上传图片类型的附件", nickname = "Attachment_uploadImage")
    @PostMapping("/uploadFile")
    public void uploadImage(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        String fileName = fileService.storeImage(file, true);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/downloadFile/")
            .path(fileName)
            .toUriString();

        log.info("download url: {}", fileDownloadUri);

        UeditorImage ueditorImage = new UeditorImage();
        ueditorImage.setState("SUCCESS");
        ueditorImage.setTitle(fileName);
        ueditorImage.setOriginal(file.getOriginalFilename());
        ueditorImage.setUrl(fileDownloadUri);
        Gson gson = new Gson();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(gson.toJson(ueditorImage));
        writer.flush();
    }
}
