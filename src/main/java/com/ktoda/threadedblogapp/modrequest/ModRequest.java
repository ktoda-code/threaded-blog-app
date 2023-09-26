package com.ktoda.threadedblogapp.modrequest;

import com.ktoda.threadedblogapp.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ModRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;
    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    public ModRequest(User fromUser) {
        this.fromUser = fromUser;
    }
}
