package jp.ac.u_aizu.ta_report_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.AssistantGraduate;

@Repository
public interface AssistantGraduateRepository extends JpaRepository<AssistantGraduate, Long> {
    AssistantGraduate findByName(String name);
}
