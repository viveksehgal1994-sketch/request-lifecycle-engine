# Request Lifecycle Engine

### Overview

This application is a robust, clean-architecture service designed to manage the lifecycle of requests. It tracks requests through various statuses, handles priority assignments, manages SLAs (Service Level Agreements), and provides an audit trail for all transitions.

---

### Tech Stack

* **Java 21 / Spring Boot 3.x**
* **Spring Data JPA / Hibernate**
* **H2 Database** (In-memory for demonstration)
* **Maven**

---

### Features

* **Lifecycle Management:** Transitions requests between defined states (e.g., `CREATED`, `ASSIGNED`, `IN_PROGRESS`, `COMPLETED`).
* **SLA Escalation:** Automated background processing to flag or escalate requests that exceed defined time limits.
* **Clean Architecture:** Separated concerns using Controller-Service-Repository layers.
* **Audit Logging:** Tracks state changes and timestamps for full traceability.

---

### Getting Started

#### Prerequisites

* GitHub Codespaces or a local JDK 21+ environment.
* Maven installed.

#### How to Run

1. Clone the repository.
2. Open in your IDE or GitHub Codespace.
3. Run the application using:
```bash
./mvnw spring-boot:run

```


4. The API will be available at `http://localhost:8080`.

---

### Architecture Design

The project follows a modular design to ensure high maintainability:

* **Controller Layer:** Exposes REST endpoints to interact with the lifecycle.
* **Service Layer:** Contains the core business logic, state transition rules, and SLA validation.
* **Repository Layer:** Handles data persistence and retrieval.

---

### API Endpoints

* `POST /api/requests`: Create a new request.
* `GET /api/requests/{id}`: Fetch request details.
* `PUT /api/requests/{id}/status`: Transition a request to a new state.

---

### Future Improvements

* Implement event-driven architecture using Kafka/RabbitMQ for high-scale notification processing.
* Add integration with a persistent database like PostgreSQL for production usage.
* Introduce Spring Security for operator authorization.
