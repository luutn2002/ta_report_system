package jp.ac.u_aizu.ta_report_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.u_aizu.ta_report_system.entity.WorkCategory;

@Repository
public interface WorkCategoryRepository extends JpaRepository<WorkCategory, Long> {
  
    List<WorkCategory> findByDeleted(Boolean bool);
  
    WorkCategory findByName(String name);
}
