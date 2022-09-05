package edu.wpi.cs.dss.serverless.algorithm.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateAlgorithmRequestTest {

    private static final String NAME_PARAM = "name";
    private static final String AUTHOR_ID_PARAM = "authorId";
    private static final String DESCRIPTION_PARAM = "description";
    private static final String CLASSIFICATION_ID_PARAM = "classificationId";

    private CreateAlgorithmRequest createAlgorithmRequest;

    @Before
    public void init() {
        createAlgorithmRequest = new CreateAlgorithmRequest();
        createAlgorithmRequest.setName(NAME_PARAM);
        createAlgorithmRequest.setAuthorId(AUTHOR_ID_PARAM);
        createAlgorithmRequest.setDescription(DESCRIPTION_PARAM);
        createAlgorithmRequest.setClassificationId(CLASSIFICATION_ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(NAME_PARAM, createAlgorithmRequest.getName());
        assertEquals(AUTHOR_ID_PARAM, createAlgorithmRequest.getAuthorId());
        assertEquals(DESCRIPTION_PARAM, createAlgorithmRequest.getDescription());
        assertEquals(CLASSIFICATION_ID_PARAM, createAlgorithmRequest.getClassificationId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(createAlgorithmRequest);
        assertEquals(expected, createAlgorithmRequest.toString());
    }
}
