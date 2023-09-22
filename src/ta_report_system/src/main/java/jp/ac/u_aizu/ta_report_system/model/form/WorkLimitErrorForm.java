package jp.ac.u_aizu.ta_report_system.model.form;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkLimitErrorForm {
  
  private List<WeeklyWorkLimitErrorForm> weeklyWorkLimitErrorForms;

}
