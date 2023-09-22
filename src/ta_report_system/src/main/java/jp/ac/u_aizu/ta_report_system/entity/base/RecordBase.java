package jp.ac.u_aizu.ta_report_system.entity.base;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import jp.ac.u_aizu.ta_report_system.base.EntityBase;
import jp.ac.u_aizu.ta_report_system.entity.Report;
import jp.ac.u_aizu.ta_report_system.entity.WorkCategory;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class RecordBase extends EntityBase {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Report.class, optional = false)
  @JoinColumn(name = "report_id", nullable = false)
  private Report report;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = WorkCategory.class, optional = false)
  @JoinColumn(name = "work_category_id", nullable = false)
  private WorkCategory workCategory;

  @Column(name = "target_date", nullable = false)
  private LocalDate targetDate;

  @Column(name = "start_time", nullable = true)
  private LocalTime startTime;

  @Column(name = "end_time", nullable = true)
  private LocalTime endTime;

  @Column(name = "break_minute", nullable = false)
  private Integer breakMinute;

  @Column(name = "total_work_minute", nullable = false)
  private Integer totalWorkMinute;

}
