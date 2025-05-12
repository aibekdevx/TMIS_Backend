package com.diploma.authservice.service;

import com.diploma.authservice.dto.JobInfoDTO;

import java.util.List;

public interface JobInfoService {

    // Создать JobInfo, привязанное к конкретному teacherId
    JobInfoDTO createJobInfoForTeacher(Integer teacherId, JobInfoDTO dto);

    // Получить все JobInfo для конкретного teacherId
    List<JobInfoDTO> getAllJobInfosByTeacherId(Integer teacherId);

    // Получить JobInfo по jobId, но проверяем, что оно относится к teacherId
    JobInfoDTO getJobInfoByTeacher(Integer teacherId, Integer jobId);

    // Обновить JobInfo (jobId) для конкретного teacherId
    JobInfoDTO updateJobInfoByTeacher(Integer teacherId, Integer jobId, JobInfoDTO dto);


    List<String> getAllDistinctOrganizations();


    // Удалить JobInfo (jobId) для конкретного teacherId
    void deleteJobInfoByTeacher(Integer teacherId, Integer jobId);
}

