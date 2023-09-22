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
import org.springframework.data.annotation.CreatedDate;

import jp.ac.u_aizu.ta_report_system.base.EntityBase;
import jp.ac.u_aizu.ta_report_system.entity.Account;
import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.entity.Faculty;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import jp.ac.u_aizu.ta_report_system.entity.Staff;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class UserBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class, optional = false)
  @JoinColumn(name = "account_id", nullable = false, unique = true)
  private Account account;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Assistant.class, mappedBy = "user")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Assistant> assistants;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Faculty.class, mappedBy = "user")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Faculty> faculties;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Staff.class, mappedBy = "user")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Staff> staffs;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Report.class, mappedBy = "editedUser")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Report> reports;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "middle_name", nullable = true)
  private String middleName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @CreatedDate
  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

}
