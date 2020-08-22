package com.chen.service;

import com.chen.config.FileProperties;
import com.chen.utils.ImageUtil;
import com.chen.utils.VideoUtil;
import com.chen.web.rest.errors.FileException;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Objects;

/**
 * @author chen
 */
@Service
public class FileService {
    private static final Logger log = LoggerFactory.getLogger(FileService.class);
    private static final int COVER_IMG_WIDTH = 750;

    private final Path imageStorageLocation;
    private final Path mediaStorageLocation;
    private final Path fileStorageLocation;

    @Autowired
    public FileService(FileProperties fileProperties) {
        this.imageStorageLocation = Paths.get(fileProperties.getUploadDir() + "/image").toAbsolutePath().normalize();
        this.mediaStorageLocation = Paths.get(fileProperties.getUploadDir() + "/media").toAbsolutePath().normalize();
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir() + "/file").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.imageStorageLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Map<String, Object> storeFile(MultipartFile file) {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        int idx = fileName.lastIndexOf(".");
        String preName = fileName.substring(0, idx);
        String hz = fileName.substring(idx);
        fileName = preName + System.currentTimeMillis() + hz;
        try {
            if (fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            log.info("文件上传路径: {}", targetLocation.toString());
            map.put("fileName", fileName);
            return map;
        } catch (FileException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> storeMediaFile(MultipartFile file) {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        int idx = fileName.lastIndexOf(".");
        String preName = fileName.substring(0, idx);
        String hz = fileName.substring(idx);
        fileName = preName + System.currentTimeMillis() + hz;
        try {
            if (fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.mediaStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            log.info("media文件上传路径: {}", targetLocation.toString());
            map.put("fileName", fileName);
            long duration = VideoUtil.getDuration(targetLocation.toString());
            map.put("duration", duration);
            return map;
        } catch (FileException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String storeImage(MultipartFile file, Boolean isEditor) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        int idx = fileName.lastIndexOf(".");
        String preName = fileName.substring(0, idx);
        String hz = fileName.substring(idx);
        fileName = preName + System.currentTimeMillis() + hz;
        try {
            if (fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.imageStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            log.info("文件上传路径: {}", targetLocation.toString());
            if (isEditor) {
                try {
                    ImageUtil.cutImage(350, targetLocation.toString(), targetLocation.toString());
                } catch (Exception e) {
                    log.error("图片裁剪失败: {}", e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Map<String, Object> map = ImageUtil.getImageSize(targetLocation.toString());
                if (null != map) {
                    // 封面图按 5:4 缩放
                    int imgWidth = Integer.parseInt(map.get("width").toString());
                    int imgHeight = Integer.parseInt(map.get("height").toString());
                    if (imgWidth > COVER_IMG_WIDTH) {
                        imgWidth = 750;
                        imgHeight = (750 * 4) / 5;
                    }
                    ImageUtil.cutImage(imgWidth, imgHeight, targetLocation.toString(), targetLocation.toString());
                }
            }
            return fileName;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 加载文件
     *
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadImageFileAsResource(String fileName) {
        try {
            Path filePath = this.imageStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }
    /**
     * 加载文件
     *
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadVideoFileAsResource(String fileName) {
        try {
            Path filePath = this.mediaStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }
}
