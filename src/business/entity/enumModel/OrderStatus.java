package business.entity.enumModel;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    UNCONFIRMED,
    CONFIRMED,
    DELIVERED,
    RETURNED,
    DELIVERING,
    REFUND
}
