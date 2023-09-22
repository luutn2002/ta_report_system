package jp.ac.u_aizu.ta_report_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReportAndRecordRequest {

  public static final Long STUDENT_ASSISTANT = 2L;

  public static final Long TEACHING_ASSISTANT = 1L;

  private Long assistantId;

  private Long courseNameId;

  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDate targetDate;

  private Long workCategoryId;

  @DateTimeFormat(pattern="HH:mm")
  private LocalTime startTime = LocalTime.of(0, 0);

  @DateTimeFormat(pattern="HH:mm")
  private LocalTime endTime = LocalTime.of(0, 0);
  
  @NotNull
  private Integer breakHour = 0;

  @NotNull
  private Integer breakMinute = 0;
  
  private Integer breakTime;

  private Integer totalWorkMinute;

  private Long assistanceTypeId;
}
