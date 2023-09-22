package jp.ac.u_aizu.ta_report_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.AssistantNationality;

@Repository
public interface AssistantNationalityRepository extends JpaRepository<AssistantNationality, Long> {
    AssistantNationality findByName(String name);
}
