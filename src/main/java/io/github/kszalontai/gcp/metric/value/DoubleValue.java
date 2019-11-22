package io.github.kszalontai.gcp.metric.value;

import static java.util.Objects.requireNonNull;

import com.google.monitoring.v3.TypedValue;

public final class DoubleValue implements Value {

  private final Double value;

  private DoubleValue(Double value) {
    requireNonNull(value, "value is required");
    this.value = value;
  }

  @SuppressWarnings("PMD.ShortMethodName")
  public static DoubleValue of(Double value) {
    return new DoubleValue(value);
  }

  @Override
  public TypedValue typedValue() {
    return TypedValue.newBuilder().setDoubleValue(value).build();
  }
}
