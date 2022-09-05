package edu.wpi.cs.dss.serverless.classification;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.dss.serverless.classification.http.LoadClassificationHierarchyRequest;
import edu.wpi.cs.dss.serverless.classification.http.LoadClassificationHierarchyResponse;
import edu.wpi.cs.dss.serverless.classification.model.HierarchyEntry;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import edu.wpi.cs.dss.serverless.util.DataSource;
import edu.wpi.cs.dss.serverless.util.ErrorMessage;
import edu.wpi.cs.dss.serverless.util.HttpStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassificationHierarchyHandler implements RequestHandler<LoadClassificationHierarchyRequest, GenericResponse> {

    private LambdaLogger logger;

    @Override
    public GenericResponse handleRequest(LoadClassificationHierarchyRequest request, Context context) {
        logger = context.getLogger();
        logger.log("Received a get classification request from AWS Lambda:\n" + request);

        // get classification hierarchy
        final GenericResponse response = getHierarchy();
        logger.log("Sent a get classification response to AWS Lambda:\n" + response);

        return response;
    }

    private GenericResponse getHierarchy() {
        final String query =
                "SELECT id, parent_id, name, 'classification' AS type FROM classification" +
                " UNION " +
                "SELECT id, classification_id AS parent_id, name, 'algorithm' AS type FROM algorithm" +
                " UNION " +
                "SELECT id, algorithm_id AS parent_id, name, 'implementation' AS type FROM implementation";

        try (final Connection connection = DataSource.getConnection(logger);
             final PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            logger.log("Successfully connected to db!");

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                final List<HierarchyEntry> entries = new ArrayList<>();

                while (resultSet.next()) {
                    final String id = resultSet.getString(1);
                    final String parentId = resultSet.getString(2);
                    final String name = resultSet.getString(3);
                    final String type = resultSet.getString(4);

                    final HierarchyEntry hierarchy = new HierarchyEntry(id, name, parentId, type);
                    entries.add(hierarchy);
                }

                return LoadClassificationHierarchyResponse.builder()
                        .statusCode(HttpStatus.SUCCESS.getValue())
                        .hierarchy(entries)
                        .build();
            }

        } catch (SQLException e) {
            logger.log(ErrorMessage.SQL_EXECUTION_EXCEPTION.getValue());
            return GenericResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.getValue())
                    .error(ErrorMessage.SQL_EXECUTION_EXCEPTION.getValue())
                    .build();
        }
    }
}
