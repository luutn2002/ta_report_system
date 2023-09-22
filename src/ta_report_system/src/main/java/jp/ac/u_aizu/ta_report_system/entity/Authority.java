package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.AuthorityBase;

@Entity
@Table(name = "authority")
public class Authority extends AuthorityBase {

  private static final long serialVersionUID = 1L;

}
