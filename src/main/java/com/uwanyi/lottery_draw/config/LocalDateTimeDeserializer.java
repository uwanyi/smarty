package com.uwanyi.lottery_draw.config;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
/**
 * created by jasonwang
 * on 2020/8/1 11:15
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String dateStr = parser.getText();
        DateTime dateTime;
        try {
            dateTime = DateUtil.parse(dateStr);
        } catch (Exception e) {
            dateTime = DateUtil.parseDateTime(dateStr.replaceAll("T", " "));
        }
        Date date = dateTime.toJdkDate();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    @Override
    public Class<?> handledType() {
        // 关键
        return LocalDateTime.class;
    }
}
