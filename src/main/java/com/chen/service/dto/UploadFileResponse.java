package com.chen.service.dto;

import java.io.Serializable;

/**
 * @author chen
 */
public class UploadFileResponse implements Serializable {
    private static final long serialVersionUID = 2607358646209994670L;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private long duration;
    private String durationDesc;

    protected UploadFileResponse() {
    }

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public void setVideoDuration(long duration) {
        this.duration = duration;
        long minutes = duration / 60;
        if (minutes > 0) {
            duration = duration - minutes * 60;
        }
        this.durationDesc = minutes + "分" + duration + "秒";
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getDurationDesc() {
        return durationDesc;
    }

    public void setDurationDesc(String durationDesc) {
        this.durationDesc = durationDesc;
    }
}
