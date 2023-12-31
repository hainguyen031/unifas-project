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
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "ROLE")
    private String role;

    @Column(name="IS_DELETED")
    private boolean isDeleted;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addressList ;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews ;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orderList ;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

}
