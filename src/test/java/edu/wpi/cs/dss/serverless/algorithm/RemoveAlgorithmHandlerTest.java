package edu.wpi.cs.dss.serverless.algorithm;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.generic.GenericRemoveRequest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RemoveAlgorithmHandlerTest extends LambdaTest {

    @Test
    public void testSuccessInput() {
        final GenericRemoveRequest request = new GenericRemoveRequest();
        request.setId("16ac8e25-4a75-462f-a12e-f0d5395df9de");

        try {
            testSuccessInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid: " + e.getMessage());
        }
    }

    @Test
    public void testFailInput() {
        final GenericRemoveRequest request = new GenericRemoveRequest();
        request.setId("16ac5");

        try {
            testFailInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid: " + e.getMessage());
        }
    }

    private void testSuccessInput(GenericRemoveRequest request) throws IOException {
        final RemoveAlgorithmHandler handler = new RemoveAlgorithmHandler();
        final GenericResponse response =  handler.handleRequest(request, createContext("remove"));

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(GenericRemoveRequest request) throws IOException {
        final RemoveAlgorithmHandler handler = new RemoveAlgorithmHandler();
        final GenericResponse response =  handler.handleRequest(request, createContext("remove"));

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }
}