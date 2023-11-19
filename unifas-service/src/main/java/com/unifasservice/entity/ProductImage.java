package com.unifasservice.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "IMAGE_PRODUCT")

public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "URL")
    private String url;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
