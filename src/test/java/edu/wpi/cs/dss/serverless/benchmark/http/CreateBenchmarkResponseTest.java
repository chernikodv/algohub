package edu.wpi.cs.dss.serverless.benchmark.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateBenchmarkResponseTest {

    private static final String ID_PARAM = "id";
    private static final String ERROR_PARAM = "error";
    private static final Integer STATUS_CODE_PARAM = 200;

    private CreateBenchmarkResponse createBenchmarkResponse;

    @Before
    public void init() {
        createBenchmarkResponse = CreateBenchmarkResponse.builder()
                .statusCode(STATUS_CODE_PARAM)
                .error(ERROR_PARAM)
                .id(ID_PARAM)
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(STATUS_CODE_PARAM, createBenchmarkResponse.getStatusCode());
        assertEquals(ERROR_PARAM, createBenchmarkResponse.getError());
        assertEquals(ID_PARAM, createBenchmarkResponse.getId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(createBenchmarkResponse);
        assertEquals(expected, createBenchmarkResponse.toString());
    }
}
