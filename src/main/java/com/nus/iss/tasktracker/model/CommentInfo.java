package com.nus.iss.tasktracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class CommentInfo {
    public CommentInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "task_id")
    private Integer taskId;
    @Column(name = "task_comment")
    private String taskComment;
    @Column(name = "created_by")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "deleted_flag")
    private boolean deletedFlag;

    @Override
    public String toString() {
        return "CommentInfo{" +
                "commentId=" + commentId +
                ", taskId=" + taskId +
                ", taskComment='" + taskComment + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", deletedFlag=" + deletedFlag +
                '}';
    }
}

/*

CREATE TABLE task_comments(
   task_comment_id INTEGER NOT NULL,
   task_id INTEGER NOT NULL,
   task_comment TEXT,
   created_by TEXT NOT NULL,
   created_date datetime default current_timestamp NOT NULL,
   PRIMARY KEY (task_comment_id,task_id),
   FOREIGN KEY (task_id)
      REFERENCES task_info (task_id)
         ON DELETE CASCADE
         ON UPDATE NO ACTION
);



 */