package com.ktoda.threadedblogapp.thread;

import com.ktoda.threadedblogapp.comment.Comment;
import com.ktoda.threadedblogapp.space.Space;
import com.ktoda.threadedblogapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "threads")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_id")
    private Long threadId;
    @Size(min = 10, max = 50)
    private String title;
    @Size(min = 10, max = 500)
    private String description;
    @Enumerated(EnumType.STRING)
    private ThreadType type;
    @Enumerated(EnumType.STRING)
    private ThreadTopic topic;
    @Column(name = "engagement")
    private Double threadEngagement = 0d;
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "modified_on")
    private Date modifiedOn;
    @ManyToOne
    @JoinColumn(name = "space")
    private Space space;
    @ManyToOne
    @JoinColumn(name = "user")
    private User creator;
    @OneToMany(mappedBy = "thread")
    private List<Comment> comments = new ArrayList<>();
    @Transient
    private double engagementFactor = 2.5d;

    public Thread(String title, String description, ThreadType type, ThreadTopic topic, Date createdOn, Space space) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.topic = topic;
        this.createdOn = createdOn;
        this.space = space;
    }

    public Double getThreadEngagement() { // Simulated formula for thread engagement
        return (space.getUserCount() + space.getThreadsCount()) / engagementFactor;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

}
