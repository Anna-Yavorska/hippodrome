import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {

    @Test
    @DisplayName("The method must execute in no more than 22 seconds")
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("This test is disabled to save time during routing test execution")
    void main() throws Exception {
        String[] args = {};
        Main.main(args);
    }
}