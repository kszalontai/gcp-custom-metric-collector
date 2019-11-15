package io.github.kszalontai.gcp.metric.value;

import static com.google.monitoring.v3.TypedValue.ValueCase.INT64_VALUE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongValueTest {

    private static final long VALUE = 123L;

    @Test(expected = NullPointerException.class)
    public void assert_exception_thrown_when_value_is_null() {
        LongValue.of(null);
    }

    @Test
    public void assert_typed_int_value_returned() {
        LongValue value = LongValue.of(VALUE);
        assertEquals(INT64_VALUE, value.typedValue().getValueCase());
        assertEquals(VALUE, value.typedValue().getInt64Value());
    }

}