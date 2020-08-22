package com.chen.web.rest;

import com.chen.domain.WorksId;
import com.chen.service.WorkService;
import com.chen.service.dto.WorksDTO;
import com.chen.utils.CommonResult;
import io.github.jhipster.web.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * @author chen
 */
@RestController
@RequestMapping("/api/works")
public class WorksResource {
    private final WorkService workService;

    public WorksResource(WorkService workService) {
        this.workService = workService;
    }

    @PostMapping
    public CommonResult<WorksId> addWork(@RequestBody WorksDTO dto) {
        WorksId worksId = workService.addWorks(dto);
        return CommonResult.success(worksId);
    }

    @PostMapping("/submit")
    public CommonResult<WorksId> submitWork(@RequestBody WorksDTO dto) {
        WorksId worksId = workService.submitWork(dto.getWorksId(), dto.getFileName(), dto.getFilePath(), dto.getDuration(), dto.getDurationDesc());
        return CommonResult.success(worksId);
    }

    @PutMapping("/{workId}/verify/{status}")
    public CommonResult<WorksId> verifyWork(@PathVariable("workId") String workId, @PathVariable("status") Integer status) {
        WorksId worksId = workService.verify(workId, status);
        return CommonResult.success(worksId);
    }

    @PutMapping("/{workId}/rank/{rank}")
    public CommonResult<WorksId> rankWork(@PathVariable("workId") String workId, @PathVariable("rank") Integer rank) {
        WorksId worksId = workService.rank(workId, rank);
        return CommonResult.success(worksId);
    }

    @GetMapping
    public ResponseEntity<List<WorksDTO>> search(@RequestParam(value = "category") String category, Pageable pageable) {
        Page<WorksDTO> page = workService.searchWorks(category, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorksDTO>> findAllByCategory(@RequestParam(value = "category") String category, Pageable pageable) {
        Page<WorksDTO> page = workService.findAllByCategory(category, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/user")
    public CommonResult<List<WorksDTO>> findUserWorks(@RequestParam("category") String category) {
        List<WorksDTO> list = workService.findUserWorks(category);
        return CommonResult.success(list);
    }
}
