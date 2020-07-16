package com.chen.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 前台菜单
 *
 * @author chen
 */
@Entity
@Table(name = "idms_menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = -2551570144482663546L;
    @Id
    private Long id;

    @NotNull
    @Column(name = "text", nullable = false)
    private String text;

    @NotNull
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    private String icon;

    private String link;

    @Column(name = "menu_sort")
    private Integer sort;

    @Column(name = "delete_flag", columnDefinition = "bit COMMENT '是否删除'")
    private Integer deleteFlag;

    private LocalDateTime createdDate;

    protected Menu(Long id, String text, Long parentId, String icon, String link, Integer sort) {
        this.id = id;
        this.text = text;
        this.parentId = parentId;
        this.icon = "iconfont " + icon;
        this.link = link;
        this.sort = sort;
        this.deleteFlag = DeleteFlagEnum.OK.ordinal();
        this.createdDate = LocalDateTime.now();
    }

    public static Menu createMenu(Long id, String text, Long parentId, String icon, String link, Integer sort) {
        return new Menu(id, text, parentId, icon, link, sort);
    }

    protected Menu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id.equals(menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
