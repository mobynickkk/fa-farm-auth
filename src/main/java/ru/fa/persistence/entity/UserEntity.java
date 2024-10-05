package ru.fa.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String username;
    private String password;

    @Basic(optional = false)
    private String firstName;
    private String secondName;
    @Basic(optional = false)
    private String email;
    private String phone;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "authorities",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role")
    )
    private Collection<RoleEntity> roles = new ArrayList<>();
}
