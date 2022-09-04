package edu.wpi.cs.dss.serverless.algorithm;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.algorithm.http.AlgorithmGetRequest;
import edu.wpi.cs.dss.serverless.algorithm.http.AlgorithmGetResponse;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AlgorithmGetHandlerTest extends LambdaTest {

    private static final String NAME = "BFS";
    private static final String DESCRIPTION = "Breadth-First Search";
    private static final String AUTHOR_ID = "1b53c068-5bb3-11ec-933c-16c4115dd1ff";
    private static final String CLASSIFICATION_ID = "1b53c068-5bb3-11ec-933c-16c4115dd1ff";

    @Test
    public void testAlgorithmGet() {
        final AlgorithmGetRequest request = new AlgorithmGetRequest();
        request.setId("52cac455-5bb3-11ec-933c-16c4115dd1ff");

        try {
            testSuccessInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid: " + e.getMessage());
        }
    }

    @Test
    public void testFailInput(){
        final AlgorithmGetRequest request = new AlgorithmGetRequest();
        request.setId("16ac8e25-4a75-462f-a12e-f0d5395d909de");

        try {
            testFailInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid: " + e.getMessage());
        }
    }

    private void testSuccessInput(AlgorithmGetRequest request) throws IOException {
        final AlgorithmGetHandler handler = new AlgorithmGetHandler();
        final AlgorithmGetResponse response = (AlgorithmGetResponse) handler.handleRequest(
                request, createContext("get")
        );

        assertEquals(request.getId(), response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(AUTHOR_ID, response.getAuthorId());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(CLASSIFICATION_ID, response.getClassificationId());
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(AlgorithmGetRequest request) throws IOException {
        final AlgorithmGetHandler handler = new AlgorithmGetHandler();
        final GenericResponse response = handler.handleRequest(request, createContext("get"));

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}

