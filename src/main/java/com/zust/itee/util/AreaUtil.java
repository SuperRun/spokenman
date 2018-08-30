package com.zust.itee.util;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 区域工具类
 *
 * @author pojun
 */
@Component
@Slf4j
public class AreaUtil {

    private static final String FILE_PATH = "/json/";

    private static final String FILE_NAME = "area.json";

    private JSONObject areaConfig = new JSONObject();

    private JSONObject areaCodeAndNameJson = new JSONObject();

    @PostConstruct
    private void init() {
        try (InputStream is = AreaUtil.class.getResourceAsStream(FILE_PATH + FILE_NAME)) {
            String jsonStr = IOUtils.toString(is);
            areaConfig = JSONObject.parseObject(jsonStr);

            JSONArray provinces = areaConfig.getJSONArray("province");
            for (int i = 0; i < provinces.size(); i++) {
                JSONObject province = provinces.getJSONObject(i);
                String provinceName = province.getString("name");
                String provinceCode = province.getString("zip");
                areaCodeAndNameJson.put(provinceCode, provinceName);

                String provincePre = provinceName + "-";
                JSONArray cities = province.getJSONArray("city");
                if (cities != null && !cities.isEmpty()) {
                    for (int j = 0; j < cities.size(); j++) {
                        JSONObject city = cities.getJSONObject(j);
                        String cityName = city.getString("name");
                        String cityCode = city.getString("zip");
                        areaCodeAndNameJson.put(cityCode, provincePre + cityName);

                        String cityPre = provincePre + cityName + "-";
                        JSONArray counties = city.getJSONArray("county");
                        if (counties != null && !counties.isEmpty()) {
                            for (int k = 0; k < counties.size(); k++) {
                                JSONObject county = counties.getJSONObject(k);
                                String countyName = county.getString("name");
                                String countyCode = county.getString("zip");
                                areaCodeAndNameJson.put(countyCode, cityPre + countyName);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            log.error("==读取区域 json 出错== e:{}", e);
        }
    }

    public String getAreaName(String code) {
        return areaCodeAndNameJson.getString(code);
    }
}
