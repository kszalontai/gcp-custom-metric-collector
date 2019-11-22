package io.github.kszalontai.gcp.metric;

import static java.util.Objects.requireNonNull;

import com.google.common.collect.Lists;
import io.github.kszalontai.gcp.metric.gauge.GaugeMetricBuilder;
import java.util.List;

public final class Metric {

  private final String name;
  private final Resource monitoredResource;
  private final List<Label> labels;

  private Metric(MetricBuilder builder) {
    requireNonNull(builder.name);
    requireNonNull(builder.monitoredResource);
    this.name = builder.name;
    this.monitoredResource = builder.monitoredResource;
    this.labels = builder.labels;
  }

  public static MetricBuilder name(String name) {
    return new MetricBuilder(name);
  }

  public static GaugeMetricBuilder gauge(Metric metric) {
    return new GaugeMetricBuilder(metric);
  }

  public String name() {
    return name;
  }

  public Resource monitoredResource() {
    return monitoredResource;
  }

  public List<Label> labels() {
    return labels;
  }

  public static final class MetricBuilder {

    private final String name;
    private final List<Label> labels = Lists.newArrayList();
    private Resource monitoredResource;

    public MetricBuilder(String name) {
      this.name = name;
    }

    public MetricWithLabelsBuilder monitoredResource(Resource resource) {
      this.monitoredResource = resource;
      return new MetricWithLabelsBuilder(this);
    }
  }

  public static final class MetricWithLabelsBuilder {

    private final MetricBuilder builder;

    public MetricWithLabelsBuilder(MetricBuilder builder) {
      this.builder = builder;
    }

    public MetricWithLabelsBuilder label(Label label) {
      builder.labels.add(label);
      return this;
    }

    public Metric build() {
      return new Metric(builder);
    }
  }

}
