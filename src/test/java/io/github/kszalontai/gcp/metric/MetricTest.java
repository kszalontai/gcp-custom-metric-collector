package io.github.kszalontai.gcp.metric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MetricTest {

  private static final String NAME = "name";
  private Resource resource = Resource.type("type").build();

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_name_and_resource_is_missing() {
    Metric.name(null).monitoredResource(null).build();
  }

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_name_is_missing() {
    Metric.name(null).monitoredResource(resource).build();
  }

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_resource_is_missing() {
    Metric.name(NAME).monitoredResource(null).build();
  }

  @Test
  public void assert_metric_created_when_name_is_given() {
    Metric metric = Metric.name(NAME).monitoredResource(resource).build();
    assertNotNull(metric);
    assertEquals(NAME, metric.name());
  }

  @Test
  public void assert_metric_created_when_name_is_given_with_label() {
    String key1 = "key1";
    String value1 = "value1";

    Metric metric = Metric.name(NAME)
        .monitoredResource(resource)
        .label(Label.of(key1, value1))
        .build();

    assertNotNull(metric);
    assertEquals(NAME, metric.name());
    assertEquals(1, metric.labels().size());
    Label label = metric.labels().get(0);
    assertEquals(key1, label.key());
    assertEquals(value1, label.value());
  }

  @Test
  public void assert_metric_created_when_name_is_given_with_multiple_labels() {
    String key1 = "key1";
    String value1 = "value1";
    String key2 = "key2";
    String value2 = "value2";

    Metric metric = Metric.name(NAME)
        .monitoredResource(resource)
        .label(Label.of(key1, value1))
        .label(Label.of(key2, value2))
        .build();

    assertNotNull(metric);
    assertEquals(NAME, metric.name());
    assertEquals(2, metric.labels().size());
    Label label = metric.labels().get(0);
    assertEquals(key1, label.key());
    assertEquals(value1, label.value());
    Label label2 = metric.labels().get(1);
    assertEquals(key2, label2.key());
    assertEquals(value2, label2.value());
  }

}