package jp.ac.u_aizu.ta_report_system.entity.base;

import java.time.LocalDate;
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
import jp.ac.u_aizu.ta_report_system.entity.AssistanceType;
import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.entity.Course;
import jp.ac.u_aizu.ta_report_system.entity.Faculty;
import jp.ac.u_aizu.ta_report_system.entity.Record;
import jp.ac.u_aizu.ta_report_system.entity.Staff;
import jp.ac.u_aizu.ta_report_system.entity.User;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class ReportBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Assistant.class, optional = false)
  @JoinColumn(name = "assistant_id", nullable = false)
  private Assistant assistant;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class, optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = AssistanceType.class, optional = false)
  @JoinColumn(name = "assistance_type_id", nullable = false)
  private AssistanceType assistanceType;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Staff.class, optional = true)
  @JoinColumn(name = "verified_staff_id", nullable = true)
  private Staff verifiedStaff;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Faculty.class, optional = true)
  @JoinColumn(name = "approved_faculty_id", nullable = true)
  private Faculty approvedFaculty;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = false)
  @JoinColumn(name = "edited_user_id", nullable = false)
  private User editedUser;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Record.class, mappedBy = "report")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Record> records;

  @Column(name = "target_year", nullable = false)
  private Integer targetYear;

  @Column(name = "target_month", nullable = false)
  private LocalDate targetMonth;

  @Column(name = "monthly_total_work_minute", nullable = false)
  private Integer monthlyTotalWorkMinute = 0;

  @Column(name = "monthly_total_allocated_minute", nullable = true)
  private Integer monthlyTotalAllocatedMinute = 0;

  @Column(name = "verified", nullable = false)
  private Boolean verified;

  @Column(name = "verified_date", nullable = true)
  private LocalDate verifiedDate;

  @Column(name = "approved", nullable = false)
  private Boolean approved;

  @Column(name = "approved_date", nullable = true)
  private LocalDate approvedDate;

  @Column(name = "edited_date", nullable = false)
  private LocalDate editedDate;

}