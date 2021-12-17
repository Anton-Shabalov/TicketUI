package tools;

import collection.Ticket;

import java.util.Comparator;

public class Comparat implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Ticket ticket1 = (Ticket) o1;
        Ticket ticket2 = (Ticket) o2;
        return Integer.compare(ticket1.getCapacity(), ticket2.getCapacity());
    }
}
