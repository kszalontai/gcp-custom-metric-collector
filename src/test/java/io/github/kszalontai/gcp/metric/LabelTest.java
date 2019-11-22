package io.github.kszalontai.gcp.metric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.common.collect.Lists;
import java.util.Map;
import org.junit.Test;

public class LabelTest {

  private static final String KEY = "key";
  private static final String VALUE = "value";

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_key_and_value_missing() {
    Label.of(null, null);
  }

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_key_is_missing() {
    Label.of(null, VALUE);
  }

  @Test(expected = NullPointerException.class)
  public void assert_exception_thrown_when_value_is_missing() {
    Label.of(KEY, null);
  }

  @Test
  public void assert_label_is_valid() {
    Label label = Label.of(KEY, VALUE);
    assertNotNull(label);
    assertEquals(KEY, label.key());
    assertEquals(VALUE, label.value());
  }

  @Test
  public void assert_asMap_returns_empty_map_when_called_with_null() {
    Map<String, String> map = Label.asMap(null);

    assertNotNull(map);
    assertEquals(0, map.size());
  }

  @Test
  public void assert_asMap_returns_empty_map_when_called_with_empty_list() {
    Map<String, String> map = Label.asMap(Lists.newArrayList());

    assertNotNull(map);
    assertEquals(0, map.size());
  }


  @Test
  public void assert_asMap_returns_key_value_entry() {
    String key2 = "key2";
    String value2 = "value2";
    Map<String, String> map = Label.asMap(Lists.newArrayList(
        Label.of(KEY, VALUE),
        Label.of(key2, value2))
    );

    assertNotNull(map);
    assertEquals(2, map.size());
    assertEquals(VALUE, map.get(KEY));
    assertEquals(value2, map.get(key2));
  }


}