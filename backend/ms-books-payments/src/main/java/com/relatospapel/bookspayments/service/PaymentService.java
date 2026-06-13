package com.relatospapel.bookspayments.service;

import com.relatospapel.bookspayments.dto.BookResponse;
import com.relatospapel.bookspayments.dto.OrderDto;

public interface PaymentService {

    OrderDto addOrder(OrderDto orderDto);

}
