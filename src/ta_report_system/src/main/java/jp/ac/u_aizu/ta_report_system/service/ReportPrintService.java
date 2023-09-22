package jp.ac.u_aizu.ta_report_system.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.u_aizu.ta_report_system.entity.Record;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import jp.ac.u_aizu.ta_report_system.entity.WorkCategory;
import jp.ac.u_aizu.ta_report_system.model.form.ReportPrintForm;
import jp.ac.u_aizu.ta_report_system.model.form.ReportPrintListForm;
import jp.ac.u_aizu.ta_report_system.repository.RecordRepository;
import jp.ac.u_aizu.ta_report_system.repository.ReportRepository;
import jp.ac.u_aizu.ta_report_system.repository.WorkCategoryRepository;
import jp.ac.u_aizu.ta_report_system.utils.TimeUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportPrintService {

  @Autowired
  private final ReportRepository reportRepository;

  @Autowired
  private final RecordRepository recordRepository;
  
  @Autowired
  private final WorkCategoryRepository workCategoryRepository;

  public ReportPrintForm createReportPrintForm(Long reportId) {

    Optional<Report> reportOptional = reportRepository.findById(reportId);
    if (reportOptional.isEmpty()) {
      throw new EntityNotFoundException();
    }
    Double totalHour = 0.0;
    final ReportPrintForm reportPrintForm = new ReportPrintForm();
    final Report report = reportOptional.get();
    final LocalDate targetYearMonth = report.getTargetMonth();
    final List<ReportPrintListForm> reportPrintListForms = createReportPrintListForm(reportId);
    reportPrintForm.setTargetMonth(targetYearMonth.getMonth().toString());
    reportPrintForm.setTargetYear(report.getTargetYear().toString());
    reportPrintForm.setCourseName(report.getCourse().getCourseName().getName());
    reportPrintForm.setFacultyFullName(report.getCourse().getCoordinator().getUser().getFullName());
    reportPrintForm.setAssistanceType(report.getAssistanceType().getName());
    reportPrintForm.setStudentFullName(report.getAssistant().getUser().getFullName());
    for (ReportPrintListForm obj : reportPrintListForms) {
      totalHour += obj.getDailyTotalWorkHour();
    }
    reportPrintForm.setTotalWorkHour(Integer.valueOf((int)Math.round(totalHour)));
    reportPrintForm.setReportPrintListForm(reportPrintListForms);

    return reportPrintForm;
  }

  private List<ReportPrintListForm> createReportPrintListForm(Long reportId) {

    List<Record> records = recordRepository.findDistinctIdByReportIdAndDeletedFalse(reportId);
    if (records.isEmpty()) {
      return new ArrayList<>();
    }

    Map<String, Long> workCategoryNameById = workCategoryRepository.findByDeleted(false).stream()
        .collect(Collectors.toMap(WorkCategory::getName, WorkCategory::getId));

    List<ReportPrintListForm> reportPrintListforms = new ArrayList<>();
    String date;
    for( Record record : records){
      ReportPrintListForm reportPrintListForm = new ReportPrintListForm();
      reportPrintListForm.setTargetYear(record.getTargetDate().getYear());
      date = record.getTargetDate().format(DateTimeFormatter.ofPattern("MM/dd"));;
      reportPrintListForm.setTargetDate(String.valueOf(date));
      reportPrintListForm.setDailyTotalWorkHour(TimeUtils.convertMinuteToHour(record.getTotalWorkMinute()));
      reportPrintListForm.setWorkCategoryName(record.getWorkCategory().getName());
      reportPrintListforms.add(reportPrintListForm);
    }
    return reportPrintListforms.stream()
        .sorted(Comparator.comparing(rplf -> {
          String workCategoryName = rplf.getWorkCategoryName();
          return workCategoryNameById.get(workCategoryName);
        }))
        .sorted(Comparator.comparing(ReportPrintListForm::getTargetDate))
        .toList();
  }
  private Integer calculateTotalWorkMinuteFromRecords(List<Record> records) {
    return records.stream().mapToInt(Record::getTotalWorkMinute).sum();
  }
}
