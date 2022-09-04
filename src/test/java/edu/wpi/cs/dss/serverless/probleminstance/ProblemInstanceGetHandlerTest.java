package edu.wpi.cs.dss.serverless.probleminstance;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import edu.wpi.cs.dss.serverless.probleminstance.http.ProblemInstanceGetRequest;
import edu.wpi.cs.dss.serverless.probleminstance.http.ProblemInstanceGetResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ProblemInstanceGetHandlerTest extends LambdaTest {

    @Test
    public void testValidProblemInstanceGet() {
        final ProblemInstanceGetRequest request = new ProblemInstanceGetRequest();
        request.setId("1");

        try {
            testInput(request);
        } catch (IOException e) {
            fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidProblemInstanceGet() {
        final ProblemInstanceGetRequest request = new ProblemInstanceGetRequest();
        request.setId("Invalid ID");

        try {
            testFailInput(request);
        } catch (IOException e) {
            fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    void testInput(ProblemInstanceGetRequest request) throws IOException {
        final ProblemInstanceGetHandler handler = new ProblemInstanceGetHandler();
        final ProblemInstanceGetResponse response = (ProblemInstanceGetResponse) handler.handleRequest(
                request, createContext("get problem instance")
        );

        assertEquals("test-file-name", response.getDatasetFilename());
        assertEquals("128", response.getDatasetSize());
        assertEquals("test-type", response.getProblemType());
        assertEquals("52cac455-5bb3-11ec-933c-16c4115dd1ff", response.getAlgorithmId());
        assertEquals("test-author-id", response.getAuthorId());
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    void testFailInput(ProblemInstanceGetRequest request) throws IOException {
        final ProblemInstanceGetHandler handler = new ProblemInstanceGetHandler();
        final GenericResponse response = handler.handleRequest(
                request, createContext("get problem instance")
        );

        assertTrue(response.getError().length() > 0);
        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}
