package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.UserBase;

@Entity
@Table(name = "user")
public class User extends UserBase {

  private static final long serialVersionUID = 1L;

  public String getFullName() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.getFirstName());
    stringBuilder.append(" ");
    if (this.getMiddleName() != null) {
      stringBuilder.append(this.getMiddleName());
      stringBuilder.append(" ");
    }
    stringBuilder.append(this.getLastName());

    return stringBuilder.toString();
  }

}
