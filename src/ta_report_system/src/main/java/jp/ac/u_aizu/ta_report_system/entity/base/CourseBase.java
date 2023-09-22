package jp.ac.u_aizu.ta_report_system.entity.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import jp.ac.u_aizu.ta_report_system.base.EntityBase;
import jp.ac.u_aizu.ta_report_system.entity.AssistantCourse;
import jp.ac.u_aizu.ta_report_system.entity.CourseName;
import jp.ac.u_aizu.ta_report_system.entity.Faculty;
import jp.ac.u_aizu.ta_report_system.entity.FacultyCourse;
import jp.ac.u_aizu.ta_report_system.entity.Period;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class CourseBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Faculty.class, optional = false)
  @JoinColumn(name = "coordinator_id", nullable = false)
  private Faculty coordinator;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = CourseName.class, optional = false)
  @JoinColumn(name = "course_name_id", nullable = false)
  private CourseName courseName;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Period.class, optional = false)
  @JoinColumn(name = "period_id", nullable = false)
  private Period period;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = FacultyCourse.class, mappedBy = "course")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<FacultyCourse> facultyCourses;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = AssistantCourse.class, mappedBy = "course")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<AssistantCourse> assistantCourses;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Report.class, mappedBy = "course")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Report> reports;

  @Column(name = "open_year", nullable = false)
  private Integer openYear;

}
