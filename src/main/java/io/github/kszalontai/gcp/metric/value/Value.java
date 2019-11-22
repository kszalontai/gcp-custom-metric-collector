package io.github.kszalontai.gcp.metric.value;

import com.google.monitoring.v3.TypedValue;

public interface Value {

  TypedValue typedValue();
}
