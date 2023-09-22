package jp.ac.u_aizu.ta_report_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.Account;
import jp.ac.u_aizu.ta_report_system.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);
    User findByAccount(Account account);
}
