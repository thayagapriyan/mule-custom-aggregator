package com.priyan.aggregator.internal;

import org.mule.sdk.api.annotation.Extension;
import org.mule.sdk.api.annotation.Configurations;
import org.mule.sdk.api.annotation.dsl.xml.Xml;

@Xml(prefix = "mule-custom-aggregator")
@Extension(name = "Mule Custom Aggregator")
@Configurations(MuleCustomAggregatorConfiguration.class)
public class MuleCustomAggregatorExtension {

}
