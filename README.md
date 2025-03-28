[<img src="./images/logo.png" width="400" height="200"/>](./images/logo.png)

# SQL DAO library
[![Build And Sonar scan](https://github.com/eclipse-ecsp/sql-dao/actions/workflows/maven-build.yml/badge.svg)](https://github.com/eclipse-ecsp/sql-dao/actions/workflows/maven-build.yml)
[![License Compliance](https://github.com/eclipse-ecsp/sql-dao/actions/workflows/license-compliance.yml/badge.svg)](https://github.com/eclipse-ecsp/sql-dao/actions/workflows/license-compliance.yml)
[![Deployment](https://github.com/eclipse-ecsp/sql-dao/actions/workflows/maven-deploy.yml/badge.svg)](https://github.com/eclipse-ecsp/sql-dao/actions/workflows/maven-deploy.yml)

`sql-dao` provides `HikariCP` pooled connections to the Postgres DB. It creates, manages and authenticates connection to Postgres DB.
Retry on failure in obtaining a connection is implemented with the configurable retry count.
Graceful shutdown of the connection is ensured whenever a connection is closed.

Apart from providing connections, it also provides the below functionalities to a service.

1. Health Monitoring of Postgres DB.
2. A JDBC template provider to execute queries using JDBC.
3. Extensible Credentials provider to fetch credentials from Vault or from environment properties or a customized implementation.
4. Metrics exporter to export DB connection pool metrics to Prometheus for reliability.

# Table of Contents
* [Getting Started](#getting-started)
* [Usage](#usage)
* [How to contribute](#how-to-contribute)
* [Built with Dependencies](#built-with-dependencies)
* [Code of Conduct](#code-of-conduct)
* [Authors](#authors)
* [Security Contact Information](#security-contact-information)
* [Support](#support)
* [Troubleshooting](#troubleshooting)
* [License](#license)
* [Announcements](#announcements)


## Getting Started

To build the project in the local working directory after the project has been cloned/forked, run:

```mvn clean install```

from the command line interface.

### Prerequisites

1. Maven
2. Java 11

### Installation

[How to set up maven](https://maven.apache.org/install.html)

[Install Java](https://stackoverflow.com/questions/52511778/how-to-install-openjdk-11-on-windows)

### Running the tests

```mvn test```

Or run a specific test

```mvn test -Dtest="TheFirstUnitTest"```

To run a method from within a test

```mvn test -Dtest="TheSecondUnitTest#whenTestCase2_thenPrintTest2_1"```

### Deployment

`sql-dao-library` project serves as a library for the services. It is not meant to be deployed as a service in any cloud environment.

## Usage
Add the following dependency in the target project
```
<dependency>
  <groupId>org.eclipse.ecsp</groupId>
  <artifactId>sql-dao</artifactId>
  <version>1.X.X</version>
</dependency>

```

### Specifying Postgres DB connection properties

The Postgres DB connection properties can be specified in the environment properties as shown below:

```properties
postgres.jdbc.url=jdbc:postgresql://localhost:5432/postgres
postgres.username=********
postgres.password=********
```

### Using the `JdbcTemplate` to execute queries

The `JdbcTemplate` can be autowired in the implementing service to execute queries as shown below:

```java

import org.springframework.beans.factory.annotation.Qualifier;

@Autowired
@Qualifier("jdbcTemplate")
private JdbcTemplate jdbcTemplate;

@Autowired
private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

public void executeQuery() {
    jdbcTemplate.query("SELECT * FROM table_name", (rs, rowNum) -> {
        // Process the result set
        return null;
    });
}
```

### Health Monitoring

The health of the Postgres DB is monitored by the `PostgresDBHealthMonitor` class. To enabled health monitoring, the below configuration needs to be added in the environment properties:

```properties
health.postgresdb.monitor.enabled=true

## If graceful restart is required
health.postgresdb.monitor.restart.on.failure=true
```

### Publishing Metrics to Prometheus server

To publish Postgres DB pool metrics to Prometheus server, the below configuration needs to be added in the environment properties:

```properties
## To enable postgres DB metrics fetching from PostgresDB connection pool 
postgresdb.metrics.enabled=true

## To enable Prometheus metrics publishing
metrics.prometheus.enabled=true

## Prometheus agent port configuration
prometheus.agent.port=9100
prometheus.agent.port.exposed=9100
```


## Built With Dependencies

|                            Dependency                            | Purpose                                    |
|:----------------------------------------------------------------:|:-------------------------------------------|
|  [Ignite Utils](https://github.com/eclipse-ecsp/utils)           | For logging & health monitoring            |
|         [Prometheus HTTP server](https://prometheus.io/)         | For metrics publishing                     |
|            [postgresql](https://jdbc.postgresql.org/)            | For Postgres DB connection                 |
|     [HikariCP](https://github.com/brettwooldridge/HikariCP)      | For connection pooling                     |
| [Spring Framework](https://spring.io/projects/spring-framework)  | The core spring support                    |
|      [Spring Boot](https://spring.io/projects/spring-boot/)      | The web framework used                     |
|                [Maven](https://maven.apache.org/)                | Dependency Management                      |
|                [Junit](https://junit.org/junit5/)                | Testing framework                          |
|               [Mockito](https://site.mockito.org/)               | Test Mocking framework                     |

## How to contribute

Please read [CONTRIBUTING.md](./CONTRIBUTING.md) for details on our contribution guidelines, and the process for submitting pull requests to us.

## Code of Conduct

Please read [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md) for details on our code of conduct.

## Authors

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
	  <td align="center" valign="top" width="14.28%"><a href="https://github.com/kaushalaroraharman"><img src="https://github.com/kaushalaroraharman.png" width="100px;" alt="Kaushal Arora"/><br /><sub><b>Kaushal Arora</b></sub></a><br /><a href="https://github.com/all-contributors/all-contributors/commits?author=kaushalaroraharman" title="Code and Documentation">📖</a> <a href="https://github.com/all-contributors/all-contributors/pulls?q=is%3Apr+reviewed-by%3Akaushalaroraharman" title="Reviewed Pull Requests">👀</a></td>
    </tr>
  </tbody>
</table>

See also the list of [contributors](https://github.com/eclipse-ecsp/nosql-dao/graphs/contributors) who participated in this project.

## Security Contact Information

Please read [SECURITY.md](./SECURITY.md) to raise any security related issues.

## Support
Please write to us at [csp@harman.com](mailto:csp@harman.com)

## Troubleshooting

Please read [CONTRIBUTING.md](./CONTRIBUTING.md) for details on how to raise an issue and submit a pull request to us.

## License

This project is licensed under the Apache-2.0 License - see the [LICENSE](./LICENSE) file for details.

## Announcements

All updates to this library are documented in our [Release Notes](./release_notes.txt) and [releases](https://github.com/eclipse-ecsp/sql-dao/releases).
For the versions available, see the [tags on this repository](https://github.com/eclipse-ecsp/sql-dao/tags).
