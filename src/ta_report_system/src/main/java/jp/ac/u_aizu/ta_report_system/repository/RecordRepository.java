package jp.ac.u_aizu.ta_report_system.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

  List<Record> findDistinctIdByReportIdAndDeletedFalse(Long reportId);

  List<Record> findByReportIdAndDeletedFalse(Long reportId);

  List<Record> findByTargetDateAndDeletedFalse(LocalDate targetDate);

  @Query("select rc from Record rc"
      + " inner join rc.report rp"
      + " where rp.assistant.id = :assistantId"
      + " and rc.targetDate between :startDate and :endDate"
      + " and rc.deleted = false"
      + " and rp.deleted = false"
      + " and rp.assistant.deleted = false")
  List<Record> findByAssistantIdAndTargetDateBetween(@Param("assistantId") Long assistantId,
      @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

  @Query("select rc from Record rc"
  		+ " inner join rc.report rp"
  		+ " where rp.assistant.id = :assistantId"
  		+ " and rc.targetDate = :targetDate"
  		+ " and rc.deleted = false"
  		+ " and rp.deleted = false"
  		+ " and rp.assistant.deleted = false")
  List<Record> findByAssistantIdAndTargetDate(@Param("assistantId") Long assistantId, @Param("targetDate") LocalDate targetDate);

  @Query("select rc from Record rc"
      + " inner join rc.report rp"
      + " where rp.assistant.id = :assistantId"
      + " and rc.deleted = false"
      + " and rp.deleted = false"
      + " and rp.assistant.deleted = false")
  List<Record> findByAssistantId(@Param("assistantId") Long assistantId);
}
