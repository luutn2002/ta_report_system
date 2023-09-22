package jp.ac.u_aizu.ta_report_system.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.ValueRange;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.entity.Record;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import jp.ac.u_aizu.ta_report_system.model.form.WeeklyWorkLimitErrorForm;
import jp.ac.u_aizu.ta_report_system.model.form.WorkLimitErrorForm;
import jp.ac.u_aizu.ta_report_system.repository.AssistantRepository;
import jp.ac.u_aizu.ta_report_system.repository.RecordRepository;
import jp.ac.u_aizu.ta_report_system.utils.TimeUtils;

@Service
public class WorkLimitValidationService {

  @Autowired
  AssistantRepository assistantRepository;

  @Autowired
  RecordRepository recordRepository;

  private static final Long SUNDAY = 1L;
  private static final Long SATURDAY = 7L;

  public WorkLimitErrorForm validateWeeklyWorkLimitForReport(Report report) {
    Assistant assistant = report.getAssistant();
    LocalDate initialDate = LocalDate.of(report.getTargetYear(), report.getTargetMonth().getMonthValue(), 1);
    LocalDate finalDate = LocalDate.of(report.getTargetYear(), report.getTargetMonth().getMonthValue(), 1)
        .with(TemporalAdjusters.lastDayOfMonth());
    return validateWeeklyWorkLimit(assistant, initialDate, finalDate);
  }

  public WorkLimitErrorForm validateWeeklyWorkLimitForAllReport(Assistant assistant) {
    List<Record> workRecord = recordRepository.findByAssistantId(assistant.getId());
    LocalDate initialDate = workRecord.stream()
        .map(Record::getTargetDate)
        .min(LocalDate::compareTo)
        .orElse(null);
    LocalDate finalDate = workRecord.stream()
        .map(Record::getTargetDate)
        .max(LocalDate::compareTo)
        .orElse(null);
    if (initialDate == null || finalDate == null) {
      WorkLimitErrorForm workLimitErrorForm = new WorkLimitErrorForm();
      workLimitErrorForm.setWeeklyWorkLimitErrorForms(new ArrayList<>());
      return workLimitErrorForm;
    }

    return validateWeeklyWorkLimit(assistant, initialDate, finalDate);
  }

  private WorkLimitErrorForm validateWeeklyWorkLimit(Assistant assistant, LocalDate initialDate, LocalDate finalDate) {
    WorkLimitErrorForm workLimitErrorForm = new WorkLimitErrorForm();
    List<WeeklyWorkLimitErrorForm> weeklyWorkLimitErrorForms = validateWeeklyWorkLimitBetweenTwoDates(assistant,
        initialDate, finalDate);
    workLimitErrorForm.setWeeklyWorkLimitErrorForms(weeklyWorkLimitErrorForms);
    return workLimitErrorForm;
  }

  private List<WeeklyWorkLimitErrorForm> validateWeeklyWorkLimitBetweenTwoDates(
      Assistant assistant, LocalDate initialDate, LocalDate finalDate) {
    List<WeeklyWorkLimitErrorForm> weeklyWorkLimitErrorForms = new ArrayList<>();
    WeekFields weekFields = WeekFields.of(DayOfWeek.SUNDAY, 7);
    Integer initialYear = initialDate.get(weekFields.weekBasedYear());
    Integer finalYear = finalDate.get(weekFields.weekBasedYear());
    Map<LocalDate, Set<Record>> allRecordsByTargetDate = recordRepository.findByAssistantId(assistant.getId()).stream()
        .collect(Collectors.groupingBy(Record::getTargetDate, Collectors.toSet()));

    IntStream.rangeClosed(initialYear, finalYear).forEach(year -> {
      ValueRange weekOfYearRange = LocalDate.of(year, 1, 1).range(weekFields.weekOfWeekBasedYear());
      Integer startWeekOfYear = (int) ((year == initialYear)
          ? initialDate.get(weekFields.weekOfWeekBasedYear())
          : weekOfYearRange.getMinimum());
      Integer endWeekOfYear = (int) ((year == finalYear)
          ? finalDate.get(weekFields.weekOfWeekBasedYear())
          : weekOfYearRange.getMaximum());

      IntStream.rangeClosed(startWeekOfYear, endWeekOfYear).forEach(weekOfYear -> {
        LocalDate weekStartDate = LocalDate.now()
            .with(weekFields.weekBasedYear(), year)
            .with(weekFields.weekOfWeekBasedYear(), weekOfYear)
            .with(weekFields.dayOfWeek(), SUNDAY);
        LocalDate weekEndDate = LocalDate.now()
            .with(weekFields.weekBasedYear(), year)
            .with(weekFields.weekOfWeekBasedYear(), weekOfYear)
            .with(weekFields.dayOfWeek(), SATURDAY);
        
        Set<Record> weekRecords = allRecordsByTargetDate.entrySet().stream()
            .filter(arbtde -> dateIsBetween(arbtde.getKey(), weekStartDate, weekEndDate))
            .flatMap(arbtde -> arbtde.getValue().stream())
            .collect(Collectors.toSet());

        Integer weeklyTotalWorkMinute = weekRecords.stream().mapToInt(Record::getTotalWorkMinute).sum();
        if (weeklyTotalWorkMinute > TimeUtils
            .convertClockTimeStringToMinute((assistant.getAssistantNationality().getWeeklyWorkHourLimit()))) {
          weeklyWorkLimitErrorForms.add(createWeeklyWorkLimitErrorForm(
              WeeklyWorkLimitErrorForm.WEEKLY_TOTAL_TIME_ERROR, weekStartDate, weekEndDate));
        }

        Set<LocalDate> weeklyWorkDays = new HashSet<>();
        weekRecords.stream().forEach(workRecord -> weeklyWorkDays.add(workRecord.getTargetDate()));
        if (weeklyWorkDays.size() > 6) {
          weeklyWorkLimitErrorForms.add(createWeeklyWorkLimitErrorForm(
              WeeklyWorkLimitErrorForm.WEEKLY_TOTAL_WORK_DAY_ERROR, weekStartDate, weekEndDate));
        }
      });
    });
    return weeklyWorkLimitErrorForms;
  }

  private WeeklyWorkLimitErrorForm createWeeklyWorkLimitErrorForm(
      Long errorType, LocalDate weekStartDate, LocalDate weekEndDate) {
    WeeklyWorkLimitErrorForm weeklyWorkLimitErrorForm = new WeeklyWorkLimitErrorForm();
    weeklyWorkLimitErrorForm.setWeeklyWorkLimitErrorType(errorType);
    weeklyWorkLimitErrorForm.setWeekStartDate(weekStartDate);
    weeklyWorkLimitErrorForm.setWeekEndDate(weekEndDate);
    return weeklyWorkLimitErrorForm;
  }
  
  private Boolean dateIsBetween(LocalDate targetDate, LocalDate startDate, LocalDate endDate) {
    return !(startDate.isAfter(targetDate) || endDate.isBefore(targetDate));
  }
}