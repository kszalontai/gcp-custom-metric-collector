package io.github.kszalontai.gcp.metric.value;

import static java.util.Objects.requireNonNull;

import com.google.monitoring.v3.TypedValue;

public final class LongValue implements Value {

  private final Long value;

  private LongValue(Long value) {
    requireNonNull(value, "value is required");
    this.value = value;
  }

  @SuppressWarnings("PMD.ShortMethodName")
  public static LongValue of(Long value) {
    return new LongValue(value);
  }

  @Override
  public TypedValue typedValue() {
    return TypedValue.newBuilder().setInt64Value(value).build();
  }
}
