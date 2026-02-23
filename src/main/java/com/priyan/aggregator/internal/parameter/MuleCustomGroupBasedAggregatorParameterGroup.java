package com.priyan.aggregator.internal.parameter;

import java.util.concurrent.TimeUnit;

import org.mule.sdk.api.annotation.param.Optional;
import org.mule.sdk.api.annotation.param.Expression;
import org.mule.sdk.api.annotation.param.display.DisplayName;
import org.mule.sdk.api.annotation.param.display.Summary;
import org.mule.sdk.api.annotation.param.Parameter;


public class MuleCustomGroupBasedAggregatorParameterGroup extends TimeoutContainingAggregatorParameterGroup {

  @Parameter
  @Expression(ExpressionSupport.REQUIRED)
  @Optional(defaultValue = "#[correlationId]")
  private String groupId;

  @Parameter
  @Expression(ExpressionSupport.REQUIRED)
  @Optional(defaultValue = "#[itemSequenceInfo.sequenceSize]")
  private Integer groupSize;

  @Parameter
  @Expression(ExpressionSupport.REQUIRED)
  @Optional(defaultValue = "180")
  private int evictionTime;

  @Parameter
  @Expression(ExpressionSupport.REQUIRED)
  @Optional(defaultValue = "SECONDS")
  private TimeUnit evictionTimeUnit;

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public Integer getEvictionTime() {
        return Integer.valueOf(this.evictionTime);
    }

    public void setEvictionTime(Integer evictionTime) {
        this.evictionTime = evictionTime.intValue();
    }

    public void setEvictionTime(int evictionTime) {
        this.evictionTime = evictionTime;
    }

    public TimeUnit getEvictionTimeUnit() {
        return evictionTimeUnit;
    }

    public void setEvictionTimeUnit(TimeUnit evictionTimeUnit) {
        this.evictionTimeUnit = evictionTimeUnit;
    }


}