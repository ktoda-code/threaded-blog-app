package com.ktoda.threadedblogapp.authority;


import com.ktoda.threadedblogapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
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
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}
