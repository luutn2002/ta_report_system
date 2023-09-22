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
import jp.ac.u_aizu.ta_report_system.entity.AssistantGraduate;
import jp.ac.u_aizu.ta_report_system.entity.AssistantNationality;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import jp.ac.u_aizu.ta_report_system.entity.User;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class AssistantBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = false)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = AssistantNationality.class, optional = false)
  @JoinColumn(name = "assistant_nationality_id", nullable = false)
  private AssistantNationality assistantNationality;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = AssistantGraduate.class, optional = false)
  @JoinColumn(name = "assistant_graduate_id", nullable = false)
  private AssistantGraduate assistantGraduate;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = AssistantCourse.class, mappedBy = "assistant")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<AssistantCourse> assistantCourses;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Report.class, mappedBy = "assistant")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Report> reports;

  @Column(name = "student_year", nullable = false)
  private Integer studentYear;

  @Column(name = "student_id", nullable = false)
  private String studentId;

}
