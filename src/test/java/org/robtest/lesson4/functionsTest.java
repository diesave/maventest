package org.robtest.lesson4;

import com.google.common.base.Functions;
import com.sun.jna.Function;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuctionsTest {
    private static final Logger logger = LoggerFactory.getLogger(FuctionsTest.class);

    @BeforeAll
    static void beforeAll(){
        System.out.println("Выполнится 1 раз перед всеми тестами");
        logger.info("Выполнится 1 раз перед всеми тестами.");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach(){
        logger.error("ERROR");
        System.out.println("Выполняется перед запуском каждого теста");

    }

    @Test
    @DisplayName("Проверка метода isPalindrome() со словом-палиндромом с четным числом букв")
    void isPalindromeTest() {
        boolean result = new Fuctions ().isPalindrome("123");
        Assertions.assertEquals(true, result);
    }


    @ParameterizedTest
    @ValueSource(strings = {"123321", "1234321"})
    @DisplayName("Проверка метода isPalindrome() со словом-палиндромом с нечетным числом букв")
    void isPalindromeTest2(String testWorld) {
        boolean result = new Functions ().isPalindrome(testWorld);
        Assertions.assertEquals(true, result);
    }

    @ParameterizedTest
    @CsvSource({"123, false", "123321, true", "1234321, true"})
    void isPalindrome(String testWord, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, new Function().isPalidrome(testWord)); {
        }
    }

    @ParameterizedTest
    @MethodSource("catAndAgeDataProvider")
    void catAndAgeTest(Cat cat, Integer age) {
        assertEquals(cat.getAge(), age);
    }

    private static List<Arguments> catAndAgeDataProvider() {
        return Arrays.asList(
                Arguments.of(new Cat ("Sharik", 10), 10),
                Arguments.of(new Cat ("Barsik", 11), 12)
        );
    }
    @AfterEach
    void  afterEachMethed(){
        System.out.println("Выполнится каждый раз,после завершения каждого теста");

    }

    @AfterAll
    static void  afterAllMethod(){
        System.out.println("метод выполнится 1 раз после выполнения всех тестов класса");

    }


}
