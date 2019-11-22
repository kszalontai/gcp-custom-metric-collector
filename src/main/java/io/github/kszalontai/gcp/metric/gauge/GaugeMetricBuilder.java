package io.github.kszalontai.gcp.metric.gauge;

import java.time.Instant;

import io.github.kszalontai.gcp.metric.Metric;
import io.github.kszalontai.gcp.metric.value.BoolValue;
import io.github.kszalontai.gcp.metric.value.DoubleValue;
import io.github.kszalontai.gcp.metric.value.LongValue;
import io.github.kszalontai.gcp.metric.value.Value;

public final class GaugeMetricBuilder {

    final Metric metric;
    Value value;
    Instant at;

    public GaugeMetricBuilder(Metric metric) {
        this.metric = metric;
    }

    public GaugeMetricWithValueBuilder value(BoolValue value) {
        return withValue(value);
    }

    public GaugeMetricWithValueBuilder value(DoubleValue value) {
        return withValue(value);
    }

    public GaugeMetricWithValueBuilder value(LongValue value) {
        return withValue(value);
    }

    private GaugeMetricWithValueBuilder withValue(Value value) {
        this.value = value;
        return new GaugeMetricWithValueBuilder(this);
    }

    public static final class GaugeMetricWithValueBuilder {
        private final GaugeMetricBuilder builder;

        private GaugeMetricWithValueBuilder(GaugeMetricBuilder builder) {
            this.builder = builder;
        }

        public GaugeMetricWithValueAndTimestampBuilder at(Instant at) {
            this.builder.at = at;
            return new GaugeMetricWithValueAndTimestampBuilder(builder);
        }
    }

    public static final class GaugeMetricWithValueAndTimestampBuilder {
        private final GaugeMetricBuilder builder;

        private GaugeMetricWithValueAndTimestampBuilder(GaugeMetricBuilder builder) {
            this.builder = builder;
        }

        public GaugeMetric build() {
            return new GaugeMetric(builder);
        }
    }
}
