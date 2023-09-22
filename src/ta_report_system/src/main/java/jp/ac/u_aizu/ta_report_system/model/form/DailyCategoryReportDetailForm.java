package jp.ac.u_aizu.ta_report_system.model.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyCategoryReportDetailForm {

  private Long recordId;

  private Long workCategoryId;

  private String workCategoryName;

  private Double dailyCategoryWorkHour;
}
