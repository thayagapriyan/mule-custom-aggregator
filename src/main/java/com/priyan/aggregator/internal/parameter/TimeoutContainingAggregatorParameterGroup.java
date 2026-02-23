package com.priyan.aggregator.internal.parameter;


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