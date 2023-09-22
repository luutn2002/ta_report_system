package jp.ac.u_aizu.ta_report_system.dto;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RecordRequest {

  private Long id;

  private Long reportId;

  private Long workCategoryId;

  private String targetMonth;

  @NotBlank
  private String targetDay = "1";

  @DateTimeFormat(pattern="HH:mm")
  private LocalTime startTime = LocalTime.of(0, 0);

  @DateTimeFormat(pattern="HH:mm")
  private LocalTime endTime = LocalTime.of(0, 0);

  @NotBlank
  private String breakHour = "0";

  @NotBlank
  private String breakMinute = "0";

  private Integer breakTime;

  private String totalWorkHourDisplay;

  private Integer totalWorkMinute;
}
