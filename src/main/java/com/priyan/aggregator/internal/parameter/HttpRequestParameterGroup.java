package com.priyan.aggregator.internal.parameter;

import org.mule.sdk.api.annotation.Expression;
import org.mule.sdk.api.annotation.param.Optional;
import org.mule.sdk.api.annotation.param.Parameter;
import org.mule.sdk.api.annotation.param.display.DisplayName;
import org.mule.sdk.api.annotation.param.display.Summary;
import org.mule.sdk.api.meta.ExpressionSupport;

import java.util.Map;

public class HttpRequestParameterGroup {

    @Parameter
    @DisplayName("Request Path")
    @Summary("Path appended to the base URL (e.g., /resource/123)")
    @Expression(ExpressionSupport.SUPPORTED)
    @Optional(defaultValue = "/")
    private String requestPath;

    @Parameter
    @DisplayName("HTTP Method")
    @Summary("HTTP method: GET, POST, PUT, DELETE, PATCH")
    @Expression(ExpressionSupport.SUPPORTED)
    @Optional(defaultValue = "POST")
    private String method;

    @Parameter
    @DisplayName("Headers")
    @Summary("HTTP request headers as key-value pairs")
    @Expression(ExpressionSupport.SUPPORTED)
    @Optional
    private Map<String, String> headers;

    @Parameter
    @DisplayName("Query Parameters")
    @Summary("URL query parameters as key-value pairs")
    @Expression(ExpressionSupport.SUPPORTED)
    @Optional
    private Map<String, String> queryParams;

    @Parameter
    @DisplayName("Request Body")
    @Summary("HTTP request body content")
    @Expression(ExpressionSupport.SUPPORTED)
    @Optional
    private String body;

    @Parameter
    @DisplayName("Request Timeout")
    @Summary("Timeout in milliseconds for the HTTP request")
    @Expression(ExpressionSupport.SUPPORTED)
    @Optional(defaultValue = "30000")
    private int requestTimeout;

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }
}
