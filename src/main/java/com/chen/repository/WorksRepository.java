package com.chen.repository;

import com.chen.domain.Works;
import com.chen.domain.WorksId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author chen
 */
public interface WorksRepository extends JpaRepository<Works, WorksId>, JpaSpecificationExecutor<Works> {
    List<Works> findAllByLoginAndWorkCategory(String login, String category);

    Page<Works> findAllByWorkCategory(String category, Pageable pageable);

    Optional<Works> findByWorksIdAndWorkRank(WorksId worksId, Integer rank);
}
