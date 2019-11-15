package io.github.kszalontai.gcp.metric.value;

import static com.google.monitoring.v3.TypedValue.ValueCase.DOUBLE_VALUE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DoubleValueTest {

    private static final double VALUE = 123d;
    private static final double DELTA = 0d;

    @Test(expected = NullPointerException.class)
    public void assert_exception_thrown_when_value_is_null() {
        DoubleValue.of(null);
    }

    @Test
    public void assert_typed_int_value_returned() {
        DoubleValue value = DoubleValue.of(VALUE);
        assertEquals(DOUBLE_VALUE, value.typedValue().getValueCase());
        assertEquals(VALUE, value.typedValue().getDoubleValue(), DELTA);
    }

}