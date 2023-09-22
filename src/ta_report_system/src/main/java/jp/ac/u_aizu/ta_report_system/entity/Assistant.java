package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.AssistantBase;

@Entity
@Table(name = "assistant")
public class Assistant extends AssistantBase {

  private static final long serialVersionUID = 1L;

}