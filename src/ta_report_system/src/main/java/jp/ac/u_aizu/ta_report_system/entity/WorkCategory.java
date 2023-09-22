package jp.ac.u_aizu.ta_report_system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.ac.u_aizu.ta_report_system.entity.base.WorkCategoryBase;

@Entity
@Table(name = "work_category")
public class WorkCategory extends WorkCategoryBase {

  private static final long serialVersionUID = 1L;

}
