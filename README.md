# msa-customer

## Features

* demonstrates the use of Jenkins 2 Declarative Pipelines using the Docker agent and parallel stages
* contains a basic Spring Boot application with a simple REST API backed by a Postgres database
* uses Docker to provision the Postgres DB (the script for provisioning the DB can be found in the https://github.com/membrane/msa-utils
repository)
* shows some core features of JUnit 5, namely `@Displayname` and `@ParametrizedTests`

## Additional notes

To better visualise the parallel pipeline execution, use the Blue Ocean UI.
