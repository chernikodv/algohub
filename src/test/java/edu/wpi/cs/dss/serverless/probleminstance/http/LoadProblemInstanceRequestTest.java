package edu.wpi.cs.dss.serverless.probleminstance.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadProblemInstanceRequestTest {

    private static final String ID_PARAM = "id";

    private LoadProblemInstanceRequest loadProblemInstanceRequest;

    @Before
    public void init() {
        loadProblemInstanceRequest = new LoadProblemInstanceRequest();
        loadProblemInstanceRequest.setId(ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(ID_PARAM, loadProblemInstanceRequest.getId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadProblemInstanceRequest);
        assertEquals(expected, loadProblemInstanceRequest.toString());
    }
}
