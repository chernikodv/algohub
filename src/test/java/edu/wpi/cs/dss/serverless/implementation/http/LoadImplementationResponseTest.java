package edu.wpi.cs.dss.serverless.implementation.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadImplementationResponseTest {

    private static final String ID_PARAM = "id";
    private static final String ERROR_PARAM = "error";
    private static final Integer STATUS_CODE_PARAM = 200;
    private static final String FILENAME_PARAM = "filename";
    private static final String AUTHOR_ID_PARAM = "authorId";
    private static final String ALGORITHM_ID_PARAM = "algorithmId";
    private static final String PROGRAMMING_LANGUAGE_PARAM = "programmingLanguage";

    private LoadImplementationResponse loadImplementationResponse;

    @Before
    public void init() {
        loadImplementationResponse = LoadImplementationResponse.builder()
                .programmingLanguage(PROGRAMMING_LANGUAGE_PARAM)
                .algorithmId(ALGORITHM_ID_PARAM)
                .statusCode(STATUS_CODE_PARAM)
                .authorId(AUTHOR_ID_PARAM)
                .filename(FILENAME_PARAM)
                .error(ERROR_PARAM)
                .id(ID_PARAM)
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(ID_PARAM, loadImplementationResponse.getId());
        assertEquals(ERROR_PARAM, loadImplementationResponse.getError());
        assertEquals(FILENAME_PARAM, loadImplementationResponse.getFilename());
        assertEquals(AUTHOR_ID_PARAM, loadImplementationResponse.getAuthorId());
        assertEquals(STATUS_CODE_PARAM, loadImplementationResponse.getStatusCode());
        assertEquals(ALGORITHM_ID_PARAM, loadImplementationResponse.getAlgorithmId());
        assertEquals(PROGRAMMING_LANGUAGE_PARAM, loadImplementationResponse.getProgrammingLanguage());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadImplementationResponse);
        assertEquals(expected, loadImplementationResponse.toString());
    }
}
