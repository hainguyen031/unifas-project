package com.unifasservice.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SALE_VOUCHER")


public class SaleVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DISCOUNT")
    private double discount;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @OneToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
