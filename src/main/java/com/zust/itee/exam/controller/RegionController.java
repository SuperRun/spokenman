package com.zust.itee.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zust.itee.exam.dto.DataDictionaryModel;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.RegionDto;
import com.zust.itee.exam.dto.RegionTreeDto;
import com.zust.itee.exam.service.DataDictionaryService;

/**
 * 区域管理
 * Created by dxb on 12/15/2016.
 */
@Controller
@RequestMapping("organization/region")
public class RegionController {

    public static String ddRegion = "region";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataDictionaryService dataDictionaryService;

    /**
     * @return 管理区域的界面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String pageRegion() {
        return "2/hr/organization/region2";
    }

    /**
     * @return 整个地区树
     */
    @RequestMapping(value = "tree", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<RegionTreeDto> ajaxGetTree() {
        RegionTreeDto regionTreeDto = null;
        String error = "";
        try {
            regionTreeDto = getRegionTreeDto();
        } catch (Exception e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        boolean x = regionTreeDto != null;
        return new JsonResult<>(x, x ? regionTreeDto : null, x ? null : "没有获取到数据 " + error);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Long> ajaxAdd(DataDictionaryModel dataDictionaryModel) {

        Long parentId = dataDictionaryModel.getParentId();

        if (parentId == null) {
            parentId = getDd().getId();
        }
        //设置父项
        dataDictionaryModel.setParentId(parentId);
        // 保存
        Long id = dataDictionaryService.saveItem(dataDictionaryModel);

        //        return "redirect:/dd/" + parentId;
        return new JsonResult<>(true, id);
    }

    @RequestMapping(value = "dd", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<List<RegionDto>> getChildren(
            @RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            id = getDd().getId();
        }

        List<DataDictionaryModel> subItems = dataDictionaryService.getSubItems(id, false);
        List<RegionDto> regionDtos = trans2RegionDto(subItems);
        return new JsonResult<>(true, regionDtos);
    }

    //    ---------------------------------- private
    private RegionTreeDto getRegionTreeDto() {
        return trans2RegionTreeDto(getDd());
    }

    /**
     * @return 树根
     */
    private DataDictionaryModel getDd() {
        DataDictionaryModel dd = dataDictionaryService.getMainItemByCodeType(ddRegion);
        if (dd == null) {
            dd = new DataDictionaryModel();
            dd.setValueStr("区域信息");
            dd.setCodeType(ddRegion);
            logger.error("区域树不存在 正在创建！ dd - region ");
            Long aLong = dataDictionaryService.saveItem(dd);
            dd.setId(aLong);
        }
        return dd;
    }

    /**
     * 数据字典转化到模型
     */
    private RegionTreeDto trans2RegionTreeDto(DataDictionaryModel dataDictionaryModel) {
        if (dataDictionaryModel == null) {
            return null;
        }

        RegionTreeDto regionTreeDto = new RegionTreeDto();
        BeanUtils.copyProperties(dataDictionaryModel, regionTreeDto);

        List<DataDictionaryModel> subItems = dataDictionaryService.getSubItems(
                dataDictionaryModel.getId(), false);
        if (subItems != null && subItems.size() > 0) {
            List<RegionTreeDto> children = new ArrayList<>();
            for (DataDictionaryModel ddm : subItems) {
                RegionTreeDto c1 = trans2RegionTreeDto(ddm);
                if (c1 != null) {
                    children.add(c1);
                }
            }
            regionTreeDto.setChildren(children);
        }

        return regionTreeDto;
    }

    private List<RegionDto> trans2RegionDto(List<DataDictionaryModel> DataDictionaryModels) {
        if (DataDictionaryModels == null) {
            return null;
        }
        List<RegionDto> list = new ArrayList<>();
        for (DataDictionaryModel d : DataDictionaryModels) {
            RegionDto regionDto = trans2RegionDto(d);
            if (regionDto != null) {
                list.add(regionDto);
            }
        }
        return list;
    }

    /**
     * 数据字典转化成 数据传输对象
     */
    private RegionDto trans2RegionDto(DataDictionaryModel dataDictionaryModel) {
        if (dataDictionaryModel == null) {
            return null;
        }
        RegionDto regionDto = new RegionDto();

        BeanUtils.copyProperties(dataDictionaryModel, regionDto);
        return regionDto;
    }
}
