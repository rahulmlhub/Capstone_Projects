package com.metlife.guests.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Admin
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Guest {

    @Id
    private String guestId;
    private String name;
    private String email;
    private String phoneNumber;
   private String aadharNumber;

    // Getters and setters
}
