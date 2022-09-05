# AlgoHub

**Name:** Dmitriy Chernikov

**Email:** chernikov.d@northeastern.edu

**Preferred Name:** Dmitriy Chernikov

### About/Overview

It is the ultimate algorithm analysis and sharing platform.
The main idea of this platform is to keep various well-structured algorithms along with implementations, data sets, and benchmarks in one place.
The platform allows users to create algorithms, upload/download implementations/data sets, provide metrics for running time analysis, and other stuff.
It also tracks users' activity for administrators to look through it.

### Prerequisites

1) `Java 8`
2) `Apache Maven`
3) `Amazon Web Services`

### Technology stack

1) `Java 8, MySQL`
2) `Lombok, Swagger, Apache Maven`
3) `AWS RDS, Lambda, S3, API Gateway, Cognito`

### List of Features

1) `GET /classifications/{id}`: looking for a classification by its id
2) `GET /classifications/hierarchy` loading classifications' hierarchy
3) `POST /classifications/create` creating a classification (for authenticated users only)
4) `PUT /classifications/merge` merging target classification into source classification (for authenticated users only)
5) `DELETE /classifications/{id}` removing a classification by its id (for authenticated users only)
6) `GET /algorithms/{id}`: looking for an algorithm by its id
7) `POST /algorithms/create`: creating an algorithm (for authenticated users only)
8) `PUT /algorithms/reclassify`: updating algorithm's classification (for authenticated users only)
9) `DELETE /algorithms/remove/{id}`: removing an algorithm by its id (for authenticated users only)
10) `GET /implementations/{id}`: looking for an implementation by its id
11) `POST /implementations/create`: creating an implementation (for authenticated users only)
12) `DELETE /implementations/remove`: removing an implementation by its id (for authenticated users only)
13) `GET /problem-instances/{id}`: looking for a problem instance by its id
14) `GET /problem-instances/by-algorithm/{id}`: looking for a problem instance by associated algorithm's id
15) `POST /problem-instances/create`: creating a problem instance (for authenticated users only)
16) `DELETE /problem-instances/remove/{id}`: removing a problem instance by its id (for authenticated users only)
17) `GET /benchmarks/by-implementation/{id}`: looking for a benchmark by associated implementation's id
18) `POST /benchmarks/create`: creating a benchmark (for authenticated users only)
19) `DELETE /benchmarks/remove/{id}`: removing a benchmark by it id (for authenticated users only)
20) `GET /users`: loading all registered users (for administrators only)
21) `GET /users/{username}`: loading activity of a user by its username (for administrators only)
22) `DELETE /users/data/remove/{username}`: remove data of a user by its username (for administrators only)
23) `DELETE /users/account/remove/{username}`: remove account of a user by its username (for administrators only)

To see the detailed information about endpoints, open `https://editor.swagger.io/` and paste the content of `/res/api-specification.yaml`.

### How to Run

1) Open a terminal in any folder you like and run the following command
2) `git clone https://github.com/chernikodv/algohub-backend`
3) Set up `AWS RDS, Lambda, S3, API Gateway, Cognito`
4) Run `/res/schema.sql` to configure `AWS RDS` and upload `/res/api-specification.yaml` to configure `AWS API Gateway`
5) Use `AWS Lambda` or `AWS API Gateway` to send requests and analyse responses

### How to Develop

1) Open a terminal in any folder you like and run the following command
2) `git clone https://github.com/chernikodv/algohub-backend`
3) Set up `AWS RDS, Lambda, S3, API Gateway, Cognito`
4) Run `/res/schema.sql` to configure `AWS RDS` and upload `/res/api-specification.yaml` to configure `AWS API Gateway`
5) Make any changes you want, re-upload related lambda, and use `AWS Lambda` or `AWS API Gateway` to send requests and analyse responses
