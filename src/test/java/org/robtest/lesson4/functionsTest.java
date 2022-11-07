package org.robtest.lesson4;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.Functions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Function;

public class functionsTest {
    @BeforeAll
    static void beforeAll(){
        System.out.println("Выполняется 1 раз перед всеми тестами класса");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("Выполняется перед запуском каждого теста");

    }

    @Test
    @DisplayName("Проверка метода isPalindrome")
    void isPalindromeTest() {
        boolean result = new Functions().isPalindrome("123321");
        Assertions.assertEquals(true, result);
    }


    @ParameterizedTest
    @ValueSource(strings = {"123321", "1234321"})
    @DisplayName("Проверка метода isPalindrome с нечетким количеством симболов")
    void isPalindromeTest2(String testWorld) {
        boolean result = new Functions().isPalindrome(testWorld);
        Assertions.assertEquals(true, result);
    }

    @ParameterizedTest
    @CsvSource({"123, false", "1234321, true"})
    void isPalindrome(String testWord, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, new Function().isPalidrome(testWord)); {
        }
    }

    @AfterEach
    void afterEach(){
        System.out.println("Метод выполнится после каждого теста");

    }

    @AfterAll
    static void afterAll(){
        System.out.println("метод выполнится 1 раз после выполнения всех тестов класса");

    }

}