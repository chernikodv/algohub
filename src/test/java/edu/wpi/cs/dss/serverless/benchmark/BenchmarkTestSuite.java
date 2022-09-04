package edu.wpi.cs.dss.serverless.benchmark;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BenchmarkRemoveHandlerTest.class,
        BenchmarkAddHandlerTest.class,
        BenchmarkGetByImplementationHandlerTest.class
})
public class BenchmarkTestSuite {
}
