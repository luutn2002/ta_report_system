package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.StaffBase;

@Entity
@Table(name = "staff")
public class Staff extends StaffBase {

  private static final long serialVersionUID = 1L;

}
