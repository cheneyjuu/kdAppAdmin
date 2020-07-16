package com.chen.web.rest;

import com.chen.service.AccountService;
import com.chen.service.dto.AccountDTO;
import com.chen.utils.CommonResult;
import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/account")
public class AccountResource {
    private static final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> searchPageAccount(@RequestParam(value = "startDate", required = false) String startDate,
                                                              @RequestParam(value = "endDate", required = false) String endDate,
                                                              Pageable pageable) {
        Page<AccountDTO> page = accountService.pageSearchAccount(startDate, endDate, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/current")
    public CommonResult<AccountDTO> findCurrentAccountInfo() {
        AccountDTO dto = accountService.getCurrentAccountInfo();
        return CommonResult.success(dto);
    }
}
