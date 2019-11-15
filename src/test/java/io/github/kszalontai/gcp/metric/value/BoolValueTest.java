package io.github.kszalontai.gcp.metric.value;

import static com.google.monitoring.v3.TypedValue.ValueCase.BOOL_VALUE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoolValueTest {

    @Test
    public void assert_true_value_returns_typed_value_true() {
        BoolValue value = BoolValue.TRUE;
        assertEquals(BOOL_VALUE, value.typedValue().getValueCase());
        assertEquals(true, value.typedValue().getBoolValue());
    }

    @Test
    public void assert_false_value_returns_typed_value_false() {
        BoolValue value = BoolValue.FALSE;
        assertEquals(BOOL_VALUE, value.typedValue().getValueCase());
        assertEquals(false, value.typedValue().getBoolValue());
    }


}