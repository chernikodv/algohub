package edu.wpi.cs.dss.serverless.benchmark;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.benchmark.http.BenchmarkGetByImplementationRequest;
import edu.wpi.cs.dss.serverless.benchmark.http.BenchmarkGetByImplementationResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BenchmarkGetByImplementationHandlerTest extends LambdaTest {

    @Test
    public void testValidGetBenchmarkByImplementation() {
        final BenchmarkGetByImplementationRequest request = new BenchmarkGetByImplementationRequest();
        request.setId("1");

        try {
            testInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    private void testInput(BenchmarkGetByImplementationRequest request) throws IOException {
        final BenchmarkGetByImplementationHandler handler = new BenchmarkGetByImplementationHandler();
        final BenchmarkGetByImplementationResponse response = (BenchmarkGetByImplementationResponse) handler.handleRequest(
                request, createContext("get benchmarks by implementation")
        );

        assertTrue(response.getBenchmarks().size() > 0);
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }
}
