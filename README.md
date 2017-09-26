** An example client forging a Pact with MathAPI **

* Run tests mocking out Pact and create Pact files
sbt test

* Run a mock server based on the pact files
sbt "pact-stubber --host localhost --port 9001 --source target/pacts"
