package com.priyan.aggregator.internal.operations;

import com.priyan.aggregator.internal.config.MuleCustomAggregatorConfiguration;
import com.priyan.aggregator.internal.config.MuleCustomGroupBasedAggregatorParameterGroup;
import com.priyan.aggregator.internal.routes.AggregationCompleteRoute;
import com.priyan.aggregator.internal.routes.IncrementalAggregationRoute;


import org.mule.sdk.api.client.ExtensionsClient;
import org.mule.sdk.api.annotation.param.Config;
import org.mule.sdk.api.annotation.param.Content;
import org.mule.sdk.api.annotation.param.Optional;
import org.mule.sdk.api.annotation.param.display.DisplayName;
import org.mule.sdk.api.annotation.param.display.Summary;
import org.mule.sdk.api.runtime.operation.Result;
import org.mule.sdk.api.runtime.process.RouterCompletionCallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MuleCustomGroupBasedAggregatorOperations {

  private static final Logger LOGGER = LoggerFactory.getLogger(MuleCustomGroupBasedAggregatorOperations.class);

  @DisplayName("Custom Group Based Aggregator")
  @Summary("Custom Group Based Aggregator that aggregates messages based on a group ID and group size, with optional eviction settings.")
  @Alias("customGroupBasedAggregator")
  public void aggregateByGroup(
      @Config MuleCustomAggregatorConfiguration config,
      @ParameterGroup(name = "Aggregator Parameters") MuleCustomGroupBasedAggregatorParameterGroup parameters,
      @Optional IncrementalAggregationRoute incremental,
      AggregationCompleteRoute onComplete,
      RouterCompletionCallback callback) {

        String id=parameters.getGroupId();
        if ( id != null && !id.isEmpty() ) {
            LOGGER.info("Received message with groupId: " + id);
            incremental.getChain().process(callback::success,(error,previous) -> callback.error(error));
        } else {
            LOGGER.warn("Received message with empty groupId. This may lead to unexpected aggregation behavior.");
        }
      }
    }