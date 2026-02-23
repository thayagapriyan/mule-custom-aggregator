package com.priyan.aggregator.internal.parameter;

public class AggregatorParameterGroup {

    @Parameter
    @Expression(ExpressionSupport.SUPPORTED)
    @Content
    @Optional(defaultValue = "#[payload]")
    private TypedValue<Object> content;

    public TypedValue getContent() {
        return content;
    }

    public void setContent(TypedValue content) {
        this.content = content;
    }


   
}