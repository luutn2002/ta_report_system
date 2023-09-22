package jp.ac.u_aizu.ta_report_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

  Optional<Report> findById(Long reportId);

  List<Report> findByAssistant(Assistant assistant);
  
  List<Report> findByTargetMonth(LocalDate targetMonth);

}
