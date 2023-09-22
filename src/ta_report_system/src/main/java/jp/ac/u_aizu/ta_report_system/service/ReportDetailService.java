package jp.ac.u_aizu.ta_report_system.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.u_aizu.ta_report_system.dto.RecordRequest;
import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.entity.Record;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import jp.ac.u_aizu.ta_report_system.entity.WorkCategory;
import jp.ac.u_aizu.ta_report_system.model.form.DailyCategoryReportDetailForm;
import jp.ac.u_aizu.ta_report_system.model.form.DailyReportDetailForm;
import jp.ac.u_aizu.ta_report_system.model.form.ReportDetailForm;
import jp.ac.u_aizu.ta_report_system.repository.RecordRepository;
import jp.ac.u_aizu.ta_report_system.repository.ReportRepository;
import jp.ac.u_aizu.ta_report_system.repository.WorkCategoryRepository;
import jp.ac.u_aizu.ta_report_system.utils.TimeUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportDetailService {

	@Autowired
	private final ReportRepository reportRepository;

	@Autowired
	private final RecordRepository recordRepository;

	@Autowired
	private final WorkCategoryRepository workCategoryRepository;

	public ReportDetailForm createReportDetailForm(Long reportId) {

		Optional<Report> reportOptional = reportRepository.findById(reportId);
		if (reportOptional.isEmpty()) {
			throw new EntityNotFoundException();
		}

		final ReportDetailForm reportDetailForm = new ReportDetailForm();
		final Report report = reportOptional.get();
    final LocalDate targetYearMonth = report.getTargetMonth();
    final List<DailyReportDetailForm> dailyReportDetailForms = createDailyReportDetailForms(reportId);
		reportDetailForm.setCourseName(report.getCourse().getCourseName().getName());
		reportDetailForm.setTargetYear(targetYearMonth.getYear());
		reportDetailForm.setTargetMonth(targetYearMonth.getMonth().toString());
		reportDetailForm.setDailyReportDetailForms(dailyReportDetailForms);

		return reportDetailForm;
	}

	private List<DailyReportDetailForm> createDailyReportDetailForms(Long reportId) {

		List<Record> records = recordRepository.findDistinctIdByReportIdAndDeletedFalse(reportId);
		if (records.isEmpty()) {
			return new ArrayList<>();
		}
		Map<LocalDate, List<Record>> recordsByTargetDate =
				records.stream()
				    .sorted(Comparator.comparing(r -> r.getWorkCategory().getId()))
				    .collect(Collectors.groupingBy(Record::getTargetDate));

		return recordsByTargetDate.entrySet().stream()
		    .map(this::createDailyReportDetailForm)
		    .sorted(Comparator.comparing(DailyReportDetailForm::getTargetDay))
		    .toList();
	}

	private DailyReportDetailForm createDailyReportDetailForm(
			Entry<LocalDate, List<Record>> recordsByTargetDateEntry) {
		LocalDate targetDate = recordsByTargetDateEntry.getKey();
		List<Record> targetRecords = recordsByTargetDateEntry.getValue();

		DailyReportDetailForm dailyReportDetailForm = new DailyReportDetailForm();
		Double dailyTotalWorkHour =
				TimeUtils.convertMinuteToHour(calculateTotalWorkMinuteFromRecords(targetRecords));
		List<DailyCategoryReportDetailForm> dailyCategoryReportDetailForms =
				createDailyCategoryReportForms(targetRecords);
		dailyReportDetailForm.setTargetMonth(targetDate.getMonthValue());
		dailyReportDetailForm.setTargetDay(targetDate.getDayOfMonth());
		dailyReportDetailForm.setDayOfWeek(targetDate.getDayOfWeek().toString());
		dailyReportDetailForm.setDailyTotalWorkHour(dailyTotalWorkHour);
		dailyReportDetailForm.setDailyCategoryReportDetailForms(dailyCategoryReportDetailForms);
		return dailyReportDetailForm;
	}

	private List<DailyCategoryReportDetailForm> createDailyCategoryReportForms(List<Record> records) {

		return records.stream().map(r -> {
			DailyCategoryReportDetailForm dailyCategoryReportDetailForm =
					new DailyCategoryReportDetailForm();
			dailyCategoryReportDetailForm.setRecordId(r.getId());
			dailyCategoryReportDetailForm.setWorkCategoryId(r.getWorkCategory().getId());
			dailyCategoryReportDetailForm.setWorkCategoryName(r.getWorkCategory().getName());
			dailyCategoryReportDetailForm
			.setDailyCategoryWorkHour(TimeUtils.convertMinuteToHour(r.getTotalWorkMinute()));
			return dailyCategoryReportDetailForm;
		}).toList();
	}

	public RecordRequest createRecordRequest(Record workRecord) {
		RecordRequest recordRequest = new RecordRequest();
		recordRequest.setId(workRecord.getId());
		recordRequest.setReportId(workRecord.getReport().getId());
		recordRequest.setWorkCategoryId(workRecord.getWorkCategory().getId());
		Integer year = workRecord.getTargetDate().getYear();
		Month month = workRecord.getTargetDate().getMonth();
		YearMonth yearMonth = YearMonth.of(year, month);
		recordRequest.setTargetMonth(yearMonth.toString());
		Integer day = workRecord.getTargetDate().getDayOfMonth();
		Integer breakHour = (int) Math.floor(workRecord.getBreakMinute() / 60);
		Integer breakMinute = workRecord.getBreakMinute() % 60;
		Integer totalHour = (int) Math.floor(workRecord.getTotalWorkMinute() / 60);
		Integer totalMinute = workRecord.getTotalWorkMinute() % 60;
		String totalWorkHour = "0" + totalHour + ":" + totalMinute;

		recordRequest.setTargetDay(day.toString());
		recordRequest.setStartTime(workRecord.getStartTime());
		recordRequest.setEndTime(workRecord.getEndTime());
		recordRequest.setBreakHour(breakHour.toString());
		recordRequest.setBreakMinute(breakMinute.toString());
		recordRequest.setTotalWorkHourDisplay(totalWorkHour);
		recordRequest.setTotalWorkMinute(workRecord.getTotalWorkMinute());
		return recordRequest;
	}

	public boolean saveRecordRequest(Long reportId, RecordRequest recordRequest) {

		Record workRecord = createRecord(reportId, recordRequest);
		boolean flag = checkOverlappingTimeOfRecordWhenCreate(workRecord);
		boolean saveSuccess = false;


		if (!flag) {
			recordRepository.save(workRecord);
			saveSuccess = true;
		}
		
		return saveSuccess;
	}

	private Record createRecord(Long reportId, RecordRequest recordRequest) {
		Long workCategoryId = recordRequest.getWorkCategoryId();
		Report referenceReport = reportRepository.findById(reportId).orElseThrow(EntityNotFoundException::new);
		WorkCategory workCategory = workCategoryRepository.findById(workCategoryId).orElseThrow(EntityNotFoundException::new);

		String localMonth = recordRequest.getTargetMonth();
		Integer integerLocalDay = Integer.parseInt(recordRequest.getTargetDay());
		String localDay;
		if(integerLocalDay < 10) localDay = "0" + integerLocalDay;
		else localDay = integerLocalDay.toString();
		String localDate = localMonth + "-" + localDay;
		LocalDate date = LocalDate.parse(localDate);

		LocalTime startTime = recordRequest.getStartTime();

		LocalTime endTime = recordRequest.getEndTime();

		Integer breakTimeHour = Integer.parseInt(recordRequest.getBreakHour());
		Integer breakTimeMinute = Integer.parseInt(recordRequest.getBreakMinute());
		Integer breakMinute = breakTimeHour * 60 + breakTimeMinute;
		Integer totalWorkMinute = (int)startTime.until(endTime, ChronoUnit.MINUTES) - breakMinute;

		referenceReport.setMonthlyTotalWorkMinute(referenceReport.getMonthlyTotalWorkMinute() + totalWorkMinute);

		Record workRecord = new Record();
		workRecord.setReport(referenceReport);
		workRecord.setWorkCategory(workCategory);
		workRecord.setTargetDate(date);
		workRecord.setStartTime(startTime);
		workRecord.setEndTime(endTime);
		workRecord.setBreakMinute(breakMinute);
		workRecord.setTotalWorkMinute(totalWorkMinute);
		workRecord.setDeleted(false);

		return workRecord;
	}

	public boolean editRecord(RecordRequest recordRequest) {

		boolean saveSuccess = false;
		Record referenceRecord = recordRepository.findById(recordRequest.getId()).orElseThrow(EntityNotFoundException::new);
		Report referenceReport = reportRepository.findById(recordRequest.getReportId()).orElseThrow(EntityNotFoundException::new);
		WorkCategory workCategory = workCategoryRepository.findById(recordRequest.getWorkCategoryId()).orElseThrow(EntityNotFoundException::new);
		String[] localYearMonth = recordRequest.getTargetMonth().split("-");
		Integer year = Integer.parseInt(localYearMonth[0]);
		Integer month = Integer.parseInt(localYearMonth[1]);
		Integer day = Integer.parseInt(recordRequest.getTargetDay());

		LocalDate date = LocalDate.of(year, month, day);
		LocalTime startTime = recordRequest.getStartTime();
		LocalTime endTime = recordRequest.getEndTime();
		Integer breakHour = Integer.parseInt(recordRequest.getBreakHour());
		Integer breakMinute = Integer.parseInt(recordRequest.getBreakMinute());
		Integer breakTime = breakHour * 60 + breakMinute;
		Integer totalWorkHour = (int) startTime.until(endTime, ChronoUnit.MINUTES) - breakTime;
		Integer monthlyTotalWorkHour =
				referenceReport.getMonthlyTotalWorkMinute() - referenceRecord.getTotalWorkMinute() + totalWorkHour;


		referenceReport.setMonthlyTotalWorkMinute(monthlyTotalWorkHour);


		referenceRecord.setReport(referenceReport);
		referenceRecord.setWorkCategory(workCategory);
		referenceRecord.setTargetDate(date);
		referenceRecord.setStartTime(startTime);
		referenceRecord.setEndTime(endTime);
		referenceRecord.setBreakMinute(breakTime);
		referenceRecord.setTotalWorkMinute(totalWorkHour);
		referenceRecord.setDeleted(false);
		boolean flag = checkOverlappingTimeOfRecordWhenEdit(referenceRecord);
		if(!flag) {
			recordRepository.save(referenceRecord);
			saveSuccess = true;
		}
		
		return saveSuccess;
	}

	private Integer calculateTotalWorkMinuteFromRecords(List<Record> records) {
		return records.stream().mapToInt(Record::getTotalWorkMinute).sum();
	}

	public boolean checkDailyTotalWorkHour(Assistant assistant, Integer year, Month month) {
		boolean leapYear = checkLeapYear(year);
		Integer lengthOfMonth = month.length(leapYear);
		boolean flag = false;
		for(int i = 1; i <= lengthOfMonth; i++) {
			LocalDate date = LocalDate.of(year, month, i);
			flag = judgeDailyTotalWorkHour(assistant, date);
			if(flag) return flag;
		}
		
		return flag;
	}
	
	private boolean checkLeapYear(Integer year) {
		if ( 0 == ( year % 400 ) ) return true;

		if ( 0 == ( year % 100 ) ) return false;

		if ( 0 == ( year % 4 ) ) return true;

		return false;
	}
	
	private boolean judgeDailyTotalWorkHour(Assistant assistant, LocalDate targetDate) {
		List<Record> records = recordRepository.findByAssistantIdAndTargetDate(assistant.getId(), targetDate);

		final Integer limitWorkMinute = 480;
		Integer totalWorkMinute = 0;
		boolean flag = false;
		for(int i = 0; i < records.size(); i++) {
			totalWorkMinute += records.get(i).getTotalWorkMinute();
			if(totalWorkMinute > limitWorkMinute) {
				flag = true;
				break;
			}
		}

		return flag;
	}

	private boolean checkOverlappingTimeOfRecordWhenCreate(Record record) {
		boolean flag = false;
		List<Record> records = recordRepository.findByAssistantIdAndTargetDate(record.getReport().getAssistant().getId(), record.getTargetDate());

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

	private boolean checkOverlappingTimeOfRecordWhenEdit(Record record) {
		boolean flag = false;
		List<Record> records = recordRepository.findByAssistantIdAndTargetDate(record.getReport().getAssistant().getId(), record.getTargetDate());

		for(int i = 0; i < records.size(); i++) {
			if(!(record.getId().equals(records.get(i).getId()))) {
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
		}

		return flag;
	}
	
	public void setTotalWorkMinuteForReport(Record record) {
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
