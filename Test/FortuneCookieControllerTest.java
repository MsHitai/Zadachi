import org.junit.jupiter.api.Test;
import seventh.sprint.FortuneCookie.FortuneConfig;

import static org.junit.jupiter.api.Assertions.*;

class FortuneCookieControllerTest {

    @Test
    public void shouldReturnPositiveFortune () {
        FortuneConfig fortuneConfig = new FortuneConfig(true);
        assertTrue(fortuneConfig.isPositive());
    }

    @Test
    public void shouldReturnNegativeFortune () {
        FortuneConfig fortuneConfig = new FortuneConfig(false);
        assertFalse(fortuneConfig.isPositive());
    }

}