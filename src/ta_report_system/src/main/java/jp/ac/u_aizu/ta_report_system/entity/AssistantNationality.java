package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.AssistantNationalityBase;

@Entity
@Table(name = "assistant_nationality")
public class AssistantNationality extends AssistantNationalityBase {

  private static final long serialVersionUID = 1L;

}
