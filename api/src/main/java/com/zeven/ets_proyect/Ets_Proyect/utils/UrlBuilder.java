package com.zeven.ets_proyect.Ets_Proyect.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UrlBuilder {

    private final String baseUrl;
    private final Map<String, String> queryParams = new LinkedHashMap<>();
    private final List<String> filterParts = new ArrayList<>();

    public UrlBuilder(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public UrlBuilder addQueryParam(String key, String value) {
        if (value != null && !value.isBlank()) {
            queryParams.put(key, value);
        }
        return this;
    }

    public UrlBuilder addFilter(String filterPart) {
        if (filterPart != null && !filterPart.isBlank()) {
            filterParts.add(filterPart);
        }
        return this;
    }

    public UrlBuilder addFilterKeyValue(String key, String value) {
        if (key != null && value != null && !key.isBlank() && !value.isBlank()) {
            filterParts.add(key + ":" + value);
        }
        return this;
    }

    public String build() {
        StringBuilder url = new StringBuilder(baseUrl);

        if (!filterParts.isEmpty()) {
            queryParams.put("filter", String.join(":", filterParts));
        }

        if (!queryParams.isEmpty()) {
            url.append("?");
            queryParams.forEach((key, value) ->
                    url.append(URLEncoder.encode(key, StandardCharsets.UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(value, StandardCharsets.UTF_8))
                            .append("&")
            );
            url.setLength(url.length() - 1);
        }

        return url.toString();
    }
}
