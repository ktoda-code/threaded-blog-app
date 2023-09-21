package com.ktoda.threadedblogapp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long authorityId;
    @Column(name = "authority_role", unique = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    private AuthorityRole authorityRole;
    @ManyToMany(mappedBy = "authorities", targetEntity = User.class)
    private List<User> users;

    public Authority(AuthorityRole authorityRole) {
        this.authorityRole = authorityRole;
    }
}
