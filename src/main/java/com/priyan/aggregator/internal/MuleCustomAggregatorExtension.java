package com.priyan.aggregator.internal;

import com.priyan.aggregator.internal.config.MuleCustomAggregatorConfiguration;

import org.mule.sdk.api.annotation.Extension;
import org.mule.sdk.api.annotation.Configurations;
import org.mule.sdk.api.annotation.dsl.xml.Xml;

import org.mule.sdk.api.annotation.JavaVersionSupport;
import org.mule.sdk.api.meta.JavaVersion;

@Xml(prefix = "mule-custom-aggregator")
@Extension(name = "Mule Custom Aggregator")
@Configurations(MuleCustomAggregatorConfiguration.class)
@JavaVersionSupport(JavaVersion.JAVA_17)
public class MuleCustomAggregatorExtension {

}
