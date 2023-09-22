package jp.ac.u_aizu.ta_report_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.Staff;
import jp.ac.u_aizu.ta_report_system.entity.User;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByUser(User user);
}
