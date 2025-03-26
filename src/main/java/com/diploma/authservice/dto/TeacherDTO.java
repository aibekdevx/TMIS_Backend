package com.diploma.authservice.dto;

import com.diploma.authservice.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private Integer teacherId;
    private User user;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String criminalRecord;
    private Boolean hasMedicalBook;
    private String militaryRank;
    private String otherTitles;
    private Date createdAt;
    private Date updatedAt;

    // Аналогично можно добавить поля createdBy, updatedBy, если нужно
}
