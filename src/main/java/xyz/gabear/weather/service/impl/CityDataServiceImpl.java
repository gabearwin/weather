package xyz.gabear.weather.service.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import xyz.gabear.weather.service.CityDataService;
import xyz.gabear.weather.utils.XmlBuilder;
import xyz.gabear.weather.vo.City;
import xyz.gabear.weather.vo.CityList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * City Data Service.
 */
@Service
public class CityDataServiceImpl implements CityDataService {

    @Override
    public List<City> listCity() throws Exception {
        // 读取XML文件
        Resource resource = new ClassPathResource("static/cityList.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuilder builder = new StringBuilder();
        String line = "";

        while ((line = br.readLine()) != null) {
            builder.append(line);
        }

        br.close();

        // XML转为Java对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToOject(CityList.class, builder.toString());
        return cityList.getCityList();
    }

}
