package com.org.design.Behavioral.Visitor.room;

import com.org.design.Behavioral.Visitor.visitor.RoomVisitor;

public class DeluxeRoom implements RoomElement {
    public int roomPrice = 0;

    @Override
    public void accept(RoomVisitor visitor) {
        visitor.visit(this);
    }
}