package jp.ac.u_aizu.ta_report_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findByUsername(String username);
}
