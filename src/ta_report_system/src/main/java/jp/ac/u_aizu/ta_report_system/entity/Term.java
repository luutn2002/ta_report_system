package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.TermBase;

@Entity
@Table(name = "term")
public class Term extends TermBase {

  private static final long serialVersionUID = 1L;

}
