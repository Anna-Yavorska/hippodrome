import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    @DisplayName("Method should throw IllegalArgumentException when first argument is null")
    void shouldThrowException_whenNameIsNull() {
        //given
        String name = null;
        double speed = 10.0;
        double distance = 100.0;

        String expectedMessage = "Name cannot be null.";

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    @DisplayName("Method should throw IllegalArgumentException when first argument is empty")
    void shouldThrowException_whenNameIsEmpty(String name) {
        //given
        double speed = 10.0;
        double distance = 100.0;

        String expectedMessage = "Name cannot be blank.";

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Method should throw IllegalArgumentException when second argument is negative")
    void shouldThrowException_whenSpeedIsNegative() {
        //given
        String name = "TestName";
        double speed = -10.0;
        double distance = 100.0;

        String expectedMessage = "Speed cannot be negative.";

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Method should throw IllegalArgumentException when third argument is negative")
    void shouldThrowException_whenDistanceIsNegative() {
        //given
        String name = "TestName";
        double speed = 10.0;
        double distance = -100.0;

        String expectedMessage = "Distance cannot be negative.";

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Method should return String which was the first at the constructor.")
    void getName() {
        //given
        String name = "TestName";
        double speed = 10.0;
        double distance = 100.0;
        Horse horse = new Horse(name, speed, distance);

        //when
        String actualName = horse.getName();

        //then
        assertEquals(name, actualName);
    }

    @Test
    @DisplayName("Method should return double which was the second at the constructor.")
    void getSpeed() {
        String name = "TestName";
        double speed = 10.0;
        double distance = 100.0;
        Horse horse = new Horse(name, speed, distance);

        //when
        double actualSpeed = horse.getSpeed();

        //then
        assertEquals(speed, actualSpeed);
    }

    @Test
    @DisplayName("Method move() should call getRandomDouble with correct parameters.")
    void shouldCallGetRandomDouble_withCorrectParameters() {
        //given
        Horse horse = new Horse("TestName", 10.0, 100.0);

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(5.5);

            //when
            horse.move();

            //then
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 100.0, 0.5, 105.0",
            "15.0, 200.0, 0.8, 212.0",
            "5.0, 50.0, 0.3, 51.5"
    })
    @DisplayName("Method should count distance correctly")
    void shouldCalculateDistanceCorrectly(double speed, double initialDistance, double randomFactor, double expectedDistance) {

        //given
        Horse horse = new Horse("TestName", speed, initialDistance);

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomFactor);

            //when
            horse.move();

            //then
            assertEquals(expectedDistance, horse.getDistance());
        }

    }
}