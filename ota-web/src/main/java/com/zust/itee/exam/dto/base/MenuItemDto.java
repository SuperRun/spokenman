package com.zust.itee.exam.dto.base;

import java.util.ArrayList;
import java.util.List;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.enums.LoginTypeEnum;

/**
 * 菜单项的一个模型 Created by dxb on 2016/8/29.
 */
public class MenuItemDto {

    private String name;

    private String url;

    private Integer num;

    private List<MenuItemDto> items;

    public MenuItemDto() {
    }

    public MenuItemDto(SessionInfo sessionInfo, Integer unverifyCount) {
        List<MenuItemDto> menuList = new ArrayList<>();

        if (sessionInfo != null) {

            if (sessionInfo.getLoginType() == LoginTypeEnum.DRIVER) {

                // 驾驶员 ly
                menuList.add(new MenuItemDto("首页", "/driver/index"));

			/*
             * 考试管理 sdy
			 */
                List<MenuItemDto> exam = new ArrayList<MenuItemDto>();
                exam.add(new MenuItemDto("考试报名", "/exam/driver/signup"));
                exam.add(new MenuItemDto("我的考试", "/exam/driver/list"));
                exam.add(new MenuItemDto("模拟考试", "/exam/driver/practice"));
                menuList.add(new MenuItemDto("考试管理", exam));
            } else if (sessionInfo.getLoginType() == LoginTypeEnum.MEMBER) {
                // org
                // 首页 dxb
                menuList.add(new MenuItemDto("首页", "/member"));

                // 机构操作 dxb
                List<MenuItemDto> menuOrgList = new ArrayList<>();
                menuOrgList.add(new MenuItemDto("创建子机构", "/organization/newchild"));
                menuOrgList
                        .add(new MenuItemDto("待审核", "/organization/unverify", unverifyCount));
                menuOrgList.add(new MenuItemDto("我的机构", "/organization/"
                        + sessionInfo.getOrganizationId()));
                menuOrgList.add(new MenuItemDto("机构列表", "/organization/list"));
                menuList.add(new MenuItemDto("机构管理", menuOrgList));

                // 部门操作 dxb
                List<MenuItemDto> menuDepList = new ArrayList<>();
                menuDepList.add(new MenuItemDto("创建部门", "/department/new"));
                menuDepList.add(new MenuItemDto("部门列表", "/department"));
                menuList.add(new MenuItemDto("部门管理", menuDepList));

                // 员工操作 dxb
                List<MenuItemDto> menuMemberList = new ArrayList<>();
                menuMemberList.add(new MenuItemDto("添加员工", "/member/new"));
                menuMemberList.add(new MenuItemDto("员工列表",
                        "/organization/" + sessionInfo.getOrganizationId() + "/members"));
                menuList.add(new MenuItemDto("员工管理", menuMemberList));

                // 驾驶员档案 ly
                List<MenuItemDto> menuDriverList = new ArrayList<>();
                menuDriverList.add(new MenuItemDto("新增驾驶员", "/driver/create"));
                menuDriverList.add(new MenuItemDto("Excel导入驾驶员", "/driver/loadExcel"));
                menuDriverList.add(new MenuItemDto("新驾驶员审核", "/driver/checks"));
                menuDriverList.add(new MenuItemDto("驾驶员基本信息", "/driver"));
                menuDriverList.add(new MenuItemDto("驾驶员档案审核", "/driver/record/check"));
                menuDriverList.add(new MenuItemDto("驾驶员档案信息", "/driver/record"));
                menuDriverList.add(new MenuItemDto("搜索驾驶员", "/driver/sfz"));
                menuList.add(new MenuItemDto("驾驶员管理", menuDriverList));

                //汽车管理
                List<MenuItemDto> menuTruckDtoList = new ArrayList<>();
                menuTruckDtoList.add(new MenuItemDto("新增卡车", "/hr/vehicle/create"));
                menuTruckDtoList.add(new MenuItemDto("Excel导入卡车", "/hr/vehicle/loadExcel"));
                menuTruckDtoList.add(new MenuItemDto("卡车列表", "/hr/vehicle"));
                menuList.add(new MenuItemDto("汽车管理", menuTruckDtoList));

                // 通知管理
                List<MenuItemDto> menuAnnouncementList = new ArrayList<>();
                menuAnnouncementList.add(new MenuItemDto("新建通知",
                        "/announcement/new"));
                menuList.add(new MenuItemDto("通知", menuAnnouncementList));


			/*
             * 考试管理 sdy
			 */
                List<MenuItemDto> examList = new ArrayList<MenuItemDto>();
                examList.add(new MenuItemDto("所有考试", "/exam/member/home"));
                examList.add(new MenuItemDto("发布考试", "/exam/member/create"));
                examList.add(new MenuItemDto("报名管理",
                        "/exam/member/home?page=1&rows=5&searchKey=&status=1"));
                examList.add(new MenuItemDto("成绩录入",
                        "/exam/member/home?page=1&rows=5&searchKey=&status=4"));
                examList.add(new MenuItemDto("证书录入",
                        "/exam/member/home?page=1&rows=5&searchKey=&status=5"));
                examList.add(new MenuItemDto("已完成考试",
                        "/exam/member/home?page=1&rows=5&searchKey=&status=6"));
                examList.add(new MenuItemDto("模拟考试管理", "/exam/member/practice"));
                examList.add(new MenuItemDto("试卷管理", "/paper/member/list"));
                examList.add(new MenuItemDto("试题管理", "/question/list"));
                menuList.add(new MenuItemDto("考试管理", examList));
            }
        } else {
            //未登录
            menuList.add(new MenuItemDto("没登录不显示", ""));
        }
        items = menuList;
    }

    public MenuItemDto(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public MenuItemDto(String name, String url, int num) {
        this.name = name;
        this.num = num;
        this.url = url;
    }

    public MenuItemDto(String name, List<MenuItemDto> items) {
        this.items = items;
        this.name = name;
    }

    public List<MenuItemDto> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDto> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
