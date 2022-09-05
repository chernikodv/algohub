package edu.wpi.cs.dss.serverless.algorithm.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadAlgorithmRequestTest {

    private static final String ID_PARAM = "id";

    private LoadAlgorithmRequest loadAlgorithmRequest;

    @Before
    public void init() {
        loadAlgorithmRequest = new LoadAlgorithmRequest();
        loadAlgorithmRequest.setId(ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(ID_PARAM, loadAlgorithmRequest.getId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadAlgorithmRequest);
        assertEquals(expected, loadAlgorithmRequest.toString());
    }
}
