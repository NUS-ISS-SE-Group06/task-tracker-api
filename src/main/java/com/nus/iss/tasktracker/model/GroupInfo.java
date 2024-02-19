package com.nus.iss.tasktracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class GroupInfo {
    public GroupInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int groupId;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "group_description")
    private String groupDescription;
    @Column(name = "created_by")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    @Column(name = "deleted_flag")
    private boolean deletedFlag;

    @Override
    public String toString() {
        return "GroupInfo{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", deletedFlag=" + deletedFlag +
                '}';
    }
}

/*

CREATE TABLE group_info(
   group_id INTEGER NOT NULL,
   group_name TEXT,
   group_description TEXT,
   created_by TEXT NOT NULL,
   created_date datetime default current_timestamp NOT NULL,
   modified_by TEXT,
   modified_date datetime default current_timestamp,
   "delete_flag" TEXT NOT NULL
        CHECK( typeof("boolean") = "text" AND
               "boolean" IN ("TRUE","FALSE")
   ),
   PRIMARY KEY (group_id)

);





CREATE TABLE group_info(
   group_id INTEGER NOT NULL,
   group_name TEXT,
   group_description TEXT,
   created_by TEXT NOT NULL,
   created_date datetime default current_timestamp NOT NULL,
   modified_by TEXT,
   modified_date datetime default current_timestamp,
   "delete_flag" TEXT NOT NULL
        CHECK( typeof("boolean") = "text" AND
               "boolean" IN ("TRUE","FALSE")
   ),
   PRIMARY KEY (group_id)

);


CREATE TABLE task_info(
   task_id INTEGER NOT NULL,
   task_name INTEGER,
   task_description TEXT,
   task_priority TEXT NOT NULL,
   task_cateogry_id INTEGER NOT NULL,
   task_due_date datetime default current_timestamp NOT NULL,
   task_assignee INTEGER,
   task_reward_pints INTEGER,
   task_status TEXT NOT NULL,
   created_by TEXT NOT NULL,
   created_date datetime default current_timestamp NOT NULL,
   modified_by TEXT,
   modifed_date datetime default current_timestamp NOT NULL,
    "delete_flag" TEXT NOT NULL
        CHECK( typeof("boolean") = "text" AND
               "boolean" IN ("TRUE","FALSE")
   ),
   PRIMARY KEY (task_id)

);




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



CREATE TABLE task_audit(
   task_audit_id INTEGER NOT NULL,
   audit_comment  TEXT,
   audit_by TEXT,
   PRIMARY KEY (task_audit_id)

);

 */