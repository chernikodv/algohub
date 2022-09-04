package edu.wpi.cs.dss.serverless.classification;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClassificationGetHierarchyTest.class,
        ClassificationAddHandlerTest.class,
        ClassificationRemoveHandlerTest.class,
        ClassificationMergeHandlerTest.class
})
public class ClassificationTestSuit {
}
