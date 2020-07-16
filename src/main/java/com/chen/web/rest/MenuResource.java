package com.chen.web.rest;

import com.chen.service.MenuService;
import com.chen.service.dto.MenuDTO;
import com.chen.utils.CommonResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chen
 */
@RestController
@RequestMapping("/api/menus")
public class MenuResource {
    private final MenuService menuService;

    public MenuResource(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public CommonResult<Long> create(@RequestBody MenuDTO dto) {
        Long id = menuService.createMenu(dto);
        return CommonResult.success(id);
    }

    @GetMapping
    public CommonResult<List<MenuDTO>> allMenus() {
        List<MenuDTO> menus = menuService.allMenus();
        return CommonResult.success(menus);
    }
}
