package edu.wpi.cs.dss.serverless.benchmark.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
@NoArgsConstructor
public class CreateBenchmarkRequest {

    private String cpuName;
    private String authorId;
    private String implementationId;
    private String problemInstanceId;

    private Integer memory;
    private Integer cpuCores;
    private Integer cpuThreads;
    private Integer cpuL1Cache;
    private Integer cpuL2Cache;
    private Integer cpuL3Cache;
    private Integer memoryUsage;
    private Integer executionTime;

    @Override
    @SneakyThrows
    public String toString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(this);
    }
}
