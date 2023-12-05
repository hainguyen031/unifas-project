package com.unifasservice.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "WARD")
    private String ward;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "CITY")
    private String city;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "RECEIVER")
    private String receiver;

    @Column(name = "IS_DEFAULT")
    private String isDefault;

    @Column(name="IS_DELETED")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "address")
    private List<Order> orderList;





}
