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
@Table(name = "CATEGORY")
@Where(clause = "IS_SHOWN = 1")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "IS_SHOWN")
    private boolean isShown;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<SubCategory> subCategories ;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> productList ;
}
