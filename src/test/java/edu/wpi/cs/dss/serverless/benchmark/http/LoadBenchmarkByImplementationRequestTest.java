package edu.wpi.cs.dss.serverless.benchmark.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadBenchmarkByImplementationRequestTest {

    private static final String ID_PARAM = "id";

    private LoadBenchmarkByImplementationRequest loadBenchmarkByImplementationRequest;

    @Before
    public void init() {
        loadBenchmarkByImplementationRequest = new LoadBenchmarkByImplementationRequest();
        loadBenchmarkByImplementationRequest.setId(ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(ID_PARAM, loadBenchmarkByImplementationRequest.getId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadBenchmarkByImplementationRequest);
        assertEquals(expected, loadBenchmarkByImplementationRequest.toString());
    }
}
