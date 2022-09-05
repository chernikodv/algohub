package edu.wpi.cs.dss.serverless.algorithm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateAlgorithmHandlerTest.class,
        AlgorithmGetHandlerTest.class,
        ReclassifyAlgorithmHandlerTest.class,
        RemoveAlgorithmHandlerTest.class
})
public class AlgorithmTestSuite {
}
