W# AGENTS.md

## Project

Personal LLD/DSA study repo. Java 21 + Kotlin 2.0, Maven, no CI, no running services.

## Build & Run

```bash
# Compile (Java + Kotlin together)
mvn compile

# Run tests (SolutionTest.java is currently empty — passes trivially)
mvn test

# Run a specific main class
mvn exec:java -Dexec.mainClass="com.org.design.Behavioral.Strategy.StrategyRunner"
```

**Quirk:** `maven-compiler-plugin` default `compile`/`testCompile` phases are disabled in `pom.xml`. All compilation is handled by `kotlin-maven-plugin`. Do not add a separate `maven-compiler-plugin` execution — it will break the build.

## Java Version

Requires **Java 21**. Source and target are both set to 21 in `pom.xml`.

## JavaFX Dependency

`javafx-base` is declared with `<classifier>mac</classifier>`. It will fail to resolve on non-macOS machines. Remove or change the classifier if working on Linux/Windows.

## Package Layout

```
src/main/java/com/org/
├── concurrency/          Java concurrency demos (ReentrantLock, Executor, CountDownLatch)
├── design/
│   ├── Behavioral/       ChainOfResponsibility, Memento, Observer, Strategy, Template, Visitor
│   ├── Creational/       Builder, FactoryMethod (+ AbstractFactory), Prototype
│   ├── Structural/       Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy
│   └── question/         LLD interview problems: parking, tic_tac_toe, trueMeds
├── striver/              `GraphSolutions.java` contains ~25 graph algorithm solutions (Striver A2Z series)
├── Main.java             Scratch file / ListNode helper — not an authoritative entrypoint
└── Kotlin_Main.kt        Kotlin DSL demo — not an authoritative entrypoint
```

Module-level notes also exist in `src/main/java/com/org/concurrency/README.md` and `src/main/java/com/org/design/Behavioral/Strategy/README.md`.

## Entrypoints

Most design/problem demos use `*Runner.java` with `main()` (for example, `ParkingLotRunner`, `StrategyRunner`, `PrototypeRunner`). There is no single application entrypoint. Some packages also expose non-`*Runner` mains (for example, `striver/GraphSolutions.java`, `concurrency/ReentrantExample.java`, `concurrency/ThreadCommunication.java`).

## Tests

`src/test/java/com/org/SolutionTest.java` exists but is empty. No real tests are written. JUnit 5 (Jupiter) and JUnit 4 are both on the test classpath.

## Mixed Java + Kotlin

Kotlin files (`.kt`) sit alongside `.java` files under `src/main/java`. The `kotlin-maven-plugin` compiles both. New Kotlin files can be added anywhere under `src/main/java/com/org/` without extra config.
