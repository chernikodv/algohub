package edu.wpi.cs.dss.serverless.user;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import edu.wpi.cs.dss.serverless.generic.GenericResponse;
import edu.wpi.cs.dss.serverless.user.http.UserRemoveDataRequest;
import edu.wpi.cs.dss.serverless.util.DataSource;
import edu.wpi.cs.dss.serverless.util.ErrorMessage;
import edu.wpi.cs.dss.serverless.util.HttpStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRemoveDataHandler implements RequestHandler<UserRemoveDataRequest, GenericResponse> {

    private LambdaLogger logger;

    @Override
    public GenericResponse handleRequest(UserRemoveDataRequest request, Context context) {
        logger = context.getLogger();
        logger.log("Received an delete user data request from AWS Lambda:\n" + request);

        // save problem instance to the database
        final GenericResponse response = removeData(request);
        logger.log("Sent an delete user data response to AWS Lambda:\n" + response);

        return response;
    }

    private GenericResponse removeData(UserRemoveDataRequest request) {
        final String username = request.getUsername();

        try (final Connection connection = DataSource.getConnection(logger)) {
            removeData("classification", username, connection);
            removeData("algorithm", username, connection);
            removeData("implementation", username, connection);
            removeData("benchmark", username, connection);
            removeData("problem_instance", username, connection);

            return GenericResponse.builder()
                    .statusCode(HttpStatus.SUCCESS.getValue())
                    .build();

        } catch (SQLException e) {
            logger.log(ErrorMessage.SQL_EXECUTION_EXCEPTION.getValue());
            return GenericResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.getValue())
                    .error(ErrorMessage.SQL_EXECUTION_EXCEPTION.getValue())
                    .build();
        }
    }

    private void removeData(String type, String username, Connection connection) throws SQLException {
        final String query = "DELETE FROM " + type + " WHERE author_id = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            logger.log("Successfully connected to db!");

            preparedStatement.setString(1, username);
            final int rowsAffected = preparedStatement.executeUpdate();

            logger.log("Delete from " + type + " table statement has affected " + rowsAffected + " rows!");
        }
    }
}
