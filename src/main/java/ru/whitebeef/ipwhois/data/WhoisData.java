package ru.whitebeef.ipwhois.data;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class WhoisData {
    private boolean success;
    private String ip;
    private String continent;
    private String country;
    private String capital;
    private String emoji;
    private String city;
    private String timezone;
    private String organization;
    private String domain;
    private String region;

    public static WhoisData fromJson(JSONObject jsonObject) {
        WhoisDataBuilder builder = WhoisData.builder();
        builder.success(false);

        if (jsonObject.has("success")) {
            builder.success(jsonObject.getBoolean("success"));
        }

        if (jsonObject.has("ip")) {
            builder.ip(jsonObject.getString("ip"));
        }
        if (jsonObject.has("continent")) {
            builder.continent(jsonObject.getString("continent"));
        }
        if (jsonObject.has("country")) {
            builder.country(jsonObject.getString("country"));
        }
        if (jsonObject.has("capital")) {
            builder.capital(jsonObject.getString("capital"));
        }

        if (jsonObject.has("flag")) {
            JSONObject flag = jsonObject.getJSONObject("flag");
            if (flag.has("emoji")) {
                builder.emoji(flag.getString("emoji"));
            }
        }

        if (jsonObject.has("city")) {
            builder.city(jsonObject.getString("city"));
        }


        if (jsonObject.has("timezone")) {
            StringBuilder timezoneBuilder = new StringBuilder();
            JSONObject timezone = jsonObject.getJSONObject("timezone");
            if (timezone.has("abbr")) {
                timezoneBuilder.append(timezone.getString("abbr"));
            }
            if (timezone.has("id")) {
                timezoneBuilder.append(" ").append(timezone.getString("id"));
            }
            if (timezone.has("utc")) {
                timezoneBuilder.append(" ").append(timezone.getString("utc"));
            }
            builder.timezone(timezoneBuilder.toString());
        }

        if (jsonObject.has("connection")) {
            JSONObject connection = jsonObject.getJSONObject("connection");
            if (connection.has("org")) {
                builder.organization(connection.getString("org"));
            }
            if (connection.has("domain")) {
                builder.domain(connection.getString("domain"));
            }
        }
        if (jsonObject.has("region")) {
            builder.region(jsonObject.getString("region"));
        }

        return builder.build();
    }
}
