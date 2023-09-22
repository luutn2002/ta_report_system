package jp.ac.u_aizu.ta_report_system.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jp.ac.u_aizu.ta_report_system.entity.Course;
import jp.ac.u_aizu.ta_report_system.entity.CourseName;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

  public Optional<Course> findByCourseName(CourseName courseName);
}
