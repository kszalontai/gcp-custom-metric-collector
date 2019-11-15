package io.github.kszalontai.gcp.metric.value;

import com.google.common.base.Preconditions;
import com.google.monitoring.v3.TypedValue;

public final class DoubleValue implements Value {

    private final Double value;

    private DoubleValue(Double value) {
        Preconditions.checkNotNull(value, "value is required");
        this.value = value;
    }

    public static DoubleValue of(Double value) {
        return new DoubleValue(value);
    }

    @Override
    public TypedValue typedValue() {
        return TypedValue.newBuilder().setDoubleValue(value).build();
    }
}
