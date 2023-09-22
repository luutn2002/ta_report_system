package jp.ac.u_aizu.ta_report_system.model.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportForm {
  /** course */
  private String course;

  private Double monthlyCourseWorkHour;

  private String lastEditDate;

  private String lastEditUser;

  /** report_id */
  private Long reportId;
}
