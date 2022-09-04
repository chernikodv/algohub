package edu.wpi.cs.dss.serverless.probleminstance;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.probleminstance.http.ProblemInstanceGetByAlgorithmRequest;
import edu.wpi.cs.dss.serverless.probleminstance.http.ProblemInstanceGetByAlgorithmResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProblemInstanceGetByAlgorithmHandlerTest extends LambdaTest {

    @Test
    public void testValidProblemInstanceGet() {
        final ProblemInstanceGetByAlgorithmRequest problemInstanceGetByAlgorithmRequest = new ProblemInstanceGetByAlgorithmRequest();
        problemInstanceGetByAlgorithmRequest.setId("52cac455-5bb3-11ec-933c-16c4115dd1ff");

        try {
            testInput(problemInstanceGetByAlgorithmRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidProblemInstanceGetByAlgorithm() {
        final ProblemInstanceGetByAlgorithmRequest problemInstanceGetByAlgorithmRequest = new ProblemInstanceGetByAlgorithmRequest();
        problemInstanceGetByAlgorithmRequest.setId("Bad ID");

        try {
            testFailInput(problemInstanceGetByAlgorithmRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    private void testInput(ProblemInstanceGetByAlgorithmRequest request) throws IOException {
        final ProblemInstanceGetByAlgorithmHandler handler = new ProblemInstanceGetByAlgorithmHandler();
        final ProblemInstanceGetByAlgorithmResponse response = (ProblemInstanceGetByAlgorithmResponse) handler.handleRequest(
                request, createContext("get problem instance by algorithm")
        );

        assertTrue(response.getProblemInstances().size() > 0);
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(ProblemInstanceGetByAlgorithmRequest request) throws IOException {
        final ProblemInstanceGetByAlgorithmHandler handler = new ProblemInstanceGetByAlgorithmHandler();
        final ProblemInstanceGetByAlgorithmResponse response = (ProblemInstanceGetByAlgorithmResponse) handler.handleRequest(
                request, createContext("get problem instance by algorithm")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
        assertEquals(0, response.getProblemInstances().size());
    }
}
