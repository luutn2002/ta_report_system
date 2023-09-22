package jp.ac.u_aizu.ta_report_system.entity.base;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import jp.ac.u_aizu.ta_report_system.base.EntityBase;
import jp.ac.u_aizu.ta_report_system.entity.Course;
import jp.ac.u_aizu.ta_report_system.entity.Faculty;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class FacultyCourseBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Faculty.class, optional = false)
  @JoinColumn(name = "faculty_id", nullable = false)
  Faculty faculty;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class, optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  Course course;

}
