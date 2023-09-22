package jp.ac.u_aizu.ta_report_system.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.u_aizu.ta_report_system.dto.ReportAndRecordRequest;
import jp.ac.u_aizu.ta_report_system.entity.AssistanceType;
import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.entity.Course;
import jp.ac.u_aizu.ta_report_system.entity.CourseName;
import jp.ac.u_aizu.ta_report_system.entity.Record;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import jp.ac.u_aizu.ta_report_system.entity.WorkCategory;
import jp.ac.u_aizu.ta_report_system.model.form.ReportForm;
import jp.ac.u_aizu.ta_report_system.model.form.ReportListForm;
import jp.ac.u_aizu.ta_report_system.repository.AssistanceTypeRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistantRepository;
import jp.ac.u_aizu.ta_report_system.repository.CourseNameRepository;
import jp.ac.u_aizu.ta_report_system.repository.CourseRepository;
import jp.ac.u_aizu.ta_report_system.repository.RecordRepository;
import jp.ac.u_aizu.ta_report_system.repository.ReportRepository;
import jp.ac.u_aizu.ta_report_system.repository.WorkCategoryRepository;
import jp.ac.u_aizu.ta_report_system.utils.DateUtils;
import jp.ac.u_aizu.ta_report_system.utils.TimeUtils;

@Service
public class ReportListService {

  @Autowired
  AssistantRepository assistantRepository;

  @Autowired
  AssistanceTypeRepository assistanceTypeRepository;

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  CourseNameRepository courseNameRepository;

  @Autowired
  ReportRepository reportRepository;

  @Autowired
  RecordRepository recordRepository;

  @Autowired
  WorkCategoryRepository workCategoryRepository;

  public List<ReportListForm> createReportListForms(Long assistantId) {
    Optional<Assistant> assistantOptional = assistantRepository.findById(assistantId);
    if (assistantOptional.isEmpty()) {
      return new ArrayList<>();
    }
    Map<String, Integer> intMonthByStr = IntStream.rangeClosed(1, 12).boxed()
        .map(num -> LocalDate.now().withMonth(num))
        .collect(Collectors.toMap(date -> date.getMonth().toString(), LocalDate::getMonthValue));
    
    List<Report> reports = reportRepository.findByAssistant(assistantOptional.get());
    Map<String, List<Report>> groupedReports = groupReportsByPeriod(reports);
    List<ReportListForm> reportListForms = groupedReports.entrySet().stream()
        .map(gres -> createReportListForm(gres.getValue()))
        .sorted(Comparator.comparing(rlf -> intMonthByStr.get(rlf.getMonth())))
        .sorted(Comparator.comparing(rlf -> rlf.getYear()))
        .collect(Collectors.toList());
    Collections.reverse(reportListForms);
    return reportListForms;
  }

  private Map<String, List<Report>> groupReportsByPeriod(List<Report> reports) {
    return reports.stream()
        .collect(Collectors.groupingBy(r -> String.valueOf(r.getTargetYear()) + String.valueOf(r.getTargetMonth())));
  }

  private ReportListForm createReportListForm(List<Report> reports) {
    Report referenceReport = reports.get(0);
    List<ReportForm> reportForms = createReportForms(reports);

    ReportListForm reportListForm = new ReportListForm();
    reportListForm.setYear(referenceReport.getTargetMonth().getYear());
    reportListForm.setMonth(referenceReport.getTargetMonth().getMonth().toString());
    reportListForm.setMonthlyWorkHour(calculateMonthlyTotalWorkHourForAllReports(reports));
    reportListForm.setMonthlyAllocatedHour(calculateMonthlyTotalAllocatedHourForAllReports(reports));
    reportListForm.setReportForms(reportForms);

    return reportListForm;
  }

  private Double calculateMonthlyTotalWorkHourForAllReports(List<Report> reports) {
    return Math.floor(reports.stream().mapToDouble(r -> r.getMonthlyTotalWorkMinute()).sum() / 60 * 10) / 10;
  }

  private Double calculateMonthlyTotalAllocatedHourForAllReports(List<Report> reports) {
    return Math.floor(reports.stream().mapToDouble(r -> r.getMonthlyTotalAllocatedMinute()).sum() / 60 * 10) / 10;
  }

  private List<ReportForm> createReportForms(List<Report> reports) {
    return reports.stream()
        .sorted(Comparator.comparing(r -> r.getCourse().getId()))
        .map(this::createReportForm)
        .toList();
  }

  private ReportForm createReportForm(Report report) {
    ReportForm reportForm = new ReportForm();
    reportForm.setCourse(report.getCourse().getCourseName().getName());
    reportForm.setMonthlyCourseWorkHour(TimeUtils.convertMinuteToHour(report.getMonthlyTotalWorkMinute()));
    reportForm.setLastEditUser(report.getEditedUser().getFullName());
    reportForm.setLastEditDate(
        DateUtils.formatDate(report.getEditedDate(), DateUtils.DATE_SPLITTED_BY_SLASH));
    reportForm.setReportId(report.getId());
    return reportForm;
  }

  public boolean createReportAndRecord(ReportAndRecordRequest reportAndRecordRequest) {

    Report report = createReport(reportAndRecordRequest);
    Record record = createRecord(reportAndRecordRequest, report);
    boolean saveSuccess = false;
    List<Report> reports = reportRepository.findAll();
    boolean reportFlag = true;
    for(int i = 0; i < reports.size(); i++) {
      if(report.getTargetMonth().getMonth().equals(reports.get(i).getTargetMonth().getMonth()) && report.getCourse().equals(reports.get(i).getCourse())) {
        reportFlag = false;
        record = createRecord(reportAndRecordRequest, reports.get(i));
        break;
      }
    }

    if(reportFlag) {
      reportRepository.save(report);
    }

    boolean recordFlag = checkOverlappingTimeOfRecordWhenCreate(record);

    if(!recordFlag) {
      recordRepository.save(record);
      setTotalWorkMinuteForReport(record);
      saveSuccess = true;
    }
    
    return saveSuccess;
  }

  private Report createReport(ReportAndRecordRequest item) {
    Date now = new Date();

    Long assistantId = item.getAssistantId();
    Optional<Assistant> assistantOptional = assistantRepository.findById(assistantId);
    Optional<CourseName> courseNameOptional =
        courseNameRepository.findById(item.getCourseNameId());
    Optional<AssistanceType> assistanceTypeOptional =
        assistanceTypeRepository.findById(item.getAssistanceTypeId());
    if (courseNameOptional.isEmpty() || assistantOptional.isEmpty()
        || assistanceTypeOptional.isEmpty()) {
      throw new EntityNotFoundException();
    }
    Assistant assistant = assistantOptional.get();
    CourseName courseName = courseNameOptional.get();
    AssistanceType assistanceType = assistanceTypeOptional.get();

    Optional<Course> courseOptional = courseRepository.findByCourseName(courseName);
    if (courseOptional.isEmpty()) {
      throw new EntityNotFoundException();
    }
    Course course = courseOptional.get();
    LocalDate targetDate = item.getTargetDate();

    Report report = new Report();
    report.setAssistant(assistant);
    report.setCourse(course);
    report.setAssistanceType(assistanceType);
    report.setTargetYear(targetDate.getYear());
    report.setTargetMonth(targetDate.withDayOfMonth(1));
    //incremented when creating a record.
    report.setMonthlyTotalWorkMinute(0);
    report.setMonthlyTotalAllocatedMinute(Report.MONTHLY_MAXIMUM_ALLOCATED_MINUTE);
    report.setEditedUser(assistant.getUser());
    report.setVerified(false);
    report.setApproved(false);
    report.setEditedDate(LocalDate.ofInstant(now.toInstant(), ZoneId.systemDefault()));
    report.setDeleted(false);

    return report;
  }

  private Record createRecord(ReportAndRecordRequest item, Report report) {
    Long workCategoryId = item.getWorkCategoryId();
    Optional<WorkCategory> workCategoryOptional = workCategoryRepository.findById(workCategoryId);
    if (workCategoryOptional.isEmpty()) {
      throw new EntityNotFoundException();
    }

    WorkCategory workCategory = workCategoryOptional.get();
    Integer breakHour = item.getBreakHour() * 60;
    Integer breakMinute = item.getBreakMinute();
    Integer breakTime = breakHour + breakMinute;
    LocalTime startTime = item.getStartTime();
    LocalTime endTime = item.getEndTime();
    Integer totalWorkTime = (int) startTime.until(endTime, ChronoUnit.MINUTES) - breakTime;
    
    report.setMonthlyTotalWorkMinute(report.getMonthlyTotalWorkMinute() + totalWorkTime);

    Record workRecord = new Record();
    workRecord.setReport(report);
    workRecord.setWorkCategory(workCategory);
    workRecord.setTargetDate(item.getTargetDate());
    workRecord.setStartTime(item.getStartTime());
    workRecord.setEndTime(item.getEndTime());
    workRecord.setBreakMinute(breakTime);
    workRecord.setTotalWorkMinute(totalWorkTime);
    workRecord.setDeleted(false);

    return workRecord;
  }
  
  public boolean judgeMonthlyTotalWorkHour(Assistant assistant) {
  	List<Report> reports = reportRepository.findByAssistant(assistant);
  	
  	boolean flag = false;
  	for(int i = 0; i < reports.size(); i++) {
  		flag = checkMonthlyTotalWorkMinute(reports.get(i).getTargetMonth());
  		if(flag) return flag;
  	}
  	
  	return flag;
  }
  
  private boolean checkMonthlyTotalWorkMinute(LocalDate targetMonth) {
  	final Integer limitMonthlyTotalWorkMinute = 7200;
  	Integer monthlyTotalWorkMinute = 0;
  	List<Report> reports = reportRepository.findByTargetMonth(targetMonth);
  	
  	boolean flag = false;
  	for(int i = 0; i < reports.size(); i++) {
  		monthlyTotalWorkMinute += reports.get(i).getMonthlyTotalWorkMinute();
  		if(monthlyTotalWorkMinute > limitMonthlyTotalWorkMinute) {
  			flag = true;
  			return flag;
  		}
  	}
  	
  	return flag;
  }
  
  private boolean checkOverlappingTimeOfRecordWhenCreate(Record record) {
		boolean flag = false;
		List<Record> records = recordRepository.findByAssistantIdAndTargetDate(record.getReport().getAssistant().getId(), record.getTargetDate());;

		for(int i = 0; i < records.size(); i++) {
			LocalTime startTime = records.get(i).getStartTime();
			LocalTime endTime = records.get(i).getEndTime();
			if(record.getStartTime().equals(startTime)) {
				flag = true;
				break;
			}

			if(record.getEndTime().equals(endTime)) {
				flag = true;
				break;
			}

			if(record.getStartTime().isAfter(startTime) && record.getStartTime().isBefore(endTime)) {
				flag = true;
				break;
			}

			if(record.getEndTime().isAfter(startTime) && record.getEndTime().isBefore(endTime)) {
				flag = true;
				break;
			}

			if(record.getStartTime().isBefore(startTime) && record.getEndTime().isAfter(endTime)) {
				flag = true;
				break;
			}
		}

		return flag;
	}
  
  private void setTotalWorkMinuteForReport(Record record) {
  	Optional<Report> referenceReport = reportRepository.findById(record.getReport().getId());
  	if(referenceReport.isEmpty()) {
  		throw new EntityNotFoundException();
  	}
  	
  	Report workReport = referenceReport.get();
  	Integer totalWorkMinute = 0;
  	List<Record> records = recordRepository.findDistinctIdByReportIdAndDeletedFalse(workReport.getId());
  	for(int i = 0; i < records.size(); i++) {
  		totalWorkMinute += records.get(i).getTotalWorkMinute();
  	}
  	
  	workReport.setMonthlyTotalWorkMinute(totalWorkMinute);
  	reportRepository.save(workReport);
  }
}
