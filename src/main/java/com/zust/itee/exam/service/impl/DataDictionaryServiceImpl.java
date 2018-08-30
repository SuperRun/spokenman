package com.zust.itee.exam.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zust.itee.exam.dao.hibernate.BaseDao2;
import com.zust.itee.exam.dto.DataDictionaryModel;
import com.zust.itee.exam.dto.base.Page;
import com.zust.itee.exam.dto.base.TreeNode;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.service.DataDictionaryService;

@Service("ddService")
@Transactional
public class DataDictionaryServiceImpl implements DataDictionaryService {

    Logger logger = LoggerFactory.getLogger(DataDictionaryServiceImpl.class);
    @Autowired
    private BaseDao2<DataDictionary> dataDictionaryDao;
    @Autowired
    private BaseDao2<Long> longDao;

    /* (non-Javadoc)
     * @see com.jianla.service.DataDictionaryServiceI#saveItem(com.jianla.model.DataDictionaryModel)
     * 保存数据字典项
     * code_type与uplink、ctrl_id的逻辑关系
     * 合理关系：
     * 		1、code_type不为空且数据库中不存在，则uplink与ctrl_id均应为空
     * 		2、code_type不为空且数据库中存在，则为无效添加
     *      3、code_type为空，则uplink不能为空，且ctrl_id为uplink对应节点的ctrl_id，如果为空则该uplink节点是主项，ctrl_id
     *      为该uplink节点的id
     *
     */
    @Override
    public Long saveItem(DataDictionaryModel model) {
        // TODO Auto-generated method stub
        DataDictionary dd = new DataDictionary();
        BeanUtils.copyProperties(model, dd, new String[] { "actionTime" });
        Date d = new Date();
        Timestamp ts = new Timestamp(d.getTime());
        dd.setActionTime(ts);
        if (model.getCodeType() != null && model.getCodeType().trim().length() > 0) {
            //codeType不为空
            List<DataDictionary> dds = dataDictionaryDao
                    .find("from DataDictionary t where t.codeType = '"
                            + model.getCodeType().trim() + "'");
            if (dds != null && dds.size() > 0) {
                return null;//数据库中存在，无效添加
            }
            if (model.getParentId() != null) {
                return null;
            }
            //codeType不为空，且数据库中没有记录，parentId为空，表示添加的是主项

            dd.setLevel(0);
            dd.setStatus((short) 1);
            Long ddId = (Long) dataDictionaryDao.save(dd);
            dd.makeGlobalSeq();
            return ddId;
        }
        dd.setCodeType(null);
        if (model.getParentId() != null) {
            //codeType为空，parentId不为空，表示添加的是子项
            DataDictionary parentDD = dataDictionaryDao.get(DataDictionary.class,
                    model.getParentId());
            dd.setDataDictionaryByUplink(parentDD);
            if (parentDD.getDataDictionaryByCtrlId() == null)
            //为主项增加子项
            {
                dd.setDataDictionaryByCtrlId(parentDD);
            } else
            //为子项增加子项
            {
                dd.setDataDictionaryByCtrlId(parentDD.getDataDictionaryByCtrlId());
            }
            dd.setLevel(parentDD.getLevel() + 1);
            dd.setStatus((short) 1);
            Long ddId = (Long) dataDictionaryDao.save(dd);
            dd.makeGlobalSeq();
            return ddId;
        }
        return null;//	codeType为空，parentId也为空，无效添加
    }

    /* (non-Javadoc)
     * @see com.jianla.service.DataDictionaryServiceI#updateItem(com.jianla.model
     * .DataDictionaryModel)
     * 分为主项修改和子项修改，parent不可修改
     * 共同可修改：名称、备注、状态
     * 区别修改：
     * 	主项修改（rank为空）
     * 		codeType，但必须在库中唯一；
     * 	子项修改（codeType为空）
     * 		rank
     *
     */
    @Override
    public void updateItem(DataDictionaryModel model) {
        // TODO Auto-generated method stub
        if (model.getId() == null) {
            return;
        }
        DataDictionary dd = dataDictionaryDao.get(DataDictionary.class,
                model.getId());
        String modelCodeType = model.getCodeType();
        String ddCodeType = dd.getCodeType();
        if (model.getParentId() == null) {
            //主项，rank为空，可以修改codeType，但必须在库中唯一
            if (model.getRank() != null) {
                return;
            }
            if (modelCodeType == null || modelCodeType.trim().length() == 0) {
                return;
            }
            if (!modelCodeType.equals(ddCodeType)) {
                if (getMainItemByCodeType(modelCodeType) != null) {
                    return;
                }
            }
        } else {
            //子项，codeType为空，可修改rank
            if (modelCodeType != null && modelCodeType.trim().length() > 0) {
                return;
            }
        }

        BeanUtils.copyProperties(model, dd, new String[] { "actionTime", "actionBy" });
        dd.makeGlobalSeq();

        //parentId不允许修改
        //		if (model.getParentId() != null)
        //			dd.setDataDictionaryByUplink(dataDictionaryDao.get(DataDictionary.class,
        //					model.getParentId()));

    }

    @Override
    public DataDictionaryModel getItemById(Long id) {
        // TODO Auto-generated method stub
        if (id == null) {
            return null;
        }
        DataDictionary dd = dataDictionaryDao.get(DataDictionary.class, id);
        DataDictionaryModel model = changeModel(dd);
        return model;
    }

    /* (non-Javadoc)
     * @see com.jianla.service.DataDictionaryServiceI#getMainItemById(java.lang.Long)
     * 通过主项及其子项任意一个字典项的id找到主项对应的对象
     */
    @Override
    public DataDictionaryModel getMainItemById(Long id) {
        // TODO Auto-generated method stub
        if (id == null) {
            return null;
        }
        DataDictionary dd = dataDictionaryDao.get(DataDictionary.class, id);
        if (dd == null) {
            return null;
        }
        DataDictionaryModel model = new DataDictionaryModel();
        if (dd.getDataDictionaryByUplink() != null) {
            model = changeModel(dd.getDataDictionaryByCtrlId());
        } else {
            model = changeModel(dd);
        }
        return model;
    }

    @Override
    public List<DataDictionaryModel> getSubItems(Long pid, boolean withParent,
            Short status) {
        // TODO Auto-generated method stub
        return getSubItemsWithStatus(pid, withParent, status);
    }

    @Override
    public List<DataDictionaryModel> getSubItems(Long pid, boolean withParent) {
        return getSubItemsWithStatus(pid, withParent, (short) 1);
    }

    private List<DataDictionaryModel> getSubItemsWithStatus(Long pid, boolean withParent,
            Short status) {
        List<DataDictionaryModel> modelList = new ArrayList<DataDictionaryModel>();
        if (pid == null) {//返回所有主
            String hql = " from DataDictionary t where t.codeType is not null";
            if (status != null) {
                hql += " and status = " + status;
            }
            List<Object> values = new ArrayList<Object>();
            List<DataDictionary> mainDDList = dataDictionaryDao.find(hql, values);
            //Collections.sort(mainDDList,new DataDictionaryComparator());
            for (DataDictionary subDD : mainDDList) {
                DataDictionaryModel submodel = new DataDictionaryModel();
                submodel = changeModel(subDD);
                modelList.add(submodel);
            }
            return modelList;
        }
        DataDictionary dd = dataDictionaryDao.get(DataDictionary.class, pid);
        if (dd == null) {
            return null;
        }

        List<DataDictionary> subDDSet = dd.getDataDictionariesForUplink();
        //Collections.sort(subDDSet,new DataDictionaryComparator());
        if (withParent) {
            DataDictionaryModel pmodel = new DataDictionaryModel();
            if (status == null || dd.getStatus() == status) {
                pmodel = changeModel(dd);
                modelList.add(pmodel);
            } else {
                return modelList;
            }
        }
        for (DataDictionary subDD : subDDSet) {
            if (status == null || subDD.getStatus() == status) {
                DataDictionaryModel submodel = new DataDictionaryModel();
                submodel = changeModel(subDD);
                modelList.add(submodel);
            }
        }
        return modelList;
    }

    private DataDictionaryModel changeModel(DataDictionary dd) {
        final int shiftFactor = 10000;
        DataDictionaryModel model = new DataDictionaryModel();
        BeanUtils.copyProperties(dd, model);
        if (dd.getDataDictionaryByUplink() != null) {
            model.setParentId(dd.getDataDictionaryByUplink().getId());
            model.setParentValueStr(dd.getDataDictionaryByUplink().getValueStr());
            model.setCtrlId(dd.getDataDictionaryByCtrlId().getId());
            model.setCtrlValueStr(dd.getDataDictionaryByCtrlId().getValueStr());
        }
        return model;
    }

    @Override
    public DataDictionaryModel getMainItemByCodeType(String codeType) {
        List<DataDictionary> dds = dataDictionaryDao
                .find("from DataDictionary t where t.codeType = '" + codeType
                        + "'");
        if (dds == null || dds.size() != 1) {
            return null;
        }
        DataDictionaryModel model = changeModel(dds.get(0));
        //				new DataDictionaryModel();
        //		BeanUtils.copyProperties(dds.get(0), model);
        //		if (dds.get(0).getDataDictionaryByUplink() != null) {
        //			model.setParentId(dds.get(0).getDataDictionaryByUplink().getId());
        //			model.setParentValueStr(dds.get(0).getDataDictionaryByUplink()
        //					.getValueStr());
        //		}
        return model;
    }

    @Override
    public void deleteItem(Long itemId) {
        // TODO Auto-generated method stub
        if (itemId == null) {
            return;
        }
        del(itemId);
    }

    private void del(Long id) {
        // TODO Auto-generated method stub
        DataDictionary dd = dataDictionaryDao.get(DataDictionary.class, id);
        if (dd != null) {
            List<DataDictionary> subDds = dd.getDataDictionariesForUplink();
            if (subDds != null && !subDds.isEmpty()) {
                for (DataDictionary subDd : subDds) {
                    del(subDd.getId());
                }
            }
            dd.setStatus((short) 0);//伪删除
            //dataDictionaryDao.delete(dd);//真删除
        }
    }

    @Override
    public Page<DataDictionaryModel> findItems(Page<DataDictionaryModel> page,
            DataDictionaryModel model, Short status) {
        // TODO Auto-generated method stub
        return findItemsWithStatus(page, model, status);
    }

    @Override
    public Page<DataDictionaryModel> findItems(Page<DataDictionaryModel> page,
            DataDictionaryModel model) {
        return findItemsWithStatus(page, model, (short) 1);
    }

    private Page<DataDictionaryModel> findItemsWithStatus(Page<DataDictionaryModel> page,
            DataDictionaryModel model, Short status) {
        // TODO Auto-generated method stub

        String hql = "from DataDictionary t where ";
        if (status == null) {
            hql += " 1=1 ";
        } else {
            hql += " status =" + status;
        }
        List<Object> values = new ArrayList<Object>();
        hql = addWhere(model, hql, values);
        hql += " order by t.globalSeq asc";
        List<DataDictionary> ddList = dataDictionaryDao.find(hql, values, page.getPageNo(),
                page.getPageSize());
        logger.info("search result size:" + ddList.size());
        logger.info("search hql:" + hql);
        if (ddList == null || ddList.size() == 0) {
            return page;
        }
        //Collections.sort(ddList,new DataDictionaryComparator());
        List<DataDictionaryModel> ddModelList = new ArrayList<DataDictionaryModel>();
        for (DataDictionary dd : ddList) {
            DataDictionaryModel ddModel = new DataDictionaryModel();
            ddModel = changeModel(dd);
            ddModelList.add(ddModel);
        }
        page.setResult(ddModelList);
        page.setTotalCount(dataDictionaryDao.countHqlResult(hql, values));
        return page;
    }

    private String addWhere(DataDictionaryModel model, String hql,
            List<Object> values) {
        // TODO Auto-generated method stub
        if (model.getId() != null) {
            hql += " and t.id=? ";
            values.add(model.getId());
            return hql;
        }
        String orString = " and (";
        boolean hasOr = false;
        if (model.getCodeType() != null && model.getCodeType().length() > 0) {
            hasOr = true;
            orString += " t.codeType like ? ";
            values.add("%%" + model.getCodeType().trim() + "%%");
        }
        if (model.getValueStr() != null && model.getValueStr().length() > 0) {
            if (hasOr) {
                orString += " or t.valueStr like ? ";
            } else {
                orString += " t.valueStr like ? ";
                hasOr = true;
            }
            values.add("%%" + model.getValueStr().trim() + "%%");
        }
        if (model.getValueRule() != null && model.getValueRule().length() > 0) {
            if (hasOr) {
                orString += " or t.valueRule like ? ";
            } else {
                orString += " t.valueRule like ? ";
                hasOr = true;
            }

            values.add("%%" + model.getValueRule().trim() + "%%");
        }
        if (model.getRemark() != null && model.getRemark().length() > 0) {
            if (hasOr) {
                orString += " or t.remark like ? ";
            } else {
                orString += " t.remark like ? ";
                hasOr = true;
            }
            values.add("%%" + model.getRemark().trim() + "%%");
        }
        orString += " ) ";
        if (hasOr) {
            hql += orString;
        }
        if (model.getActionBy() != null) {
            hql += " and t.actionBy = ？ ";
            values.add(model.getActionBy());
        }
        if (model.getStatus() != null) {
            hql += " and t.status = ？ ";
            values.add(model.getStatus());
        }
        return hql;
    }

    @Override
    public List<TreeNode> getMainItemTreeByCodeType(String codeType,
            Short status) {
        // TODO Auto-generated method stub
        return getMainItemTreeByCodeTypeWithStatus(codeType, status);
    }

    @Override
    public List<TreeNode> getMainItemTreeByCodeType(String codeType) {
        return getMainItemTreeByCodeTypeWithStatus(codeType, (short) 1);
    }

    private List<TreeNode> getMainItemTreeByCodeTypeWithStatus(String codeType, Short status) {
        // TODO Auto-generated method stub
        if (codeType == null || codeType.length() == 0) {
            return null;
        }
        List<DataDictionary> dds = dataDictionaryDao
                .find("from DataDictionary t where t.codeType = '" + codeType
                        + "'");
        if (dds == null || dds.size() != 1) {
            return null;
        }
        List<TreeNode> tree = new ArrayList<TreeNode>();
        DataDictionary dd = dds.get(0);
        if (status == null || dd.getStatus() == status) {
            tree.add(this.tree(dd, status));
        }
        return tree;
    }

    @Override
    public List<TreeNode> getMainItemTreeByMainItemId(Long mainId,
            boolean withMainItem, Short status) {
        // TODO Auto-generated method stub
        return getMainItemTreeByMainItemIdWithStatus(mainId, withMainItem, status);
    }

    @Override
    public List<TreeNode> getMainItemTreeByMainItemId(Long mainId, boolean withMainItem) {
        return getMainItemTreeByMainItemIdWithStatus(mainId, withMainItem, (short) 1);
    }

    private List<TreeNode> getMainItemTreeByMainItemIdWithStatus(Long mainId, boolean withMainItem,
            Short status) {
        if (mainId == null) {
            return null;
        }
        DataDictionary mainDD = dataDictionaryDao.get(DataDictionary.class, mainId);
        if (mainDD == null) {
            return null;
        }
        String codeType = mainDD.getCodeType();
        // TODO Auto-generated method stub
        if (codeType == null || codeType.trim().length() == 0) {
            return null;
        }
        //		List<DataDictionary> dds = dataDictionaryDao
        //				.find("from DataDictionary t where t.codeType = '" + codeType
        //						+ "'");
        //		if (dds == null || dds.size() != 1)
        //			return null;
        //		DataDictionary dd = dds.get(0);
        List<TreeNode> tree = new ArrayList<TreeNode>();
        if (withMainItem) {
            //主项作为根节点
            if (status == null || mainDD.getStatus() == status) {
                tree.add(this.tree(mainDD, status));
            }
        } else {
            List<DataDictionary> level1DDItemList = mainDD.getDataDictionariesForUplink();
            if (level1DDItemList == null) {
                return tree;
            }
            for (DataDictionary level1DDItem : level1DDItemList) {
                if (status == null || level1DDItem.getStatus() == status) {
                    tree.add(tree(level1DDItem, status));
                }
            }
        }

        return tree;
    }

    private TreeNode tree(DataDictionary t, Short status) {
        // TODO Auto-generated method stub
        TreeNode node = new TreeNode();
        node.setId(t.getId().toString());
        node.setKey(t.getId().toString());
        node.setText(t.getValueStr());
        node.setTitle(t.getValueStr());
        Map<String, Object> attributes = new HashMap<String, Object>();
        node.setAttributes(attributes);
        if (t.getDataDictionariesForUplink() != null
                && t.getDataDictionariesForUplink().size() > 0) {
            node.setState("closed");
            node.setExpand(false);
            List<DataDictionary> l = new ArrayList<DataDictionary>(
                    t.getDataDictionariesForUplink());
            Collections.sort(l, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    DataDictionary dd1 = (DataDictionary) o1;
                    DataDictionary dd2 = (DataDictionary) o2;
                    Long rank1 = dd1.getRank();
                    if (rank1 == null) {
                        rank1 = 0l;
                    }
                    Long rank2 = dd2.getRank();
                    if (rank2 == null) {
                        rank2 = 0l;
                    }
                    return (int) (rank1 - rank2);
                }
            });// 排序
            List<TreeNode> children = new ArrayList<TreeNode>();
            for (DataDictionary r : l) {
                if (status == null || r.getStatus() == status) {
                    TreeNode tn = tree(r, status);
                    children.add(tn);
                }
            }
            node.setChildren(children);
            node.setState("open");
            node.setExpand(true);
        }
        return node;
    }

    /*
        @Override
        public DataDictionaryModel getMainItemBySubItemId(Long id) {
            // TODO Auto-generated method stub
            if(id==null)
                return null;
            DataDictionary dd=dataDictionaryDao.get(DataDictionary.class, id);
            if(dd==null)
                return null;
            DataDictionary ctrlDD=dd.getDataDictionaryByCtrlId();
            DataDictionaryModel model=new DataDictionaryModel();
            if(ctrlDD==null){
                BeanUtils.copyProperties(dd, model);
            }else{
                BeanUtils.copyProperties(ctrlDD, model);
            }
            return model;
        }
    */
    /* (non-Javadoc)
     * @see com.jianla.service.DataDictionaryServiceI#searchDDTree(java.lang.String)
	 * 就从字典表中搜索，出来的记录把主数据项及其子项放在一起就可以了，
	 * 我在表中加了一个ctrl_id，ctrl_id就是就是改数据项的主项的id，主项的ctrl_id为null
	 * 一个主项下面若干级子项的ctrl_id都是该主项的id
	 * 同一个数据主项及下面的内容放在一起，看起来就舒服多了
	 *
	 */
    @Override
    public Page<DataDictionaryModel> searchDD(Page<DataDictionaryModel> page,
            String searchKey, String keyword, Short status) {
        // TODO Auto-generated method stub
        return searchDDWithStatus(page, searchKey, keyword, status);
    }

    @Override
    public Page<DataDictionaryModel> searchDD(Page<DataDictionaryModel> page,
            String searchKey, String keyword) {
        // TODO Auto-generated method stub
        return searchDDWithStatus(page, searchKey, keyword, (short) 1);
    }

    private Page<DataDictionaryModel> searchDDWithStatus(Page<DataDictionaryModel> page,
            String searchKey, String keyword, Short status) {
        // TODO Auto-generated method stub
        DataDictionaryModel model = new DataDictionaryModel();
        if (keyword == null | keyword.trim().length() == 0) {
            keyword = null;
        } else {
            keyword = keyword.trim();
        }
        if (searchKey == null || searchKey.trim().length() == 0 || searchKey.trim().equals("all")) {

            model.setCodeType(keyword);
            model.setValueStr(keyword);
            model.setRemark(keyword);
        } else {
            if (searchKey.trim().equals("codeType")) {
                model.setCodeType(keyword);
            }
            if (searchKey.trim().equals("remark")) {
                model.setRemark(keyword);
            }
            if (searchKey.trim().equals("valueStr")) {
                model.setValueStr(keyword);
            }
        }
        logger.info("search model:" + JSON.toJSONString(model));
        return findItems(page, model, status);
    }

    @Override
    public List<TreeNode> getMainItemTreeByItemId(Long itemId, boolean withMainItem, Short status) {
        return getMainItemTreeByItemIdWithStatus(itemId, withMainItem, status);
    }

    @Override
    public List<TreeNode> getMainItemTreeByItemId(Long itemId, boolean withMainItem) {
        return getMainItemTreeByItemIdWithStatus(itemId, withMainItem, (short) 1);
    }

    private List<TreeNode> getMainItemTreeByItemIdWithStatus(Long itemId, boolean withMainItem,
            Short status) {
        // TODO Auto-generated method stub
        if (itemId == null) {
            return null;
        }
        DataDictionary itemDD = dataDictionaryDao.get(DataDictionary.class, itemId);
        if (itemDD == null) {
            return null;
        }
        Long mainId = itemId;
        if (itemDD.getDataDictionaryByUplink() != null) {
            mainId = itemDD.getDataDictionaryByCtrlId().getId();
        }
        return getMainItemTreeByMainItemId(mainId, withMainItem, status);
    }

    @Override
    public List<DataDictionaryModel> getSubItemsOfMainItem(String codeType) {
        List<DataDictionary> dds = dataDictionaryDao
                .find("from DataDictionary t where t.codeType = '" + codeType
                        + "'");
        if (dds == null || dds.size() != 1) {
            return null;
        }
        DataDictionary mainItem = dds.get(0);

        List<DataDictionary> subItems = mainItem.getDataDictionariesForUplink();
        List<DataDictionaryModel> ddModels = new ArrayList<DataDictionaryModel>(0);
        for (DataDictionary subItem : subItems) {
            ddModels.add(changeModel(subItem));
        }
        return ddModels;
    }

    @Override
    public List<DataDictionaryModel> getMultiItemsByIds(String itemIds) {
        // TODO Auto-generated method stub
        if (itemIds == null || itemIds.trim().length() == 0) {
            return null;
        }
        List<DataDictionaryModel> ddList = new ArrayList<DataDictionaryModel>();
        String hql = "from DataDictionary t where t.id in (" + itemIds.trim() + ")";
        List<DataDictionary> ddItems = dataDictionaryDao.find(hql);
        for (DataDictionary item : ddItems) {
            ddList.add(changeModel(item));
        }
        return ddList;
    }

    //new interface code
    @Override
    public Map<Long, List<DataDictionaryModel>> getMultiSubItemListByIds(
            String itemIds, boolean withParent) {
        List<DataDictionaryModel> ddList = getMultiItemsByIds(itemIds);
        Map<Long, List<DataDictionaryModel>> ddListMap = new HashMap<Long,
                List<DataDictionaryModel>>();
        if (ddList == null || ddList.size() == 0) {
            return null;
        }
        for (DataDictionaryModel ddModel : ddList) {
            List<DataDictionaryModel> subDDList = this.getSubItems(ddModel.getId(), withParent);
            ddListMap.put(ddModel.getId(), subDDList);
        }
        return ddListMap;
    }

    @Override
    public DataDictionaryModel getDDItemBySubCodeType(Long pid,
            String subCodeType) {
        String hql = "from DataDictionary t where t.dataDictionaryByUplink.id = "
                + pid
                + " and t.subCodeType ='"
                + subCodeType.trim()
                + "'";
        List<DataDictionary> ddItems = dataDictionaryDao.find(hql);
        if (ddItems == null || ddItems.size() == 0) {
            return null;
        } else {
            return changeModel(ddItems.get(0));
        }
    }

    @Override
    public DataDictionaryModel getDDItemBySubCodeType(String codeType,
            String subCodeType) {
        String hql = "from DataDictionary t where t.dataDictionaryByUplink.codeType = '"
                + codeType.trim()
                + "' and t.subCodeType ='"
                + subCodeType.trim()
                + "'";
        List<DataDictionary> ddItems = dataDictionaryDao.find(hql);
        if (ddItems == null || ddItems.size() == 0) {
            return null;
        } else {
            return changeModel(ddItems.get(0));
        }
    }

    @Override
    public List<DataDictionaryModel> getDDItemBySubCodeTypes(String codeType,
            String subCodeTypes) {
        if (codeType == null || codeType.trim().length() == 0) {
            return null;
        }
        if (subCodeTypes == null || subCodeTypes.trim().length() == 0) {
            return null;
        }
        String[] subCodeTypeList = subCodeTypes.trim().split(",");
        String param = "";
        for (String str : subCodeTypeList) {
            param += "'" + str + "',";
        }
        if (param.endsWith(",")) {
            param = param.substring(0, param.length() - 1);
        }
        List<DataDictionaryModel> ddList = new ArrayList<DataDictionaryModel>();
        String hql = "from DataDictionary t where t.dataDictionaryByUplink.codeType = '"
                + codeType.trim()
                + "' and t.subCodeType in ("
                + param
                + ")";
        List<DataDictionary> ddItems = dataDictionaryDao.find(hql);
        for (DataDictionary item : ddItems) {
            ddList.add(changeModel(item));
        }
        return ddList;
    }

    @Override
    public DataDictionaryModel getDDItemBySubCodeTypePath(String subCodeTypePath) {
        if (subCodeTypePath == null || subCodeTypePath.trim().length() == 0) {
            return null;
        }
        String[] codePathArray = subCodeTypePath.split(">");
        DataDictionaryModel parentItem = getMainItemByCodeType(codePathArray[0]);
        if (parentItem == null) {
            return null;
        }
        if (codePathArray.length == 1) {
            return parentItem;
        }

        for (int i = 1; i < codePathArray.length; i++) {
            DataDictionaryModel subItemModel = getDDItemBySubCodeType(parentItem.getId(),
                    codePathArray[i]);
            if (subItemModel == null) {
                return null;
            }
            parentItem = subItemModel;
        }
        return parentItem;
    }

    @Override
    public List<DataDictionaryModel> getSubItemsBySubCodeType(Long pid,
            String subCodeType, boolean withParent) {
        DataDictionaryModel parentItem = getDDItemBySubCodeType(pid, subCodeType);
        if (parentItem == null) {
            return null;
        }
        return getSubItems(parentItem.getId(), withParent);
    }

    @Override
    public List<DataDictionaryModel> getSubItemsBySubCodeType(String codeType,
            String subCodeType, boolean withParent) {
        DataDictionaryModel parentItem = getDDItemBySubCodeType(codeType, subCodeType);
        if (parentItem == null) {
            return null;
        }
        return getSubItems(parentItem.getId(), withParent);
    }

    @Override
    public List<DataDictionaryModel> getSubItemsBySubCodeTypePath(String subCodeTypePath,
            boolean withParent) {
        DataDictionaryModel parentItem = getDDItemBySubCodeTypePath(subCodeTypePath);
        if (parentItem == null) {
            return null;
        }
        return getSubItems(parentItem.getId(), withParent);
    }

    @Override
    public Map<String, List<DataDictionaryModel>> getMultiSubItemListByCodeTypePaths(
            String parentCodeTypePath, String subCodeTypes, boolean withParent) {
        DataDictionaryModel parentItem = getDDItemBySubCodeTypePath(parentCodeTypePath);
        if (parentItem == null) {
            return null;
        }
        if (subCodeTypes == null || subCodeTypes.trim().length() == 0) {
            return null;
        }
        Map<String, List<DataDictionaryModel>> ddItemListMap = new HashMap<String,
                List<DataDictionaryModel>>();
        String[] subCodeTypeArray = subCodeTypes.split(",");
        for (String subCodeType : subCodeTypeArray) {
            List<DataDictionaryModel> subItems = getSubItemsBySubCodeType(parentItem.getId(),
                    subCodeType, withParent);
            if (subItems != null) {
                ddItemListMap.put(subCodeType, subItems);
            }
        }
        return ddItemListMap;
    }

    @Override
    public Map<String, List<DataDictionaryModel>> getMultiSubItemListByCodeTypePaths(
            Long pid, String subCodeTypes, boolean withParent) {
        DataDictionaryModel parentItem = this.getItemById(pid);
        if (parentItem == null) {
            return null;
        }
        if (subCodeTypes == null || subCodeTypes.trim().length() == 0) {
            return null;
        }
        Map<String, List<DataDictionaryModel>> ddItemListMap = new HashMap<String,
                List<DataDictionaryModel>>();
        String[] subCodeTypeArray = subCodeTypes.split(",");
        for (String subCodeType : subCodeTypeArray) {
            List<DataDictionaryModel> subItems = getSubItemsBySubCodeType(parentItem.getId(),
                    subCodeType, withParent);
            if (subItems != null) {
                ddItemListMap.put(subCodeType, subItems);
            }
        }
        return ddItemListMap;
    }

    @Override
    public void updateValue(DataDictionaryModel model) {
        DataDictionary dd = dataDictionaryDao.get(DataDictionary.class,
                model.getId());
        dd.setValueStr(model.getValueStr());
        dataDictionaryDao.update(dd);
    }

    @Override
    public List<Long> findChildIds(Long pid) {
        String hql = "select d.id from DataDictionary d where d.dataDictionaryByUplink.id = ?";
        List<Long> ids = longDao.find(hql, new Object[] { pid });
        return ids;
    }

    @Override
    public long countAll() {
        String hql = "select count(*) from DataDictionary";
        long count = dataDictionaryDao.count(hql);
        return count;
    }
}
