package edu.wpi.cs.dss.serverless.classification.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateClassificationRequestTest {

    private static final String NAME_PARAM = "name";
    private static final String AUTHOR_ID_PARAM = "authorId";
    private static final String PARENT_ID_PARAM = "parentId";

    private CreateClassificationRequest createClassificationRequest;

    @Before
    public void init() {
        createClassificationRequest = new CreateClassificationRequest();
        createClassificationRequest.setName(NAME_PARAM);
        createClassificationRequest.setAuthorId(AUTHOR_ID_PARAM);
        createClassificationRequest.setParentId(PARENT_ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(NAME_PARAM, createClassificationRequest.getName());
        assertEquals(AUTHOR_ID_PARAM, createClassificationRequest.getAuthorId());
        assertEquals(PARENT_ID_PARAM, createClassificationRequest.getParentId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(createClassificationRequest);
        assertEquals(expected, createClassificationRequest.toString());
    }
}
