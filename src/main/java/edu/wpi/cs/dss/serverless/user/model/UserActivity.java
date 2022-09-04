package edu.wpi.cs.dss.serverless.user.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(of = "id")
public class UserActivity {
    String id;
    String name;
    String typeName;
}
