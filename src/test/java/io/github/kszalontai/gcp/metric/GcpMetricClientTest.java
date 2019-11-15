package io.github.kszalontai.gcp.metric;

import static org.junit.Assert.*;

import org.junit.Test;

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



}