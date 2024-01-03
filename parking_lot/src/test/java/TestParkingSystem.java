import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.ParkingLotSystem;

public class TestParkingSystem {
    @Test
    public void testParkingFunctionality() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        String carNum = "ABC1300";
        assertEquals("parked car " + carNum, parkingLotSystem.parkCar(carNum));
    }
}