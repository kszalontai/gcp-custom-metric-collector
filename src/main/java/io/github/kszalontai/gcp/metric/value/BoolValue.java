package io.github.kszalontai.gcp.metric.value;

import com.google.common.base.Preconditions;
import com.google.monitoring.v3.TypedValue;

public final class BoolValue implements Value {

    public static final BoolValue TRUE = new BoolValue(Boolean.TRUE);
    public static final BoolValue FALSE = new BoolValue(Boolean.FALSE);

    private final Boolean value;

    private BoolValue(Boolean value) {
        Preconditions.checkNotNull(value, "value is required");
        this.value = value;
    }

    @Override
    public TypedValue typedValue() {
        return TypedValue.newBuilder().setBoolValue(value).build();
    }
}
