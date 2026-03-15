package com.priyan.aggregator.internal.connection;

import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.sdk.api.annotation.param.Parameter;
import org.mule.sdk.api.annotation.param.Optional;
import org.mule.sdk.api.annotation.param.display.DisplayName;
import org.mule.sdk.api.annotation.param.display.Summary;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.sdk.api.connectivity.CachedConnectionProvider;
import org.mule.sdk.api.connectivity.ConnectionValidationResult;

import javax.inject.Inject;

@DisplayName("HTTP Connection")
public class HttpConnectionProvider implements CachedConnectionProvider<HttpConnectionWrapper> {

    @Inject
    private HttpService httpService;

    @Parameter
    @DisplayName("Host")
    @Summary("HTTP server host (e.g., api.example.com)")
    private String host;

    @Parameter
    @DisplayName("Port")
    @Summary("HTTP server port")
    @Optional(defaultValue = "443")
    private int port;

    @Parameter
    @DisplayName("Base Path")
    @Summary("Base path prepended to all request paths (e.g., /api/v1)")
    @Optional(defaultValue = "/")
    private String basePath;

    @Parameter
    @DisplayName("Protocol")
    @Summary("HTTP or HTTPS")
    @Optional(defaultValue = "HTTPS")
    private String protocol;

    @Override
    public HttpConnectionWrapper connect() throws ConnectionException {
        try {
            HttpClientConfiguration clientConfig = new HttpClientConfiguration.Builder()
                .setName("custom-aggregator-http-client")
                .build();

            HttpClient client = httpService.getClientFactory().create(clientConfig);
            client.start();

            String normalizedBasePath = basePath.startsWith("/") ? basePath : "/" + basePath;
            String baseUrl = protocol.toLowerCase() + "://" + host + ":" + port + normalizedBasePath;

            return new HttpConnectionWrapper(client, baseUrl);
        } catch (Exception e) {
            throw new ConnectionException("Failed to create HTTP client", e);
        }
    }

    @Override
    public void disconnect(HttpConnectionWrapper connection) {
        connection.stop();
    }

    @Override
    public ConnectionValidationResult validate(HttpConnectionWrapper connection) {
        if (connection.getHttpClient() != null) {
            return ConnectionValidationResult.success();
        }
        return ConnectionValidationResult.failure("HTTP client is null", new Exception("Client not initialized"));
    }
}
