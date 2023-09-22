package jp.ac.u_aizu.ta_report_system.model.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaForm {
  /** assistant id */
  private String assistantId;

  /** first name */
  private String firstName;

  /** middle name */
  private String middleName;

  /** last name */
  private String lastName;
}
