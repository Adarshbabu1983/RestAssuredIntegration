name: TestNG Tests

on:
  push:
    branches:
      - main # Trigger on pushes to the 'main' branch
  pull_request:
    branches:
      - main # Trigger on pull requests targeting the 'main' branch

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      # Checkout the code from your repository
      - name: Checkout code
        uses: actions/checkout@v3

      # Set up Java
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: 17 # Use the same Java version as your project
          distribution: 'temurin' # Use the Temurin JDK distribution

      # Cache Maven dependencies
      - name: Cache Maven dependencies
        uses: actions/cache@v3
        with:
          path: ~/.m2/repository
          key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
          restore-keys: |
            ${{ runner.os }}-maven-

      # Install dependencies
      - name: Install dependencies
        run: mvn install -DskipTests

      # Run TestNG tests
      - name: Run TestNG tests
        run: mvn test