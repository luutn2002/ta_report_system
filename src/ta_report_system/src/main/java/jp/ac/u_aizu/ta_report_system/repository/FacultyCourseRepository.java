package jp.ac.u_aizu.ta_report_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.FacultyCourse;

@Repository
public interface FacultyCourseRepository extends JpaRepository<FacultyCourse, Long> {

}
