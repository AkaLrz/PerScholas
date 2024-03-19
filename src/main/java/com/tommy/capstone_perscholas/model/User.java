package com.tommy.capstone_perscholas.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Collection;

@SuperBuilder
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    @LastModifiedDate
    @Column(name = "loginTime")
    private LocalDateTime loginTime;

    @CreatedDate
    @Column(name = "createTime", updatable = false)
    private LocalDateTime createTime;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection <Role> roles;

    public User(String userName, String firstName, String lastName, String email, String password ) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }




}
