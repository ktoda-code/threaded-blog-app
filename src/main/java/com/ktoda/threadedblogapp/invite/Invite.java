package com.ktoda.threadedblogapp.invite;

import com.ktoda.threadedblogapp.space.Space;
import com.ktoda.threadedblogapp.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "invites")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_id")
    private Long inviteId;
    @OneToOne
    @JoinColumn(name = "space")
    private Space space;
    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;
    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;
    @Enumerated(EnumType.STRING)
    private InviteStatus status;

    public Invite(Space space, User fromUser, User toUser) {
        this.space = space;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}
