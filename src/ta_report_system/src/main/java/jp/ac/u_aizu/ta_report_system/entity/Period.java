package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.PeriodBase;

@Entity
@Table(name = "period")
public class Period extends PeriodBase {

  private static final long serialVersionUID = 1L;

}
