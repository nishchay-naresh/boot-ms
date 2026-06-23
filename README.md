# boot-ms

This repository contains a small Spring Boot microservices demo: a service registry (Eureka), several independent microservices (customer, product, menu) and a reactive client that calls services via a load-balanced WebClient. Product and Customer services use in-memory H2 databases and JPA for simple CRUD-style persistence.

Tech stack
- Java 17
- Spring Boot 4.1.0
- Spring Cloud (2024.0.0 where used)
- Maven (wrapper included)

Services (default local ports)
- `service-registry` — Eureka server (8040)
- `customer-service` — JPA + H2 (4040)
- `product-service` — JPA + H2 (4050)
- `menu-service` — Actuator-enabled service (4060)
- `reactive-client` — WebFlux client using load-balanced WebClient (4070)

Quick smoke checks

```powershell
# registry health
curl http://localhost:8040/actuator/health
# product health
curl http://localhost:4050/actuator/health
# H2 console (product): http://localhost:4050/h2-console
```

## Build & Run (per-service)

These examples use the repository Maven wrapper on Windows Powershell (`.\mvnw.cmd`). If the wrapper is unavailable, replace `.\mvnw.cmd` with `mvn`.

General notes
- Start `service-registry` first so other services can register.
- You can run services with `spring-boot:run` for development, or package and run the JAR for a production-like start.
- To change a service port, edit its `src/main/resources/application.yml` or pass `-Dserver.port=` on the command line.

Common build command (package without tests):

```powershell
.\mvnw.cmd -f <service>/pom.xml -DskipTests package
```

Run using the wrapper (fast development):

```powershell
.\mvnw.cmd -f <service>/pom.xml spring-boot:run
```

Run the packaged JAR (after packaging):

```powershell
.\mvnw.cmd -f <service>/pom.xml -DskipTests package
java -jar <service>\target\*.jar
```

Per-service quick commands

- service-registry (Eureka) — port 8040

```powershell
.\mvnw.cmd -f service-registry/pom.xml -DskipTests package
.\mvnw.cmd -f service-registry/pom.xml spring-boot:run
# or
# java -jar service-registry\target\service-registry-0.0.1-SNAPSHOT.jar
```

Actuator (health): http://localhost:8040/actuator/health

- customer-service — port 4040 (JPA + H2)

```powershell
.\mvnw.cmd -f customer-service/pom.xml -DskipTests package
.\mvnw.cmd -f customer-service/pom.xml spring-boot:run
```

H2 console (if enabled): http://localhost:4040/h2-console

- product-service — port 4050 (JPA + H2)

```powershell
.\mvnw.cmd -f product-service/pom.xml -DskipTests package
.\mvnw.cmd -f product-service/pom.xml spring-boot:run
```

H2 console (if enabled): http://localhost:4050/h2-console

- menu-service — port 4060 (Actuator)

```powershell
.\mvnw.cmd -f menu-service/pom.xml -DskipTests package
.\mvnw.cmd -f menu-service/pom.xml spring-boot:run
```

Actuator health: http://localhost:4060/actuator/health

- reactive-client — port 4070 (WebFlux client)

```powershell
.\mvnw.cmd -f reactive-client/pom.xml -DskipTests package
.\mvnw.cmd -f reactive-client/pom.xml spring-boot:run
```

The reactive client calls services through the discovery client; ensure the registry and target services are running first.

Build all services (Powershell loop from repo root)

```powershell
$services = 'service-registry','customer-service','product-service','menu-service','reactive-client'
foreach ($s in $services) { .\mvnw.cmd -f "$s/pom.xml" -DskipTests package }
```

Troubleshooting
- If `./mvnw test` from the repo root fails with "no POM in this directory" it means there is no root aggregator POM — run per-module with `-f <module>/pom.xml` or add an aggregator POM at the repo root.
- Integration tests using `@SpringBootTest` may enable many auto-configurations (Spring Cloud/Eureka). If tests fail due to missing web/server classes, either add the necessary test-scoped dependencies, run the services manually for that test, or convert the test into a lighter unit-style test while iterating.

---

Start order summary: run `service-registry` → run services (customer/product/menu) → run `reactive-client`.

Use actuator endpoints and the H2 consoles to verify proper startup and connectivity.


