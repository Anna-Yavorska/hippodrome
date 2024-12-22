import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HippodromeTest {
    @Test
    @DisplayName("Method should throw IllegalArgumentException when argument is null")
    void shouldThrowException_whenParameterInConstructorIsNull() {
        //given
        String expectedMessage = "Horses cannot be null.";

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Method should throw IllegalArgumentException when List is empty")
    void shouldThrowException_whenListIsEmpty() {
        //given
        List<Horse> horses = new ArrayList<>();
        String expectedMessage = "Horses cannot be empty.";

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("The method should return list of horses")
    void getHorses() {
        //given
        List<Horse> horses = createHorses();
        Hippodrome hippodrome = new Hippodrome(horses);

        //when
        List<Horse> actual = hippodrome.getHorses();

        //then
        assertEquals(horses, actual);
    }

    @Test
    @DisplayName("The method must be called for each horse")
    void move() {
        //given
        List<Horse> mockHorses = createMockHorses();
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        List<Horse> horses = hippodrome.getHorses();

        //when
        hippodrome.move();

        //then
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    @DisplayName("Method should return the horse with the maximum distance")
    void getWinner() {
        //given
        List<Horse> horses = List.of(
                new Horse("Horse1", 5, 10),
                new Horse("Horse2", 3, 15),
                new Horse("Horse3", 1, 2)
        );
        Horse expected = horses.get(1);

        Hippodrome hippodrome = new Hippodrome(horses);

        //when
        Horse actual = hippodrome.getWinner();

        //then
        assertEquals(expected, actual);
    }

    private List<Horse> createHorses() {
        List<Horse> result = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            result.add(new Horse("Horse" + i, Math.random() * i));
        }
        return result;
    }

    @ExtendWith(MockitoExtension.class)
    private List<Horse> createMockHorses() {
        List<Horse> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockedHorse = Mockito.mock(Horse.class);
            result.add(mockedHorse);
        }
        return result;
    }
}