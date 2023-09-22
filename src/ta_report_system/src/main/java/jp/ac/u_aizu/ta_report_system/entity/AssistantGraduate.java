package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.AssistantGraduateBase;

@Entity
@Table(name = "assistant_graduate")
public class AssistantGraduate extends AssistantGraduateBase {

  private static final long serialVersionUID = 1L;

}
