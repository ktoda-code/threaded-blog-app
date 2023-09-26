package com.ktoda.threadedblogapp.space;

import com.ktoda.threadedblogapp.thread.Thread;
import com.ktoda.threadedblogapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "spaces")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Space {
    @Id
    @Column(name = "space_id", unique = true)
    private String spaceId;
    @Column(unique = true)
    @Size(max = 100)
    private String name;
    @Column(name = "background_img")
    private byte[] backgroundImg;
    @Size(max = 500)
    private String description;
    @Column(name = "user_count")
    private Integer userCount = 0; // number of active users
    @Column(name = "threads_count")
    private Integer threadsCount = 0; // number of threads in space
    @OneToMany(mappedBy = "space")
    private List<Thread> threads = new ArrayList<>();
    @ManyToMany(mappedBy = "spaces", targetEntity = User.class)
    private List<User> users = new ArrayList<>();

    public Space(String spaceId, String name, byte[] backgroundImg, String description) {
        this.name = name;
        this.spaceId = spaceId;
        this.backgroundImg = backgroundImg;
        this.description = description;
    }

    public void addThread(Thread thread) {
        threads.add(thread);
        threadsCount++;
    }

    public void removeThread(Thread thread) {
        threads.remove(thread);
        threadsCount--;
    }

    public void addUser(User user) {
        users.add(user);
        userCount++;
    }

    public void removeUser(User user) {
        users.remove(user);
        userCount--;
    }
}
