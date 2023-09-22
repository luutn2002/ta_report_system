package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.ReportBase;

@Entity
@Table(name = "report")
public class Report extends ReportBase {

  private static final long serialVersionUID = 1L;

  public static final Integer MONTHLY_MAXIMUM_ALLOCATED_MINUTE = 7200;
}
