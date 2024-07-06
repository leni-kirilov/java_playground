package com.kirilov.interview.revolut;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TinyUrlService {

    private Map<String, String> urlMap = new ConcurrentHashMap<>();

    public String shorten(String originalUrl) {
        return shorten(originalUrl, null);
    }

    public  String shorten(String originalUrl, String customName) {
        String result = "";

        //handles custom name
        if (customName != null) {
            if (urlMap.containsKey(customName)) { //TODO use putIfAbsent instead of 2 calls to the map
                throw new IllegalArgumentException("CustomName " + customName + " is already taken");
            }

            result = customName;
        } else {
            result = UUID.randomUUID().toString();
        }

        //persists
        urlMap.putIfAbsent(result, originalUrl);

        return result;
    }

    public String expand(String url) {
        if (!urlMap.containsKey(url)) {
            throw new IllegalArgumentException("No such shortened url found");
        }

        return "Redirect to " + urlMap.get(url);
    }
}
