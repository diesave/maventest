package org.robtest.lesson4;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJExamplesTest {

    @Test
    void assertJTest() {
        List<String> stringList = Arrays.asList("test1", "test2", "test3");

        org.junit.jupiter.api.Assertions.assertAll(
                () -> assertThat(new Fuctions().isPalindrome("123")).isFalse(),
                () -> assertThat(new Fuctions().isPalindrome("123321")).isTrue(),
                () -> assertThat(5).isGreaterThan(4).isLessThan(10),
                () -> assertThat(stringList).contains("test1", "test2")
        );
    }
}
