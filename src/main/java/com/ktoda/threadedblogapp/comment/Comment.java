package com.ktoda.threadedblogapp.comment;

import com.ktoda.threadedblogapp.thread.Thread;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentId;
    @NotNull
    private String content;
    @Column(name = "created_on")
    @NotNull
    private Timestamp createdOn = new Timestamp(System.currentTimeMillis());
    @Column(name = "edited_on")
    private Timestamp editedOn;
    @ManyToOne
    private Thread thread;

    public Comment(String content, Thread thread) {
        this.content = content;
        this.thread = thread;
    }
}
