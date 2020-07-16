package com.chen.repository;

import com.chen.domain.DeleteFlagEnum;
import com.chen.domain.Menu;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 查找所有未删除的菜单
 *
 * @author chen
 */
public class AllMenusSpecification implements Specification<Menu> {
    private static final long serialVersionUID = 3219240561097913088L;

    @Override
    public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        predicate.getExpressions().add(cb.equal(root.get("deleteFlag"), DeleteFlagEnum.OK.ordinal()));
        query.where(predicate).orderBy(new OrderImpl(root.get("sort")));
        return query.getRestriction();
    }
}
