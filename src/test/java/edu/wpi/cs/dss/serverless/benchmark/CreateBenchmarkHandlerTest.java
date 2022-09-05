package edu.wpi.cs.dss.serverless.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.benchmark.http.CreateBenchmarkRequest;
import edu.wpi.cs.dss.serverless.benchmark.http.CreateBenchmarkResponse;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateBenchmarkHandlerTest extends LambdaTest {
    
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testValidAddBenchmark() {
        final CreateBenchmarkRequest request = new CreateBenchmarkRequest();
        request.setCpuCores(4);
        request.setMemory(512);
        request.setCpuL1Cache(1);
        request.setCpuL2Cache(2);
        request.setCpuL3Cache(3);
        request.setCpuThreads(10);
        request.setMemoryUsage(256);
        request.setExecutionTime(1000);
        request.setCpuName("intel");
        request.setImplementationId("1");
        request.setProblemInstanceId("1");
        request.setAuthorId("junit-add-benchmark-author-id");
        
        try {
            testInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidBenchmarkAdd() {
        // invalid problem instance id
        final CreateBenchmarkRequest invalidProblemInstanceIdRequest = new CreateBenchmarkRequest();
        invalidProblemInstanceIdRequest.setAuthorId("junit-add-benchmark-author-id");
        invalidProblemInstanceIdRequest.setCpuCores(4);
        invalidProblemInstanceIdRequest.setCpuL1Cache(1);
        invalidProblemInstanceIdRequest.setCpuL2Cache(2);
        invalidProblemInstanceIdRequest.setCpuL3Cache(3);
        invalidProblemInstanceIdRequest.setCpuName("intel");
        invalidProblemInstanceIdRequest.setCpuThreads(10);
        invalidProblemInstanceIdRequest.setExecutionTime(1000);
        invalidProblemInstanceIdRequest.setMemory(512);
        invalidProblemInstanceIdRequest.setMemoryUsage(256);
        invalidProblemInstanceIdRequest.setProblemInstanceId("bad-problem-instance-id");
        invalidProblemInstanceIdRequest.setImplementationId("1");

        // invalid implementation id
        final CreateBenchmarkRequest invalidImplementationIdRequest = new CreateBenchmarkRequest();
        invalidImplementationIdRequest.setAuthorId("junit-add-benchmark-author-id");
        invalidImplementationIdRequest.setCpuCores(4);
        invalidImplementationIdRequest.setCpuL1Cache(1);
        invalidImplementationIdRequest.setCpuL2Cache(2);
        invalidImplementationIdRequest.setCpuL3Cache(3);
        invalidImplementationIdRequest.setCpuName("intel");
        invalidImplementationIdRequest.setCpuThreads(10);
        invalidImplementationIdRequest.setExecutionTime(1000);
        invalidImplementationIdRequest.setMemory(512);
        invalidImplementationIdRequest.setMemoryUsage(256);
        invalidImplementationIdRequest.setProblemInstanceId("1");
        invalidImplementationIdRequest.setImplementationId("bad-implementation-id");

        try {
            testFailInput(invalidProblemInstanceIdRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }

        try {
            testFailInput(invalidImplementationIdRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    private void testInput(CreateBenchmarkRequest request) throws IOException {
        final CreateBenchmarkHandler handler = new CreateBenchmarkHandler();
        final CreateBenchmarkResponse response = (CreateBenchmarkResponse) handler.handleRequest(
                request, createContext("add benchmark")
        );

        assertTrue(response.getId().length() > 0);
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(CreateBenchmarkRequest request) throws IOException {
        final CreateBenchmarkHandler handler = new CreateBenchmarkHandler();
        final GenericResponse response = handler.handleRequest(
                request, createContext("add benchmark fail")
        );

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}
