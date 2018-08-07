package ir.serenade.minerva.repository;

import ir.serenade.minerva.domain.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("select a from Activity a where a.publisher IN :keywords or a.aggregator IN :keywords ")
    Page<Activity> findAllByKeyword(@Param("keywords") Set<String> keywords, Pageable pageRequest);
}
