package edu.wpi.cs.dss.serverless.classification;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.classification.http.ClassificationAddRequest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ClassificationAddHandlerTest extends LambdaTest {

    @Test
    public void testValidClassificationAdd() {
        final ClassificationAddRequest validRequest = new ClassificationAddRequest();
        validRequest.setAuthorId("junit-test-authorId");
        validRequest.setName("valid-classification-name");
        validRequest.setParentId(null);

        try {
            testInput(validRequest);
        } catch (IOException e) {
            fail("Invalid get classification: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidClassificationAdd() {
        final ClassificationAddRequest invalidRequest = new ClassificationAddRequest();
        invalidRequest.setAuthorId("junit-test-authorId");
        invalidRequest.setName("valid-classification-name");
        invalidRequest.setParentId("non-existent-parent");

        try {
            testFailInput(invalidRequest);
        } catch (IOException e) {
            fail("Invalid get classification: " + e.getMessage());
        }
    }

    private void testInput(ClassificationAddRequest validRequest) throws IOException {
        final ClassificationAddHandler handler = new ClassificationAddHandler();
        final GenericResponse response = handler.handleRequest(
                validRequest, createContext("add classification")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    void testFailInput(ClassificationAddRequest invalidRequest) throws IOException {
        final ClassificationAddHandler handler = new ClassificationAddHandler();
        final GenericResponse response = handler.handleRequest(
                invalidRequest, createContext("add")
        );

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}