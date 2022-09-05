package edu.wpi.cs.dss.serverless.implementation.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateImplementationRequestTest {

    private static final String NAME_PARAM = "name";
    private static final String AUTHOR_ID_PARAM = "authorId";
    private static final String EXTENSION_PARAM = "extension";
    private static final String ALGORITHM_ID_PARAM = "algorithmId";
    private static final String ALGORITHM_NAME_PARAM = "algorithmName";
    private static final String SOURCE_CODE_BASE_64_PARAM = "sourceCodeBase64";

    private CreateImplementationRequest createImplementationRequest;

    @Before
    public void init() {
        createImplementationRequest = new CreateImplementationRequest();
        createImplementationRequest.setName(NAME_PARAM);
        createImplementationRequest.setAuthorId(AUTHOR_ID_PARAM);
        createImplementationRequest.setExtension(EXTENSION_PARAM);
        createImplementationRequest.setAlgorithmId(ALGORITHM_ID_PARAM);
        createImplementationRequest.setAlgorithmName(ALGORITHM_NAME_PARAM);
        createImplementationRequest.setSourceCodeBase64(SOURCE_CODE_BASE_64_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(NAME_PARAM, createImplementationRequest.getName());
        assertEquals(AUTHOR_ID_PARAM, createImplementationRequest.getAuthorId());
        assertEquals(EXTENSION_PARAM, createImplementationRequest.getExtension());
        assertEquals(ALGORITHM_ID_PARAM, createImplementationRequest.getAlgorithmId());
        assertEquals(ALGORITHM_NAME_PARAM, createImplementationRequest.getAlgorithmName());
        assertEquals(SOURCE_CODE_BASE_64_PARAM, createImplementationRequest.getSourceCodeBase64());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(createImplementationRequest);
        assertEquals(expected, createImplementationRequest.toString());
    }
}
