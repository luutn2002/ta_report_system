package jp.ac.u_aizu.ta_report_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.Faculty;
import jp.ac.u_aizu.ta_report_system.entity.User;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByUser(User user);
}
