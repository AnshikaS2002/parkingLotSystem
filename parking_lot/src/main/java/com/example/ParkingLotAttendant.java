package com.example;

public class ParkingLotAttendant {
    private ParkingLotSystem parkingLotSystem;

    public ParkingLotAttendant(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public String parkCarForOwner(String carNum) {
        return parkingLotSystem.parkCarForOwner(carNum);
    }

    public String parkCar(String carNum) {
        return parkingLotSystem.parkCar(carNum);
    }
}
