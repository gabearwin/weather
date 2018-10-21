package xyz.gabear.weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.gabear.weather.service.WeatherDataService;
import xyz.gabear.weather.service.WeatherReportService;
import xyz.gabear.weather.vo.Weather;
import xyz.gabear.weather.vo.WeatherResponse;

/**
 * Weather Report Service.
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {
    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
        return resp.getData();
    }

}
