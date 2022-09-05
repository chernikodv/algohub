package edu.wpi.cs.dss.serverless.benchmark;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.benchmark.http.LoadBenchmarkByImplementationRequest;
import edu.wpi.cs.dss.serverless.benchmark.http.LoadBenchmarkByImplementationResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoadBenchmarkByImplementationHandlerTest extends LambdaTest {

    @Test
    public void testValidGetBenchmarkByImplementation() {
        final LoadBenchmarkByImplementationRequest request = new LoadBenchmarkByImplementationRequest();
        request.setId("1");

        try {
            testInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    private void testInput(LoadBenchmarkByImplementationRequest request) throws IOException {
        final LoadBenchmarkByImplementationHandler handler = new LoadBenchmarkByImplementationHandler();
        final LoadBenchmarkByImplementationResponse response = (LoadBenchmarkByImplementationResponse) handler.handleRequest(
                request, createContext("get benchmarks by implementation")
        );

        assertTrue(response.getBenchmarks().size() > 0);
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }
}
