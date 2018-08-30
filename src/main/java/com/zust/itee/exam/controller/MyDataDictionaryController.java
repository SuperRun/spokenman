package com.zust.itee.exam.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zust.itee.exam.dto.DataDictionaryModel;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.service.DataDictionaryService;

/**
 * 数据字典维护模块
 * Created by dxb on 2016/12/9.
 */
@Controller
@RequestMapping("mdd")
public class MyDataDictionaryController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataDictionaryService dataDictionaryService;

    /**
     * 获取数据字典列表
     *
     * @return 数据字典列表页面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String pageList(Model model) {

        //获取顶级字典列表
        List<DataDictionaryModel> ctrlDataDictionaryModels = dataDictionaryService.getSubItems(null,
                false);
        model.addAttribute("list", ctrlDataDictionaryModels);

        return "2/dd/list";
    }

    /**
     * 获取一个根数据字典的字数据字典们 并拥有添加 删除 功能
     *
     * @param parentId 父项id
     * @return 父节点包含的子节点们的页面
     */
    @RequestMapping(value = "{parentId}", method = RequestMethod.GET)
    public String pageChilds(@PathVariable Long parentId, Model model) {

        //父项
        DataDictionaryModel parentItem = dataDictionaryService.getItemById(parentId);
        model.addAttribute("parent", parentItem);
        model.addAttribute("vparent", JSON.toJSONString(parentItem));

        //子项列表
        List<DataDictionaryModel> subItems = dataDictionaryService.getSubItems(parentId, false);
        model.addAttribute("list", subItems);
        model.addAttribute("vlist", JSON.toJSONString(subItems));

        return "2/dd/detail";
        //        return "datadictionary/children";
    }

    @RequestMapping(value = "cd/{codeType}", method = RequestMethod.GET)
    public String pageChilds(@PathVariable String codeType) {
        DataDictionaryModel parentItem = dataDictionaryService.getMainItemByCodeType(codeType);
        if (parentItem != null) {
            return "forward:/mdd/" + parentItem.getId();
        } else {
            return "forward:/404";
        }
    }

    /**
     * 向一个数据字典项创建一个子项
     *
     * @param parentId 父项
     */
    @RequestMapping(value = "{parentId}/new", method = RequestMethod.GET)
    public String pageNewChild(@PathVariable Long parentId, Model model) {

        //父项
        DataDictionaryModel parentItem = dataDictionaryService.getItemById(parentId);
        model.addAttribute("parent", parentItem);

        //        return "datadictionary/new";
        return "datadictionary/new2";
    }

    /**
     * @param parentId 上级
     * @param dataDictionaryModel 资料
     * @return 创建成功后重定向到子项页面
     */
    @RequestMapping(value = "{parentId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Long> postNewChild(@PathVariable Long parentId,
            DataDictionaryModel dataDictionaryModel) {
        //设置父项
        dataDictionaryModel.setParentId(parentId);
        // 保存
        Long id = dataDictionaryService.saveItem(dataDictionaryModel);

        //        return "redirect:/dd/" + parentId;
        return new JsonResult<>(true, id);
    }

    /**
     * 修改数据字典的页面
     *
     * @param itemId 需要修改的项
     * @return 修改数据字典的页面
     */
    @RequestMapping(value = "{itemId}/modify", method = RequestMethod.GET)
    public String pageModify(@PathVariable Long itemId, Model model) {

        DataDictionaryModel item = dataDictionaryService.getItemById(itemId);
        model.addAttribute("dd", item);

        return "datadictionary/modify2";
        //        return "datadictionary/modify";
    }

    /**
     * 修改数据字典
     *
     * @param itemId 被修改的项
     * @return 修改后重定向到父级的子项页面
     */
    @RequestMapping(value = "{itemId}/modify", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Long> postModify(@PathVariable Long itemId,
            DataDictionaryModel dataDictionaryModel) {

        DataDictionaryModel itemDb = dataDictionaryService.getItemById(itemId);

        itemDb.setValueStr(dataDictionaryModel.getValueStr());
        itemDb.setRemark(dataDictionaryModel.getRemark());

        dataDictionaryService.updateItem(itemDb);
        DataDictionaryModel item = dataDictionaryService.getItemById(itemId);
        //        return "redirect:/dd/" + item.getParentId();
        return new JsonResult<>(true, itemId);
    }

    /**
     * 删除一个对象
     *
     * @param itemId 被删除对象主键
     * @return 删除后重定向到父级的子项页面
     */
    @RequestMapping(value = "{itemId}/delete")
    @ResponseBody
    public JsonResult<Long> delete(@PathVariable Long itemId) {
        DataDictionaryModel itemDb = dataDictionaryService.getItemById(itemId);
        Long parentId = itemDb.getParentId();
        dataDictionaryService.deleteItem(itemId);
        //        return "redirect:/dd/" + parentId;
        return new JsonResult<>(true, itemId);
    }
}
