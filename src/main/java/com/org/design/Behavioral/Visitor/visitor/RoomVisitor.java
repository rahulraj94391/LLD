package com.org.design.Behavioral.Visitor.visitor;


import com.org.design.Behavioral.Visitor.room.DeluxeRoom;
import com.org.design.Behavioral.Visitor.room.DoubleRoom;
import com.org.design.Behavioral.Visitor.room.SingleRoom;

/**
 * Visitor hosts all the operation (methods) from the parent class.
 * All the methods that can be done in the room are listed here.
 */

public interface RoomVisitor {
    void visit(SingleRoom singleRoomObj);

    void visit(DoubleRoom doubleRoomObj);

    void visit(DeluxeRoom deluxeRoom);
}
