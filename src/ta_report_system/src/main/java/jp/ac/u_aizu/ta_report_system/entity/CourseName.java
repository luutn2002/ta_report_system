package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.CourseNameBase;

@Entity
@Table(name = "course_name")
public class CourseName extends CourseNameBase {

  private static final long serialVersionUID = 1L;

}
