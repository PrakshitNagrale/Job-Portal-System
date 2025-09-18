package com.prakshit.jobportalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass                       //it will not create any table in db,only put the attributes to the class
@Getter
@Setter
public abstract class BaseModel {

    @Id                                 //it will map the field as primary key in db
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")            //mysql store UUId as binary ,and binary 16 occupy less space and improve query performance than VARCHAR(36)
    private UUID id;

    @CreationTimestamp                  //it will automatically put the date & time in db when the new entry is created
    @Column(name = "created_at",nullable = false,updatable = false) //every entry must have created_at and it should not be updated once created
    private LocalDateTime createdAt;

    @UpdateTimestamp                  //it will automatically put the date & time in db when the existing column is updated
    @Column(name ="updated_at")
    private LocalDateTime updatedAt;
}
