package jp.ac.u_aizu.ta_report_system.controller;

import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.u_aizu.ta_report_system.dto.RecordRequest;
import jp.ac.u_aizu.ta_report_system.entity.Record;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import jp.ac.u_aizu.ta_report_system.model.form.ReportDetailForm;
import jp.ac.u_aizu.ta_report_system.model.form.ReportPrintForm;
import jp.ac.u_aizu.ta_report_system.model.form.WorkLimitErrorForm;
import jp.ac.u_aizu.ta_report_system.repository.RecordRepository;
import jp.ac.u_aizu.ta_report_system.repository.ReportRepository;
import jp.ac.u_aizu.ta_report_system.service.ReportDetailService;
import jp.ac.u_aizu.ta_report_system.service.ReportListService;
import jp.ac.u_aizu.ta_report_system.service.ReportPrintService;
import jp.ac.u_aizu.ta_report_system.service.WorkLimitValidationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReportDetailController {
	
	@Autowired
	ReportListService reportListService;

  @Autowired
  ReportDetailService reportDetailService;

  @Autowired
  ReportPrintService reportPrintService;

  @Autowired
  WorkLimitValidationService workLimitValidationService;

  @Autowired
  ReportRepository reportRepository;

  @Autowired
  RecordRepository recordRepository;

  @GetMapping("/report/{reportId}")
  public String show(@PathVariable Long reportId, @ModelAttribute("errorMessage") String errorMessage, Model model) {

    final ReportDetailForm reportDetailForm = reportDetailService.createReportDetailForm(reportId);
    Report report = reportRepository.findById(reportId).orElseThrow(EntityNotFoundException::new);

    RecordRequest recordRequest = new RecordRequest();
    RecordRequest myRecordRequest = (RecordRequest) model.getAttribute("myRecordRequest");
    
    Integer year = report.getTargetYear();
    Integer month = report.getTargetMonth().getMonthValue();
    YearMonth yearMonth = YearMonth.of(year, month);
    recordRequest.setTargetMonth(yearMonth.toString());
    if(myRecordRequest != null) {
      recordRequest.setTargetDay(myRecordRequest.getTargetDay());
      recordRequest.setStartTime(myRecordRequest.getStartTime());
      recordRequest.setEndTime(myRecordRequest.getEndTime());
      recordRequest.setBreakHour(myRecordRequest.getBreakHour());
      recordRequest.setBreakMinute(myRecordRequest.getBreakMinute());
    }
    
    boolean resultOfJudgeDailyTotalWorkHour = reportDetailService.checkDailyTotalWorkHour(report.getAssistant(), report.getTargetYear(), report.getTargetMonth().getMonth());
    if(resultOfJudgeDailyTotalWorkHour) errorMessage = "There are days when the 8 hours per day is exceeded";
    Integer valueOfMonth = report.getTargetMonth().with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
    
    WorkLimitErrorForm workLimitErrorForm = workLimitValidationService.validateWeeklyWorkLimitForReport(report);

    model.addAttribute("assistant_id", report.getAssistant().getId());
    model.addAttribute("reportDetailForm", reportDetailForm);
    model.addAttribute("report", report);
    model.addAttribute("recordRequest", recordRequest);
    model.addAttribute("resultOfJudgeDailyTotalWorkHour", resultOfJudgeDailyTotalWorkHour);
    model.addAttribute("errorMessage", errorMessage);
    model.addAttribute("valueOfMonth", valueOfMonth);
    model.addAttribute("workLimitErrorForm", workLimitErrorForm);
    return "report_detail";
  }

  @PostMapping("/process_report_detail/{id}")
  public String process(@PathVariable Long id, @Validated @ModelAttribute RecordRequest recordRequest, BindingResult result, RedirectAttributes redirectAttributes) {
    
  	String errorMessage;
  	if(result.hasErrors()) {
  		RecordRequest myRecordRequest = new RecordRequest();
  		myRecordRequest.setTargetDay(recordRequest.getTargetDay());
      myRecordRequest.setStartTime(recordRequest.getStartTime());
      myRecordRequest.setEndTime(recordRequest.getEndTime());
      myRecordRequest.setBreakHour(recordRequest.getBreakHour());
      myRecordRequest.setBreakMinute(recordRequest.getBreakMinute());
  		redirectAttributes.addFlashAttribute("myRecordRequest", myRecordRequest);
  		
  		errorMessage = "The record was not saved properly because there was a blank entered.";
  		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
  		return "redirect:/report/{id}";
  	}
  	
  	boolean saveSuccess = reportDetailService.saveRecordRequest(id, recordRequest);

    if (!saveSuccess) {
      errorMessage = "It was not saved successfully because overlapping times existed.";
      redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
    }

    return "redirect:/report/{id}";
  }

  @GetMapping("/edit_record/{id}")
  public String edit(@PathVariable Long id, Model model, @ModelAttribute("errorMessage") String errorMessage) {
    Record workRecord = recordRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    RecordRequest recordRequest = reportDetailService.createRecordRequest(workRecord);
    Integer valueOfMonth =workRecord.getReport().getTargetMonth().with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
    WorkLimitErrorForm workLimitErrorForm = new WorkLimitErrorForm();
    model.addAttribute("record", workRecord);
    model.addAttribute("recordRequest", recordRequest);
    model.addAttribute("valueOfMonth", valueOfMonth);
    model.addAttribute("errorMessage", errorMessage);
    model.addAttribute("workLimitErrorForm", workLimitErrorForm);
    return "edit_report_detail";
  }

  @PostMapping("/edit/{reportId}")
  public String editRecord(@PathVariable Long reportId, @Validated @ModelAttribute RecordRequest recordRequest, BindingResult result,
      RedirectAttributes redirectAttributes) {
  	String errorMessage; 	
  	Long recordId = recordRequest.getId();
		redirectAttributes.addAttribute("id", recordId);
		
  	if(result.hasErrors()) {
  		errorMessage = "The record was not saved properly because there was a blank entered.";
  		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
  		return "redirect:/edit_record/{id}";
  	}
  	
    boolean saveSuccess = reportDetailService.editRecord(recordRequest);

    if (!saveSuccess) {
      errorMessage = "It was not saved successfully because overlapping times existed.";
      redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
      return "redirect:/edit_record/{id}";
    }
    return "redirect:/report/{reportId}";
  }

  @PostMapping("/record/delete")
  public String delete(@RequestParam("recordId") Long recordId, Model model, RedirectAttributes redirectAttributes) {
    Optional<Record> recordOptional = recordRepository.findById(recordId);
    Long reportId = recordOptional.get().getReport().getId();
    redirectAttributes.addAttribute("reportId", reportId);
    if (recordOptional.isPresent()) {
      Record attendanceRecord = recordOptional.get();
      attendanceRecord.setDeleted(true);
      recordRepository.save(attendanceRecord);
      reportDetailService.setTotalWorkMinuteForReport(attendanceRecord);
    }
    return "redirect:/report/{reportId}";
  }

  @GetMapping("/print/{reportId}")
  public String printReport(@PathVariable Long reportId, Model model) {
    ReportPrintForm reportPrintForm = reportPrintService.createReportPrintForm(reportId);
    model.addAttribute("reportPrintForm", reportPrintForm);
    model.addAttribute("reportId", reportId);
    return "print_report";
  }
}