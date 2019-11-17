package io.github.kszalontai.gcp.metric;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Answers;

import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.cloud.monitoring.v3.stub.MetricServiceStub;
import com.google.monitoring.v3.TimeSeries;

public class GcpMetricClientTest {

    private static final String PROJECT_ID = "gcp_project_id";

    @Test
    public void assert_client_created_when_project_id_given() {
        GcpMetricClient client = GcpMetricClient
                .projectId(PROJECT_ID)
                .build();
        assertNotNull(client);
    }

    @Test(expected = NullPointerException.class)
    public void assert_exception_thrown_when_project_id_is_missing() {
        GcpMetricClient
                .projectId(null)
                .build();
    }

    @Test
    public void assert_send_custom_metric_calls_client() {
        CustomMetric metric = mock(CustomMetric.class);
        when(metric.timeSeries()).thenReturn(TimeSeries.newBuilder().build());
        MetricServiceStub mockStub = mock(MetricServiceStub.class, Answers.RETURNS_MOCKS);
        MetricServiceClient serviceClient = MetricServiceClient.create(mockStub);
        GcpMetricClient client = new GcpMetricClient(PROJECT_ID, serviceClient);

        client.sendMetric(metric);

        verify(metric).timeSeries();
        verify(mockStub).createTimeSeriesCallable();
    }

}