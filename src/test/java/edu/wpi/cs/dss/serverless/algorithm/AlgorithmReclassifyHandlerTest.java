package edu.wpi.cs.dss.serverless.algorithm;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.algorithm.http.AlgorithmGetRequest;
import edu.wpi.cs.dss.serverless.algorithm.http.AlgorithmGetResponse;
import edu.wpi.cs.dss.serverless.algorithm.http.AlgorithmReclassifyRequest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AlgorithmReclassifyHandlerTest extends LambdaTest {

    private static final String ID = "52cac455-5bb3-11ec-933c-16c4115dd1ff";
    private static final String VALID_CLASSIFICATION_ID = "1b53c044-5bb3-11ec-933c-16c4115dd1ff";
    private static final String INVALID_CLASSIFICATION_ID = "1b53c044-5bb3-11ec-933c-16c4115dd134ff";

    @Test
    public void testSuccessInput() {
        final AlgorithmReclassifyRequest request = new AlgorithmReclassifyRequest();
        request.setAlgorithmId(ID);
        request.setNewClassificationId(VALID_CLASSIFICATION_ID);

        try {
            testSuccessInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid: " + e.getMessage());
        }
    }

    @Test
    public void testFailInput() {
        final AlgorithmReclassifyRequest request = new AlgorithmReclassifyRequest();
        request.setAlgorithmId(ID);
        request.setNewClassificationId(INVALID_CLASSIFICATION_ID);

        try {
            testFailInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid: " + e.getMessage());
        }
    }

    private void testSuccessInput(AlgorithmReclassifyRequest algorithmReclassifyRequest) throws IOException {
        final AlgorithmReclassifyHandler algorithmReclassifyHandler = new AlgorithmReclassifyHandler();
        final GenericResponse response = algorithmReclassifyHandler.handleRequest(
                algorithmReclassifyRequest, createContext("reclassify")
        );

        final AlgorithmGetHandler algorithmGetHandler = new AlgorithmGetHandler();
        final AlgorithmGetRequest algorithmGetRequest = new AlgorithmGetRequest();
        algorithmGetRequest.setId(algorithmReclassifyRequest.getAlgorithmId());
        final AlgorithmGetResponse algorithmGetResponse = (AlgorithmGetResponse) algorithmGetHandler.handleRequest(
                algorithmGetRequest, createContext("get")
        );

        assertEquals(algorithmReclassifyRequest.getAlgorithmId(), algorithmGetResponse.getId());
        assertEquals(algorithmReclassifyRequest.getNewClassificationId(), algorithmGetResponse.getClassificationId());
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(AlgorithmReclassifyRequest algorithmReclassifyRequest) throws IOException {
        final AlgorithmReclassifyHandler algorithmAddHandler = new AlgorithmReclassifyHandler();
        final GenericResponse response = algorithmAddHandler.handleRequest(
                algorithmReclassifyRequest, createContext("reclassify")
        );

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}
