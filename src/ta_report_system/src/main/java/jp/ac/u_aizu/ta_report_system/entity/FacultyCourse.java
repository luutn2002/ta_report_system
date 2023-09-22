package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.FacultyCourseBase;

@Entity
@Table(name = "faculty_course")
public class FacultyCourse extends FacultyCourseBase {

  private static final long serialVersionUID = 1L;

}
