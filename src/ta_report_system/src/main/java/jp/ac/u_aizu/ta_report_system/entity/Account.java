package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.AccountBase;

@Entity
@Table(name = "account")
public class Account extends AccountBase {

  private static final long serialVersionUID = 1L;

}