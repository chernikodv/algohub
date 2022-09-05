package edu.wpi.cs.dss.serverless.benchmark.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class LoadBenchmarkByImplementationResponseTest {

    private static final String ERROR_PARAM = "error";
    private static final Integer STATUS_CODE_PARAM = 200;

    private LoadBenchmarkByImplementationResponse loadBenchmarkByImplementationResponse;

    @Before
    public void init() {
        loadBenchmarkByImplementationResponse = LoadBenchmarkByImplementationResponse.builder()
                .benchmarks(Collections.emptyList())
                .statusCode(STATUS_CODE_PARAM)
                .error(ERROR_PARAM)
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(STATUS_CODE_PARAM, loadBenchmarkByImplementationResponse.getStatusCode());
        assertEquals(0, loadBenchmarkByImplementationResponse.getBenchmarks().size());
        assertEquals(ERROR_PARAM, loadBenchmarkByImplementationResponse.getError());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadBenchmarkByImplementationResponse);
        assertEquals(expected, loadBenchmarkByImplementationResponse.toString());
    }
}
