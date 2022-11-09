package com.revature.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CalculatorTest {

    @Test
    public void subtract() {

        // Arrange
        Calculator calculator = new Calculator();
        int x1 = 5;
        int x2 = 3;
        int expected = x1 - x2;

        // Act
        int actual = calculator.subtract(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    // methodSource: gives you a dataset from a method
    @ParameterizedTest
    @MethodSource("subtractionParameters") // target method is determined by string value
    public void subtractMethodSource(int x1, int x2, int expected) {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int actual = calculator.subtract(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    public static Stream<Arguments> subtractionParameters() {
        // don't for get the toStream method on collections
        // may return array, or stream of values
        return Stream.of(
                Arguments.of(32,1,31), // x1, x2, expected
                Arguments.of(22,2,21), // expected to fail for demonstration: 22 - 2 = 20
                Arguments.of(44,4,40)
        );
    }

    // csvSource: gives you a dataset from a csv file source
    @ParameterizedTest
    @CsvSource({
            "22,2,20",
            "30,3,32",
            "44,4,40"
    }) // this replaces the subtractionParameters() from before
    // second set will fail 30 - 3 = 27
    public void subtractCSV(int x1, int x2, int expected) {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int actual = calculator.subtract(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);


    }

    // csvFileSource: gives you a dataset from a csv file
    @ParameterizedTest
    @CsvFileSource(resources="/subtract-data.csv") // we need to give a file path, from resources /* you may have to create, you can mark as test resources root in IntelliJ  under mark directory as*/
    public void subtractCSVFile(int x1, int x2, int expected) {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int actual = calculator.subtract(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);

    }
    // you may also specify the resources parameter as an array {"/location1.csv", "/location2.csv"}

    @ParameterizedTest
    @CsvSource({
        "12, 1, 13",
        "14, 5, 19",
        "15, 8, 23"
    })
    public void addCSVSource(int x1, int x2, int expected) {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int actual = calculator.add(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    // treat the resources directory as your root directory and you can specify file paths
    @ParameterizedTest
    @CsvFileSource(resources = "/add/add-data.csv")
    public void addCsvFileSource(int x1, int x2, int expected) {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int actual = calculator.add(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    // using a random integers in test to find any edge cases... eventually
    @ParameterizedTest
    @MethodSource("addParameters")
    public void addMethodSource(int x1, int x2, int expected) {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int actual = calculator.add(x1, x2);

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    // method sources need to be static and public!!!
    public static Stream<Arguments> addParameters() {
        Random random = new Random();
        List<Arguments> argumentsList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int x1 = random.nextInt();
            int x2 = random.nextInt();
            int actual = x1 + x2;
            argumentsList.add(
                    Arguments.of(
                            x1, x2, actual
                    )
            );
        }
        return argumentsList.stream();
    }

}
