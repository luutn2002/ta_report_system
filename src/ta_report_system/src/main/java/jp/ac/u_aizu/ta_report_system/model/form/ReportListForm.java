package jp.ac.u_aizu.ta_report_system.model.form;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportListForm {
  /** month */
  private String month;

  /** year */
  private Integer year;

  /** total work time */
  private Double monthlyWorkHour;

  private Double monthlyAllocatedHour;

  /** Report forms */
  private List<ReportForm> reportForms;
}
