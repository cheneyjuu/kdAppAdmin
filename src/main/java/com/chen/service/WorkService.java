package com.chen.service;

import com.chen.domain.*;
import com.chen.repository.WorksRepository;
import com.chen.security.SecurityUtils;
import com.chen.security.UserNotActivatedException;
import com.chen.service.dto.WorksDTO;
import com.chen.service.exceptions.CommonException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chen
 */
@Service
public class WorkService {
    private final WorksRepository worksRepository;
    private final WorksFactory worksFactory;

    public WorkService(WorksRepository worksRepository, WorksFactory worksFactory) {
        this.worksRepository = worksRepository;
        this.worksFactory = worksFactory;
    }

    @Transactional(rollbackFor = Exception.class)
    public WorksId addWorks(WorksDTO dto) {
        Works works = worksFactory.submitWork(dto.getAuthor(), dto.getCollege(), dto.getGrade(), dto.getPhoneNumber(), dto.getWorksName(), dto.getWorkDesc(),
            dto.getTeacher(), dto.getTeacherCollege(), dto.getWorkCategory(), dto.getWorksType(), dto.getSubmitType(), dto.getFileName(), dto.getFilePath(), dto.getDuration(), dto.getDurationDesc());
        Works savedWorks = worksRepository.save(works);
        return savedWorks.getWorksId();
    }

    @Transactional(rollbackFor = Exception.class)
    public WorksId verify(String stringWorksId, Integer verifyStatus) {
        WorksId worksId = new WorksId(stringWorksId);
        worksRepository.findById(worksId).ifPresent(works -> {
            works.verify(verifyStatus);
            worksRepository.save(works);
        });
        return worksId;
    }

    @Transactional(rollbackFor = Exception.class)
    public WorksId rank(String stringWorksId, Integer rank) {
        WorksId worksId = new WorksId(stringWorksId);
        /*Optional<Works> optional = worksRepository.findByWorksIdAndWorkRank(new WorksId(stringWorksId), rank);
        if (optional.isPresent()) {
            throw new CommonException("排名重复");
        }*/
        worksRepository.findById(worksId).ifPresent(works -> {
            works.setWorkRank(rank);
            worksRepository.save(works);
        });
        return worksId;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Page<WorksDTO> searchWorks(String worksCategory, Pageable pageable) {
        Page<Works> page = worksRepository.findAll(new SearchWorksSpecification(worksCategory, 1), pageable);
        List<WorksDTO> list = page.getContent().parallelStream().map(WorksDTO::new).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<WorksDTO> findUserWorks(String category) {
        String login = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UserNotActivatedException(""));
        return worksRepository.findAllByLoginAndWorkCategory(login, category).parallelStream().map(WorksDTO::new).collect(Collectors.toList());
    }

    public Page<WorksDTO> findAllByCategory(String category, Pageable pageable) {
        Page<Works> page = worksRepository.findAllByWorkCategory(category, pageable);
        List<WorksDTO> list = page.getContent().parallelStream().map(WorksDTO::new).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Transactional(rollbackFor = Exception.class)
    public WorksId submitWork(WorksId worksId, String fileName, String filePath, Integer duration, String durationDesc) {
        Works works = worksRepository.findById(worksId).orElseThrow(() -> new CommonException("未找到作品"));
        WorksFile worksFile = new WorksFile(fileName, filePath, duration, durationDesc);
        works.addFile(worksFile);
        worksRepository.save(works);
        return worksId;
    }
}
