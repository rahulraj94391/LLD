package com.org.design.Behavioral.Visitor.room;

import com.org.design.Behavioral.Visitor.visitor.RoomVisitor;

// the interface is intended to take out the methods from the class
//


public interface RoomElement {
    void accept(RoomVisitor visitor);
}
