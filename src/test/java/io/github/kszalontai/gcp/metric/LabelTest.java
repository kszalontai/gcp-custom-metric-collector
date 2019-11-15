package io.github.kszalontai.gcp.metric;

import static org.junit.Assert.*;

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


}