package jp.ac.u_aizu.ta_report_system.entity.base;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import jp.ac.u_aizu.ta_report_system.base.EntityBase;
import jp.ac.u_aizu.ta_report_system.entity.Authority;
import jp.ac.u_aizu.ta_report_system.entity.User;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AccountBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Authority.class, optional = false)
  @JoinColumn(name = "authority_id", nullable = false)
  private Authority authority;

  @OneToOne(cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.LAZY, targetEntity = User.class, mappedBy = "account", optional = false)
  private User user;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false, length = 64)
  private String password;
}
