package com.chen.service.dto;

import com.chen.domain.Works;
import com.chen.domain.WorksId;
import com.chen.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorksDTO implements Serializable {
    private static final long serialVersionUID = -8752406045886674994L;
    private WorksId worksId;
    private String login;
    private String author;
    private String college;
    private String grade;
    private String phoneNumber;
    private String worksName;
    private String workDesc;
    private String teacher;
    private String teacherCollege;
    private String workCategory;
    private String worksType;
    private Integer submitType;
    private String fileName;
    private String filePath;
    private String fileSuffix;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submitTime;

    private Integer duration;
    private String durationDesc;

    private Integer status;
    private Integer workRank;
    /**
     * 是否提交作品标识
     * 如果提交方式是邮件提交，并且附件为空，则判定作品未提交
     */
    private Boolean submitFlag;

    public WorksDTO(Works works) {
        this.worksId = works.getWorksId();
        this.login = works.getLogin();
        this.author = works.getAuthor();
        this.college = works.getCollege();
        this.grade = works.getGrade();
        this.phoneNumber = works.getPhoneNumber();
        this.worksName = works.getWorksName();
        this.workDesc = works.getWorkDesc();
        this.teacher = works.getTeacher();
        this.teacherCollege = works.getTeacherCollege();
        this.workCategory = works.getWorkCategory();
        this.worksType = works.getWorksType();
        this.submitType = works.getSubmitType();
        if (null != works.getWorksFile()) {
            this.fileName = works.getWorksFile().getFileName();
            this.filePath = works.getWorksFile().getFilePath();
            this.fileSuffix = works.getWorksFile().getFileSuffix();
            this.duration = works.getWorksFile().getDuration();
            this.durationDesc = works.getWorksFile().getDurationDesc();
        }
        this.submitTime = works.getSubmitTime();
        this.status = works.getStatus();
        this.workRank = works.getWorkRank();
        if (this.submitType == 1) {
            this.submitFlag = !StringUtils.isEmpty(this.filePath);
        } else {
            this.submitFlag = true;
        }
    }

    public WorksDTO() {
    }

    public WorksId getWorksId() {
        return worksId;
    }

    public void setWorksId(WorksId worksId) {
        this.worksId = worksId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherCollege() {
        return teacherCollege;
    }

    public void setTeacherCollege(String teacherCollege) {
        this.teacherCollege = teacherCollege;
    }

    public String getWorkCategory() {
        return workCategory;
    }

    public void setWorkCategory(String workCategory) {
        this.workCategory = workCategory;
    }

    public String getWorksType() {
        return worksType;
    }

    public void setWorksType(String worksType) {
        this.worksType = worksType;
    }

    public Integer getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Integer submitType) {
        this.submitType = submitType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDurationDesc() {
        return durationDesc;
    }

    public void setDurationDesc(String durationDesc) {
        this.durationDesc = durationDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWorkRank() {
        return workRank;
    }

    public void setWorkRank(Integer workRank) {
        this.workRank = workRank;
    }

    public Boolean getSubmitFlag() {
        return submitFlag;
    }

    public void setSubmitFlag(Boolean submitFlag) {
        this.submitFlag = submitFlag;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    @Override
    public String toString() {
        return "WorksDTO{" +
            "worksId=" + worksId +
            ", login='" + login + '\'' +
            ", author='" + author + '\'' +
            ", college='" + college + '\'' +
            ", grade='" + grade + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", worksName='" + worksName + '\'' +
            ", workDesc='" + workDesc + '\'' +
            ", teacher='" + teacher + '\'' +
            ", teacherCollege='" + teacherCollege + '\'' +
            ", workCategory='" + workCategory + '\'' +
            ", worksType='" + worksType + '\'' +
            ", submitType=" + submitType +
            ", fileName='" + fileName + '\'' +
            ", filePath='" + filePath + '\'' +
            ", submitTime=" + submitTime +
            ", duration=" + duration +
            ", durationDesc='" + durationDesc + '\'' +
            '}';
    }
}
