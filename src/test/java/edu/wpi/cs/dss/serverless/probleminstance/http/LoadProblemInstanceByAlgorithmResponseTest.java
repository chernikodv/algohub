package edu.wpi.cs.dss.serverless.probleminstance.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class LoadProblemInstanceByAlgorithmResponseTest {

    private LoadProblemInstanceByAlgorithmResponse loadProblemInstanceByAlgorithmResponse;

    @Before
    public void init() {
        loadProblemInstanceByAlgorithmResponse = LoadProblemInstanceByAlgorithmResponse.builder()
                .problemInstances(Collections.emptyList())
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(0, loadProblemInstanceByAlgorithmResponse.getProblemInstances().size());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadProblemInstanceByAlgorithmResponse);
        assertEquals(expected, loadProblemInstanceByAlgorithmResponse.toString());
    }
}
