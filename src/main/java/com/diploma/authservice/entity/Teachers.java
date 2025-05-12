package com.diploma.authservice.entity;

import com.diploma.authservice.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    @JsonManagedReference
    private User user;

    private Date dateOfBirth;
    private String placeOfBirth;
    private String criminalRecord;
    private Boolean hasMedicalBook;
    private String militaryRank;
    private String otherTitles;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobInfo> jobInfos;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcademicDegree> academicDegrees;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcademicTitle> academicTitles;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ForeignCognition> foreignCognitions;

    @OneToMany(mappedBy="teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InclusiveEducation> inclusiveEducations;

}
