package com.relatospapel.bookspayments.mapper;

import com.relatospapel.bookspayments.dto.BookResponse;
import com.relatospapel.bookspayments.dto.OrderDetailDto;
import com.relatospapel.bookspayments.dto.OrderDto;
import com.relatospapel.bookspayments.entity.OrderDetailsEntity;
import com.relatospapel.bookspayments.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
public class Mapper {

    public OrderEntity toOrderEntity(OrderDto dto){

        log.info("Maperar el pedido dto a entity...");
        if(dto == null){
            throw new NullPointerException("El pedido DTO no debe ser null");
        }
        if (dto.details() == null || dto.details().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener al menos un detalle");
        }
        String uuid = UUID.randomUUID().toString();
        return OrderEntity.builder()
                .clientId(dto.clientId())
                .orderDate(OffsetDateTime.now())
                .statusOrder(dto.statusOrder())
                .statusPayment(dto.statusPayment())
                .statusShipment(dto.statusShipment())
                .subtotal(dto.subtotal())
                .totalDiscount(dto.totalDiscount())
                .tax(dto.tax())
                .shippingCost(dto.shippingCost())
                .total(dto.total())
                .currency(dto.currency())
                .paymentMethod(dto.paymentMethod())
                .shippingAddress(dto.shippingAddress())
                .trackingCode(uuid)
                .build();
    }

    public OrderDetailsEntity toOrderDetailsEntity(BookResponse bookResponse,
                                                   OrderDetailDto detailDto){

        log.info("Mapper detalle del pedido dto a entity...");
        BigDecimal lineSubtotal = bookResponse.price().multiply(BigDecimal.valueOf(detailDto.quantity()));
        return  OrderDetailsEntity.builder()
                .bookId(bookResponse.idBook())
                .stock(detailDto.quantity())
                .precioUnitario(bookResponse.price())
                .subtotalLinea(lineSubtotal)
                .build();

    }

}
