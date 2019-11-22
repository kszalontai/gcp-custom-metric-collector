package io.github.kszalontai.gcp.metric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ResourceTest {

  private static final String TYPE = "type";

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_type_is_missing() {
    Resource.type(null).build();
  }

  @Test
  public void assert_resource_created_when_type_is_given() {
    Resource resource = Resource.type(TYPE).build();
    assertNotNull(resource);
    assertEquals(TYPE, resource.type());
  }

  @Test
  public void assert_resource_created_when_type_is_given_with_label() {
    String key1 = "key1";
    String value1 = "value1";

    Resource resource = Resource.type(TYPE)
        .label(Label.of(key1, value1))
        .build();

    assertNotNull(resource);
    assertEquals(TYPE, resource.type());
    assertEquals(1, resource.labels().size());
    Label label = resource.labels().get(0);
    assertEquals(key1, label.key());
    assertEquals(value1, label.value());
  }

  @Test
  public void assert_resource_created_when_type_is_given_with_multiple_labels() {
    String key1 = "key1";
    String value1 = "value1";
    String key2 = "key2";
    String value2 = "value2";

    Resource resource = Resource.type(TYPE)
        .label(Label.of(key1, value1))
        .label(Label.of(key2, value2))
        .build();

    assertNotNull(resource);
    assertEquals(TYPE, resource.type());
    assertEquals(2, resource.labels().size());
    Label label = resource.labels().get(0);
    assertEquals(key1, label.key());
    assertEquals(value1, label.value());
    Label label2 = resource.labels().get(1);
    assertEquals(key2, label2.key());
    assertEquals(value2, label2.value());
  }

}