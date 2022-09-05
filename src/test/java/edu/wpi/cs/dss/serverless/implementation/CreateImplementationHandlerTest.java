package edu.wpi.cs.dss.serverless.implementation;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.algorithm.CreateAlgorithmHandler;
import edu.wpi.cs.dss.serverless.algorithm.http.CreateAlgorithmRequest;
import edu.wpi.cs.dss.serverless.algorithm.http.CreateAlgorithmResponse;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import edu.wpi.cs.dss.serverless.implementation.http.LoadImplementationRequest;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CreateImplementationHandlerTest extends LambdaTest {

    @Test
    public void testImplementationGet() {
        try {
            final LoadImplementationRequest validRequest = new LoadImplementationRequest();
            final String id = addAlgorithm();
            validRequest.setId(id);

            testValidInput(validRequest);
        } catch (IOException e) {
            fail("Invalid: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidInput(){
        final LoadImplementationRequest invalidRequest = new LoadImplementationRequest();
        invalidRequest.setId("16ac8e25-4");

        try {
            testInvalidInput(invalidRequest);
        } catch (IOException e) {
            fail("Invalid: " + e.getMessage());
        }
    }

    private String addAlgorithm() {
        final CreateAlgorithmRequest request = new CreateAlgorithmRequest();
        request.setName("Quick Sort");
        request.setAuthorId("john_smith_sr");
        request.setDescription("Cool sorting algorithm");
        request.setClassificationId("1b53c044-5bb3-11ec-933c-16c4115dd1ff");

        final CreateAlgorithmHandler handler = new CreateAlgorithmHandler();
        final CreateAlgorithmResponse response = (CreateAlgorithmResponse) handler.handleRequest(
                request, createContext("add algorithm")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
        return response.getId();
    }

    private void testValidInput(LoadImplementationRequest validRequest) throws IOException {
        final CreateImplementationHandler handler = new CreateImplementationHandler();
        final GenericResponse response = handler.handleRequest(validRequest, createContext("get"));

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testInvalidInput(LoadImplementationRequest invalidRequest) throws IOException {
        final CreateImplementationHandler handler = new CreateImplementationHandler();
        final GenericResponse response = handler.handleRequest(invalidRequest, createContext("get"));

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}