package com.org.design.Behavioral.Visitor.visitor;

import com.org.design.Behavioral.Visitor.room.DeluxeRoom;
import com.org.design.Behavioral.Visitor.room.DoubleRoom;
import com.org.design.Behavioral.Visitor.room.SingleRoom;

public class RoomPricingVisitor implements RoomVisitor {
    @Override
    public void visit(SingleRoom singleRoomObj) {
        System.out.println("Pricing computation logic of SingleRoom.");
        singleRoomObj.roomPrice = 1000;
    }

    @Override
    public void visit(DoubleRoom doubleRoomObj) {
        System.out.println("Pricing computation logic of DoubleRoom.");
        doubleRoomObj.roomPrice = 2000;
    }

    @Override
    public void visit(DeluxeRoom deluxeRoom) {
        System.out.println("Pricing computation logic of DeluxeRoom.");
        deluxeRoom.roomPrice = 3500;
    }
}