package edu.wpi.cs.dss.serverless.classification;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.classification.http.ClassificationHierarchyRequest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ClassificationGetHierarchyTest extends LambdaTest {

    @Test
    public void testValidClassificationGetHierarchy() {
        final ClassificationHierarchyRequest request = new ClassificationHierarchyRequest();

        try {
            testInput(request);
        } catch (IOException e) {
            fail("Invalid get classification hierarchy: " + e.getMessage());
        }
    }

    private void testInput(ClassificationHierarchyRequest request) throws IOException {
        final ClassificationHierarchyHandler handler = new ClassificationHierarchyHandler();
        final GenericResponse response = handler.handleRequest(
                request, createContext("get hierarchy")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }
}