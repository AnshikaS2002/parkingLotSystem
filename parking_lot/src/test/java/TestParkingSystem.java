import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.ParkingLotOwner;
import com.example.*;

public class TestParkingSystem {
    @Test
    public void testParkingFunctionality() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        String carNum = "ABC1300";
        assertEquals("parked car " + carNum, parkingLotSystem.parkCar(carNum));
    }

    @Test
    public void testUnparkingFunctionality() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        String carNum = "ABC1234";
        assertEquals("unparked car " + carNum, parkingLotSystem.unparkCar(carNum));
    }

    @Test
    public void testAllocatingSlotWithMaximumCapacity() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        int maxCapacity = 100;
        for (int i = 1; i <= maxCapacity; i++) {
            parkingLotSystem.parkCar("i");
        }
        assertEquals(true, parkingLotSystem.isParkingFull());
    }

    @Test
    public void testParkingByAttendant() {
        ParkingLotSystem parkingLotSystemMock = mock(ParkingLotSystem.class);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotSystemMock);

        String carNum = "XYZ987";

        parkingLotAttendant.parkCar(carNum);

        verify(parkingLotSystemMock, times(1)).parkCar(carNum);
    }

    @Test
    public void testNotifyOwnerWhenSpaceAvailable() {
        ParkingLotOwner parkingLotOwnerMock = mock(ParkingLotOwner.class);

        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.addParkingLotOwner(parkingLotOwnerMock);

        for (int i = 1; i <= 100; i++) {
            parkingLotSystem.parkCar(Integer.toString(i));
        }

        parkingLotSystem.unparkCar("50");

        verify(parkingLotOwnerMock, times(1)).notifyParkingLotHasSpace();
    }

    @Test
    public void testParkingByAttendantForOwner() {
        ParkingLotSystem parkingLotSystemMock = mock(ParkingLotSystem.class);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotSystemMock);

        String carNum = "XYZ987";

        parkingLotSystemMock.addParkingLotOwner(new ParkingLotOwner("OwnerXYZ"));
        parkingLotSystemMock.addParkingLotAttendant(parkingLotAttendant);

        parkingLotAttendant.parkCarForOwner(carNum);

        verify(parkingLotSystemMock, times(1)).parkCarForOwner(carNum);
    }

    @Test
    public void testFindCar() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotSystem);

        String carNum = "XYZ987";

        parkingLotAttendant.parkCar(carNum);

        String result = parkingLotSystem.findCar(carNum);

        assertEquals("Car XYZ987 is parked at Spot1", result);
    }

    @Test
    public void testGetParkingTimestamp() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotSystem);

        String carNum = "XYZ987";

        parkingLotAttendant.parkCar(carNum);

        LocalDateTime parkingTimestamp = parkingLotSystem.getParkingTimestamp(carNum);

        assertNotNull(parkingTimestamp);
    }

}
