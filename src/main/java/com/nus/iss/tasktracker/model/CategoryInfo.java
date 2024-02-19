package com.nus.iss.tasktracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class CategoryInfo {
    public CategoryInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "category_name")
    private String categoryName;
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
        return "CategoryInfo{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", deletedFlag=" + deletedFlag +
                '}';
    }
}

/*


CREATE TABLE task_category(
   task_category_id INTEGER NOT NULL,
   category_name  TEXT,
   PRIMARY KEY (task_category_id)

);


 */