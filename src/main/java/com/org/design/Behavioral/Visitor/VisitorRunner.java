package com.org.design.Behavioral.Visitor;

import com.org.design.Behavioral.Visitor.room.DeluxeRoom;
import com.org.design.Behavioral.Visitor.room.DoubleRoom;
import com.org.design.Behavioral.Visitor.room.SingleRoom;
import com.org.design.Behavioral.Visitor.visitor.RoomMaintenanceVisitor;
import com.org.design.Behavioral.Visitor.visitor.RoomPricingVisitor;

/**
 * <ul>
 *     <li>Use the Visitor when you need to perform an operation on all elements of a complex object structure (for example, an object tree).</li>
 *     <li>Use the Visitor to clean up the business logic of auxiliary behaviors.</li>
 *     <li>Use the pattern when a behavior makes sense only in some classes of a class hierarchy, but not in others.</li>
 * </ul>
 */


public class VisitorRunner {
    public static void main(String[] args) {
        client();
    }

    private static void client() {
        SingleRoom singleRoom = new SingleRoom();
        DoubleRoom doubleRoom = new DoubleRoom();
        DeluxeRoom deluxeRoom = new DeluxeRoom();


        RoomPricingVisitor pricingVisitor = new RoomPricingVisitor();
        pricingVisitor.visit(singleRoom);
        pricingVisitor.visit(doubleRoom);
        pricingVisitor.visit(deluxeRoom);


        RoomMaintenanceVisitor roomMaintenanceVisitor = new RoomMaintenanceVisitor();
        roomMaintenanceVisitor.visit(singleRoom);
        roomMaintenanceVisitor.visit(doubleRoom);
        roomMaintenanceVisitor.visit(deluxeRoom);
    }
}
