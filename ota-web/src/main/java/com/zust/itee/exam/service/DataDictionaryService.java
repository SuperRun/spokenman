package com.zust.itee.exam.service;

import java.util.List;
import java.util.Map;

import com.zust.itee.exam.dto.DataDictionaryModel;
import com.zust.itee.exam.dto.base.Page;
import com.zust.itee.exam.dto.base.TreeNode;

public interface DataDictionaryService {

    /**
     * 保存字典项，可以是主项，也可以是子项
     */
    public Long saveItem(DataDictionaryModel model);

    /**
     * 更新字典项，可以是主项，也可以是子项
     */
    public void updateItem(DataDictionaryModel model);

    /**
     * 根据id获得对应的字典项
     */
    public DataDictionaryModel getItemById(Long id);

    /**
     * 根据id获得该字典项所在的主项
     */
    public DataDictionaryModel getMainItemById(Long id);

    /**
     * 根据code_type获得对应的字典主项
     */
    public DataDictionaryModel getMainItemByCodeType(String codeType);

    /**
     * 根据itemId删除对应的字典项，如果有子项将级联删除
     */
    public void deleteItem(Long itemId);

    /**
     * 根据条件查询字典项，所有符合条件的主项和子项都返回
     *
     * @param page 分页条件，主要为从页面传递过来的pageNo（默认为1）和pageSize（默认为10）两个参数
     * @param model 字典查询条件codeType、valueStr、remark三个字段为or查询，其他为and查询
     */
    public Page<DataDictionaryModel> findItems(Page<DataDictionaryModel> page,
            DataDictionaryModel model);

    public Page<DataDictionaryModel> findItems(Page<DataDictionaryModel> page,
            DataDictionaryModel model, Short status);

    /**
     * 字典关键字查询
     *
     * @param page 分页条件，主要为从页面传递过来的pageNo（默认为1）和pageSize（默认为10）两个参数
     * @param searchKey 搜索字段，值为codeType、valueStr、remark，如果三个字段都搜索则为all
     * @param keyword 搜索关键字
     */
    public Page<DataDictionaryModel> searchDD(Page<DataDictionaryModel> page, String searchKey,
            String keyword);

    public Page<DataDictionaryModel> searchDD(Page<DataDictionaryModel> page, String searchKey,
            String keyword, Short status);

    /**
     * 根据主项的codeType获得该主项的完整字典树
     *
     * @return+
     */
    public List<TreeNode> getMainItemTreeByCodeType(String codeType);

    public List<TreeNode> getMainItemTreeByCodeType(String codeType, Short status);

    /**
     * 根据主项的id获得该主项的完整字典树
     */
    public List<TreeNode> getMainItemTreeByMainItemId(Long mainId, boolean withMainItem);

    public List<TreeNode> getMainItemTreeByMainItemId(Long mainId, boolean withMainItem,
            Short status);

    /**
     * 根据id获得该字典项所在主项的完整字典树
     */
    public List<TreeNode> getMainItemTreeByItemId(Long itemId, boolean withMainItem);

    public List<TreeNode> getMainItemTreeByItemId(Long itemId, boolean withMainItem, Short status);

    /**
     * 获得pid对应的字典项的直接子项（不往下递归）
     *
     * @param withParent 是否包括pid对应的字典项：结果中包含parent时为true，结果中不包含parent时为false
     */
    public List<DataDictionaryModel> getSubItems(Long pid, boolean withParent);

    public List<DataDictionaryModel> getSubItems(Long pid, boolean withParent, Short status);

    public List<DataDictionaryModel> getSubItemsOfMainItem(String codeType);
    //根据id串找字典项

    /**
     * 根据逗号分割的id字符串一次获取所有对应的字典项。
     * 用法：1、把要获取的字典项的id用逗号分割写到配置文件中，通过工具类获取，然后通过本方法一次获取所有对应字典项信息
     * 2、在页面上通过js收集要获取信息的id，组织成id字符串
     * 示例：机构可以发布的新闻咨询栏目；游客查看新闻资讯页面的左边栏栏目
     *
     * @return 对应字典项对象的list
     */
    public List<DataDictionaryModel> getMultiItemsByIds(String itemIds);

    /**
     * 根据逗号分割的id字符串一次获取所有对应的字典项及对应字典项的所有子项
     * 如果withParent为true，则将每个id对应的字典项放在响应的列表的第一个，否则只在列表中放id对一个字典项的所有子项对象
     *
     * @return 以id为key，id对应的字典项的子项对象列表为value的map
     */
    public Map<Long, List<DataDictionaryModel>> getMultiSubItemListByIds(String itemIds,
            boolean withParent);

    //根据subCodeType找字典项

    /**
     * 通过subCodeType查找对应子项对象
     *
     * @param pid 父项的id
     * @param subCodeType 要找的子项subCodeType
     */
    public DataDictionaryModel getDDItemBySubCodeType(Long pid, String subCodeType);

    /**
     * 获得主项codeType下面subCodeType对应的子项对象
     */
    public DataDictionaryModel getDDItemBySubCodeType(String codeType, String subCodeType);

    /**
     * 获得主项codeType下面指定的子项的集合
     *
     * @param subCodeTypes 指定子项的subCodeType用逗号分割组成的字符串
     * @return subCodeTypes对应的子项对象的集合
     */
    public List<DataDictionaryModel> getDDItemBySubCodeTypes(String codeType, String subCodeTypes);

    /**
     * 通过由主项开始的subCodeTypePath串查找子项对象
     *
     * @param subCodeTypePath 用“>”连接的codeType串，如:mainCodeType>subCodeType>subsubCodeType
     */
    public DataDictionaryModel getDDItemBySubCodeTypePath(String subCodeTypePath);

    //根据subCodeType找字典项的子项集合

    /**
     * 通过字典项的id及其子项的subCodeType查找对应子项对象的直接子项对象集合
     *
     * @param pid 父项id
     * @param subCodeType 子项subCodeType
     * @param withParent 是否包含subCodeType对应的子项
     */
    public List<DataDictionaryModel> getSubItemsBySubCodeType(Long pid, String subCodeType,
            boolean withParent);

    /**
     * 获得主项codeType下面subCodeType对应子项下面的直接子项对象集合（主项的孙子）
     */
    public List<DataDictionaryModel> getSubItemsBySubCodeType(String codeType, String subCodeType,
            boolean withParent);

    /**
     * 通过由主项开始的subCodeTypePath串查找子项对象的直接子项对象集合
     *
     * @param subCodeTypePath subCodeTypePath是由>链接起来的字符串，必须以主项的codeType开始
     */
    public List<DataDictionaryModel> getSubItemsBySubCodeTypePath(String subCodeTypePath,
            boolean withParent);

    /**
     * 根据逗号分割的subCodeType串一次获取所有对应的字典项及对应字典项的所有子项
     *
     * @return 以subCodeType为key，对应的字典项的子项对象列表为value的map
     */
    public Map<String, List<DataDictionaryModel>> getMultiSubItemListByCodeTypePaths(Long pid,
            String subCodeTypes, boolean withParent);

    /**
     * 根据逗号分割的subCodeType串一次获取所有对应的字典项及对应字典项的所有子项
     *
     * @return 以subCodeType为key，对应的字典项的子项对象列表为value的map
     */
    public Map<String, List<DataDictionaryModel>> getMultiSubItemListByCodeTypePaths(
            String parentCodeTypePath, String subCodeTypes, boolean withParent);

    public void updateValue(DataDictionaryModel model);

    public List<Long> findChildIds(Long pid);

    long countAll();
}
