package com.unifasservice.entity;


import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
@Where(clause = "IS_DELETED = 'FALSE'")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "STOCK")
    private int stock;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STAR")
    private int star;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "SUB_CATEGORY_ID")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Variant>  variantList ;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ImageProduct>  imageProductList ;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review>  reviews ;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CartProduct>  cartProductList ;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderLine>  orderLineList ;



}
