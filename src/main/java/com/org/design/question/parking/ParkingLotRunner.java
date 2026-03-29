package com.org.design.question.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ParkingLotRunner {
    public static void main(String[] args) {

    }
}

enum VehicleType {
    BIKE, CAR
}

interface SpotSearchStrategy {
    ParkingSpot searchSpot(Floor floor, VehicleType type);
}

interface RateService {
    public double getPayment(long duration);
}

class Vehicle {
    private String regNumber;
    private VehicleType type;

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}

abstract class Gate {
    private final String id;

    public Gate() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void openGate() {
        System.out.println(this.getClass().getSimpleName() + " is opening.");
    }
}

class ParkingSpot {
    private final String spotId;
    private final VehicleType type;
    private boolean isOccupied;

    public ParkingSpot(VehicleType type, String spotId) {
        this.type = type;
        this.spotId = spotId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean setOccupied() {
        isOccupied = true;
        return true;
    }

    public void setFree() {
        isOccupied = false;
    }
}

class FloorService {
    private ArrayList<Floor> floors = new ArrayList<>();

    Floor getFloor(int floorNo) {
        if (floorNo >= 0 && floorNo < floors.size()) {
            return floors.get(floorNo);
        }
        return null;
    }
}

class Floor {
    private final ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();

    public Floor() {
        addParkingSpot();
    }

    private void addParkingSpot() {
        // Add dummy BIKE spots: B1-B10
        for (int i = 1; i <= 10; i++) {
            parkingSpots.add(new ParkingSpot(VehicleType.BIKE, "B" + i));
        }

        // Add dummy CAR spots: C1-C20
        for (int i = 1; i <= 20; i++) {
            parkingSpots.add(new ParkingSpot(VehicleType.CAR, "C" + i));
        }
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
}

class EntryGate extends Gate {
    private final SpotReserveService reserveService;
    private final TicketService ticketService;
    private final FloorService floorService;

    public EntryGate(SpotReserveService reserveService, TicketService ticketService, FloorService floorService) {
        this.reserveService = reserveService;
        this.ticketService = ticketService;
        this.floorService = floorService;
    }

    public Ticket parkVehicle(String regNumber, VehicleType type, SpotSearchStrategy strategy) {
        ParkingSpot parkingSpot = reserveService.searchSpot(strategy, floorService.getFloor(0), type);
        if (parkingSpot == null) {
            return null;
        }
        parkingSpot.setOccupied();
        return ticketService.generateTicket(regNumber);
    }
}

class ExitGate extends Gate {
    private final SpotReserveService reserveService;
    private final PaymentService paymentService;
    private final TicketService ticketService;

    public ExitGate(SpotReserveService reserveService, PaymentService paymentService, TicketService ticketService) {
        this.reserveService = reserveService;
        this.paymentService = paymentService;
        this.ticketService = ticketService;
    }

    public void exitParkingLot(String regNumber) {
        Ticket ticket = ticketService.getTicket(regNumber);
        boolean isPaymentReceived = paymentService.getPayment(ticket);
        if (isPaymentReceived) {
            // open gate to let the car go through exit gate.
        } else {
            // retry payment or call for assistance.
        }
    }
}

class PaymentService {
    private final FlatRateService flatRateService;

    public PaymentService(FlatRateService flatRateService) {
        this.flatRateService = flatRateService;
    }

    public boolean getPayment(Ticket ticket) {
        double paymentToReceive = calculatePayment(ticket);
        System.out.println("Payment of " + paymentToReceive + " has been received.");
        return true;
    }

    private double calculatePayment(Ticket ticket) {
        long duration = ticket.getDuration();
        return flatRateService.getPayment(duration);
    }
}

class FlatRateService implements RateService {
    private final long FIFTEEN_MIN = 900_000;

    @Override
    public double getPayment(long duration) {
        long time = duration / FIFTEEN_MIN;
        return time * 10;
    }
}


class TicketService {
    private final HashMap<String, Ticket> ticketHistory = new HashMap<>();

    public Ticket generateTicket(String regNumber) {
        Ticket ticket = new Ticket(regNumber);
        ticketHistory.put(regNumber, ticket);
        return ticket;
    }

    public Ticket getTicket(String regNumber) {
        return ticketHistory.get(regNumber);
    }
}

class Ticket {
    private final String ticketId;
    private final String regNumber;
    private final Long entryTime;
    private Long exitTime;

    public Ticket(String regNumber) {
        this.ticketId = random4DigitString();
        this.entryTime = System.currentTimeMillis();
        this.regNumber = regNumber;
    }

    private static String random4DigitString() {
        int n = ThreadLocalRandom.current().nextInt(0, 10_000); // 0..9999
        return "TIC" + String.format("%04d", n);
    }

    public long getDuration() {
        exitTime = System.currentTimeMillis();
        return exitTime - entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public Long getEntryTime() {
        return entryTime;
    }

    public Long getExitTime() {
        return exitTime;
    }

    public void setExitTime(Long exitTime) {
        this.exitTime = exitTime;
    }
}

class SpotReserveService {
    public ParkingSpot searchSpot(SpotSearchStrategy s, Floor floor, VehicleType type) {
        return s.searchSpot(floor, type);
    }

    public synchronized boolean reserveSpot(ParkingSpot spot) {
        return spot.setOccupied();
    }

    public synchronized void freeSpot(ParkingSpot spot) {
        spot.setFree();
    }
}

class NearEntranceSpot implements SpotSearchStrategy {
    @Override
    public ParkingSpot searchSpot(Floor floor, VehicleType type) {
        List<ParkingSpot> spots = floor.getParkingSpots();
        ParkingSpot spot = null;
        for (ParkingSpot parkingSpot : spots) {
            if (!parkingSpot.isOccupied()) {
                spot = parkingSpot;
                break;
            }
        }
        return spot;
    }
}

class NearExitSpot implements SpotSearchStrategy {
    @Override
    public ParkingSpot searchSpot(Floor floor, VehicleType type) {
        List<ParkingSpot> spots = floor.getParkingSpots();
        ParkingSpot spot = null;
        for (ParkingSpot parkingSpot : spots) {
            if (!parkingSpot.isOccupied()) {
                spot = parkingSpot;
                break;
            }
        }
        return spot;
    }
}

class RandomSpot implements SpotSearchStrategy {
    @Override
    public ParkingSpot searchSpot(Floor floor, VehicleType type) {
        List<ParkingSpot> spots = floor.getParkingSpots();
        ParkingSpot spot = null;
        for (ParkingSpot parkingSpot : spots) {
            if (!parkingSpot.isOccupied()) {
                spot = parkingSpot;
                break;
            }
        }
        return spot;
    }
}
