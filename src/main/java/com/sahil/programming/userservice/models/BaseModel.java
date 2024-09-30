package com.sahil.programming.userservice.models;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private boolean deleted;
}
