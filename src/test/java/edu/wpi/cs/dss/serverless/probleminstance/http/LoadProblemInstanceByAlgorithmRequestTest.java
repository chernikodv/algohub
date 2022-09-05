package edu.wpi.cs.dss.serverless.probleminstance.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadProblemInstanceByAlgorithmRequestTest {

    private static final String ID_PARAM = "id";

    private LoadProblemInstanceByAlgorithmRequest loadProblemInstanceByAlgorithmRequest;

    @Before
    public void init() {
        loadProblemInstanceByAlgorithmRequest = new LoadProblemInstanceByAlgorithmRequest();
        loadProblemInstanceByAlgorithmRequest.setId(ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(ID_PARAM, loadProblemInstanceByAlgorithmRequest.getId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadProblemInstanceByAlgorithmRequest);
        assertEquals(expected, loadProblemInstanceByAlgorithmRequest.toString());
    }
}
