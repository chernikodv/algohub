package edu.wpi.cs.dss.serverless.implementation;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.algorithm.AlgorithmAddHandler;
import edu.wpi.cs.dss.serverless.algorithm.http.AlgorithmAddRequest;
import edu.wpi.cs.dss.serverless.algorithm.http.AlgorithmAddResponse;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import edu.wpi.cs.dss.serverless.implementation.http.ImplementationGetRequest;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ImplementationGetHandlerTest extends LambdaTest {

    @Test
    public void testImplementationGet() {
        try {
            final ImplementationGetRequest validRequest = new ImplementationGetRequest();
            final String id = addAlgorithm();
            validRequest.setId(id);

            testValidInput(validRequest);
        } catch (IOException e) {
            fail("Invalid: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidInput(){
        final ImplementationGetRequest invalidRequest = new ImplementationGetRequest();
        invalidRequest.setId("16ac8e25-4");

        try {
            testInvalidInput(invalidRequest);
        } catch (IOException e) {
            fail("Invalid: " + e.getMessage());
        }
    }

    private String addAlgorithm() {
        final AlgorithmAddRequest request = new AlgorithmAddRequest();
        request.setName("Quick Sort");
        request.setAuthorId("john_smith_sr");
        request.setDescription("Cool sorting algorithm");
        request.setClassificationId("1b53c044-5bb3-11ec-933c-16c4115dd1ff");

        final AlgorithmAddHandler handler = new AlgorithmAddHandler();
        final AlgorithmAddResponse response = (AlgorithmAddResponse) handler.handleRequest(
                request, createContext("add algorithm")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
        return response.getId();
    }

    private void testValidInput(ImplementationGetRequest validRequest) throws IOException {
        final ImplementationGetHandler handler = new ImplementationGetHandler();
        final GenericResponse response = handler.handleRequest(validRequest, createContext("get"));

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testInvalidInput(ImplementationGetRequest invalidRequest) throws IOException {
        final ImplementationGetHandler handler = new ImplementationGetHandler();
        final GenericResponse response = handler.handleRequest(invalidRequest, createContext("get"));

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}