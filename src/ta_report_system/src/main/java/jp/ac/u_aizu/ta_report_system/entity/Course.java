package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.CourseBase;

@Entity
@Table(name = "course")
public class Course extends CourseBase {

  private static final long serialVersionUID = 1L;

}
