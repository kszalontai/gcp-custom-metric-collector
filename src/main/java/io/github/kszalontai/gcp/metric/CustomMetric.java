package io.github.kszalontai.gcp.metric;

import com.google.monitoring.v3.TimeSeries;

public interface CustomMetric {

    String CUSTOM_METRIC_PREFIX = "custom.googleapis.com/";

    TimeSeries timeSeries();
}
