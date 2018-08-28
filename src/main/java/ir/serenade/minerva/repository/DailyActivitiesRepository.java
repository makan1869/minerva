package ir.serenade.minerva.repository;

import ir.serenade.minerva.domain.DailyActivity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by serenade on 8/28/18.
 */
@Repository
public interface DailyActivitiesRepository extends DataTablesRepository<DailyActivity, Long> {
}
