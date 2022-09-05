package edu.wpi.cs.dss.serverless.classification.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateClassificationResponseTest {

    private static final String ID_PARAM = "id";
    private static final String NAME_PARAM = "name";
    private static final String ERROR_PARAM = "error";
    private static final Integer STATUS_CODE_PARAM = 200;
    private static final String AUTHOR_ID_PARAM = "authorId";
    private static final String PARENT_ID_PARAM = "parentId";

    private CreateClassificationResponse createClassificationResponse;

    @Before
    public void init() {
        createClassificationResponse = CreateClassificationResponse.builder()
                .statusCode(STATUS_CODE_PARAM)
                .parentId(PARENT_ID_PARAM)
                .authorId(AUTHOR_ID_PARAM)
                .error(ERROR_PARAM)
                .name(NAME_PARAM)
                .id(ID_PARAM)
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(ID_PARAM, createClassificationResponse.getId());
        assertEquals(NAME_PARAM, createClassificationResponse.getName());
        assertEquals(ERROR_PARAM, createClassificationResponse.getError());
        assertEquals(AUTHOR_ID_PARAM, createClassificationResponse.getAuthorId());
        assertEquals(PARENT_ID_PARAM, createClassificationResponse.getParentId());
        assertEquals(STATUS_CODE_PARAM, createClassificationResponse.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(createClassificationResponse);
        assertEquals(expected, createClassificationResponse.toString());
    }
}
