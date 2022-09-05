package edu.wpi.cs.dss.serverless.implementation.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateImplementationResponseTest {

    private static final String ID_PARAM = "id";
    private static final String ERROR_PARAM = "error";
    private static final Integer STATUS_CODE_PARAM = 200;
    private static final String FILENAME_PARAM = "filename";

    private CreateImplementationResponse createImplementationResponse;

    @Before
    public void init() {
        createImplementationResponse = CreateImplementationResponse.builder()
                .statusCode(STATUS_CODE_PARAM)
                .filename(FILENAME_PARAM)
                .error(ERROR_PARAM)
                .id(ID_PARAM)
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(ID_PARAM, createImplementationResponse.getId());
        assertEquals(ERROR_PARAM, createImplementationResponse.getError());
        assertEquals(FILENAME_PARAM, createImplementationResponse.getFilename());
        assertEquals(STATUS_CODE_PARAM, createImplementationResponse.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(createImplementationResponse);
        assertEquals(expected, createImplementationResponse.toString());
    }
}
