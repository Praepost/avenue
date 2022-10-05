package com.avenues.avenues.work.user.repository.entity;

import com.avenues.avenues.work.AEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends AEntity {
    private String status;
    private String username;
    private String email;
    private String image;


}
