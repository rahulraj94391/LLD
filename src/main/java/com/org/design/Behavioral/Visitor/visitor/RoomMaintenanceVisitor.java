package com.org.design.Behavioral.Visitor.visitor;

import com.org.design.Behavioral.Visitor.room.DeluxeRoom;
import com.org.design.Behavioral.Visitor.room.DoubleRoom;
import com.org.design.Behavioral.Visitor.room.SingleRoom;

public class RoomMaintenanceVisitor implements RoomVisitor {
    @Override
    public void visit(SingleRoom singleRoomObj) {
        System.out.println("Preforming maintenance of SingleRoom");
    }

    @Override
    public void visit(DoubleRoom doubleRoomObj) {
        System.out.println("Preforming maintenance of DoubleRoom");
    }

    @Override
    public void visit(DeluxeRoom deluxeRoom) {
        System.out.println("Preforming maintenance of DeluxeRoom");
    }
}
