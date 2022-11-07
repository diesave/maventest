package org.robtest.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoxTest {
    Box box;

    @Test
    void boxCountIsEquals1Test(){
        Box box = new Box();
        box.addBall();
        assertEquals(1, box.getBallCount());
    }

    @Nested
    class whenBoxIsEmpty {

        @BeforeEach
        void createBox() {
            box = new Box();
        }

        @Test
        void exceptionWhenTryToDeleteBall() {
            Assertions.assertThrows(BallCountException.class, () -> box.deleteBall());
            assertThatExceptionOfType(BallCountException.class).isThrownBy(() -> box.deleteBall());
        }

        @Nested
        class WhenOneBall {
            @BeforeEach
            void addBall() {
                box.addBall();
            }

            @Test
            void deleteBallTest() throws BallCountException {
                box.deleteBall();
                assertThat(box.getBallCount()).isZero();
            }
        }
    }
}
