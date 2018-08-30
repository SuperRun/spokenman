package com.zust.itee.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zust.itee.exam.dto.DataDictionaryModel;
import com.zust.itee.exam.dto.base.Page;
import com.zust.itee.exam.dto.base.TreeNode;
import com.zust.itee.exam.service.DataDictionaryService;

/*
 * 操作原则
 * 1、点击左侧树节点，右边表格显示该项及其子项内容，可增/删/改/调整顺序
 * 2、输入条件搜索，右边表格显示符合该搜索条件的项，以主项为单位排序，
 *   主项内部按rank排，可增/删/改，不可不同主项间调整顺序
 * 3、点击右边表格中的项，左边树加载该项所在的完整的主项结构，
 *   并在树上定位到当前所选项
 */
@Controller
@RequestMapping("/dd")
public class DataDictionaryController {

    Logger logger = LoggerFactory.getLogger(DataDictionaryController.class);
    @Autowired
    private DataDictionaryService ddService;

    /**
     * 数据字典主项维护功能的主页面
     */
    @RequestMapping("/mainItemMaintain")
    public ModelAndView mainItemMaintain(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/ddMainItemMaintain");
        mav.addObject("itemId", id);
        return mav;
    }

    /**
     * 数据字典主项维护功能的左边树（字典主项字典树）
     */
    @RequestMapping("/ddTree")
    public ModelAndView ddTree() {
        ModelAndView mav = new ModelAndView("admin/ddTree");
        return mav;
    }

    /**
     * 数据字典主项维护功能的右边表格（显示pid对应字典项的直接子项，结果中包含pid对应的字典项）
     *
     * @param pid 指定的项
     */
    @RequestMapping("/ddItemContent")
    public ModelAndView ddItemContent(@RequestParam(value = "pid", required = false) Long pid) {
        ModelAndView mav = new ModelAndView("admin/ddItemContent");
        List<DataDictionaryModel> ddList = ddService.getSubItems(pid, true);
        logger.info("getSubItems pid=" + pid);
        mav.addObject("ddList", ddList);
        return mav;
    }

    /**
     @RequestMapping("/ddContent") public ModelAndView ddContent(@RequestParam(value="pageNo",
     required=false,defaultValue="1")int pageNo){
     ModelAndView mav = new ModelAndView("admin/ddContent");
     Page<DataDictionaryModel> page= new Page<DataDictionaryModel>();
     page.setPageNo(pageNo);
     page = dataDictionaryService.getSubItems(page, null);
     mav.addObject("page", page);
     return mav;
     }

     /**
      * 首次访问显示空树
      * @param maiItemId
     * @return

     @RequestMapping("/ddTreeData")
     @ResponseBody public List<TreeNode> ddTreeData(Long maiItemId){
     List<TreeNode> dumyTree=new ArrayList<TreeNode>();
     //		TreeNode node=new TreeNode();
     //		node.setId("root");
     //		node.setKey("root");
     //		node.setText("所有字典项");
     //		node.setTitle("所有字典项");
     //		dumyTree.add(node);
     return dumyTree;
     }
     */
    /**
     * 数据字典主项维护功能中，右边表格中添加字典项的子项
     */
    @RequestMapping("/saveSubDD")
    public String saveSubDDItem(DataDictionaryModel model) {
        ddService.saveItem(model);
        //logger.info(JSON.toJSONString(model));
        return "redirect:ddItemContent.html?pid=" + model.getParentId();
    }

    /**
     * 数据字典综合查询功能中，添加字典主项
     */
    @RequestMapping("/saveMainDD")
    public String saveMainDDItem(DataDictionaryModel model) {
        ddService.saveItem(model);
        //logger.info(JSON.toJSONString(model));
        return "redirect:searchDD.html?searchType=codeType&searchKey=" + model.getCodeType();
    }

    /**
     * 数据字典综合查询功能中修改字典项
     */
    @RequestMapping("/updateDDInSearch")
    public String updateDDItemInSearch(DataDictionaryModel model,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "searchType", required = false, defaultValue = "all")
                    String searchType,
            @RequestParam(value = "searchKey", required = false, defaultValue = "")
                    String searchKey) {
        ddService.updateItem(model);
        return "redirect:searchDD.html?pageNo="
                + pageNo
                + "&pageSize="
                + pageSize
                + "&searchType="
                + searchType
                + "&searchKey="
                + searchKey;
        //return "";

    }

    /**
     * 数据字典主项维护功能中修改字典项
     */
    @RequestMapping("/updateDDInMaintain")
    public String updateDDItemInMaintain(DataDictionaryModel model) {
        ddService.updateItem(model);
        return "redirect:ddItemContent.html?pid=" + model.getParentId();
        //return "";

    }

    @RequestMapping("/updateValue")
    @ResponseBody
    public Map<String, Object> updateValue(DataDictionaryModel model) {
        ddService.updateValue(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        ddService.deleteItem(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> save(DataDictionaryModel model) {
        ddService.saveItem(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return map;
    }

    /**
     * 数据字典综合查询功能中删除字典项，可级联删除
     */
    @RequestMapping("/deleteDDInSearch")
    public String deleteDDItemInSearch(@RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        ddService.deleteItem(id);
        return "redirect:searchDD.html?pageNo=" + pageNo + "&pageSize=" + pageSize;
    }

    /**
     * 数据字典主项维护功能中删除字典项，可级联删除
     */
    @RequestMapping("/deleteDDInMaintain")
    public String deleteDDItemInMaintain(@RequestParam(value = "id", required = true) Long id) {
        Long parentId = ddService.getItemById(id).getParentId();
        ddService.deleteItem(id);
        return "redirect:ddItemContent.html?pid=" + parentId;
    }

    /**
     * 获取id直接对应的字典项的信息
     */
    @RequestMapping("/getDDItemById")
    @ResponseBody
    public Map<String, Object> getDDItemById(@RequestParam(value = "id", required = true) Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        DataDictionaryModel model = ddService.getItemById(id);
        if (model != null) {
            map.put("item", model);
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 获取id对应的项的主字典项的信息
     */
    @RequestMapping("/getDDMainItemById")
    @ResponseBody
    public Map<String, Object> getDDMainItemById(
            @RequestParam(value = "id", required = true) Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        DataDictionaryModel model = ddService.getMainItemById(id);
        if (model != null) {
            map.put("mainItem", model);
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 获取codeType对应的字典主项的信息
     */
    @RequestMapping("/getDDMainItemByCodeType")
    @ResponseBody
    public Map<String, Object> getDDMainItemByCodeType(
            @RequestParam(value = "codeType", required = true) String codeType) {
        Map<String, Object> map = new HashMap<String, Object>();
        DataDictionaryModel model = ddService.getMainItemByCodeType(codeType);
        if (model != null) {
            map.put("mainItem", model);
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 搜索数据字典并用ModelAndView跳转，结果排序分两级，第一级按主项id大小排序，第二级在主项内按照rank排序，主项的rank为0
     * （由于分页排序有问题）
     *
     * @param searchType： all/valueStr/remark/codeType
     */
    @RequestMapping("/searchDD")
    public ModelAndView searchDD(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "searchType", required = false, defaultValue = "all")
                    String searchType,
            @RequestParam(value = "searchKey", required = false, defaultValue = "")
                    String searchKey) {
        ModelAndView mav = new ModelAndView("admin/ddSearch");
        /**/
        Page<DataDictionaryModel> page = new Page<DataDictionaryModel>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        logger.info("searchDD:searchType=" + searchType + ",searchKey=" + searchKey);
        page = ddService.searchDD(page, searchType, searchKey);
        //logger.info("searchDD:result size="+page.getResult().size());
        mav.addObject("page", page);
        mav.addObject("searchType", searchType);
        mav.addObject("searchKey", searchKey);

        return mav;
    }

    /**
     * 搜索数据字典，返回json数据
     */
    @RequestMapping("/searchDDJson")
    @ResponseBody
    public Map<String, Object> searchDDJson(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "searchType", required = false, defaultValue = "all")
                    String searchType,
            @RequestParam(value = "searchKey", required = false, defaultValue = "")
                    String searchKey) {
        Map<String, Object> map = new HashMap<String, Object>();
        Page<DataDictionaryModel> page = new Page<DataDictionaryModel>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        logger.info("searchDD:searchType=" + searchType + ",searchKey=" + searchKey);
        page = ddService.searchDD(page, searchType, searchKey);
        //logger.info("searchDD:result size="+page.getResult().size());
        map.put("page", page);
        map.put("searchType", searchType);
        map.put("searchKey", searchKey);
        return map;
    }

    /**
     * 根据itemId获得该字典项所在主项的完整字典树
     */
    @RequestMapping("/getDDTreeById")
    @ResponseBody
    public List<TreeNode> getSubItemTreeById(
            @RequestParam(value = "id", required = true) Long itemId,
            @RequestParam(value = "withMainItem", required = true, defaultValue = "true")
                    boolean withMainItem) {
        return ddService.getMainItemTreeByItemId(itemId, withMainItem);
    }

    /**
     * 根据codeType获得该字典项所在主项的完整字典树
     */
    @RequestMapping("/getDDTreeByCodeType")
    @ResponseBody
    public List<TreeNode> getSubItemTreeByCodeType(String codeType) {
        return ddService.getMainItemTreeByCodeType(codeType);
    }

	/*
    @RequestMapping("/getSampleType")
	@ResponseBody
	public TreeNode getSubItemTreeByCodeType() {
		return dataDictionaryService.getMainItemTreeByCodeType("inspection_sample_type").get(0);
	}
	*/

    /* 增加一项品控工具 dd； 其实所有的数据字典项的数据保存都是此方法 */
    @RequestMapping(value = "/saveQualityControl", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> saveQualityControl(DataDictionaryModel model) {
        Long id = ddService.saveItem(model);
        Boolean boolean1 = false;
        Map<String, Object> map = new HashMap<String, Object>();
        if (id != null) {
            boolean1 = true;
        }
        if (boolean1 == true) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /* 更新一项品控工具 dd；其实所有的数据字典项的数据删除都是此方法 */
    @RequestMapping(value = "/deleteQualityControl", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> deleteQualityControl(Long id) {
        ddService.deleteItem(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return map;
    }
}
