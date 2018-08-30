package com.zust.itee.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.OrganizationDto;
import com.zust.itee.exam.dto.base.MenuItemDto;
import com.zust.itee.exam.enums.LoginTypeEnum;
import com.zust.itee.exam.service.OrganizationService;

/**
 * 获取菜单用的控制器
 * Created by dxb on 2016/8/29.
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private OrganizationService organizationService;

    /**
     * @return 获取菜单
     */
    @RequestMapping("")
    @ResponseBody
    public JsonResult<MenuItemDto> getMenu(SessionInfo sessionInfo) {
        MenuItemDto menuItemDto;
        if (sessionInfo != null && sessionInfo.getLoginType() == LoginTypeEnum.MEMBER) {
            List<OrganizationDto> l = organizationService.getUnverifyDtoList(
                    sessionInfo.getOrganizationId(), 0, 1, 0, 0);
            menuItemDto = new MenuItemDto(sessionInfo, l.size());
        } else {
            menuItemDto = new MenuItemDto(sessionInfo, null);
        }
        return new JsonResult<>(true, menuItemDto);
    }
}
