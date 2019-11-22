package io.github.kszalontai.gcp.metric.gauge;

import static java.util.Objects.requireNonNull;

import com.google.api.Metric;
import com.google.api.MonitoredResource;
import com.google.common.collect.Lists;
import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.TimeInterval;
import com.google.monitoring.v3.TimeSeries;
import com.google.protobuf.util.Timestamps;
import io.github.kszalontai.gcp.metric.CustomMetric;
import io.github.kszalontai.gcp.metric.Label;
import io.github.kszalontai.gcp.metric.value.Value;
import java.time.Instant;

public final class GaugeMetric implements CustomMetric {

  private final io.github.kszalontai.gcp.metric.Metric metric;
  private final Value value;
  private final Instant at;

  GaugeMetric(GaugeMetricBuilder builder) {
    requireNonNull(builder.metric);
    requireNonNull(builder.value);
    requireNonNull(builder.at);
    this.metric = builder.metric;
    this.value = builder.value;
    this.at = builder.at;
  }

  @Override
  public TimeSeries timeSeries() {
    return TimeSeries.newBuilder()
        .setMetric(metric())
        .setResource(resource())
        .addAllPoints(Lists.newArrayList(point()))
        .build();
  }

  private Metric metric() {
    return Metric.newBuilder()
        .setType(CUSTOM_METRIC_PREFIX + metric.name())
        .putAllLabels(Label.asMap(metric.labels()))
        .build();
  }

  private MonitoredResource resource() {
    return MonitoredResource.newBuilder()
        .setType(metric.monitoredResource().type())
        .putAllLabels(Label.asMap(metric.monitoredResource().labels()))
        .build();
  }

  private Point point() {
    return Point.newBuilder()
        .setValue(value.typedValue())
        .setInterval(timeInterval())
        .build();
  }

  private TimeInterval timeInterval() {
    return TimeInterval.newBuilder()
        .setEndTime(Timestamps.fromMillis(at.toEpochMilli()))
        .build();
  }

}
