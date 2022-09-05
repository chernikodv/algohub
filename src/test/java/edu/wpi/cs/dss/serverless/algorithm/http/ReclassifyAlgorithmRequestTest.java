package edu.wpi.cs.dss.serverless.algorithm.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReclassifyAlgorithmRequestTest {

    private static final String ALGORITHM_ID_PARAM = "algorithmId";
    private static final String NEW_CLASSIFICATION_ID_PARAM = "newClassificationId";

    private ReclassifyAlgorithmRequest reclassifyAlgorithmRequest;

    @Before
    public void init() {
        reclassifyAlgorithmRequest = new ReclassifyAlgorithmRequest();
        reclassifyAlgorithmRequest.setAlgorithmId(ALGORITHM_ID_PARAM);
        reclassifyAlgorithmRequest.setNewClassificationId(NEW_CLASSIFICATION_ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(ALGORITHM_ID_PARAM, reclassifyAlgorithmRequest.getAlgorithmId());
        assertEquals(NEW_CLASSIFICATION_ID_PARAM, reclassifyAlgorithmRequest.getNewClassificationId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(reclassifyAlgorithmRequest);
        assertEquals(expected, reclassifyAlgorithmRequest.toString());
    }
}
