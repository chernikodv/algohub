package edu.wpi.cs.dss.serverless.classification.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeClassificationRequestTest {

    private static final String SOURCE_ID_PARAM = "sourceId";
    private static final String TARGET_ID_PARAM = "targetId";

    private MergeClassificationRequest mergeClassificationRequest;

    @Before
    public void init() {
        mergeClassificationRequest = new MergeClassificationRequest();
        mergeClassificationRequest.setSourceId(SOURCE_ID_PARAM);
        mergeClassificationRequest.setTargetId(TARGET_ID_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(SOURCE_ID_PARAM, mergeClassificationRequest.getSourceId());
        assertEquals(TARGET_ID_PARAM, mergeClassificationRequest.getTargetId());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(mergeClassificationRequest);
        assertEquals(expected, mergeClassificationRequest.toString());
    }
}
