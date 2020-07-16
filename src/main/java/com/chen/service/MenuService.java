package com.chen.service;

import com.chen.domain.Menu;
import com.chen.repository.AllMenusSpecification;
import com.chen.repository.MenuRepository;
import com.chen.service.dto.MenuDTO;
import com.chen.service.mapper.MenuMapper;
import com.github.wujun234.uid.UidGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen
 */
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final UidGenerator defaultUidGenerator;

    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper, UidGenerator defaultUidGenerator) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.defaultUidGenerator = defaultUidGenerator;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long createMenu(MenuDTO dto) {
        Menu menu = Menu.createMenu(defaultUidGenerator.getUID(), dto.getText(), dto.getParentId(), dto.getIcon(), dto.getLink(), dto.getSort());
        return menuRepository.save(menu).getId();
    }

    @Transactional(readOnly = true)
    public List<MenuDTO> allMenus() {
        return menuRepository.findAll(new AllMenusSpecification()).parallelStream()
            .map(menuMapper::toDto).collect(Collectors.toList());
    }
}
