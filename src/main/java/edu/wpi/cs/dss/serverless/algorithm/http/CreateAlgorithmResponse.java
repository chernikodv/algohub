package edu.wpi.cs.dss.serverless.algorithm.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CreateAlgorithmResponse extends GenericResponse {

    private final String id;
    private final String name;
    private final String authorId;
    private final String description;
    private final String classificationId;

    @Override
    @SneakyThrows
    public String toString() {
        final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(this);
    }
}
