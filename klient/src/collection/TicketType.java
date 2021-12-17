package collection;

import java.io.Serializable;

/**
 * Тип билета
 */
public enum TicketType implements Serializable {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;
}
