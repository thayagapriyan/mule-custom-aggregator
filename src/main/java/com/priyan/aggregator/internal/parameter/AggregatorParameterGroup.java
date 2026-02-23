package com.priyan.aggregator.internal.parameter;

import org.mule.sdk.api.annotation.param.Content;
import org.mule.sdk.api.annotation.Expression;
import org.mule.sdk.api.annotation.param.Optional;
import org.mule.sdk.api.annotation.param.Parameter;
import org.mule.sdk.api.annotation.param.display.DisplayName;
import org.mule.sdk.api.annotation.param.display.Summary;
import org.mule.sdk.api.meta.ExpressionSupport;
import org.mule.runtime.api.metadata.TypedValue;


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