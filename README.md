# BitBites

BitBites is a command-line chatbot for tracking your daily food intake, calories, and protein. It is designed for users who prefer a fast, keyboard-driven workflow over graphical interfaces.

## Setting up in IntelliJ

Prerequisites: JDK 17 (use the exact version), update IntelliJ to the most recent version.

1. **Ensure IntelliJ JDK 17 is defined as an SDK**, as described [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) — this step is not needed if you have used JDK 17 in a previous IntelliJ project.
2. **Import the project as a Gradle project**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
3. **Verify the setup**: After importing, locate `src/main/java/seedu/bitbites/Bitbites.java`, right-click it, and choose `Run Bitbites.main()`. If the setup is correct, you should be prompted to enter your name to begin your session.

**Warning:** Keep the `src\main\java` folder as the root folder for Java files. Do not rename those folders or move Java files outside of this path, as tools like Gradle expect to find Java files there.

## Build Automation using Gradle

This project uses Gradle for build automation and dependency management. It includes a `build.gradle` build script.

If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O Redirection Tests

To run I/O redirection tests (Text UI tests), navigate to the `text-ui-test` folder and run the `runtest(.bat/.sh)` script.

### JUnit Tests

JUnit tests are located in `src/test/java/`. If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

This project uses Checkstyle to enforce the Google Java Style Guide. A configuration file is provided. If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub Actions](https://github.com/features/actions) for CI. When you push a commit or open a PR, GitHub Actions will automatically build and verify the updated code.

## Documentation

The `/docs` folder contains the User Guide and Developer Guide.

To publish documentation:
1. Go to your fork on GitHub.
2. Click the `Settings` tab.
3. Scroll down to the `GitHub Pages` section.
4. Set the source to `master branch /docs folder`.
5. Optionally, choose a theme for your documentation.

For more information, refer to the [User Guide](docs/UserGuide.md) and [Developer Guide](docs/DeveloperGuide.md).
