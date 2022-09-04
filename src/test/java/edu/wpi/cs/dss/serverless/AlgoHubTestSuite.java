package edu.wpi.cs.dss.serverless;

import edu.wpi.cs.dss.serverless.algorithm.AlgorithmTestSuite;
import edu.wpi.cs.dss.serverless.benchmark.BenchmarkTestSuite;
import edu.wpi.cs.dss.serverless.probleminstance.ProblemInstanceTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AlgorithmTestSuite.class,
        BenchmarkTestSuite.class,
        ProblemInstanceTestSuite.class
})
public class AlgoHubTestSuite {
}
