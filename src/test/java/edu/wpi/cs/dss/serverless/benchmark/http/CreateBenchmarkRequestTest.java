package edu.wpi.cs.dss.serverless.benchmark.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateBenchmarkRequestTest {

    private static final Integer MEMORY_PARAM = 1;
    private static final Integer CPU_CORES_PARAM = 2;
    private static final Integer CPU_THREADS_PARAM = 3;
    private static final Integer CPU_L1_CACHE_PARAM = 4;
    private static final Integer CPU_L2_CACHE_PARAM = 5;
    private static final Integer CPU_L3_CACHE_PARAM = 6;
    private static final Integer MEMORY_USAGE_PARAM = 7;
    private static final Integer EXECUTION_TIME_PARAM = 8;

    private static final String CPU_NAME_PARAM = "cpuName";
    private static final String AUTHOR_ID_PARAM = "authorId";
    private static final String IMPLEMENTATION_ID_PARAM = "implementationId";
    private static final String PROBLEM_INSTANCE_ID_PARAM = "problemInstanceId";

    private CreateBenchmarkRequest createBenchmarkRequest;

    @Before
    public void init() {
        createBenchmarkRequest = new CreateBenchmarkRequest();
        createBenchmarkRequest.setMemory(MEMORY_PARAM);
        createBenchmarkRequest.setCpuCores(CPU_CORES_PARAM);
        createBenchmarkRequest.setCpuThreads(CPU_THREADS_PARAM);
        createBenchmarkRequest.setCpuL1Cache(CPU_L1_CACHE_PARAM);
        createBenchmarkRequest.setCpuL2Cache(CPU_L2_CACHE_PARAM);
        createBenchmarkRequest.setCpuL3Cache(CPU_L3_CACHE_PARAM);
        createBenchmarkRequest.setMemoryUsage(MEMORY_USAGE_PARAM);
        createBenchmarkRequest.setExecutionTime(EXECUTION_TIME_PARAM);
        createBenchmarkRequest.setCpuName(CPU_NAME_PARAM);
        createBenchmarkRequest.setAuthorId(AUTHOR_ID_PARAM);
        createBenchmarkRequest.setImplementationId(IMPLEMENTATION_ID_PARAM);
        createBenchmarkRequest.setProblemInstanceId(PROBLEM_INSTANCE_ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(MEMORY_PARAM, createBenchmarkRequest.getMemory());
        assertEquals(CPU_CORES_PARAM, createBenchmarkRequest.getCpuCores());
        assertEquals(CPU_THREADS_PARAM, createBenchmarkRequest.getCpuThreads());
        assertEquals(CPU_L1_CACHE_PARAM, createBenchmarkRequest.getCpuL1Cache());
        assertEquals(CPU_L2_CACHE_PARAM, createBenchmarkRequest.getCpuL2Cache());
        assertEquals(CPU_L3_CACHE_PARAM, createBenchmarkRequest.getCpuL3Cache());
        assertEquals(MEMORY_USAGE_PARAM, createBenchmarkRequest.getMemoryUsage());
        assertEquals(EXECUTION_TIME_PARAM, createBenchmarkRequest.getExecutionTime());
        assertEquals(CPU_NAME_PARAM, createBenchmarkRequest.getCpuName());
        assertEquals(AUTHOR_ID_PARAM, createBenchmarkRequest.getAuthorId());
        assertEquals(IMPLEMENTATION_ID_PARAM, createBenchmarkRequest.getImplementationId());
        assertEquals(PROBLEM_INSTANCE_ID_PARAM, createBenchmarkRequest.getProblemInstanceId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(createBenchmarkRequest);
        assertEquals(expected, createBenchmarkRequest.toString());
    }
}
