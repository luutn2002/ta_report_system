package jp.ac.u_aizu.ta_report_system.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.Period;
import jp.ac.u_aizu.ta_report_system.entity.Term;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {
    Period findByPeriodFromAndPeriodTo (LocalDate periodFrom, LocalDate periodTo);
    Period findByTerm (Term term);
}
