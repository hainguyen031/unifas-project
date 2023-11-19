package com.unifasservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VARIANT")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "COLOR_ID")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "SIZE_ID")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;


}
