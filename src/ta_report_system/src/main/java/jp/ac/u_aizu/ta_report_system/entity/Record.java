package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.RecordBase;

@Entity
@Table(name = "record")
public class Record extends RecordBase {

  private static final long serialVersionUID = 1L;

}
