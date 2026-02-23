package com.priyan.aggregator.internal.parameter;

import com.priyan.aggregator.internal.parameter.AggregatorParameterGroup;

import java.util.concurrent.TimeUnit;

import org.mule.sdk.api.annotation.param.Optional;
import org.mule.sdk.api.annotation.Expression;
import org.mule.sdk.api.annotation.param.display.DisplayName;
import org.mule.sdk.api.annotation.param.display.Summary;
import org.mule.sdk.api.annotation.param.Parameter;
import org.mule.sdk.api.meta.ExpressionSupport;


public class TimeoutContainingAggregatorParameterGroup extends AggregatorParameterGroup {

    @Parameter
    @Expression(ExpressionSupport.NOT_SUPPORTED)
    @Optional(defaultValue = "-1")
    private int timeout;

    @Parameter
    @Expression(ExpressionSupport.NOT_SUPPORTED)
    @Optional(defaultValue = "SECONDS")
    private TimeUnit timeoutUnit;

    public Integer getTimeout() {
        return Integer.valueOf(this.timeout);
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout.intValue();
    }

    public TimeUnit getTimeoutUnit() {
        return this.timeoutUnit;
    }

    public void setTimeoutUnit(TimeUnit timeoutUnit) {
        this.timeoutUnit = timeoutUnit;
    }
}