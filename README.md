# About this project

In our CI/CD Quality stage we run Android Instrumentation Tests and JUnit Tests and generate a
unified test report with Jacoco. Since updating to Android Gradle Plugin 7.2.0 the instrumentation
tests have 0% coverage in the test report. This is a minimal example of our project setup.

## How to run this project

- Open the project in Android Studio
- Connect an emulator or a physical device
- Run the RunConfiguration "Generate Code Coverage Report" (or ./gradlew rootCodeCoverageReport)

-> You will see the App flash up on your device/emulator, so the test is running. -> Then the gradle
logs will already display this information:

> [ant:jacocoReport] Classes in bundle 'ProblemExample' do not match with execution data. For report generation the same class files must be used as at runtime.
> [ant:jacocoReport] Execution data for class com/example/problem/MainActivity does not match.
> [ant:jacocoReport] Execution data for class com/example/problem/MainFragment does not match.

And when you open [ProjectRoot]/build/reports/html/com.example.problem/index.html you can see that
MainFragment has 0% Coverage.

This happens for all Instrumentation tests of Hilt-annotated classes: Application, Activities,
Fragments.

## Known workarounds

1. Go back to Android Gradle Plugin (AGP) Version 7.1.3
   -> Disadvantage: Nobody likes old versions
 
2. Separate the classes from the annotation like in this similar
   issue: https://issuetracker.google.com/issues/161300933#comment9

   -> Disadvantage: This overcomplicates the code and makes it ugly to read

3. Add build/intermediates/asm_instrumented_project_classes/<Product>Debug/ folder to
   classDirectories of Jacoco Report (See root build.gradle)

   -> Disadvantage: A lot of Hilt classes show up in the report. I was able to add exclusion rules
   for them, except for 'MainFragment.special...'.

## Suppositions

The gradle log assumes that class files have changed since creating the test execution data. It
seems that Hilt's changes are not acknowledged by Jacoco. As it was working in AGP 7.1.3 I suspect
the problem is connected to the new AGP Transform API that Hilt is using, but Jacoco seems to be not
aware of.

