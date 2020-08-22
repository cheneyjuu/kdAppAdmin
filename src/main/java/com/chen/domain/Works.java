package com.chen.domain;

import com.chen.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 作品
 *
 * @author chen
 */
@Entity
@Table(name = "works")
public class Works implements BaseEntity<Works> {
    @EmbeddedId
    private WorksId worksId;
    private String login;
    @Column(columnDefinition = "varchar(10) COMMENT '作者姓名'")
    private String author;
    @Column(columnDefinition = "varchar(10) COMMENT '所属院校'")
    private String college;
    @Column(columnDefinition = "varchar(10) COMMENT '专业/年级'")
    private String grade;
    @Column(columnDefinition = "varchar(10) COMMENT '联系方式'")
    private String phoneNumber;
    @Column(columnDefinition = "varchar(50) COMMENT '作品名称'")
    private String worksName;
    @Column(columnDefinition = "varchar(10) COMMENT '创作说明'")
    private String workDesc;
    @Column(columnDefinition = "varchar(10) COMMENT '指导老师'")
    private String teacher;
    @Column(columnDefinition = "varchar(10) COMMENT '指导老师所在院校'")
    private String teacherCollege;
    @Column(columnDefinition = "varchar(6) COMMENT '作品类别，外语类、艺术类、文学与教育类'")
    private String workCategory;
    @Column(columnDefinition = "varchar(4) COMMENT '作品类型，外语类、视觉艺术、音乐类、舞蹈类、文学类、教育类'")
    private String worksType;
    @Column(columnDefinition = "bit COMMENT '提交方式，0：小程序提交；1：邮件提交'")
    private Integer submitType;
    @Embedded
    private WorksFile worksFile;
    @Column(columnDefinition = "datetime COMMENT '提交时间'")
    private LocalDateTime submitTime;
    @Column(columnDefinition = "int COMMENT '审核状态,0：待审核；1：审核通过'")
    private Integer status;
    @Column(columnDefinition = "integer COMMENT '排名'")
    private Integer workRank;
    @Column(columnDefinition = "integer COMMENT '展现次数'")
    private Integer showCount;
    @Column(columnDefinition = "integer COMMENT '查看次数'")
    private Integer viewCount;

    public Works(WorksId worksId, String login, String author, String college, String grade, String phoneNumber, String worksName, String workDesc, String teacher, String teacherCollege, String workCategory, String worksType, Integer submitType) {
        this.worksId = worksId;
        this.login = login;
        this.author = author;
        this.college = college;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.worksName = worksName;
        this.workDesc = workDesc;
        this.teacher = teacher;
        this.teacherCollege = teacherCollege;
        this.workCategory = workCategory;
        this.worksType = worksType;
        this.submitType = submitType;
        this.submitTime = LocalDateTime.now();
        this.workRank = 100000;
        this.status = 0;
    }

    protected Works() {
    }

    public static Works submitWorks(WorksId worksId, String login, String author, String college, String grade, String phoneNumber, String worksName, String workDesc, String teacher, String teacherCollege, String workCategory, String worksType, Integer submitType) {
        return new Works(worksId, login, author, college, grade, phoneNumber, worksName, workDesc, teacher, teacherCollege, workCategory, worksType, submitType);
    }

    public void addFile(WorksFile worksFile) {
        this.worksFile = worksFile;
    }

    /**
     * 审核
     *
     * @param status 审核状态
     */
    public void verify(Integer status) {
        this.status = status;
    }

    /**
     * 设置作品排名
     *
     * @param rank 排名
     */
    public void setWorkRank(Integer rank) {
        this.workRank = rank;
    }

    public WorksId getWorksId() {
        return worksId;
    }

    public String getLogin() {
        return login;
    }

    public String getAuthor() {
        return author;
    }

    public String getCollege() {
        return college;
    }

    public String getGrade() {
        return grade;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWorksName() {
        return worksName;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTeacherCollege() {
        return teacherCollege;
    }

    public String getWorkCategory() {
        return workCategory;
    }

    public String getWorksType() {
        return worksType;
    }

    public Integer getSubmitType() {
        return submitType;
    }

    public WorksFile getWorksFile() {
        return worksFile;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getWorkRank() {
        return workRank;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public boolean sameIdentityAs(Works other) {
        return worksId.equals(other.worksId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Works works = (Works) o;
        return Objects.equals(worksId, works.worksId) &&
            Objects.equals(login, works.login) &&
            Objects.equals(author, works.author) &&
            Objects.equals(college, works.college) &&
            Objects.equals(grade, works.grade) &&
            Objects.equals(phoneNumber, works.phoneNumber) &&
            Objects.equals(worksName, works.worksName) &&
            Objects.equals(workDesc, works.workDesc) &&
            Objects.equals(teacher, works.teacher) &&
            Objects.equals(teacherCollege, works.teacherCollege) &&
            Objects.equals(workCategory, works.workCategory) &&
            Objects.equals(worksType, works.worksType) &&
            Objects.equals(submitType, works.submitType) &&
            Objects.equals(worksFile, works.worksFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worksId, login, author, college, grade, phoneNumber, worksName, workDesc, teacher, teacherCollege, workCategory, worksType, submitType, worksFile);
    }

    @Override
    public String toString() {
        return "Works{" +
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
            ", worksFile=" + worksFile +
            ", submitTime=" + submitTime +
            '}';
    }
}
