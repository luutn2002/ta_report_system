package jp.ac.u_aizu.ta_report_system.model.form;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/** TA list form */
@Getter
@Setter
public class TaListForm {
  /** Number of the entrance year */
  private Integer numStudentYear;

  /** TA forms */
  private List<TaForm> taForms;
}
