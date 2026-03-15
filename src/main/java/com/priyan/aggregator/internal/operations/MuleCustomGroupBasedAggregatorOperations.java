package com.priyan.aggregator.internal.operations;

import com.priyan.aggregator.internal.config.MuleCustomAggregatorConfiguration;
import com.priyan.aggregator.internal.connection.HttpConnectionWrapper;
import com.priyan.aggregator.internal.parameter.HttpRequestParameterGroup;
import com.priyan.aggregator.internal.parameter.MuleCustomGroupBasedAggregatorParameterGroup;
import com.priyan.aggregator.internal.routes.AggregationCompleteRoute;
import com.priyan.aggregator.internal.routes.IncrementalAggregationRoute;

import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.sdk.api.annotation.Alias;
import org.mule.sdk.api.annotation.param.Connection;
import org.mule.sdk.api.annotation.param.Config;
import org.mule.sdk.api.annotation.param.Optional;
import org.mule.sdk.api.annotation.param.ParameterGroup;
import org.mule.sdk.api.annotation.param.display.DisplayName;
import org.mule.sdk.api.annotation.param.display.Summary;
import org.mule.sdk.api.runtime.process.RouterCompletionCallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MuleCustomGroupBasedAggregatorOperations {

  private static final Logger LOGGER = LoggerFactory.getLogger(MuleCustomGroupBasedAggregatorOperations.class);

  @DisplayName("Custom Group Based Aggregator")
  @Summary("Custom Group Based Aggregator that aggregates messages based on a group ID and group size, with HTTP request capability.")
  @Alias("customGroupBasedAggregator")
  public void aggregateByGroup(
      @Config MuleCustomAggregatorConfiguration config,
      @Connection HttpConnectionWrapper connection,
      @ParameterGroup(name = "Aggregator Parameters") MuleCustomGroupBasedAggregatorParameterGroup parameters,
      @ParameterGroup(name = "HTTP Request") HttpRequestParameterGroup httpParams,
      @Optional IncrementalAggregationRoute incremental,
      AggregationCompleteRoute onComplete,
      RouterCompletionCallback callback) {

    String id = parameters.getGroupId();
    if (id != null && !id.isEmpty()) {
      LOGGER.info("Received message with groupId: {}", id);

      try {
        HttpResponse httpResponse = executeHttpRequest(connection, httpParams);
        int statusCode = httpResponse.getStatusCode();
        LOGGER.info("HTTP response status: {} for groupId: {}", statusCode, id);

        incremental.getChain().process(
            callback::success,
            (error, previous) -> callback.error(error)
        );
      } catch (Exception e) {
        LOGGER.error("HTTP request failed for groupId: {}", id, e);
        callback.error(e);
      }
    } else {
      LOGGER.warn("Received message with empty groupId. This may lead to unexpected aggregation behavior.");
    }
  }

  private HttpResponse executeHttpRequest(
      HttpConnectionWrapper connection,
      HttpRequestParameterGroup httpParams) throws Exception {

    String fullUrl = connection.getBaseUrl() + normalizePath(httpParams.getRequestPath());

    if (httpParams.getQueryParams() != null && !httpParams.getQueryParams().isEmpty()) {
      StringBuilder queryString = new StringBuilder("?");
      for (Map.Entry<String, String> entry : httpParams.getQueryParams().entrySet()) {
        if (queryString.length() > 1) {
          queryString.append("&");
        }
        queryString.append(entry.getKey()).append("=").append(entry.getValue());
      }
      fullUrl += queryString.toString();
    }

    HttpRequest.HttpRequestBuilder builder = HttpRequest.builder()
        .uri(fullUrl)
        .method(httpParams.getMethod().toUpperCase());

    if (httpParams.getHeaders() != null) {
      for (Map.Entry<String, String> header : httpParams.getHeaders().entrySet()) {
        builder.addHeader(header.getKey(), header.getValue());
      }
    }

    if (httpParams.getBody() != null && !httpParams.getBody().isEmpty()) {
      builder.entity(new ByteArrayHttpEntity(
          httpParams.getBody().getBytes(StandardCharsets.UTF_8)));
    }

    HttpRequest request = builder.build();

    return connection.getHttpClient().send(
        request,
        httpParams.getRequestTimeout(),
        true,
        null
    );
  }

  private String normalizePath(String path) {
    if (path == null || path.isEmpty()) {
      return "";
    }
    return path.startsWith("/") ? path : "/" + path;
  }
}
