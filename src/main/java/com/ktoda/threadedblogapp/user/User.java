package com.ktoda.threadedblogapp.user;

import com.ktoda.threadedblogapp.authority.Authority;
import com.ktoda.threadedblogapp.comment.Comment;
import com.ktoda.threadedblogapp.invite.Invite;
import com.ktoda.threadedblogapp.modrequest.ModRequest;
import com.ktoda.threadedblogapp.space.Space;
import com.ktoda.threadedblogapp.thread.Thread;
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
    private UUID userId = UUID.randomUUID(); // Generate a new random unique UUID
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
    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn = new Timestamp(System.currentTimeMillis());
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority", referencedColumnName = "authority_id"),
            uniqueConstraints = @UniqueConstraint(name = "uc_user_authority", columnNames = {"user", "authority"})
    )
    private List<Authority> authorities = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "user_space",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "space", referencedColumnName = "space_id"),
            uniqueConstraints = @UniqueConstraint(name = "uc_user_space", columnNames = {"user", "space"})
    )
    private List<Space> spaces = new ArrayList<>();
    @OneToMany(mappedBy = "fromUser")
    private List<Invite> sendInvites = new ArrayList<>();
    @OneToMany(mappedBy = "toUser")
    private List<Invite> receivedInvites = new ArrayList<>();
    @OneToMany(mappedBy = "fromUser")
    private List<ModRequest> requests = new ArrayList<>();
    @OneToMany(mappedBy = "creator")
    private List<Thread> threads = new ArrayList<>();
    @OneToMany(mappedBy = "threader")
    private List<Comment> comments = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        authorities.remove(authority);
    }

    public void addSendInvite(Invite invite) {
        sendInvites.add(invite);
    }

    public void removeSendInvite(Invite invite) {
        sendInvites.remove(invite);
    }

    public void addReceiveInvite(Invite invite) {
        receivedInvites.add(invite);
    }

    public void removeReceiveInvite(Invite invite) {
        receivedInvites.remove(invite);
    }

    public void addRequest(ModRequest request) {
        requests.add(request);
    }

    public void addThread(Thread thread) {
        threads.add(thread);
    }

    public void removeThread(Thread thread) {
        threads.remove(thread);
    }

    public void addSpace(Space space) {
        spaces.add(space);
    }

    public void removeSpace(Space space) {
        spaces.remove(space);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
}
