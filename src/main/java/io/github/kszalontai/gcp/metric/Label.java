package io.github.kszalontai.gcp.metric;

import com.google.common.base.Preconditions;

public final class Label {

    private final String key;
    private final String value;

    private Label(String key, String value) {
        Preconditions.checkNotNull(key, "key is required");
        Preconditions.checkNotNull(value, "value is required");
        this.key = key;
        this.value = value;
    }

    public static Label of(String key, String value) {
        return new Label(key, value);
    }

    public String key() {
        return key;
    }

    public String value() {
        return value;
    }
}
