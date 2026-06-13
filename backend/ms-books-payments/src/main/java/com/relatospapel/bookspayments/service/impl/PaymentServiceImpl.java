package com.relatospapel.bookspayments.service.impl;

import com.relatospapel.bookspayments.client.BooksCatalogueClient;
import com.relatospapel.bookspayments.dto.BookResponse;
import com.relatospapel.bookspayments.dto.OrderDetailDto;
import com.relatospapel.bookspayments.dto.OrderDto;
import com.relatospapel.bookspayments.dto.StockUpdateRequest;
import com.relatospapel.bookspayments.dto.enums.StockOperation;
import com.relatospapel.bookspayments.entity.OrderDetailsEntity;
import com.relatospapel.bookspayments.entity.OrderEntity;
import com.relatospapel.bookspayments.exception.InvalidDataException;
import com.relatospapel.bookspayments.mapper.Mapper;
import com.relatospapel.bookspayments.repository.OrderRepository;
import com.relatospapel.bookspayments.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BooksCatalogueClient booksCatalogueClient;

    @Autowired
    private Mapper mapper;

    @Override
    public OrderDto addOrder(OrderDto orderDto) {

        if(orderDto == null){
            throw new NullPointerException("El pedido DTO no debe ser null");
        }
        OrderEntity order = mapper.toOrderEntity(orderDto);
        for (OrderDetailDto detailDto : orderDto.details()){
            BookResponse book = booksCatalogueClient.findBookById(detailDto.bookId());
            if(book != null){
                if (detailDto.quantity() > book.stock()) {
                    throw new InvalidDataException(
                            "El libro con id " + detailDto.bookId() +
                                    " no tiene stock suficiente. Disponible: " + book.stock() +
                                    ", solicitado: " + detailDto.quantity()
                    );
                }
                if(!book.visible()){
                    throw new InvalidDataException(
                            "El libro con id " + detailDto.bookId() +
                                    ", esta con estado oculto"
                    );
                }
                OrderDetailsEntity details = mapper.toOrderDetailsEntity(book, detailDto);
                order.addDetalle(details);
                StockUpdateRequest stockUpdateRequest = new StockUpdateRequest(detailDto.quantity(), StockOperation.SALE);
                booksCatalogueClient.updateBookQuantity(detailDto.bookId(), stockUpdateRequest);
            }
        }
        log.info("Objeto entity creado : {}", order.getShippingAddress());
        OrderEntity saved = orderRepository.save(order);
        log.info("Pedido registrado. orderId={}, trackingCode={}, detalles={}",
                saved.getOrderId(), saved.getTrackingCode(), saved.getDetails().size());
        return orderDto;
    }

}
