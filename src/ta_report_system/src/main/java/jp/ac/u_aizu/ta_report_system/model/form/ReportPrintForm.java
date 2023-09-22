package jp.ac.u_aizu.ta_report_system.model.form;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportPrintForm {
  //Month
  private String targetMonth;

  //year
  private String targetYear;

  //Student Name
  private String studentFullName;

  //Assistant type
  private String assistanceType;

  //Course name
  private String courseName;

  //Faculty name
  private String facultyFullName;

  //monthly Total work time
  private Integer totalWorkHour;

  private List<ReportPrintListForm> reportPrintListForm;
}
