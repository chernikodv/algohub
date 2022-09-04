package edu.wpi.cs.dss.serverless.algorithm;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.algorithm.http.AlgorithmAddRequest;
import edu.wpi.cs.dss.serverless.algorithm.http.AlgorithmAddResponse;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AlgorithmAddHandlerTest extends LambdaTest {

    @Test
    public void testSuccessInput(){
        final String name = "Quick Sort";
        final String authorId = "john_smith_sr";
        final String description = "Cool sorting algorithm";
        final String classificationId = "1b53c044-5bb3-11ec-933c-16c4115dd1ff";

        final AlgorithmAddRequest request = new AlgorithmAddRequest();
        request.setName(name);
        request.setAuthorId(authorId);
        request.setDescription(description);
        request.setClassificationId(classificationId);

        try {
            testSuccessInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid: " + e.getMessage());
        }
    }

    @Test
    public void testFailInput() {
        final String name = "Quick Sort";
        final String classificationId = "60";
        final String authorId = "john_smith_sr";
        final String description = "Cool sorting algorithm";

        final AlgorithmAddRequest request = new AlgorithmAddRequest();
        request.setName(name);
        request.setAuthorId(authorId);
        request.setDescription(description);
        request.setClassificationId(classificationId);

        try {
            testFailInput(request);
        } catch (IOException e) {
            Assert.fail("Invalid: " + e.getMessage());
        }
    }

    private void testSuccessInput(AlgorithmAddRequest request) throws IOException {
        final AlgorithmAddHandler handler = new AlgorithmAddHandler();
        final AlgorithmAddResponse response = (AlgorithmAddResponse) handler.handleRequest(
                request, createContext("add")
        );

        assertEquals(request.getName(),response.getName());
        assertEquals(request.getAuthorId(),response.getAuthorId());
        assertEquals(request.getDescription(),response.getDescription());
        assertEquals(request.getClassificationId(),response.getClassificationId());
        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(AlgorithmAddRequest request) throws IOException {
        final AlgorithmAddHandler handler = new AlgorithmAddHandler();
        final GenericResponse response = handler.handleRequest(request, createContext("add"));

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }
}
