package jp.ac.u_aizu.ta_report_system.model.form;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyReportDetailForm {

  private Integer targetMonth;

  private Integer targetDay;

  private String dayOfWeek;

  private Double dailyTotalWorkHour;

  private List<DailyCategoryReportDetailForm> dailyCategoryReportDetailForms;
}
