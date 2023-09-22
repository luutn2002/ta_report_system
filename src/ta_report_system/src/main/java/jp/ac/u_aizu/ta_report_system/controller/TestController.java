package jp.ac.u_aizu.ta_report_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.u_aizu.ta_report_system.repository.AccountRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistanceTypeRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistantCourseRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistantGraduateRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistantNationalityRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistantRepository;
import jp.ac.u_aizu.ta_report_system.repository.AuthorityRepository;
import jp.ac.u_aizu.ta_report_system.repository.CourseNameRepository;
import jp.ac.u_aizu.ta_report_system.repository.CourseRepository;
import jp.ac.u_aizu.ta_report_system.repository.FacultyCourseRepository;
import jp.ac.u_aizu.ta_report_system.repository.FacultyRepository;
import jp.ac.u_aizu.ta_report_system.repository.PeriodRepository;
import jp.ac.u_aizu.ta_report_system.repository.RecordRepository;
import jp.ac.u_aizu.ta_report_system.repository.ReportRepository;
import jp.ac.u_aizu.ta_report_system.repository.StaffRepository;
import jp.ac.u_aizu.ta_report_system.repository.TermRepository;
import jp.ac.u_aizu.ta_report_system.repository.UserRepository;
import jp.ac.u_aizu.ta_report_system.repository.WorkCategoryRepository;

@Controller
public class TestController {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  AssistanceTypeRepository assistanceTypeRepository;

  @Autowired
  AssistantRepository assistantRepository;

  @Autowired
  AssistantCourseRepository assistantCourseRepository;

  @Autowired
  AssistantGraduateRepository assistantGraduateRepository;

  @Autowired
  AssistantNationalityRepository assistantNationalityRepository;

  @Autowired
  AuthorityRepository authorityRepository;

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  CourseNameRepository courseNameRepository;

  @Autowired
  FacultyRepository facultyRepository;

  @Autowired
  FacultyCourseRepository facultyCourseRepository;

  @Autowired
  PeriodRepository periodRepository;

  @Autowired
  RecordRepository recordRepository;

  @Autowired
  ReportRepository reportRepository;

  @Autowired
  StaffRepository staffRepository;

  @Autowired
  TermRepository termRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  WorkCategoryRepository workCategoryRepository;

  @GetMapping("/request")
  public String home(@RequestParam(required = false) String message, Model model) {
    model.addAttribute("message", message);
    return "test/request";
  }

  @GetMapping("/response")
  public String response(@RequestParam String request, Model model) {
    model.addAttribute("response", request);
    return "test/response";
  }
}
