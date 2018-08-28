package ir.serenade.minerva.repository;

import ir.serenade.minerva.domain.NightlyStatistics;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by serenade on 8/28/18.
 */
@Repository
public interface NightlyStatisticsRepository extends DataTablesRepository<NightlyStatistics, Long> {
    public List<NightlyStatistics> findAllByDate(String date);
}
