package com.priyan.aggregator.internal.connection;

import org.mule.runtime.http.api.client.HttpClient;

public class HttpConnectionWrapper {

    private final HttpClient httpClient;
    private final String baseUrl;

    public HttpConnectionWrapper(HttpClient httpClient, String baseUrl) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void stop() {
        if (httpClient != null) {
            httpClient.stop();
        }
    }
}
