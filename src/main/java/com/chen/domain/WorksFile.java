package com.chen.domain;

import com.chen.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * 作品文件
 *
 * @author chen
 */
@Embeddable
public final class WorksFile implements ValueObject<WorksFile> {
    private static final long serialVersionUID = -351642895145769655L;

    private final String fileName;
    private final String filePath;
    @Column(columnDefinition = "varchar(4) COMMENT '文件类型, MP3,MP4,JPG,JPEG,PNG,DOC,DOCX'")
    private final String fileType;
    private final String fileSuffix;

    private final Integer duration;
    private final String durationDesc;

    public WorksFile(String fileName, String filePath, Integer duration, String durationDesc) {
        this.fileName = fileName;
        this.filePath = filePath;
        int index = filePath.lastIndexOf(".");
        this.fileSuffix = filePath.substring(index + 1);
        if (this.fileSuffix.equalsIgnoreCase("MP3")) {
            this.fileType = "音频类";
            this.duration = duration;
            this.durationDesc = durationDesc;
        } else if (this.fileSuffix.equalsIgnoreCase("MP4")) {
            this.fileType = "视频类";
            this.duration = duration;
            this.durationDesc = durationDesc;
        } else {
            this.fileType = "文档类";
            this.duration = 0;
            this.durationDesc = "";
        }
    }

    protected WorksFile() {
        this.fileName = null;
        this.filePath = null;
        this.fileType = null;
        this.fileSuffix = null;
        this.duration = null;
        this.durationDesc = null;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getDurationDesc() {
        return durationDesc;
    }

    @Override
    public boolean sameValueAs(WorksFile other) {
        assert filePath != null;
        return filePath.equals(other.filePath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorksFile worksFile = (WorksFile) o;
        return Objects.equals(fileName, worksFile.fileName) &&
            Objects.equals(filePath, worksFile.filePath) &&
            Objects.equals(fileType, worksFile.fileType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, filePath, fileType);
    }

    @Override
    public String toString() {
        return "WorksFile{" +
            "fileName='" + fileName + '\'' +
            ", filePath='" + filePath + '\'' +
            ", fileType='" + fileType + '\'' +
            ", fileSuffix='" + fileSuffix + '\'' +
            '}';
    }
}
