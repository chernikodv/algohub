package edu.wpi.cs.dss.serverless.probleminstance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProblemInstanceGetHandlerTest.class,
        ProblemInstanceGetByAlgorithmHandlerTest.class,
        ProblemInstanceRemoveHandlerTest.class
})
public class ProblemInstanceTestSuite {

}