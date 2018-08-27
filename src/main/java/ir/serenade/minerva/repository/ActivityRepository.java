package ir.serenade.minerva.repository;

import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.domain.AggregatedActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ActivityRepository extends DataTablesRepository<Activity, Long> {

    @Query("select a from Activity a where a.publisher IN :keywords or a.aggregator IN :keywords ")
    Page<Activity> findAllByKeyword(@Param("keywords") Set<String> keywords, Pageable pageRequest);

    @Query("select COUNT(a) as count, a.author as author, a.artist as artists, a.bookPublisher as bookPublisher, a.service as service, a.action as action, a.album as album, a.publisher as publisher, a.aggregator as aggregator, a.date as date from Activity a GROUP BY a.author, a.artist, a.bookPublisher, a.service, a.action, a.album, a.publisher, a.aggregator, a.date")
    Page<AggregatedActivity> findAllAggregatedActivities(Pageable pageRequest);

    @Query("select COUNT(a) as count, a.author as author, a.artist as artists, a.bookPublisher as bookPublisher, a.service as service, a.action as action, a.album as album, a.publisher as publisher, a.aggregator as aggregator, a.date as date from Activity a WHERE date = :date GROUP BY a.author, a.artist, a.bookPublisher, a.service, a.action, a.album, a.publisher, a.aggregator, a.date")
    Page<AggregatedActivity> findAllDailyActivities(String date, Pageable pageRequest);
}
