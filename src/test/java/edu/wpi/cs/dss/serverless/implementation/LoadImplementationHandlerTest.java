package edu.wpi.cs.dss.serverless.implementation;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import edu.wpi.cs.dss.serverless.implementation.http.CreateImplementationRequest;
import edu.wpi.cs.dss.serverless.implementation.http.CreateImplementationResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoadImplementationHandlerTest extends LambdaTest {

    @Test
    public void testValidInput(){
        final CreateImplementationRequest validRequest = new CreateImplementationRequest();
        validRequest.setName("Quick Sort by Chernikov");
        validRequest.setAuthorId("Author ID");
        validRequest.setExtension(".java");
        validRequest.setAlgorithmId("1bedd00f-68e5-48c2-8bca-624fc2fc425f");
        validRequest.setAlgorithmName("Quick Sort");
        validRequest.setSourceCodeBase64("base64 source code");

        try {
            testValidInput(validRequest);
        } catch (IOException e) {
            fail("Invalid: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidInput() {
        final CreateImplementationRequest invalidRequest = new CreateImplementationRequest();
        invalidRequest.setName("Quick Sort by Chernikov");
        invalidRequest.setAuthorId("Author ID");
        invalidRequest.setExtension(".java");
        invalidRequest.setAlgorithmId(null);
        invalidRequest.setAlgorithmName("Quick Sort");
        invalidRequest.setSourceCodeBase64("base64 source code");

        try {
            testInvalidInput(invalidRequest);
        } catch (IOException e) {
            fail("Invalid: " + e.getMessage());
        }

    }

    private void testValidInput(CreateImplementationRequest validRequest) throws IOException {
        final LoadImplementationHandler handler = new LoadImplementationHandler();
        final CreateImplementationResponse response = (CreateImplementationResponse) handler.handleRequest(
                validRequest, createContext("add")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testInvalidInput(CreateImplementationRequest invalidRequest) throws IOException {
        final LoadImplementationHandler handler = new LoadImplementationHandler();
        final GenericResponse response = handler.handleRequest(invalidRequest, createContext("add"));

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}