package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.AssistantCourseBase;

@Entity
@Table(name = "assistant_course")
public class AssistantCourse extends AssistantCourseBase {

  private static final long serialVersionUID = 1L;

}
