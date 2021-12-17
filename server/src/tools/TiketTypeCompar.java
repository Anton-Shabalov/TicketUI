package tools;

import collection.Ticket;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class TiketTypeCompar implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        int oneObject;
        int twoObject;
        Ticket ticket1 = (Ticket) o1;
        Ticket ticket2 = (Ticket) o2;
        oneObject = evaluator(ticket1);
        twoObject = evaluator(ticket2);
        return Integer.compare(oneObject, twoObject);
    }

    private int evaluator(Ticket ticket) {
        switch (ticket.getType()) {
            case VIP:
                return 5;
            case USUAL:
                return 4;
            case BUDGETARY:
                return 3;
            case CHEAP:
                return 2;
            default:
                return 1;
        }

    }

    @Override
    public Comparator reversed() {
        return null;
    }

    @Override
    public Comparator thenComparing(Comparator other) {
        return null;
    }

    @Override
    public Comparator thenComparingInt(ToIntFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparingLong(ToLongFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparing(Function keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparing(Function keyExtractor, Comparator keyComparator) {
        return null;
    }
}
