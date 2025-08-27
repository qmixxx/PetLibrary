# PetLibrary

![CI](https://github.com/qmixxx/PetLibrary/actions/workflows/ci.yml/badge.svg)

## Description
Creating a system for a library

System allows to manage the books and inventory. 
The following functionalities:

1. Register new books
2. Remove books
3. Borrow books
4. Check availability of a book
5. Extend the return date of a book
## Technologies & Dependencies

- **Language:** Java 17
- **Framework:** Spring Boot
- **Build Tool:** Maven
- **Testing:** JUnit 5, Mockito
- **Code Coverage:** JaCoCo
- **CI/CD:** GitHub Actions
- **Containerization:** Docker
- **Dependency Management & Security (optional):** OWASP Dependency-Check, Snyk
- **Static Code Analysis (optional):** Checkstyle, SpotBugs, SonarCloud
- **Version Control:** Git, GitHub

## Getting Started

### Prerequisites
- Java 17
- Maven
- Docker

### Running Locally
Clone the repository:

```bash
git clone https://github.com/qmixxx/PetLibrary.git
cd PetLibrary
```

### Build the project and run:

```bash
mvn clean install
mvn spring-boot:run
```
The application will start on http://localhost:8080.

### Running Tests

```bash
mvn test
```
All unit tests are written with JUnit 5 and Mockito.

### Docker

Build Docker image:

```bash
docker build -t pet-project .
Run Docker container:
```

```bash
docker run -p 8080:8080 pet-project
```

### CI/CD

This project uses GitHub Actions to automatically build, test, and check code on every push or pull request.
The CI badge above shows the current build status.

### License
This project is for learning purposes. Feel free to use and modify.
