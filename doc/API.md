# 天气数据API
`http://wthrcdn.etouch.cn/weather_mini?city=上海`

`http://wthrcdn.etouch.cn/weather_mini?citykey==1000`

# 返回Json数据格式
```json
{
  "data": {
    "yesterday": {
      "date": "19日星期五",
      "high": "高温 21℃",
      "fx": "东风",
      "low": "低温 16℃",
      "fl": "<![CDATA[<3级]]>",
      "type": "多云"
    },
    "city": "上海",
    "aqi": "44",
    "forecast": [
      {
        "date": "20日星期六",
        "high": "高温 20℃",
        "fengli": "<![CDATA[<3级]]>",
        "low": "低温 16℃",
        "fengxiang": "西南风",
        "type": "多云"
      },
      {
        "date": "21日星期天",
        "high": "高温 22℃",
        "fengli": "<![CDATA[<3级]]>",
        "low": "低温 17℃",
        "fengxiang": "东风",
        "type": "多云"
      },
      {
        "date": "22日星期一",
        "high": "高温 21℃",
        "fengli": "<![CDATA[<3级]]>",
        "low": "低温 17℃",
        "fengxiang": "东北风",
        "type": "小雨"
      },
      {
        "date": "23日星期二",
        "high": "高温 22℃",
        "fengli": "<![CDATA[<3级]]>",
        "low": "低温 18℃",
        "fengxiang": "西南风",
        "type": "多云"
      },
      {
        "date": "24日星期三",
        "high": "高温 22℃",
        "fengli": "<![CDATA[<3级]]>",
        "low": "低温 17℃",
        "fengxiang": "西南风",
        "type": "晴"
      }
    ],
    "ganmao": "天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。",
    "wendu": "19"
  },
  "status": 1000,
  "desc": "OK"
}
```