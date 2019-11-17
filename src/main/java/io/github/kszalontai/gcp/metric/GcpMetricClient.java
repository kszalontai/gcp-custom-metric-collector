package io.github.kszalontai.gcp.metric;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.monitoring.v3.ProjectName;

public final class GcpMetricClient implements AutoCloseable {

    private final ProjectName project;
    private final MetricServiceClient metricServiceClient;

    private GcpMetricClient(Builder builder) {
        requireNonNull(builder.projectId, "projectId is required");
        this.project = ProjectName.of(builder.projectId);
        try {
            this.metricServiceClient = MetricServiceClient.create();
        } catch (IOException e) {
            throw new RuntimeException("Unable to create GcpMetricClient", e);
        }
    }

    public static Builder projectId(String projectId) {
        return new Builder(projectId);
    }

    @Override
    public void close() {
        metricServiceClient.close();
    }

    public static final class Builder {
        final String projectId;

        private Builder(String projectId) {
            this.projectId = projectId;
        }

        public GcpMetricClient build() {
            return new GcpMetricClient(this);
        }
    }

}
