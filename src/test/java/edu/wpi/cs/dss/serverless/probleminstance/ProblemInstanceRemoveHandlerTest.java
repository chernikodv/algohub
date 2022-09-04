package edu.wpi.cs.dss.serverless.probleminstance;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.generic.GenericRemoveRequest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import edu.wpi.cs.dss.serverless.probleminstance.http.*;
import edu.wpi.cs.dss.serverless.util.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProblemInstanceRemoveHandlerTest extends LambdaTest {

    @Test
    public void testValidProblemInstanceRemove() {
        try {
            final GenericRemoveRequest request = new GenericRemoveRequest();
            final String id = addProblemInstance();
            request.setId(id);

            testInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance:" + e.getMessage());
        }
    }

    @Test
    public void testInvalidProblemInstanceRemove() {
        final GenericRemoveRequest request = new GenericRemoveRequest();
        request.setId("Bad ID");

        try {
            testFailInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid get problem instance: " + e.getMessage());
        }
    }

    private String addProblemInstance() {
        // add before remove
        final ProblemInstanceAddRequest addRequest = new ProblemInstanceAddRequest();
        addRequest.setDatasetFilename(UUID.randomUUID().toString()); // random file name, avoid overlapping
        addRequest.setAuthorId("junit-test-authorId");
        addRequest.setDatasetSize("128");
        addRequest.setProblemType("junit-test-type");
        addRequest.setAlgorithmId("52cac455-5bb3-11ec-933c-16c4115dd1ff");
        addRequest.setSourceCodeBase64("YXNrZGxqZmthanNkZmxramFzbGtkamY=");

        final ProblemInstanceAddHandler addHandler = new ProblemInstanceAddHandler();
        final ProblemInstanceAddResponse addResponse = (ProblemInstanceAddResponse) addHandler.handleRequest(
                addRequest, createContext("add")
        );

        assertTrue(addResponse.getId().length() > 0);
        assertEquals(Integer.valueOf(200), addResponse.getStatusCode());

        return addResponse.getId();
    }

    private void testInput(GenericRemoveRequest request) throws IOException {
        final ProblemInstanceRemoveHandler handler = new ProblemInstanceRemoveHandler();
        final GenericResponse response = handler.handleRequest(
                request, createContext("remove problem instance")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(GenericRemoveRequest removeRequest) throws IOException {
        final ProblemInstanceRemoveHandler handler = new ProblemInstanceRemoveHandler();
        final GenericResponse response = handler.handleRequest(
                removeRequest, createContext("remove problem instance")
        );

        assertTrue(response.getError().length() > 0);
        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}
