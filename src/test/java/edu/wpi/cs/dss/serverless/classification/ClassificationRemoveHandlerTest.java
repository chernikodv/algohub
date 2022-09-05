package edu.wpi.cs.dss.serverless.classification;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.classification.http.CreateClassificationRequest;
import edu.wpi.cs.dss.serverless.classification.http.CreateClassificationResponse;
import edu.wpi.cs.dss.serverless.generic.GenericRemoveRequest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ClassificationRemoveHandlerTest extends LambdaTest {

    @Test
    public void testValidClassificationRemove() {
        try {
            final GenericRemoveRequest request = new GenericRemoveRequest();
            final String id = addClassification();
            request.setId(id);

            testValidInput(request);
        } catch (IOException e) {
            fail("Invalid remove classification: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidClassificationRemove() {
        final GenericRemoveRequest invalidRemoveRequest = new GenericRemoveRequest();
        invalidRemoveRequest.setId("invalid id");

        try {
            testInvalidInput(invalidRemoveRequest);
        } catch (IOException e) {
            fail("Invalid remove classification: " + e.getMessage());
        }
    }

    private String addClassification() {
        // add before remove
        final CreateClassificationRequest request = new CreateClassificationRequest();
        request.setAuthorId("junit-test-authorId");
        request.setName("valid-classification-name");
        request.setParentId(null);

        final ClassificationAddHandler handler = new ClassificationAddHandler();
        final CreateClassificationResponse response = (CreateClassificationResponse) handler.handleRequest(
                request, createContext("add")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
        return response.getId();
    }

    private void testValidInput(GenericRemoveRequest validRemoveRequest) throws IOException {
        final ClassificationRemoveHandler handler = new ClassificationRemoveHandler();
        final GenericResponse response = handler.handleRequest(
                validRemoveRequest, createContext("remove classification")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testInvalidInput(GenericRemoveRequest invalidRemoveRequest) throws IOException {
        final ClassificationRemoveHandler handler = new ClassificationRemoveHandler();
        final GenericResponse response = handler.handleRequest(
                invalidRemoveRequest, createContext("remove classification")
        );

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}