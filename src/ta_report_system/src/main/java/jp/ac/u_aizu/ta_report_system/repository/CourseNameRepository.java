package jp.ac.u_aizu.ta_report_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.CourseName;

@Repository
public interface CourseNameRepository extends JpaRepository<CourseName, Long> {

  public CourseName findByName(String name);
}
