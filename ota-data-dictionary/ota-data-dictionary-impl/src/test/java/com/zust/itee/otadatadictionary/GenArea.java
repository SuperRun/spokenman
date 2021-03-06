package com.zust.itee.otadatadictionary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zust.itee.otadatadictionary.client.domain.DataDictionary;
import com.zust.itee.otadatadictionary.client.service.DataDictionaryService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 生成区域信息
 *
 * @author pojun
 */
// @SpringBootTest
@SpringBootTest(classes = OtaDataDictionaryApplication.class)
@RunWith(SpringRunner.class)
public class GenArea {

    @Resource
    private DataDictionaryService dataDictionaryService;

    @Test
    public void genProvince() {
        String str =
                "[\n"
                        + "    {\n"
                        + "        \"name\":\"北京市\",\n"
                        + "        \"id\":\"110000\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "        \"name\":\"天津市\",\n"
                        + "        \"id\":\"120000\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "        \"name\":\"上海市\",\n"
                        + "        \"id\":\"310000\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "        \"name\":\"浙江省\",\n"
                        + "        \"id\":\"330000\"\n"
                        + "    }\n"
                        + "]";
        JSONArray provinces = JSONArray.parseArray(str);
        for (int i = 0; i < provinces.size(); i++) {
            JSONObject province = provinces.getJSONObject(i);
            Long code = Long.valueOf(province.getString("id"));
            String name = province.getString("name");
            DataDictionary dataDictionary = DataDictionary.builder()
                    .uplink(1L)
                    .ctrlId(1L)
                    .level(1)
                    .status((short) 1)
                    .remark(name)
                    .valueStr(name)
                    .globalSeq(code)
                    .build();
            dataDictionaryService.upsert(dataDictionary);
        }
    }

    @Test
    public void genCity() {
        String str = "[\n"
                + "    {\n"
                + "        \"province\":\"北京市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"110100\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"天津市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"120100\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"上海市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"310100\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"杭州市\",\n"
                + "        \"id\":\"330100\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"宁波市\",\n"
                + "        \"id\":\"330200\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"温州市\",\n"
                + "        \"id\":\"330300\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"嘉兴市\",\n"
                + "        \"id\":\"330400\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"湖州市\",\n"
                + "        \"id\":\"330500\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"绍兴市\",\n"
                + "        \"id\":\"330600\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"金华市\",\n"
                + "        \"id\":\"330700\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"衢州市\",\n"
                + "        \"id\":\"330800\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"舟山市\",\n"
                + "        \"id\":\"330900\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"台州市\",\n"
                + "        \"id\":\"331000\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"province\":\"浙江省\",\n"
                + "        \"name\":\"丽水市\",\n"
                + "        \"id\":\"331100\"\n"
                + "    }\n"
                + "]";
        JSONArray citys = JSONArray.parseArray(str);
        for (int i = 0; i < citys.size(); i++) {
            JSONObject cityJson = citys.getJSONObject(i);
            String provinceName = cityJson.getString("province");
            String cityName = cityJson.getString("name");
            Long code = Long.valueOf(cityJson.getString("id"));
            List<DataDictionary> provinces = dataDictionaryService.listByFilter(
                    DataDictionary.builder().valueStr(provinceName).build(), 1, 1);
            DataDictionary province = provinces.get(0);
            DataDictionary city = DataDictionary.builder()
                    .uplink(province.getId())
                    .ctrlId(1L)
                    .level(2)
                    .status((short) 1)
                    .remark(cityName)
                    .valueStr(cityName)
                    .globalSeq(code)
                    .build();
            dataDictionaryService.upsert(city);
        }
    }

    @Test
    public void genArea() {

        String str = "{\n"
                + "    \"110101\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"东城区\",\n"
                + "        \"id\":\"110101\"\n"
                + "    },\n"
                + "    \"110102\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"西城区\",\n"
                + "        \"id\":\"110102\"\n"
                + "    },\n"
                + "    \"110105\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"朝阳区\",\n"
                + "        \"id\":\"110105\"\n"
                + "    },\n"
                + "    \"110106\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"丰台区\",\n"
                + "        \"id\":\"110106\"\n"
                + "    },\n"
                + "    \"110107\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"石景山区\",\n"
                + "        \"id\":\"110107\"\n"
                + "    },\n"
                + "    \"110108\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"海淀区\",\n"
                + "        \"id\":\"110108\"\n"
                + "    },\n"
                + "    \"110109\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"门头沟区\",\n"
                + "        \"id\":\"110109\"\n"
                + "    },\n"
                + "    \"110111\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"房山区\",\n"
                + "        \"id\":\"110111\"\n"
                + "    },\n"
                + "    \"110112\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"通州区\",\n"
                + "        \"id\":\"110112\"\n"
                + "    },\n"
                + "    \"110113\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"顺义区\",\n"
                + "        \"id\":\"110113\"\n"
                + "    },\n"
                + "    \"110114\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"昌平区\",\n"
                + "        \"id\":\"110114\"\n"
                + "    },\n"
                + "    \"110115\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"大兴区\",\n"
                + "        \"id\":\"110115\"\n"
                + "    },\n"
                + "    \"110116\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"怀柔区\",\n"
                + "        \"id\":\"110116\"\n"
                + "    },\n"
                + "    \"110117\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"平谷区\",\n"
                + "        \"id\":\"110117\"\n"
                + "    },\n"
                + "    \"110118\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"密云区\",\n"
                + "        \"id\":\"110118\"\n"
                + "    },\n"
                + "    \"110119\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"延庆区\",\n"
                + "        \"id\":\"110119\"\n"
                + "    },\n"
                + "    \"120101\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"和平区\",\n"
                + "        \"id\":\"120101\"\n"
                + "    },\n"
                + "    \"120102\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"河东区\",\n"
                + "        \"id\":\"120102\"\n"
                + "    },\n"
                + "    \"120103\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"河西区\",\n"
                + "        \"id\":\"120103\"\n"
                + "    },\n"
                + "    \"120104\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"南开区\",\n"
                + "        \"id\":\"120104\"\n"
                + "    },\n"
                + "    \"120105\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"河北区\",\n"
                + "        \"id\":\"120105\"\n"
                + "    },\n"
                + "    \"120106\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"红桥区\",\n"
                + "        \"id\":\"120106\"\n"
                + "    },\n"
                + "    \"120110\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"东丽区\",\n"
                + "        \"id\":\"120110\"\n"
                + "    },\n"
                + "    \"120111\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"西青区\",\n"
                + "        \"id\":\"120111\"\n"
                + "    },\n"
                + "    \"120112\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"津南区\",\n"
                + "        \"id\":\"120112\"\n"
                + "    },\n"
                + "    \"120113\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"北辰区\",\n"
                + "        \"id\":\"120113\"\n"
                + "    },\n"
                + "    \"120114\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"武清区\",\n"
                + "        \"id\":\"120114\"\n"
                + "    },\n"
                + "    \"120115\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"宝坻区\",\n"
                + "        \"id\":\"120115\"\n"
                + "    },\n"
                + "    \"120116\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"滨海新区\",\n"
                + "        \"id\":\"120116\"\n"
                + "    },\n"
                + "    \"120117\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"宁河区\",\n"
                + "        \"id\":\"120117\"\n"
                + "    },\n"
                + "    \"120118\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"静海区\",\n"
                + "        \"id\":\"120118\"\n"
                + "    },\n"
                + "    \"120119\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"蓟州区\",\n"
                + "        \"id\":\"120119\"\n"
                + "    },\n"
                + "    \"310101\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"黄浦区\",\n"
                + "        \"id\":\"310101\"\n"
                + "    },\n"
                + "    \"310104\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"徐汇区\",\n"
                + "        \"id\":\"310104\"\n"
                + "    },\n"
                + "    \"310105\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"长宁区\",\n"
                + "        \"id\":\"310105\"\n"
                + "    },\n"
                + "    \"310106\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"静安区\",\n"
                + "        \"id\":\"310106\"\n"
                + "    },\n"
                + "    \"310107\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"普陀区\",\n"
                + "        \"id\":\"310107\"\n"
                + "    },\n"
                + "    \"310109\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"虹口区\",\n"
                + "        \"id\":\"310109\"\n"
                + "    },\n"
                + "    \"310110\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"杨浦区\",\n"
                + "        \"id\":\"310110\"\n"
                + "    },\n"
                + "    \"310112\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"闵行区\",\n"
                + "        \"id\":\"310112\"\n"
                + "    },\n"
                + "    \"310113\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"宝山区\",\n"
                + "        \"id\":\"310113\"\n"
                + "    },\n"
                + "    \"310114\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"嘉定区\",\n"
                + "        \"id\":\"310114\"\n"
                + "    },\n"
                + "    \"310115\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"浦东新区\",\n"
                + "        \"id\":\"310115\"\n"
                + "    },\n"
                + "    \"310116\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"金山区\",\n"
                + "        \"id\":\"310116\"\n"
                + "    },\n"
                + "    \"310117\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"松江区\",\n"
                + "        \"id\":\"310117\"\n"
                + "    },\n"
                + "    \"310118\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"青浦区\",\n"
                + "        \"id\":\"310118\"\n"
                + "    },\n"
                + "    \"310120\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"奉贤区\",\n"
                + "        \"id\":\"310120\"\n"
                + "    },\n"
                + "    \"310151\":{\n"
                + "        \"city\":\"市辖区\",\n"
                + "        \"name\":\"崇明区\",\n"
                + "        \"id\":\"310151\"\n"
                + "    },\n"
                + "    \"330101\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"330101\"\n"
                + "    },\n"
                + "    \"330102\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"上城区\",\n"
                + "        \"id\":\"330102\"\n"
                + "    },\n"
                + "    \"330103\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"下城区\",\n"
                + "        \"id\":\"330103\"\n"
                + "    },\n"
                + "    \"330104\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"江干区\",\n"
                + "        \"id\":\"330104\"\n"
                + "    },\n"
                + "    \"330105\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"拱墅区\",\n"
                + "        \"id\":\"330105\"\n"
                + "    },\n"
                + "    \"330106\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"西湖区\",\n"
                + "        \"id\":\"330106\"\n"
                + "    },\n"
                + "    \"330108\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"滨江区\",\n"
                + "        \"id\":\"330108\"\n"
                + "    },\n"
                + "    \"330109\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"萧山区\",\n"
                + "        \"id\":\"330109\"\n"
                + "    },\n"
                + "    \"330110\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"余杭区\",\n"
                + "        \"id\":\"330110\"\n"
                + "    },\n"
                + "    \"330111\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"富阳区\",\n"
                + "        \"id\":\"330111\"\n"
                + "    },\n"
                + "    \"330122\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"桐庐县\",\n"
                + "        \"id\":\"330122\"\n"
                + "    },\n"
                + "    \"330127\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"淳安县\",\n"
                + "        \"id\":\"330127\"\n"
                + "    },\n"
                + "    \"330182\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"建德市\",\n"
                + "        \"id\":\"330182\"\n"
                + "    },\n"
                + "    \"330185\":{\n"
                + "        \"city\":\"杭州市\",\n"
                + "        \"name\":\"临安市\",\n"
                + "        \"id\":\"330185\"\n"
                + "    },\n"
                + "    \"330201\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"330201\"\n"
                + "    },\n"
                + "    \"330203\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"海曙区\",\n"
                + "        \"id\":\"330203\"\n"
                + "    },\n"
                + "    \"330204\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"江东区\",\n"
                + "        \"id\":\"330204\"\n"
                + "    },\n"
                + "    \"330205\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"江北区\",\n"
                + "        \"id\":\"330205\"\n"
                + "    },\n"
                + "    \"330206\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"北仑区\",\n"
                + "        \"id\":\"330206\"\n"
                + "    },\n"
                + "    \"330211\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"镇海区\",\n"
                + "        \"id\":\"330211\"\n"
                + "    },\n"
                + "    \"330212\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"鄞州区\",\n"
                + "        \"id\":\"330212\"\n"
                + "    },\n"
                + "    \"330225\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"象山县\",\n"
                + "        \"id\":\"330225\"\n"
                + "    },\n"
                + "    \"330226\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"宁海县\",\n"
                + "        \"id\":\"330226\"\n"
                + "    },\n"
                + "    \"330281\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"余姚市\",\n"
                + "        \"id\":\"330281\"\n"
                + "    },\n"
                + "    \"330282\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"慈溪市\",\n"
                + "        \"id\":\"330282\"\n"
                + "    },\n"
                + "    \"330283\":{\n"
                + "        \"city\":\"宁波市\",\n"
                + "        \"name\":\"奉化市\",\n"
                + "        \"id\":\"330283\"\n"
                + "    },\n"
                + "    \"330301\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"330301\"\n"
                + "    },\n"
                + "    \"330302\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"鹿城区\",\n"
                + "        \"id\":\"330302\"\n"
                + "    },\n"
                + "    \"330303\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"龙湾区\",\n"
                + "        \"id\":\"330303\"\n"
                + "    },\n"
                + "    \"330304\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"瓯海区\",\n"
                + "        \"id\":\"330304\"\n"
                + "    },\n"
                + "    \"330305\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"洞头区\",\n"
                + "        \"id\":\"330305\"\n"
                + "    },\n"
                + "    \"330324\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"永嘉县\",\n"
                + "        \"id\":\"330324\"\n"
                + "    },\n"
                + "    \"330326\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"平阳县\",\n"
                + "        \"id\":\"330326\"\n"
                + "    },\n"
                + "    \"330327\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"苍南县\",\n"
                + "        \"id\":\"330327\"\n"
                + "    },\n"
                + "    \"330328\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"文成县\",\n"
                + "        \"id\":\"330328\"\n"
                + "    },\n"
                + "    \"330329\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"泰顺县\",\n"
                + "        \"id\":\"330329\"\n"
                + "    },\n"
                + "    \"330381\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"瑞安市\",\n"
                + "        \"id\":\"330381\"\n"
                + "    },\n"
                + "    \"330382\":{\n"
                + "        \"city\":\"温州市\",\n"
                + "        \"name\":\"乐清市\",\n"
                + "        \"id\":\"330382\"\n"
                + "    },\n"
                + "    \"330401\":{\n"
                + "        \"city\":\"嘉兴市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"330401\"\n"
                + "    },\n"
                + "    \"330402\":{\n"
                + "        \"city\":\"嘉兴市\",\n"
                + "        \"name\":\"南湖区\",\n"
                + "        \"id\":\"330402\"\n"
                + "    },\n"
                + "    \"330411\":{\n"
                + "        \"city\":\"嘉兴市\",\n"
                + "        \"name\":\"秀洲区\",\n"
                + "        \"id\":\"330411\"\n"
                + "    },\n"
                + "    \"330421\":{\n"
                + "        \"city\":\"嘉兴市\",\n"
                + "        \"name\":\"嘉善县\",\n"
                + "        \"id\":\"330421\"\n"
                + "    },\n"
                + "    \"330424\":{\n"
                + "        \"city\":\"嘉兴市\",\n"
                + "        \"name\":\"海盐县\",\n"
                + "        \"id\":\"330424\"\n"
                + "    },\n"
                + "    \"330481\":{\n"
                + "        \"city\":\"嘉兴市\",\n"
                + "        \"name\":\"海宁市\",\n"
                + "        \"id\":\"330481\"\n"
                + "    },\n"
                + "    \"330482\":{\n"
                + "        \"city\":\"嘉兴市\",\n"
                + "        \"name\":\"平湖市\",\n"
                + "        \"id\":\"330482\"\n"
                + "    },\n"
                + "    \"330483\":{\n"
                + "        \"city\":\"嘉兴市\",\n"
                + "        \"name\":\"桐乡市\",\n"
                + "        \"id\":\"330483\"\n"
                + "    },\n"
                + "    \"330501\":{\n"
                + "        \"city\":\"湖州市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"330501\"\n"
                + "    },\n"
                + "    \"330502\":{\n"
                + "        \"city\":\"湖州市\",\n"
                + "        \"name\":\"吴兴区\",\n"
                + "        \"id\":\"330502\"\n"
                + "    },\n"
                + "    \"330503\":{\n"
                + "        \"city\":\"湖州市\",\n"
                + "        \"name\":\"南浔区\",\n"
                + "        \"id\":\"330503\"\n"
                + "    },\n"
                + "    \"330521\":{\n"
                + "        \"city\":\"湖州市\",\n"
                + "        \"name\":\"德清县\",\n"
                + "        \"id\":\"330521\"\n"
                + "    },\n"
                + "    \"330522\":{\n"
                + "        \"city\":\"湖州市\",\n"
                + "        \"name\":\"长兴县\",\n"
                + "        \"id\":\"330522\"\n"
                + "    },\n"
                + "    \"330523\":{\n"
                + "        \"city\":\"湖州市\",\n"
                + "        \"name\":\"安吉县\",\n"
                + "        \"id\":\"330523\"\n"
                + "    },\n"
                + "    \"330601\":{\n"
                + "        \"city\":\"绍兴市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"330601\"\n"
                + "    },\n"
                + "    \"330602\":{\n"
                + "        \"city\":\"绍兴市\",\n"
                + "        \"name\":\"越城区\",\n"
                + "        \"id\":\"330602\"\n"
                + "    },\n"
                + "    \"330603\":{\n"
                + "        \"city\":\"绍兴市\",\n"
                + "        \"name\":\"柯桥区\",\n"
                + "        \"id\":\"330603\"\n"
                + "    },\n"
                + "    \"330604\":{\n"
                + "        \"city\":\"绍兴市\",\n"
                + "        \"name\":\"上虞区\",\n"
                + "        \"id\":\"330604\"\n"
                + "    },\n"
                + "    \"330624\":{\n"
                + "        \"city\":\"绍兴市\",\n"
                + "        \"name\":\"新昌县\",\n"
                + "        \"id\":\"330624\"\n"
                + "    },\n"
                + "    \"330681\":{\n"
                + "        \"city\":\"绍兴市\",\n"
                + "        \"name\":\"诸暨市\",\n"
                + "        \"id\":\"330681\"\n"
                + "    },\n"
                + "    \"330683\":{\n"
                + "        \"city\":\"绍兴市\",\n"
                + "        \"name\":\"嵊州市\",\n"
                + "        \"id\":\"330683\"\n"
                + "    },\n"
                + "    \"330701\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"330701\"\n"
                + "    },\n"
                + "    \"330702\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"婺城区\",\n"
                + "        \"id\":\"330702\"\n"
                + "    },\n"
                + "    \"330703\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"金东区\",\n"
                + "        \"id\":\"330703\"\n"
                + "    },\n"
                + "    \"330723\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"武义县\",\n"
                + "        \"id\":\"330723\"\n"
                + "    },\n"
                + "    \"330726\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"浦江县\",\n"
                + "        \"id\":\"330726\"\n"
                + "    },\n"
                + "    \"330727\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"磐安县\",\n"
                + "        \"id\":\"330727\"\n"
                + "    },\n"
                + "    \"330781\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"兰溪市\",\n"
                + "        \"id\":\"330781\"\n"
                + "    },\n"
                + "    \"330782\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"义乌市\",\n"
                + "        \"id\":\"330782\"\n"
                + "    },\n"
                + "    \"330783\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"东阳市\",\n"
                + "        \"id\":\"330783\"\n"
                + "    },\n"
                + "    \"330784\":{\n"
                + "        \"city\":\"金华市\",\n"
                + "        \"name\":\"永康市\",\n"
                + "        \"id\":\"330784\"\n"
                + "    },\n"
                + "    \"330801\":{\n"
                + "        \"city\":\"衢州市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"330801\"\n"
                + "    },\n"
                + "    \"330802\":{\n"
                + "        \"city\":\"衢州市\",\n"
                + "        \"name\":\"柯城区\",\n"
                + "        \"id\":\"330802\"\n"
                + "    },\n"
                + "    \"330803\":{\n"
                + "        \"city\":\"衢州市\",\n"
                + "        \"name\":\"衢江区\",\n"
                + "        \"id\":\"330803\"\n"
                + "    },\n"
                + "    \"330822\":{\n"
                + "        \"city\":\"衢州市\",\n"
                + "        \"name\":\"常山县\",\n"
                + "        \"id\":\"330822\"\n"
                + "    },\n"
                + "    \"330824\":{\n"
                + "        \"city\":\"衢州市\",\n"
                + "        \"name\":\"开化县\",\n"
                + "        \"id\":\"330824\"\n"
                + "    },\n"
                + "    \"330825\":{\n"
                + "        \"city\":\"衢州市\",\n"
                + "        \"name\":\"龙游县\",\n"
                + "        \"id\":\"330825\"\n"
                + "    },\n"
                + "    \"330881\":{\n"
                + "        \"city\":\"衢州市\",\n"
                + "        \"name\":\"江山市\",\n"
                + "        \"id\":\"330881\"\n"
                + "    },\n"
                + "    \"330901\":{\n"
                + "        \"city\":\"舟山市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"330901\"\n"
                + "    },\n"
                + "    \"330902\":{\n"
                + "        \"city\":\"舟山市\",\n"
                + "        \"name\":\"定海区\",\n"
                + "        \"id\":\"330902\"\n"
                + "    },\n"
                + "    \"330903\":{\n"
                + "        \"city\":\"舟山市\",\n"
                + "        \"name\":\"普陀区\",\n"
                + "        \"id\":\"330903\"\n"
                + "    },\n"
                + "    \"330921\":{\n"
                + "        \"city\":\"舟山市\",\n"
                + "        \"name\":\"岱山县\",\n"
                + "        \"id\":\"330921\"\n"
                + "    },\n"
                + "    \"330922\":{\n"
                + "        \"city\":\"舟山市\",\n"
                + "        \"name\":\"嵊泗县\",\n"
                + "        \"id\":\"330922\"\n"
                + "    },\n"
                + "    \"331001\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"331001\"\n"
                + "    },\n"
                + "    \"331002\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"椒江区\",\n"
                + "        \"id\":\"331002\"\n"
                + "    },\n"
                + "    \"331003\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"黄岩区\",\n"
                + "        \"id\":\"331003\"\n"
                + "    },\n"
                + "    \"331004\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"路桥区\",\n"
                + "        \"id\":\"331004\"\n"
                + "    },\n"
                + "    \"331021\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"玉环县\",\n"
                + "        \"id\":\"331021\"\n"
                + "    },\n"
                + "    \"331022\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"三门县\",\n"
                + "        \"id\":\"331022\"\n"
                + "    },\n"
                + "    \"331023\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"天台县\",\n"
                + "        \"id\":\"331023\"\n"
                + "    },\n"
                + "    \"331024\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"仙居县\",\n"
                + "        \"id\":\"331024\"\n"
                + "    },\n"
                + "    \"331081\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"温岭市\",\n"
                + "        \"id\":\"331081\"\n"
                + "    },\n"
                + "    \"331082\":{\n"
                + "        \"city\":\"台州市\",\n"
                + "        \"name\":\"临海市\",\n"
                + "        \"id\":\"331082\"\n"
                + "    },\n"
                + "    \"331101\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"市辖区\",\n"
                + "        \"id\":\"331101\"\n"
                + "    },\n"
                + "    \"331102\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"莲都区\",\n"
                + "        \"id\":\"331102\"\n"
                + "    },\n"
                + "    \"331121\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"青田县\",\n"
                + "        \"id\":\"331121\"\n"
                + "    },\n"
                + "    \"331122\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"缙云县\",\n"
                + "        \"id\":\"331122\"\n"
                + "    },\n"
                + "    \"331123\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"遂昌县\",\n"
                + "        \"id\":\"331123\"\n"
                + "    },\n"
                + "    \"331124\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"松阳县\",\n"
                + "        \"id\":\"331124\"\n"
                + "    },\n"
                + "    \"331125\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"云和县\",\n"
                + "        \"id\":\"331125\"\n"
                + "    },\n"
                + "    \"331126\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"庆元县\",\n"
                + "        \"id\":\"331126\"\n"
                + "    },\n"
                + "    \"331127\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"景宁畲族自治县\",\n"
                + "        \"id\":\"331127\"\n"
                + "    },\n"
                + "    \"331181\":{\n"
                + "        \"city\":\"丽水市\",\n"
                + "        \"name\":\"龙泉市\",\n"
                + "        \"id\":\"331181\"\n"
                + "    }\n"
                + "}";
        JSONObject areaMap = JSONObject.parseObject(str);
        final List<Area> areas = new LinkedList<>();
        areaMap.keySet().forEach(key -> {
            JSONObject areaJson = areaMap.getJSONObject(key);
            String city = areaJson.getString("city");
            String name = areaJson.getString("name");
            Long code = Long.valueOf(areaJson.getString("id"));
            Area area = Area.builder().city(city).name(name).code(code).build();
            areas.add(area);
        });

        Collections.sort(areas);
        System.out.println(JSONObject.toJSONString(areas));

        areas.forEach(area -> {
            List<DataDictionary> citys = dataDictionaryService.listByFilter(
                    DataDictionary.builder().valueStr(area.getCity()).build(), 1, 1);
            DataDictionary city = citys.get(0);

            DataDictionary areaData = DataDictionary.builder()
                    .uplink(city.getId())
                    .ctrlId(1L)
                    .level(3)
                    .status((short) 1)
                    .remark(area.getName())
                    .valueStr(area.getName())
                    .globalSeq(area.getCode())
                    .build();
            dataDictionaryService.upsert(areaData);
        });
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class Area implements Comparable<Area> {

        private String city;

        private String name;

        private Long code;

        @Override
        public int compareTo(Area o) {
            return (int) (this.code - o.getCode());
        }
    }
}
