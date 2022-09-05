package edu.wpi.cs.dss.serverless.algorithm;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.algorithm.http.LoadAlgorithmRequest;
import edu.wpi.cs.dss.serverless.algorithm.http.LoadAlgorithmResponse;
import edu.wpi.cs.dss.serverless.algorithm.http.ReclassifyAlgorithmRequest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ReclassifyAlgorithmHandlerTest extends LambdaTest {

    private static final String ID = "52cac455-5bb3-11ec-933c-16c4115dd1ff";
    private static final String VALID_CLASSIFICATION_ID = "1b53c044-5bb3-11ec-933c-16c4115dd1ff";
    private static final String INVALID_CLASSIFICATION_ID = "1b53c044-5bb3-11ec-933c-16c4115dd134ff";

    @Test
    public void testSuccessInput() {
        final ReclassifyAlgorithmRequest request = new ReclassifyAlgorithmRequest();
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
        final ReclassifyAlgorithmRequest request = new ReclassifyAlgorithmRequest();
        request.setAlgorithmId(ID);
        request.setNewClassificationId(INVALID_CLASSIFICATION_ID);

        try {
            testFailInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid: " + e.getMessage());
        }
    }

    private void testSuccessInput(ReclassifyAlgorithmRequest reclassifyAlgorithmRequest) throws IOException {
        final ReclassifyAlgorithmHandler reclassifyAlgorithmHandler = new ReclassifyAlgorithmHandler();
        final GenericResponse response = reclassifyAlgorithmHandler.handleRequest(
                reclassifyAlgorithmRequest, createContext("reclassify")
        );

        final AlgorithmGetHandler algorithmGetHandler = new AlgorithmGetHandler();
        final LoadAlgorithmRequest loadAlgorithmRequest = new LoadAlgorithmRequest();
        loadAlgorithmRequest.setId(reclassifyAlgorithmRequest.getAlgorithmId());
        final LoadAlgorithmResponse loadAlgorithmResponse = (LoadAlgorithmResponse) algorithmGetHandler.handleRequest(
                loadAlgorithmRequest, createContext("get")
        );

        assertEquals(reclassifyAlgorithmRequest.getAlgorithmId(), loadAlgorithmResponse.getId());
        assertEquals(reclassifyAlgorithmRequest.getNewClassificationId(), loadAlgorithmResponse.getClassificationId());
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(ReclassifyAlgorithmRequest reclassifyAlgorithmRequest) throws IOException {
        final ReclassifyAlgorithmHandler algorithmAddHandler = new ReclassifyAlgorithmHandler();
        final GenericResponse response = algorithmAddHandler.handleRequest(
                reclassifyAlgorithmRequest, createContext("reclassify")
        );

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}
