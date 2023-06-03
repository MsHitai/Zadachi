import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seventh.sprint.FortuneCookie.FortuneConfig;
import seventh.sprint.FortuneCookie.FortuneCookieFactory;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class FortuneCookieFactoryTest {

    private static FortuneCookieFactory fortuneCookieFactory;

    @BeforeEach
    public void createCookieFactory() {
        FortuneConfig fortuneConfig = new FortuneConfig(true);

        fortuneCookieFactory = new FortuneCookieFactory(fortuneConfig, singletonList("positive"),
                singletonList("negative"));
    }

    @Test
    public void shouldIncrementCountByOneAfterOneCookieBaked () {
        fortuneCookieFactory.bakeFortuneCookie();
        assertEquals(1, fortuneCookieFactory.getCookiesBaked());
    }

    @Test
    public void shouldIncrementCountByTwoAfterTwoCookiesBaked() {
        fortuneCookieFactory.bakeFortuneCookie();
        fortuneCookieFactory.bakeFortuneCookie();
        assertEquals(2, fortuneCookieFactory.getCookiesBaked());
    }


    @Test
    public void shouldSetCounterToZeroAfterResetCookieCreatedCall() {
        fortuneCookieFactory.resetCookiesCreated();
        assertEquals(0, fortuneCookieFactory.getCookiesBaked());
    }
}