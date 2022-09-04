package edu.wpi.cs.dss.serverless.probleminstance;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import edu.wpi.cs.dss.serverless.probleminstance.http.ProblemInstanceAddRequest;
import edu.wpi.cs.dss.serverless.probleminstance.http.ProblemInstanceAddResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProblemInstanceAddHandlerTest extends LambdaTest {

    @Test
    public void testValidProblemInstanceAdd() {
        final ProblemInstanceAddRequest problemInstanceAddRequest = new ProblemInstanceAddRequest();
        problemInstanceAddRequest.setDatasetFilename("junit-test-file-name-3");
        problemInstanceAddRequest.setAuthorId("junit-test-authorId");
        problemInstanceAddRequest.setDatasetSize("128");
        problemInstanceAddRequest.setProblemType("junit-test-type");
        problemInstanceAddRequest.setAlgorithmId("52cac455-5bb3-11ec-933c-16c4115dd1ff");
        problemInstanceAddRequest.setSourceCodeBase64("YXNrZGxqZmthanNkZmxramFzbGtkamY=");

        try {
            testInput(problemInstanceAddRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidProblemInstanceAdd() {
        final ProblemInstanceAddRequest problemInstanceAddRequest = new ProblemInstanceAddRequest();
        problemInstanceAddRequest.setDatasetFilename("junit-test-file-name-3");
        problemInstanceAddRequest.setAuthorId("junit-test-authorId");
        problemInstanceAddRequest.setDatasetSize("128");
        problemInstanceAddRequest.setProblemType("junit-test-type");
        problemInstanceAddRequest.setAlgorithmId("bad-algorithm-id"); // foreign key restriction
        problemInstanceAddRequest.setSourceCodeBase64("YXNrZGxqZmthanNkZmxramFzbGtkamY=");

        try {
            testFailInput(problemInstanceAddRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    private void testInput(ProblemInstanceAddRequest problemInstanceAddRequest) throws IOException {
        final ProblemInstanceAddHandler problemInstanceAddHandler = new ProblemInstanceAddHandler();
        final ProblemInstanceAddResponse problemInstanceAddResponse = (ProblemInstanceAddResponse) problemInstanceAddHandler
                .handleRequest(problemInstanceAddRequest, createContext("add problem instance")
        );

        assertTrue(problemInstanceAddResponse.getId().length() > 0);
        assertEquals(Integer.valueOf(200), problemInstanceAddResponse.getStatusCode());
    }

    private void testFailInput(ProblemInstanceAddRequest problemInstanceAddRequest) throws IOException {
        final ProblemInstanceAddHandler problemInstanceAddHandler = new ProblemInstanceAddHandler();
        final GenericResponse genericResponse = problemInstanceAddHandler.handleRequest(
                problemInstanceAddRequest, createContext("add")
        );

        assertTrue(genericResponse.getError().length() > 0);
        assertEquals(new Integer(400), genericResponse.getStatusCode());
    }
}
