package xyz.gabear.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xyz.gabear.weather.service.WeatherDataService;
import xyz.gabear.weather.utils.JsonUtil;
import xyz.gabear.weather.vo.WeatherResponse;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * WeatherDataService 实现.
 */
@Service
@Slf4j
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final long TIME_OUT = 1800L; // 1800s

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }

    private WeatherResponse doGetWeather(@NotNull String uri) {
        String strBody = null;

        // 先查缓存，缓存有的取缓存中的数据
        if (stringRedisTemplate.hasKey(uri)) {
            log.info("Redis has data");
            strBody = stringRedisTemplate.opsForValue().get(uri);
        } else {
            // 缓存没有，再调用服务接口来获取
            log.info("Redis don't has data");
            strBody = this.saveWeatherData(uri);
        }

        return JsonUtil.toBean(strBody, WeatherResponse.class);
    }

    @Override
    public void syncDateByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放在缓存
     */
    private String saveWeatherData(String uri) {
        String strBody = null;

        // 调用服务接口来获取
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        // 数据写入缓存
        stringRedisTemplate.opsForValue().set(uri, strBody, TIME_OUT, TimeUnit.SECONDS);

        return strBody;
    }
}
