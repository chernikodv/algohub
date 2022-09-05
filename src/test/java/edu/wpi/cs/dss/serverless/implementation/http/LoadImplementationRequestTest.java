package edu.wpi.cs.dss.serverless.implementation.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadImplementationRequestTest {

    private static final String ID_PARAM = "id";

    private LoadImplementationRequest loadImplementationRequest;

    @Before
    public void init() {
        loadImplementationRequest = new LoadImplementationRequest();
        loadImplementationRequest.setId(ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(ID_PARAM, loadImplementationRequest.getId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadImplementationRequest);
        assertEquals(expected, loadImplementationRequest.toString());
    }
}
