package edu.wpi.cs.dss.serverless.algorithm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AlgorithmAddHandlerTest.class,
        AlgorithmGetHandlerTest.class,
        AlgorithmReclassifyHandlerTest.class,
        AlgorithmRemoveHandlerTest.class
})
public class AlgorithmTestSuite {
}
