package com.relatospapel.bookspayments.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderDto(
        Long clientId,
        String statusOrder,
        String statusPayment,
        String statusShipment,
        BigDecimal subtotal,
        BigDecimal totalDiscount,
        BigDecimal tax,
        BigDecimal shippingCost,
        BigDecimal total,
        String currency,
        String paymentMethod,
        String shippingAddress,
        List<OrderDetailDto> details
) {
}
