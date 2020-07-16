package com.chen.service.mapper;

import com.chen.domain.Menu;
import com.chen.service.dto.MenuDTO;
import org.mapstruct.Mapper;

/**
 * @author chen
 */
@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuDTO toDto(Menu menu);
}
