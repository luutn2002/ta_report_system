package jp.ac.u_aizu.ta_report_system.entity.base;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import jp.ac.u_aizu.ta_report_system.base.EntityBase;
import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.entity.Course;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AssistantCourseBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Assistant.class, optional = false)
  @JoinColumn(name = "assistant_id", nullable = false)
  private Assistant assistant;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class, optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;
}
