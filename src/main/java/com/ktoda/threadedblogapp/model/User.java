package com.ktoda.threadedblogapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @NotNull
    @Column(name = "user_id")
    private UUID userId;
    @Column(unique = true, nullable = false)
    @Size(min = 10, max = 20)
    private String username;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @Size(min = 8, max = 18)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,18}$")
    private String password;
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority", referencedColumnName = "authority_id"),
            uniqueConstraints = @UniqueConstraint(name = "uc_user_authority", columnNames = {"user", "authority"})
    )
    private List<Authority> authorities;

    public User(String username, String email, String password) {
        this.userId = UUID.randomUUID(); // Generate a new random unique UUID
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.authorities = new ArrayList<>();
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        authorities.remove(authority);
    }
}
