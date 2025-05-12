package com.diploma.authservice.controller;


import com.diploma.authservice.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/teachers", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public void exportTeachersExcel(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=teachers_report.xlsx");
        reportService.generateTeachersReport(response.getOutputStream());
    }

    @GetMapping(value = "/teachers/summary",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public void exportTeachersSummary(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition",
                "attachment; filename=teachers_summary.xlsx");
        reportService.generateTeachersSummaryReport(response.getOutputStream());
    }

}
