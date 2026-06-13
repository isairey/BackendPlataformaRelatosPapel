package com.relatospapel.bookspayments.controller;



import com.relatospapel.bookspayments.dto.OrderDto;
import com.relatospapel.bookspayments.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@Validated @RequestBody OrderDto orderDto) {
        log.info("Metodo para registrar una orden...");
        return new ResponseEntity<>(paymentService.addOrder(orderDto), HttpStatus.CREATED);
    }


}
