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
    public void assert_send_metric_calls_stub() {
        CustomMetric metric = metric();
        MetricServiceStub mockStub = mock(MetricServiceStub.class, Answers.RETURNS_MOCKS);
        MetricServiceClient serviceClient = MetricServiceClient.create(mockStub);
        GcpMetricClient client = new GcpMetricClient(PROJECT_ID, serviceClient);

        client.send(metric);

        verify(metric).timeSeries();
        verify(mockStub).createTimeSeriesCallable();
    }

    @Test
    public void assert_service_client_closed_when_resource_released() {
        MetricServiceStub mockStub = mock(MetricServiceStub.class);
        MetricServiceClient serviceClient = MetricServiceClient.create(mockStub);

        try(GcpMetricClient client = new GcpMetricClient(PROJECT_ID, serviceClient)) {
            //noop
        }

        verify(mockStub).close();
    }

    private CustomMetric metric() {
        CustomMetric metric = mock(CustomMetric.class);
        when(metric.timeSeries()).thenReturn(TimeSeries.newBuilder().build());
        return metric;
    }

}