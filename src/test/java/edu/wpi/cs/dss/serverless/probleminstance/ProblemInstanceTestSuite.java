package edu.wpi.cs.dss.serverless.probleminstance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoadProblemInstanceHandlerTest.class,
        LoadProblemInstanceByAlgorithmHandlerTest.class,
        RemoveProblemInstanceHandlerTest.class
})
public class ProblemInstanceTestSuite {

}