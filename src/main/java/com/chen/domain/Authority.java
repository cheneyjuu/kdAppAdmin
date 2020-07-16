package com.chen.domain;

import com.google.common.base.Preconditions;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 后台帐号所拥有的角色
 *
 * @author chen
 */
@Entity
@Table(name = "idms_authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority implements Serializable {
    private static final long serialVersionUID = 2011952570664538864L;

    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;

    /**
     * 角色拥有的菜单
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "idms_authority_menu", joinColumns = @JoinColumn(name = "name"))
    @Column(name = "menu_id")
    private List<Long> menuIds;

    public void addMenus(List<Long> menuIds) {
        Preconditions.checkNotNull(menuIds, "菜单不能为空");
        this.menuIds = menuIds;
    }

    public static Authority createAuthority(String authorityName) {
        return new Authority(authorityName);
    }

    protected Authority(String name) {
        this.name = name;
    }

    public Authority() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        return Objects.equals(name, ((Authority) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Authority{" +
            "name='" + name + '\'' +
            "}";
    }
}
