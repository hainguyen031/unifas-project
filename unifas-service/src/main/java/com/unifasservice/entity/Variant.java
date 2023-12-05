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
@Table(name = "VARIANT")
@Where(clause = "IS_DELETED = 0")
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

    @OneToMany(mappedBy = "variant", fetch = FetchType.LAZY)
    private List<CartItem> cartItemList ;

    @OneToMany(mappedBy = "variant", fetch = FetchType.LAZY)
    private List<OrderLine> orderLineList ;

    @ManyToOne
    @JoinColumn( name = "PRODUCT_ID" )
    private Product product;
}
