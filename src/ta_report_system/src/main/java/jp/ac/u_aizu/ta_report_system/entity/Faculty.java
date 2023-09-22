package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.FacultyBase;

@Entity
@Table(name = "faculty")
public class Faculty extends FacultyBase {

  private static final long serialVersionUID = 1L;

}
