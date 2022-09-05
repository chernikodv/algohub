package edu.wpi.cs.dss.serverless.probleminstance;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import edu.wpi.cs.dss.serverless.probleminstance.http.CreateProblemInstanceRequest;
import edu.wpi.cs.dss.serverless.probleminstance.http.CreateProblemInstanceResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateProblemInstanceHandlerTest extends LambdaTest {

    @Test
    public void testValidProblemInstanceAdd() {
        final CreateProblemInstanceRequest createProblemInstanceRequest = new CreateProblemInstanceRequest();
        createProblemInstanceRequest.setDatasetFilename("junit-test-file-name-3");
        createProblemInstanceRequest.setAuthorId("junit-test-authorId");
        createProblemInstanceRequest.setDatasetSize("128");
        createProblemInstanceRequest.setProblemType("junit-test-type");
        createProblemInstanceRequest.setAlgorithmId("52cac455-5bb3-11ec-933c-16c4115dd1ff");
        createProblemInstanceRequest.setSourceCodeBase64("YXNrZGxqZmthanNkZmxramFzbGtkamY=");

        try {
            testInput(createProblemInstanceRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidProblemInstanceAdd() {
        final CreateProblemInstanceRequest createProblemInstanceRequest = new CreateProblemInstanceRequest();
        createProblemInstanceRequest.setDatasetFilename("junit-test-file-name-3");
        createProblemInstanceRequest.setAuthorId("junit-test-authorId");
        createProblemInstanceRequest.setDatasetSize("128");
        createProblemInstanceRequest.setProblemType("junit-test-type");
        createProblemInstanceRequest.setAlgorithmId("bad-algorithm-id"); // foreign key restriction
        createProblemInstanceRequest.setSourceCodeBase64("YXNrZGxqZmthanNkZmxramFzbGtkamY=");

        try {
            testFailInput(createProblemInstanceRequest);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    private void testInput(CreateProblemInstanceRequest createProblemInstanceRequest) throws IOException {
        final CreateProblemInstanceHandler createProblemInstanceHandler = new CreateProblemInstanceHandler();
        final CreateProblemInstanceResponse createProblemInstanceResponse = (CreateProblemInstanceResponse) createProblemInstanceHandler
                .handleRequest(createProblemInstanceRequest, createContext("add problem instance")
        );

        assertTrue(createProblemInstanceResponse.getId().length() > 0);
        assertEquals(Integer.valueOf(200), createProblemInstanceResponse.getStatusCode());
    }

    private void testFailInput(CreateProblemInstanceRequest createProblemInstanceRequest) throws IOException {
        final CreateProblemInstanceHandler createProblemInstanceHandler = new CreateProblemInstanceHandler();
        final GenericResponse genericResponse = createProblemInstanceHandler.handleRequest(
                createProblemInstanceRequest, createContext("add")
        );

        assertTrue(genericResponse.getError().length() > 0);
        assertEquals(new Integer(400), genericResponse.getStatusCode());
    }
}
