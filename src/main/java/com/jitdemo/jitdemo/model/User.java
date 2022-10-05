package com.jitdemo.jitdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss.S")
    private Date createdOn;
    private String email;
    private String firstName;
    private String secondName;


/*    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
*/
}