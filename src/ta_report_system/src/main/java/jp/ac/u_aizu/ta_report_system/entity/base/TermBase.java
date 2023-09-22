package jp.ac.u_aizu.ta_report_system.entity.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import jp.ac.u_aizu.ta_report_system.base.EntityBase;
import jp.ac.u_aizu.ta_report_system.entity.Period;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class TermBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @OneToMany(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = Period.class, mappedBy = "term")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Period> period;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

}
