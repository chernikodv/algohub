package edu.wpi.cs.dss.serverless.probleminstance.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateProblemInstanceRequestTest {

    private static final String AUTHOR_ID_PARAM = "authorId";
    private static final String DATASET_SIZE_PARAM = "datasetSize";
    private static final String PROBLEM_TYPE_PARAM = "problemType";
    private static final String ALGORITHM_ID_PARAM = "algorithmId";
    private static final String DATASET_FILENAME_PARAM = "datasetFilename";
    private static final String SOURCE_CODE_BASE_64_PARAM = "sourceCodeBase64";

    private CreateProblemInstanceRequest createProblemInstanceRequest;

    @Before
    public void init() {
        createProblemInstanceRequest = new CreateProblemInstanceRequest();
        createProblemInstanceRequest.setAuthorId(AUTHOR_ID_PARAM);
        createProblemInstanceRequest.setDatasetSize(DATASET_SIZE_PARAM);
        createProblemInstanceRequest.setProblemType(PROBLEM_TYPE_PARAM);
        createProblemInstanceRequest.setAlgorithmId(ALGORITHM_ID_PARAM);
        createProblemInstanceRequest.setDatasetFilename(DATASET_FILENAME_PARAM);
        createProblemInstanceRequest.setSourceCodeBase64(SOURCE_CODE_BASE_64_PARAM);
    }

    @Test
    public void testGetters() {
        assertEquals(AUTHOR_ID_PARAM, createProblemInstanceRequest.getAuthorId());
        assertEquals(DATASET_SIZE_PARAM, createProblemInstanceRequest.getDatasetSize());
        assertEquals(PROBLEM_TYPE_PARAM, createProblemInstanceRequest.getProblemType());
        assertEquals(ALGORITHM_ID_PARAM, createProblemInstanceRequest.getAlgorithmId());
        assertEquals(DATASET_FILENAME_PARAM, createProblemInstanceRequest.getDatasetFilename());
        assertEquals(SOURCE_CODE_BASE_64_PARAM, createProblemInstanceRequest.getSourceCodeBase64());
    }

    @Test
    @SneakyThrows
    public void testToString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final String expected = objectWriter.writeValueAsString(createProblemInstanceRequest);
        assertEquals(expected, createProblemInstanceRequest.toString());
    }
}
