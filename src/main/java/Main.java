import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(Main.class);

        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );

        String loggerStart = String.format("Start of the race. Number of participants: %d", horses.size());
        logger.info(loggerStart);

        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println(winnerName + " wins!");

        logger.info("End of the race. Winner: " + winnerName);
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}
