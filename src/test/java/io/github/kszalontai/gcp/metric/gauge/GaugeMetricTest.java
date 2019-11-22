package io.github.kszalontai.gcp.metric.gauge;

import com.google.api.MonitoredResource;
import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.TimeInterval;
import com.google.monitoring.v3.TimeSeries;
import com.google.monitoring.v3.TypedValue;
import com.google.monitoring.v3.TypedValue.Builder;
import com.google.protobuf.util.Timestamps;
import io.github.kszalontai.gcp.metric.CustomMetric;
import io.github.kszalontai.gcp.metric.Label;
import io.github.kszalontai.gcp.metric.Metric;
import io.github.kszalontai.gcp.metric.Resource;
import io.github.kszalontai.gcp.metric.value.BoolValue;
import io.github.kszalontai.gcp.metric.value.DoubleValue;
import io.github.kszalontai.gcp.metric.value.LongValue;
import java.time.Instant;
import org.junit.Assert;
import org.junit.Test;

public class GaugeMetricTest {

  private static final Instant CUSTOM_METRIC_AT = Instant.ofEpochMilli(123L);
  private static final String CUSTOM_METRIC_NAME = "testMetricName";
  private static final String RESOURCE_TYPE = "test";
  private static final Label LABEL = Label.of("labelKey", "labelValue");
  private static final Label METRIC_LABEL = Label.of("metricLabelKey", "metricLabelValue");
  private static final Metric CUSTOM_METRIC = Metric.name(CUSTOM_METRIC_NAME)
      .monitoredResource(
          Resource.type(RESOURCE_TYPE)
              .label(LABEL)
              .build())
      .label(METRIC_LABEL)
      .build();

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_metric_null() {
    Metric.gauge(null).value(BoolValue.TRUE).at(CUSTOM_METRIC_AT).build();
  }

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_value_null() {
    Metric.gauge(CUSTOM_METRIC).value((BoolValue) null).at(CUSTOM_METRIC_AT).build();
  }

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_timestamp_null() {
    Metric.gauge(CUSTOM_METRIC).value(BoolValue.TRUE).at(null).build();
  }

  @Test
  public void assert_timeSeries_equals_for_bool_value() {
    Builder value = TypedValue.newBuilder().setBoolValue(true);
    TimeSeries expectedTimeSeries = expected(value);
    GaugeMetric metric = Metric.gauge(CUSTOM_METRIC).value(BoolValue.TRUE).at(CUSTOM_METRIC_AT)
        .build();

    Assert.assertNotNull(metric);
    Assert.assertEquals(expectedTimeSeries, metric.timeSeries());
  }

  @Test
  public void assert_timeSeries_equals_for_double_value() {
    double value = 42d;
    Builder valueBuilder = TypedValue.newBuilder().setDoubleValue(value);
    TimeSeries expectedTimeSeries = expected(valueBuilder);
    GaugeMetric metric = Metric.gauge(CUSTOM_METRIC).value(DoubleValue.of(value))
        .at(CUSTOM_METRIC_AT).build();

    Assert.assertNotNull(metric);
    Assert.assertEquals(expectedTimeSeries, metric.timeSeries());
  }

  @Test
  public void assert_timeSeries_equals_for_long_value() {
    long value = 42L;
    Builder valueBuilder = TypedValue.newBuilder().setInt64Value(value);
    TimeSeries expectedTimeSeries = expected(valueBuilder);
    GaugeMetric metric = Metric.gauge(CUSTOM_METRIC).value(LongValue.of(value)).at(CUSTOM_METRIC_AT)
        .build();

    Assert.assertNotNull(metric);
    Assert.assertEquals(expectedTimeSeries, metric.timeSeries());
  }

  private TimeSeries expected(TypedValue.Builder valueBuilder) {
    return TimeSeries.newBuilder()
        .setMetric(com.google.api.Metric.newBuilder()
            .setType(CustomMetric.CUSTOM_METRIC_PREFIX + CUSTOM_METRIC_NAME)
            .putLabels(METRIC_LABEL.key(), METRIC_LABEL.value())
        )
        .setResource(MonitoredResource.newBuilder()
            .setType(RESOURCE_TYPE)
            .putLabels(LABEL.key(), LABEL.value())
        )
        .addPoints(Point.newBuilder()
            .setInterval(TimeInterval.newBuilder()
                .setEndTime(Timestamps.fromMillis(CUSTOM_METRIC_AT.toEpochMilli())))
            .setValue(valueBuilder)
        )
        .build();
  }

}