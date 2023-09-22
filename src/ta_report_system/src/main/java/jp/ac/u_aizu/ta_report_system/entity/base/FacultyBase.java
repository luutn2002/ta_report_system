package jp.ac.u_aizu.ta_report_system.entity.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import jp.ac.u_aizu.ta_report_system.base.EntityBase;
import jp.ac.u_aizu.ta_report_system.entity.Course;
import jp.ac.u_aizu.ta_report_system.entity.FacultyCourse;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import jp.ac.u_aizu.ta_report_system.entity.User;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class FacultyBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = false)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Course.class, mappedBy = "coordinator")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Course> courses;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = FacultyCourse.class, mappedBy = "faculty")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<FacultyCourse> facultyCourses;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Report.class, mappedBy = "approvedFaculty")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Report> reports;

}
