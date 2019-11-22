package io.github.kszalontai.gcp.metric;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Label {

  private final String key;
  private final String value;

  private Label(String key, String value) {
    requireNonNull(key, "key is required");
    requireNonNull(value, "value is required");
    this.key = key;
    this.value = value;
  }

  @SuppressWarnings("PMD.ShortMethodName")
  public static Label of(String key, String value) {
    return new Label(key, value);
  }

  public static Map<String, String> asMap(List<Label> labels) {
    if (labels == null || labels.isEmpty()) {
      return new HashMap<>();
    }
    return labels.stream().collect(Collectors.toMap(l -> l.key, l -> l.value));
  }

  public String key() {
    return key;
  }

  public String value() {
    return value;
  }
}
