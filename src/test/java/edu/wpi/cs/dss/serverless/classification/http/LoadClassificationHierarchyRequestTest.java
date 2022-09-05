package edu.wpi.cs.dss.serverless.classification.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadClassificationHierarchyRequestTest {

    private LoadClassificationHierarchyRequest loadClassificationHierarchyRequest;

    @Before
    public void init() {
        loadClassificationHierarchyRequest = new LoadClassificationHierarchyRequest();
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(this);
        assertEquals(expected, loadClassificationHierarchyRequest.toString());
    }
}
