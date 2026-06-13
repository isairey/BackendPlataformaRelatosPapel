package com.relatospapel.bookspayments.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "details")
@Entity
@Table(name = "pedidos")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long orderId;

    @Column(name = "cliente_id", nullable = false)
    private Long clientId;

    @Column(name = "fecha_pedido", nullable = false)
    private OffsetDateTime orderDate;

    @Column(name = "estado_pedido", length = 20)
    private String statusOrder;

    @Column(name = "estado_pago", length = 20)
    private String statusPayment;

    @Column(name = "estado_envio", length = 20)
    private String statusShipment;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "descuento_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalDiscount;

    @Column(name = "impuestos", nullable = false, precision = 10, scale = 2)
    private BigDecimal tax;

    @Column(name = "costo_envio", nullable = false, precision = 10, scale = 2)
    private BigDecimal shippingCost;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "moneda", length = 3)
    private String currency;

    @Column(name = "metodo_pago", length = 50)
    private String paymentMethod;

    @Column(name = "direccion_envio", nullable = false, columnDefinition = "TEXT")
    private String shippingAddress;

    @Column(name = "codigo_rastreo", length = 100, unique = true)
    private String trackingCode;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderDetailsEntity> details = new ArrayList<>();

    // Helpers para mantener la relación bidireccional consistente
    public void addDetalle(OrderDetailsEntity d) {
        details.add(d);
        d.setOrder(this);
    }

    public void removeDetalle(OrderDetailsEntity d) {
        details.remove(d);
        d.setOrder(null);
    }

}
