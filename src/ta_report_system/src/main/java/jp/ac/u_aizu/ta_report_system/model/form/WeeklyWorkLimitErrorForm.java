package jp.ac.u_aizu.ta_report_system.model.form;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeeklyWorkLimitErrorForm {

  public static final Long WEEKLY_TOTAL_TIME_ERROR = 1L;

  public static final Long WEEKLY_TOTAL_WORK_DAY_ERROR = 2L;

  private Long weeklyWorkLimitErrorType;

  private LocalDate weekStartDate;

  private LocalDate weekEndDate;

}
