import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import seventh.sprint.PriceCalculator.PriceCalculator;
import seventh.sprint.PriceCalculator.TransportType;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    private final PriceCalculator priceCalculator = new PriceCalculator();

    @Test
    public void shouldThrowExceptionWhenBikeAndDistanceIs0Km() {
        IllegalArgumentException ex = Assertions.assertThrows(
                IllegalArgumentException.class,
                generateExecutable(TransportType.BIKE, 0)
        );

        Assertions.assertEquals("Distance should be more than 0 km", ex.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenCarAndDistanceIs0Km() {
        IllegalArgumentException ex = Assertions.assertThrows(
                IllegalArgumentException.class,
                generateExecutable(TransportType.CAR, 0)
        );

        Assertions.assertEquals("Distance should be more than 0 km", ex.getMessage());
    }

    @Test
    public void travel1000KmOnCarShouldEqual7000() {
        assertEquals(7000, priceCalculator.calculatePrice(TransportType.CAR, 1000));
    }

    @Test
    public void shouldThrowExceptionWhenOver1000KmOnCar () {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                generateExecutable(TransportType.CAR, 1001));
        assertEquals("Car can not go for more than 1000 km", ex.getMessage());
    }

    @Test
    public void travel100KmOnTruckShouldEqual500 () {
        assertEquals(500, priceCalculator.calculatePrice(TransportType.TRUCK, 100));
    }

    @Test
    public void shouldThrowExceptionWhenTruckAndDistanceIs0Km() {
        IllegalArgumentException ex = Assertions.assertThrows(
                IllegalArgumentException.class,
                generateExecutable(TransportType.TRUCK, 0)
        );

        Assertions.assertEquals("Distance should be more than 0 km", ex.getMessage());
    }

    @Test
    public void travel20KmOnBikeShouldEqual200() {
        assertEquals(200, priceCalculator.calculatePrice(TransportType.BIKE, 20));
    }

    @Test
    public void shouldThrowExceptionWhenOver20KmOnBike() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                generateExecutable(TransportType.BIKE, 21));
        assertEquals("Bike can not go for more than 20 km", ex.getMessage());
    }

    @Test
    public void shouldThrowExceptionWithWrongType() {
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class,
                generateExecutable(TransportType.DRONE, 1));
        assertEquals("transport type 'DRONE' is not handled correctly", ex.getMessage());
    }

    private Executable generateExecutable(TransportType type, int distance) {
        return () -> priceCalculator.calculatePrice(type, distance);
    }
}