package io.github.kszalontai.gcp.metric.value;

import com.google.common.base.Preconditions;
import com.google.monitoring.v3.TypedValue;

public final class LongValue implements Value {

    private final Long value;

    private LongValue(Long value) {
        Preconditions.checkNotNull(value, "value is required");
        this.value = value;
    }

    public static LongValue of(Long value) {
        return new LongValue(value);
    }

    @Override
    public TypedValue typedValue() {
        return TypedValue.newBuilder().setInt64Value(value).build();
    }
}
