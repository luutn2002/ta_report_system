package jp.ac.u_aizu.ta_report_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.entity.User;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, Long> {

  Optional<Assistant> findById(Long id);
  Assistant findByUser(User user);
  Assistant findByStudentId(String studentId);
}
