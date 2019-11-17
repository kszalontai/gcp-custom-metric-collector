package io.github.kszalontai.gcp.metric;

import com.google.monitoring.v3.TimeSeries;

public interface CustomMetric {
    TimeSeries timeSeries();
}
