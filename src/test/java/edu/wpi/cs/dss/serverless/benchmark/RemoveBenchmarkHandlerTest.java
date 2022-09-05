package edu.wpi.cs.dss.serverless.benchmark;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.benchmark.http.CreateBenchmarkRequest;
import edu.wpi.cs.dss.serverless.benchmark.http.CreateBenchmarkResponse;
import edu.wpi.cs.dss.serverless.generic.GenericRemoveRequest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RemoveBenchmarkHandlerTest extends LambdaTest {

    @Test
    public void testValidBenchmarkRemove() {
        try {
            final String id = testAddBenchmark();

            final GenericRemoveRequest request = new GenericRemoveRequest();
            request.setId(id);
            testInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    private String testAddBenchmark() throws IOException {
        // add before remove
        final CreateBenchmarkRequest addRequest = new CreateBenchmarkRequest();
        addRequest.setCpuCores(4);
        addRequest.setMemory(512);
        addRequest.setCpuL1Cache(1);
        addRequest.setCpuL2Cache(2);
        addRequest.setCpuL3Cache(3);
        addRequest.setCpuThreads(10);
        addRequest.setMemoryUsage(256);
        addRequest.setExecutionTime(1000);
        addRequest.setCpuName("intel");
        addRequest.setImplementationId("1");
        addRequest.setProblemInstanceId("1");
        addRequest.setAuthorId("junit-add-benchmark-author-id");


        final CreateBenchmarkHandler addHandler = new CreateBenchmarkHandler();
        final CreateBenchmarkResponse addResponse = (CreateBenchmarkResponse) addHandler.handleRequest(
                addRequest, createContext("add")
        );

        assertTrue(addResponse.getId().length() > 0);
        return addResponse.getId();
    }

    private void testInput(GenericRemoveRequest request) throws IOException {
        final RemoveBenchmarkHandler handler = new RemoveBenchmarkHandler();
        final GenericResponse response = handler.handleRequest(
                request, createContext("remove benchmark")
        );

        assertEquals("", response.getError());
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }
}
