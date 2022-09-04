package edu.wpi.cs.dss.serverless.probleminstance.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
@NoArgsConstructor
public class ProblemInstanceAddRequest {

    private String authorId;
    private String datasetSize;
    private String problemType;
    private String algorithmId;
    private String datasetFilename;
    private String sourceCodeBase64;

    @Override
    @SneakyThrows
    public String toString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(this);
    }
}