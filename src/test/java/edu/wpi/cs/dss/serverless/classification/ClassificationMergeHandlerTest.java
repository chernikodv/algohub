package edu.wpi.cs.dss.serverless.classification;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.classification.http.ClassificationAddRequest;
import edu.wpi.cs.dss.serverless.classification.http.ClassificationAddResponse;
import edu.wpi.cs.dss.serverless.classification.http.ClassificationMergeRequest;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ClassificationMergeHandlerTest extends LambdaTest {

    @Test
    public void testValidClassificationMerge() {
        final String classificationId = createClassification(null);
        final String anotherClassificationId = createClassification(null);

        final ClassificationMergeRequest mergeRequest = new ClassificationMergeRequest();
        mergeRequest.setSourceId(classificationId);
        mergeRequest.setSourceId(anotherClassificationId);

        try {
            testInput(mergeRequest);
        } catch (IOException e) {
            Assert.fail("Invalid merge classification: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidClassificationMerge() {
        final String parentClassificationId = createClassification(null);
        final String childClassificationId = createClassification(parentClassificationId);

        final ClassificationMergeRequest mergeRequest = new ClassificationMergeRequest();
        mergeRequest.setSourceId(parentClassificationId);
        mergeRequest.setTargetId(childClassificationId);

        try {
            testFailInput(mergeRequest);
        } catch (IOException e) {
            Assert.fail("Invalid merge classification: " + e.getMessage());
        }
    }

    private void testInput(ClassificationMergeRequest validRequest) throws IOException {
        final ClassificationMergeHandler handler = new ClassificationMergeHandler();
        final GenericResponse response = handler.handleRequest(
                validRequest, createContext("merge hierarchy")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(ClassificationMergeRequest invalidRequest) throws IOException {
        final ClassificationMergeHandler handler = new ClassificationMergeHandler();
        final GenericResponse response = handler.handleRequest(
                invalidRequest, createContext("merge hierarchy")
        );

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }

    private String createClassification(String parent) {
        final ClassificationAddRequest addRequest = new ClassificationAddRequest();
        addRequest.setAuthorId("junit-test-authorId");
        addRequest.setName("valid-classification-name");
        addRequest.setParentId(parent);

        final ClassificationAddHandler addHandler = new ClassificationAddHandler();
        final ClassificationAddResponse addResponse = (ClassificationAddResponse) addHandler.handleRequest(
                addRequest, createContext("add")
        );

        assertEquals(Integer.valueOf(200), addResponse.getStatusCode());
        return addResponse.getId();
    }
}