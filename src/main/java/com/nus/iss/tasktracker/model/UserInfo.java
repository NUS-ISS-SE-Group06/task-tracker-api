package com.nus.iss.tasktracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class UserInfo {
    public UserInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "group_id")
    private String groupId;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "isChangePassword")
    private boolean isChangePassword;
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
        return "UserInfo{" +
                "userId=" + userId +
                ", groupId='" + groupId + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", isChangePassword=" + isChangePassword +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", deletedFlag=" + deletedFlag +
                '}';
    }
}

/*


CREATE TABLE user_info(
   user_id INTEGER NOT NULL,
   group_id INTEGER NOT NULL,
   email TEXT NOT NULL,
   name TEXT,
   username TEXT NOT NULL,
   password TEXT,
   role TEXT NOT NULL,
   "password_change_mandatory" TEXT NOT NULL
        CHECK( typeof("boolean") = "text" AND
               "boolean" IN ("TRUE","FALSE")
   ),
   created_by TEXT NOT NULL,
   created_date datetime default current_timestamp NOT NULL,
   modified_by TEXT,
   modified_date datetime default current_timestamp,
   "delete_flag" TEXT NOT NULL
        CHECK( typeof("boolean") = "text" AND
               "boolean" IN ("TRUE","FALSE")
   ),
   PRIMARY KEY (user_id, group_id),
   FOREIGN KEY (group_id)
      REFERENCES group_info (group_id)
         ON DELETE CASCADE
         ON UPDATE NO ACTION
);


 */