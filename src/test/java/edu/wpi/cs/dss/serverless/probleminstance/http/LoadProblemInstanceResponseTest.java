package edu.wpi.cs.dss.serverless.probleminstance.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadProblemInstanceResponseTest {

    private static final String ERROR_PARAM = "error";
    private static final Integer STATUS_CODE_PARAM = 200;
    private static final String AUTHOR_ID_PARAM = "authorId";
    private static final String DATASET_SIZE_PARAM = "datasetSize";
    private static final String PROBLEM_TYPE_PARAM = "problemType";
    private static final String ALGORITHM_ID_PARAM = "algorithmId";
    private static final String DATASET_FILENAME_PARAM = "datasetFilename";

    private LoadProblemInstanceResponse loadProblemInstanceResponse;

    @Before
    public void init() {
        loadProblemInstanceResponse = LoadProblemInstanceResponse.builder()
                .datasetFilename(DATASET_FILENAME_PARAM)
                .datasetSize(DATASET_SIZE_PARAM)
                .algorithmId(ALGORITHM_ID_PARAM)
                .problemType(PROBLEM_TYPE_PARAM)
                .statusCode(STATUS_CODE_PARAM)
                .authorId(AUTHOR_ID_PARAM)
                .error(ERROR_PARAM)
                .build();
    }

    @Test
    public void testGetters() {
        assertEquals(ERROR_PARAM, loadProblemInstanceResponse.getError());
        assertEquals(AUTHOR_ID_PARAM, loadProblemInstanceResponse.getAuthorId());
        assertEquals(STATUS_CODE_PARAM, loadProblemInstanceResponse.getStatusCode());
        assertEquals(DATASET_SIZE_PARAM, loadProblemInstanceResponse.getDatasetSize());
        assertEquals(ALGORITHM_ID_PARAM, loadProblemInstanceResponse.getAlgorithmId());
        assertEquals(PROBLEM_TYPE_PARAM, loadProblemInstanceResponse.getProblemType());
        assertEquals(DATASET_FILENAME_PARAM, loadProblemInstanceResponse.getDatasetFilename());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(loadProblemInstanceResponse);
        assertEquals(expected, loadProblemInstanceResponse.toString());
    }
}
