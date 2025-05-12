package com.diploma.authservice.repository;

import com.diploma.authservice.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobInfoRepository extends JpaRepository<JobInfo, Integer> {

    // Найти все JobInfo, связанные с конкретным teacherId
    List<JobInfo> findByTeacher_TeacherId(Integer teacherId);

    @Query("SELECT DISTINCT j.organizationName FROM JobInfo j WHERE j.organizationName IS NOT NULL")
    List<String> findDistinctOrganizationNames();


}

