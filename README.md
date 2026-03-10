# Incode Test Automation Project

## 🛠️ Prerequisites

Before you get started, make sure you have the following installed on your machine:

- **Homebrew** (macOS package manager)  
  If you don’t have it yet, install it with:
  ```bash
  /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
  ```

- **Java Development Kit (JDK) 17**  
  Install via brew:
  ```bash
  brew install openjdk@17
  ```  
  Then link it so your system uses it:
  ```bash
  brew link --force --overwrite openjdk@17
  ```

- **Apache Maven** (build tool)  
  Install via brew:
  ```bash
  brew install maven
  ```

You can verify everything is set up correctly by running:
```bash
java -version
mvn -version
```

Both should report Java 17 and Maven installed.

---

## 📦 Installation

1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd <repo-folder>
   ```

2. Build and install dependencies:
   ```bash
   mvn clean install
   ```

---

## ▶️ Execution

To run all tests, simply execute:
```bash
mvn test
```

After the run finishes, Maven + Cucumber will generate a **beautiful HTML report** you can open in your browser:

- Path: `target/cucumber-reports/cucumber-report.html`

Just double-click that file or open it with:
```bash
open target/cucumber-reports/cucumber-report.html
```

This report shows which scenarios passed, failed, and skipped, with step-by-step details.

---

## 📂 Project Structure

- `src/test/resources/features` → Gherkin `.feature` files (test scenarios).
- `src/test/java/stepdefinitions` → Glue code (step definitions).
- `src/test/java/pages` → Page Object Model classes.
- `target/cucumber-reports` → Execution results (including the HTML report).

---

Here’s a more polished, professional version of that section for your README:

---

## 📌 Notes

- This project serves as a **comprehensive test suite**: scenarios are written in plain English (`.feature` files) and executed seamlessly through the automation framework.
- Running `mvn test` is sufficient to trigger the entire flow — the framework manages browser sessions, executes steps, and generates detailed reports automatically.
- Upon completion, an HTML report is created at `target/cucumber-reports/cucumber-report.html`. This report provides a clear overview of test outcomes, highlighting passed, failed, and skipped scenarios with step-level detail for efficient analysis.
