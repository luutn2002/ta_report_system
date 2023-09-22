package jp.ac.u_aizu.ta_report_system.model.form;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportPrintListForm {
  //Year
  private Integer targetYear;

  //Date
  private String targetDate;

  //daily Work time 
  private Double dailyTotalWorkHour;

  //Work category
  private String workCategoryName;

}
