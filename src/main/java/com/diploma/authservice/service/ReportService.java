package com.diploma.authservice.service;

import com.diploma.authservice.entity.ForeignCognition;
import com.diploma.authservice.entity.JobInfo;
import com.diploma.authservice.entity.Teachers;
import com.diploma.authservice.repository.TeacherRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final TeacherRepository teacherRepo;

    public ReportService(TeacherRepository teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public void generateTeachersReport(OutputStream out) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Teachers");

        String[] headers = {
                "Teacher ID","First Name","Last Name",
                "Education","Work Experience","Academic Titles",
                "Academic Degrees","Foreign Cognitions"
        };
        Row h = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            h.createCell(i).setCellValue(headers[i]);
        }

        List<Teachers> all = teacherRepo.findAll();
        for (int i = 0; i < all.size(); i++) {
            Teachers t = all.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(t.getTeacherId());
            row.createCell(1).setCellValue(t.getUser().getFirstName());
            row.createCell(2).setCellValue(t.getUser().getLastName());

            // 2. Образование
            String ed = t.getEducations().stream()
                    .map(e -> String.join("; ",
                            e.getInstitutionName(),
                            e.getSpecialty(),
                            e.getDiplomaQualification(),
                            e.getStartYear()+"-"+e.getEndYear()
                    ))
                    .collect(Collectors.joining("\n"));
            row.createCell(3).setCellValue(ed);

            // 3. Стаж работы
            String wi = t.getJobInfos().stream()
                    .map(j -> String.join("; ",
                            j.getOrganizationName(),
                            j.getPosition(),
                            j.getWorkExperience()+" years"
                    ))
                    .collect(Collectors.joining("\n"));
            row.createCell(4).setCellValue(wi);

            // 4. Academic Titles
            String at = t.getAcademicTitles().stream()
                    .map(a -> String.join("; ",
                            a.getTitleName(),
                            a.getSpecialty(),
                            String.valueOf(a.getYearConferred())
                    ))
                    .collect(Collectors.joining("\n"));
            row.createCell(5).setCellValue(at);

            // 5. Academic Degrees
            String ad = t.getAcademicDegrees().stream()
                    .map(d -> String.join("; ",
                            d.getDegreeType(),
                            d.getSpecialty(),
                            String.valueOf(d.getYearAwarded())
                    ))
                    .collect(Collectors.joining("\n"));
            row.createCell(6).setCellValue(ad);

            // 6. Foreign Cognitions
            String fc = t.getForeignCognitions().stream()
                    .map(f -> f.getDocumentName())
                    .collect(Collectors.joining("\n"));
            row.createCell(7).setCellValue(fc);
        }

        for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);
        wb.write(out);
        wb.close();
    }

    public void generateTeachersSummaryReport(OutputStream out) throws IOException {
        // 1) создаём книгу и лист
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Teachers Summary");

        // 2) шапка
        String[] headers = {
                "Teacher ID", "First Name", "Last Name",
                "Date of Birth", "Place of Birth",
                "Education",
                "Main Job (Org; Position)",
                "Total Work Experience (years)",
                "Criminal Record",
                "Has Medical Book",
                "Military Rank",
                "Inclusive Education",
                "Foreign Cognition"
        };
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // 3) перебор всех преподавателей
        List<Teachers> list = teacherRepo.findAll();  // или с EntityGraph, чтобы сразу подтянуть связи
        for (int i = 0; i < list.size(); i++) {
            Teachers t = list.get(i);
            Row row = sheet.createRow(i + 1);

            // базовые поля
            row.createCell(0).setCellValue(t.getTeacherId());
            row.createCell(1).setCellValue(t.getUser().getFirstName());
            row.createCell(2).setCellValue(t.getUser().getLastName());
            row.createCell(3).setCellValue(
                    t.getDateOfBirth() != null ? t.getDateOfBirth().toString() : "");
            row.createCell(4).setCellValue(
                    t.getPlaceOfBirth() != null ? t.getPlaceOfBirth() : "");

            // Education (OneToMany → "\n" + ";")
            String education = Optional.ofNullable(t.getEducations())
                    .orElse(Collections.emptyList()).stream()
                    .map(e -> String.join("; ",
                            e.getInstitutionName(),
                            e.getSpecialty(),
                            e.getDiplomaQualification(),
                            e.getStartYear() + "-" + e.getEndYear()
                    ))
                    .collect(Collectors.joining("\n"));
            row.createCell(5).setCellValue(education);

            // Main job (флаг mainWorkFlag)
            JobInfo main = Optional.ofNullable(t.getJobInfos())
                    .orElse(Collections.emptyList()).stream()
                    .filter(JobInfo::getMainWorkFlag)
                    .findFirst().orElse(null);
            String mainJob = main == null ? "" :
                    main.getOrganizationName() + "; " + main.getPosition();
            row.createCell(6).setCellValue(mainJob);

            // Total workExperience
            int totalExp = Optional.ofNullable(t.getJobInfos())
                    .orElse(Collections.emptyList()).stream()
                    .mapToInt(JobInfo::getWorkExperience).sum();
            row.createCell(7).setCellValue(totalExp);

            // Остальные поля из Teachers
            row.createCell(8).setCellValue(
                    t.getCriminalRecord() != null ? t.getCriminalRecord() : "");
            row.createCell(9).setCellValue(
                    Boolean.TRUE.equals(t.getHasMedicalBook()) ? "Yes" : "No");
            row.createCell(10).setCellValue(
                    t.getMilitaryRank() != null ? t.getMilitaryRank() : "");

            // Inclusive Education (если есть List<InclusiveEducation>)
            String incEd = Optional.ofNullable(t.getInclusiveEducations())
                    .orElse(Collections.emptyList()).stream()
                    .map(incl -> String.join("; ",
                            incl.getCourses(),
                            incl.getInternships()
                    ))
                    .collect(Collectors.joining("\n"));
            row.createCell(11).setCellValue(incEd);

            // Foreign Cognition
            String fc = Optional.ofNullable(t.getForeignCognitions())
                    .orElse(Collections.emptyList()).stream()
                    .map(ForeignCognition::getDocumentName)
                    .collect(Collectors.joining("\n"));
            row.createCell(12).setCellValue(fc);
        }

        // 4) авто-размер колонок
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // 5) запись в поток и закрытие
        wb.write(out);
        wb.close();
    }

}
