package jp.ac.u_aizu.ta_report_system.model.form;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailForm {

  private String courseName;

  private Integer targetYear;

  private String targetMonth;

  private List<DailyReportDetailForm> dailyReportDetailForms;
}
