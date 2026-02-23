package com.priyan.aggregator.internal.config;

import com.priyan.aggregator.internal.operations.MuleCustomGroupBasedAggregatorOperations;

import org.mule.sdk.api.annotation.Operations;
import org.mule.sdk.api.annotation.param.Parameter;
import org.mule.sdk.api.annotation.param.Optional;
import org.mule.sdk.api.annotation.param.display.DisplayName;
import org.mule.sdk.api.annotation.param.display.Summary;

@Operations({MuleCustomGroupBasedAggregatorOperations.class})
public class MuleCustomAggregatorConfiguration {

  @DisplayName("Aggregator Name")
  @Summary("Unique identifier for this aggregator instance")
  @Parameter
  private String configId;

}
