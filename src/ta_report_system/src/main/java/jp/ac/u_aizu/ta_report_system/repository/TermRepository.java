package jp.ac.u_aizu.ta_report_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
    Term findByName(String name);
}
