package edu.wpi.cs.dss.serverless.probleminstance;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.probleminstance.http.LoadProblemInstanceByAlgorithmRequest;
import edu.wpi.cs.dss.serverless.probleminstance.http.LoadProblemInstanceByAlgorithmResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoadProblemInstanceByAlgorithmHandlerTest extends LambdaTest {

    @Test
    public void testValidProblemInstanceGet() {
        final LoadProblemInstanceByAlgorithmRequest loadProblemInstanceByAlgorithmRequest = new LoadProblemInstanceByAlgorithmRequest();
        loadProblemInstanceByAlgorithmRequest.setId("52cac455-5bb3-11ec-933c-16c4115dd1ff");

        try {
            testInput(loadProblemInstanceByAlgorithmRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidProblemInstanceGetByAlgorithm() {
        final LoadProblemInstanceByAlgorithmRequest loadProblemInstanceByAlgorithmRequest = new LoadProblemInstanceByAlgorithmRequest();
        loadProblemInstanceByAlgorithmRequest.setId("Bad ID");

        try {
            testFailInput(loadProblemInstanceByAlgorithmRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    private void testInput(LoadProblemInstanceByAlgorithmRequest request) throws IOException {
        final LoadProblemInstanceByAlgorithmHandler handler = new LoadProblemInstanceByAlgorithmHandler();
        final LoadProblemInstanceByAlgorithmResponse response = (LoadProblemInstanceByAlgorithmResponse) handler.handleRequest(
                request, createContext("get problem instance by algorithm")
        );

        assertTrue(response.getProblemInstances().size() > 0);
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(LoadProblemInstanceByAlgorithmRequest request) throws IOException {
        final LoadProblemInstanceByAlgorithmHandler handler = new LoadProblemInstanceByAlgorithmHandler();
        final LoadProblemInstanceByAlgorithmResponse response = (LoadProblemInstanceByAlgorithmResponse) handler.handleRequest(
                request, createContext("get problem instance by algorithm")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
        assertEquals(0, response.getProblemInstances().size());
    }
}
