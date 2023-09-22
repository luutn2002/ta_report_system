package jp.ac.u_aizu.ta_report_system.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.u_aizu.ta_report_system.dto.ReportAndRecordRequest;
import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.model.form.ReportListForm;
import jp.ac.u_aizu.ta_report_system.model.form.WorkLimitErrorForm;
import jp.ac.u_aizu.ta_report_system.repository.AssistantRepository;
import jp.ac.u_aizu.ta_report_system.service.ReportListService;
import jp.ac.u_aizu.ta_report_system.service.WorkLimitValidationService;

@Controller
public class ReportListController {

  @Autowired
  AssistantRepository assistantRepository;

  @Autowired
  ReportListService reportListService;

  @Autowired
  WorkLimitValidationService workLimitValidationService;

  @GetMapping("/ta/{assistant_id}")
  public String show(@PathVariable(required = true, name = "assistant_id") Long assistantId,
      Model model, @AuthenticationPrincipal(expression = "username") String username,
      @ModelAttribute("errorMessage") String errorMessage) {

    if (getAssistantId(username).equals(assistantId)) {
      List<ReportListForm> reportListForms = reportListService.createReportListForms(assistantId);
      Assistant assistant = assistantRepository.findById(assistantId).orElseThrow(EntityNotFoundException::new);

      String assistanceType = "";
      
      ReportAndRecordRequest reportAndRecordRequest = new ReportAndRecordRequest();
      ReportAndRecordRequest myRARRequest = (ReportAndRecordRequest) model.getAttribute("myRARRequest");
      char firstLetter = assistant.getStudentId().charAt(0);
      if (firstLetter == 'm') {
        reportAndRecordRequest.setAssistanceTypeId(ReportAndRecordRequest.TEACHING_ASSISTANT);
        assistanceType = "TA";
      } else if (firstLetter == 's') {
        reportAndRecordRequest.setAssistanceTypeId(ReportAndRecordRequest.STUDENT_ASSISTANT);
        assistanceType = "SA";
      }
      
      if(myRARRequest != null) {
      	reportAndRecordRequest.setTargetDate(myRARRequest.getTargetDate());
      	reportAndRecordRequest.setStartTime(myRARRequest.getStartTime());
      	reportAndRecordRequest.setEndTime(myRARRequest.getEndTime());
      	reportAndRecordRequest.setBreakHour(myRARRequest.getBreakHour());
      	reportAndRecordRequest.setBreakMinute(myRARRequest.getBreakMinute());
      }

      boolean judgeMonthlyTotalWorkMinute = reportListService.judgeMonthlyTotalWorkHour(assistant);
      if(judgeMonthlyTotalWorkMinute) errorMessage = "Some months have more than 120 hours";

      WorkLimitErrorForm workLimitErrorForm = workLimitValidationService.validateWeeklyWorkLimitForAllReport(assistant);

      model.addAttribute("report_list_forms", reportListForms);
      model.addAttribute("assistant", assistant);
      model.addAttribute("assistanceType", assistanceType);
      model.addAttribute("reportAndRecordRequest", reportAndRecordRequest);
      model.addAttribute("errorMessage", errorMessage);
      model.addAttribute("workLimitErrorForm", workLimitErrorForm);
      return "report_list";
    }

    return "URL_NOT_ACCESSIBLE";
  }

  @PostMapping("process_report_list/{id}")
  public String insertReportAndRecord(@PathVariable Long id,
      @Validated @ModelAttribute ReportAndRecordRequest reportAndRecordRequest, BindingResult result, RedirectAttributes redirectAttributes) {
  	String errorMessage;
  	if(result.hasErrors()) {
  		ReportAndRecordRequest myRARRequest = new ReportAndRecordRequest();
  		myRARRequest.setTargetDate(reportAndRecordRequest.getTargetDate());
  		myRARRequest.setStartTime(reportAndRecordRequest.getStartTime());
  		myRARRequest.setEndTime(reportAndRecordRequest.getEndTime());
  		myRARRequest.setBreakHour(reportAndRecordRequest.getBreakHour());
  		myRARRequest.setBreakMinute(reportAndRecordRequest.getBreakMinute());
  		redirectAttributes.addFlashAttribute("myRARRequest", myRARRequest);
  		
  		errorMessage = "The record was not saved properly because there was a blank entered.";
  		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
  		return "redirect:/ta/{id}";
  	}
  	
  	
    boolean saveSuccess = reportListService.createReportAndRecord(reportAndRecordRequest);

    if (!saveSuccess) {
      errorMessage = "It was not saved successfully because overlapping times existed.";
      redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
    }

    return "redirect:/ta/{id}";
  }

  Long getAssistantId(String studentId) {
    return assistantRepository.findByStudentId(studentId).getId();
  }
}
