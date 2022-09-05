package edu.wpi.cs.dss.serverless.classification.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class LoadClassificationHierarchyResponseTest {

    private static final String ERROR_PARAM = "error";
    private static final Integer STATUS_CODE_PARAM = 200;

    private LoadClassificationHierarchyResponse loadClassificationHierarchyResponse;

    @Before
    public void init() {
        loadClassificationHierarchyResponse = LoadClassificationHierarchyResponse.builder()
                .hierarchy(Collections.emptyList())
                .statusCode(STATUS_CODE_PARAM)
                .error(ERROR_PARAM)
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(0, loadClassificationHierarchyResponse.getHierarchy().size());
        assertEquals(ERROR_PARAM, loadClassificationHierarchyResponse.getError());
        assertEquals(STATUS_CODE_PARAM, loadClassificationHierarchyResponse.getStatusCode());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadClassificationHierarchyResponse);
        assertEquals(expected, loadClassificationHierarchyResponse.toString());
    }
}
