package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.AssistanceTypeBase;

@Entity
@Table(name = "assistance_type")
public class AssistanceType extends AssistanceTypeBase {

  private static final long serialVersionUID = 1L;

}