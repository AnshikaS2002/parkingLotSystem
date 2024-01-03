import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

}
