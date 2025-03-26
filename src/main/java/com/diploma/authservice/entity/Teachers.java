package com.diploma.authservice.entity;

import com.diploma.authservice.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    private User user;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String criminalRecord;
    private Boolean hasMedicalBook;
    private String militaryRank;
    private String otherTitles;
    private Date createdAt;
    private Date updatedAt;

    //BY
}
