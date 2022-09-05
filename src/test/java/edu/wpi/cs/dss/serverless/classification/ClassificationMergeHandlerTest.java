package edu.wpi.cs.dss.serverless.classification;

import edu.wpi.cs.dss.serverless.LambdaTest;
import edu.wpi.cs.dss.serverless.classification.http.CreateClassificationRequest;
import edu.wpi.cs.dss.serverless.classification.http.CreateClassificationResponse;
import edu.wpi.cs.dss.serverless.classification.http.MergeClassificationRequest;
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

        final MergeClassificationRequest mergeRequest = new MergeClassificationRequest();
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

        final MergeClassificationRequest mergeRequest = new MergeClassificationRequest();
        mergeRequest.setSourceId(parentClassificationId);
        mergeRequest.setTargetId(childClassificationId);

        try {
            testFailInput(mergeRequest);
        } catch (IOException e) {
            Assert.fail("Invalid merge classification: " + e.getMessage());
        }
    }

    private void testInput(MergeClassificationRequest validRequest) throws IOException {
        final ClassificationMergeHandler handler = new ClassificationMergeHandler();
        final GenericResponse response = handler.handleRequest(
                validRequest, createContext("merge hierarchy")
        );

        assertEquals(Integer.valueOf(200), response.getStatusCode());
    }

    private void testFailInput(MergeClassificationRequest invalidRequest) throws IOException {
        final ClassificationMergeHandler handler = new ClassificationMergeHandler();
        final GenericResponse response = handler.handleRequest(
                invalidRequest, createContext("merge hierarchy")
        );

        assertEquals(Integer.valueOf(400), response.getStatusCode());
    }

    private String createClassification(String parent) {
        final CreateClassificationRequest addRequest = new CreateClassificationRequest();
        addRequest.setAuthorId("junit-test-authorId");
        addRequest.setName("valid-classification-name");
        addRequest.setParentId(parent);

        final ClassificationAddHandler addHandler = new ClassificationAddHandler();
        final CreateClassificationResponse addResponse = (CreateClassificationResponse) addHandler.handleRequest(
                addRequest, createContext("add")
        );

        assertEquals(Integer.valueOf(200), addResponse.getStatusCode());
        return addResponse.getId();
    }
}