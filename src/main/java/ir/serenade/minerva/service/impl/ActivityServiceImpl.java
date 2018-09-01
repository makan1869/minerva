package ir.serenade.minerva.service.impl;

import ir.serenade.minerva.domain.*;
import ir.serenade.minerva.repository.ActivityRepository;
import ir.serenade.minerva.repository.DailyActivitiesRepository;
import ir.serenade.minerva.repository.NightlyStatisticsRepository;
import ir.serenade.minerva.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.SpecificationBuilder;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    DailyActivitiesRepository dailyActivitiesRepository;

    @Autowired
    NightlyStatisticsRepository nightlyStatisticsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);

    }

    @Override
    public DailyActivity saveDailyActivity(DailyActivity activity) {
        return dailyActivitiesRepository.save(activity);
    }

    @Override
    public DataTablesOutput<DailyActivity> findAllDailyActivities(DataTablesInput input) {
        return dailyActivitiesRepository.findAll(input);
    }

    @Override
    public DataTablesOutput<DailyActivity> findAllDailyActivities(DataTablesInput input, User user) {
        DataTablesOutput<DailyActivity> output = new DataTablesOutput<>();
        output.setDraw(input.getDraw());
        if (input.getLength() == 0) {
            return output;
        }


        SpecificationBuilder<DailyActivity> specificationBuilder = new SpecificationBuilder<>(input);
        Pageable pageable = specificationBuilder.createPageable();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DailyActivity> query = builder.createQuery(DailyActivity.class);
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root r = query.from(DailyActivity.class);
        Root _r = countQuery.from(DailyActivity.class);


        countQuery.select(builder.count(countQuery.from(DailyActivity.class)));
        Predicate predicate = builder.conjunction();
        Predicate _predicate = builder.conjunction();
        Map<String, String> keywords = user.getKeywords();
        for(String key : keywords.keySet()) {
            predicate = builder.and(predicate, builder.equal(r.get(key), keywords.get(key)));
            _predicate = builder.and(_predicate, builder.equal(_r.get(key), keywords.get(key)));
        }
        query.where(predicate);
        countQuery.where(_predicate);
        Long totalRecords = entityManager.createQuery(countQuery).getSingleResult();
        List<DailyActivity> result = entityManager.createQuery(query).setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList();


        output.setData(result);
        output.setRecordsTotal(totalRecords);
        output.setRecordsFiltered(totalRecords);


        return output;
    }

    @Override
    public DataTablesOutput<NightlyStatistics> findAllNightlyStatistics(DataTablesInput input) {
        return nightlyStatisticsRepository.findAll(input);
    }

    @Override
    public DataTablesOutput<NightlyStatistics> findAllNightlyStatistics(DataTablesInput input, User user) {
        DataTablesOutput<NightlyStatistics> output = new DataTablesOutput<>();
        output.setDraw(input.getDraw());
        if (input.getLength() == 0) {
            return output;
        }


        SpecificationBuilder<NightlyStatistics> specificationBuilder = new SpecificationBuilder<>(input);
        Pageable pageable = specificationBuilder.createPageable();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NightlyStatistics> query = builder.createQuery(NightlyStatistics.class);
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root r = query.from(NightlyStatistics.class);
        Root _r = countQuery.from(NightlyStatistics.class);


        countQuery.select(builder.count(countQuery.from(NightlyStatistics.class)));
        Predicate predicate = builder.conjunction();
        Predicate _predicate = builder.conjunction();
        Map<String, String> keywords = user.getKeywords();
        for(String key : keywords.keySet()) {
            predicate = builder.and(predicate, builder.equal(r.get(key), keywords.get(key)));
            _predicate = builder.and(_predicate, builder.equal(_r.get(key), keywords.get(key)));
        }
        query.where(predicate);
        countQuery.where(_predicate);
        Long totalRecords = entityManager.createQuery(countQuery).getSingleResult();
        List<NightlyStatistics> result = entityManager.createQuery(query).setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList();


        output.setData(result);
        output.setRecordsTotal(totalRecords);
        output.setRecordsFiltered(totalRecords);


        return output;
    }

    @Override
    public DataTablesOutput<NightlyStatistics> findAllNightlyStatisticsByDate(DataTablesInput input, String from, String to) {
        System.out.println(from);
        System.out.println(to);

        if(to == null || to.equalsIgnoreCase("null")) {
            to = dateFormat.format(new Date());
        }
        DataTablesOutput<NightlyStatistics> output = new DataTablesOutput<>();
        output.setDraw(input.getDraw());
        if (input.getLength() == 0) {
            return output;
        }


        SpecificationBuilder<NightlyStatistics> specificationBuilder = new SpecificationBuilder<>(input);
        Pageable pageable = specificationBuilder.createPageable();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NightlyStatistics> query = builder.createQuery(NightlyStatistics.class);
        Root r = query.from(NightlyStatistics.class);


        Predicate predicate = builder.conjunction();
        if(from!= null && !from.equalsIgnoreCase("null")) {
            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get("date"), from));
        }

        if(to!= null && !to.equalsIgnoreCase("null")) {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get("date"), to));
        }

        query.where(predicate);
        List<NightlyStatistics> result = entityManager.createQuery(query).setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList();


        output.setData(result);
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery.select(builder.count(countQuery.from(NightlyStatistics.class))).where(predicate);
        Long totalRecords = entityManager.createQuery(countQuery).getSingleResult();
        output.setRecordsTotal(totalRecords);
        output.setRecordsFiltered(totalRecords);


        return output;
    }

    @Override
    public DataTablesOutput<NightlyStatistics> findAllNightlyStatisticsByDate(DataTablesInput input, String from, String to, User user) {
        return null;
    }

    @Override
    public List<NightlyStatistics> findAllNightlyStatisticsByUserAndDate(User user, String from, String to) {
        if(to == null || to.equalsIgnoreCase("null")) {
            to = dateFormat.format(new Date());
        }
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NightlyStatistics> query = builder.createQuery(NightlyStatistics.class);
        Root r = query.from(NightlyStatistics.class);


        Predicate predicate = builder.conjunction();
        if(from!= null && !from.equalsIgnoreCase("null")) {
            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get("date"), from));
        }

        if(to!= null && !to.equalsIgnoreCase("null")) {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get("date"), to));
        }

        query.where(predicate);
        List<NightlyStatistics> result = entityManager.createQuery(query).getResultList();


        return result;
    }


    public DataTablesOutput<Activity> findAll(DataTablesInput input) {
        return activityRepository.findAll(input);
    }

    public DataTablesOutput<Activity> findAll(DataTablesInput input, User user) {

        DataTablesOutput<Activity> output = new DataTablesOutput<>();
        output.setDraw(input.getDraw());
        if (input.getLength() == 0) {
            return output;
        }


        SpecificationBuilder<AggregatedActivity> specificationBuilder = new SpecificationBuilder<>(input);
        Pageable pageable = specificationBuilder.createPageable();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Activity> query = builder.createQuery(Activity.class);
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root r = query.from(Activity.class);
        Root _r = countQuery.from(Activity.class);


        countQuery.select(builder.count(countQuery.from(Activity.class)));
        Predicate predicate = builder.conjunction();
        Predicate _predicate = builder.conjunction();
        Map<String, String> keywords = user.getKeywords();
        for(String key : keywords.keySet()) {
            predicate = builder.and(predicate, builder.equal(r.get(key), keywords.get(key)));
            _predicate = builder.and(_predicate, builder.equal(_r.get(key), keywords.get(key)));
        }
        query.where(predicate);
        countQuery.where(_predicate);
        Long totalRecords = entityManager.createQuery(countQuery).getSingleResult();
        List<Activity> result = entityManager.createQuery(query).setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList();


        output.setData(result);
        output.setRecordsTotal(totalRecords);
        output.setRecordsFiltered(totalRecords);


        return output;
    }

    public DataTablesOutput<AggregatedActivity> findAllAggregatedActivities(DataTablesInput input) {
        DataTablesOutput<AggregatedActivity> output = new DataTablesOutput<>();
        output.setDraw(input.getDraw());
        if (input.getLength() == 0) {
            return output;
        }

        try {
            SpecificationBuilder<AggregatedActivity> specificationBuilder = new SpecificationBuilder<>(input);

            Page<AggregatedActivity> data = activityRepository.findAllAggregatedActivities(
                    specificationBuilder.createPageable());

            @SuppressWarnings("unchecked")
            List<AggregatedActivity> content = (List<AggregatedActivity>) data.getContent();
            output.setData(content);
            output.setRecordsTotal(data.getTotalElements());

        } catch (Exception e) {
            output.setError(e.toString());
        }

        return output;
    }



    public DataTablesOutput<AggregatedActivity> findAllAggregatedActivities(DataTablesInput input, User user) {
        DataTablesOutput<AggregatedActivity> output = new DataTablesOutput<>();
        output.setDraw(input.getDraw());
        if (input.getLength() == 0) {
            return output;
        }


        SpecificationBuilder<AggregatedActivity> specificationBuilder = new SpecificationBuilder<>(input);
        Pageable pageable = specificationBuilder.createPageable();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AggregatedActivity> query = builder.createQuery(AggregatedActivity.class);

        Root r = query.from(Activity.class);


        Predicate predicate = builder.conjunction();
        Map<String, String> keywords = user.getKeywords();
        for(String key : keywords.keySet()) {
            predicate = builder.and(predicate, builder.equal(r.get(key), keywords.get(key)));
        }
        query.where(predicate);
        query.multiselect(r.get("author"), r.get("artist"), r.get("bookPublisher"), r.get("service"), r.get("action"), r.get("album"), r.get("publisher"),
                r.get("aggregator"), r.get("date"),builder.count(r).alias("count"))
                .groupBy(r.get("author"), r.get("artist"), r.get("bookPublisher"), r.get("service"), r.get("action"),
                    r.get("album"), r.get("publisher"), r.get("aggregator"), r.get("date"));

        List<AggregatedActivity> result = entityManager.createQuery(query).setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList();


        String countQueryString = "SELECT COUNT(*) from activity ";
        if(keywords.size() > 0) {
            countQueryString += " WHERE ";
            for(String key : keywords.keySet()) {
                countQueryString +=  (key + " = :" + key);
            }
        }
        countQueryString = "SELECT count(*) from (" + countQueryString + " GROUP BY author, artist, book_publisher, service, action, album, publisher, aggregator, date) as t";
        Query countQuery = entityManager.createNativeQuery(countQueryString);
        for(String key : keywords.keySet()) {
            countQuery = countQuery.setParameter(key, keywords.get(key));
        }
        Number totalRecords = (Number)countQuery.getSingleResult();

        output.setRecordsTotal(totalRecords.longValue());
        output.setRecordsFiltered(totalRecords.longValue());
        output.setData(result);
        return output;
    }

    /*
    @Override
    public DataTablesOutput<AggregatedActivity> findAllDailyActivities(DataTablesInput input) {

        String date = dateFormat.format(new Date());
        if(input.getSearch() != null && input.getSearch().getValue() != null && input.getSearch().getValue().length() > 0) {
            date = input.getSearch().getValue();
        }
        DataTablesOutput<AggregatedActivity> output = new DataTablesOutput<>();
        output.setDraw(input.getDraw());
        if (input.getLength() == 0) {
            return output;
        }

        try {
            SpecificationBuilder<AggregatedActivity> specificationBuilder = new SpecificationBuilder<>(input);

            Page<AggregatedActivity> data = activityRepository.findAllDailyActivities(
                    date, specificationBuilder.createPageable());

            @SuppressWarnings("unchecked")
            List<AggregatedActivity> content = (List<AggregatedActivity>) data.getContent();
            output.setData(content);
            output.setRecordsTotal(data.getTotalElements());

        } catch (Exception e) {
            output.setError(e.toString());
        }

        return output;
    }

    @Override
    public DataTablesOutput<AggregatedActivity> findAllDailyActivities(DataTablesInput input, User user) {
        String date = dateFormat.format(new Date());
        if(input.getSearch() != null && input.getSearch().getValue() != null && input.getSearch().getValue().length() > 0) {
            date = input.getSearch().getValue();
        }

        DataTablesOutput<AggregatedActivity> output = new DataTablesOutput<>();
        output.setDraw(input.getDraw());
        if (input.getLength() == 0) {
            return output;
        }


        SpecificationBuilder<AggregatedActivity> specificationBuilder = new SpecificationBuilder<>(input);
        Pageable pageable = specificationBuilder.createPageable();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AggregatedActivity> query = builder.createQuery(AggregatedActivity.class);

        Root r = query.from(Activity.class);


        Predicate predicate = builder.conjunction();
        Map<String, String> keywords = user.getKeywords();
        predicate = builder.and(predicate, builder.equal(r.get("date"), date));
        for(String key : keywords.keySet()) {
            predicate = builder.and(predicate, builder.equal(r.get(key), keywords.get(key)));
        }
        query.where(predicate);
        query.multiselect(r.get("author"), r.get("artist"), r.get("bookPublisher"), r.get("service"), r.get("action"), r.get("album"), r.get("publisher"),
                r.get("aggregator"), r.get("date"),builder.count(r).alias("count"))
                .groupBy(r.get("author"), r.get("artist"), r.get("bookPublisher"), r.get("service"), r.get("action"),
                        r.get("album"), r.get("publisher"), r.get("aggregator"), r.get("date"));

        List<AggregatedActivity> result = entityManager.createQuery(query).setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList();


        String countQueryString = "SELECT COUNT(*) from activity ";
        countQueryString += " WHERE date = :date ";
        if(keywords.size() > 0) {
            for(String key : keywords.keySet()) {
                countQueryString +=  ("AND " + key + " = :" + key);
            }
        }
        countQueryString = "SELECT count(*) from (" + countQueryString + " GROUP BY author, artist, book_publisher, service, action, album, publisher, aggregator, date) as t";
        Query countQuery = entityManager.createNativeQuery(countQueryString);
        countQuery = countQuery.setParameter("date", date);
        for(String key : keywords.keySet()) {
            countQuery = countQuery.setParameter(key, keywords.get(key));
        }
        Number totalRecords = (Number)countQuery.getSingleResult();

        output.setRecordsTotal(totalRecords.longValue());
        output.setRecordsFiltered(totalRecords.longValue());
        output.setData(result);
        return output;
    }

    */

    public List<AggregatedActivity> findAllDailyActivities(String date) {
        date = dateFormat.format(new Date());
        if(date != null && date.length() > 0) {
            date = date;
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AggregatedActivity> query = builder.createQuery(AggregatedActivity.class);

        Root r = query.from(Activity.class);


        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate, builder.equal(r.get("date"), date));
        query.where(predicate);
        query.multiselect(r.get("author"), r.get("artist"), r.get("bookPublisher"), r.get("service"), r.get("action"), r.get("album"), r.get("publisher"),
                r.get("aggregator"), r.get("date"),builder.count(r).alias("count"))
                .groupBy(r.get("author"), r.get("artist"), r.get("bookPublisher"), r.get("service"), r.get("action"),
                        r.get("album"), r.get("publisher"), r.get("aggregator"), r.get("date"));

        List<AggregatedActivity> result = entityManager.createQuery(query).getResultList();

        return result;
    }

    public List<AggregatedActivity> findAllDailyActivities(User user, String date) {
        date = dateFormat.format(new Date());
        if(date != null && date.length() > 0) {
            date = date;
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AggregatedActivity> query = builder.createQuery(AggregatedActivity.class);

        Root r = query.from(Activity.class);


        Predicate predicate = builder.conjunction();
        Map<String, String> keywords = user.getKeywords();
        predicate = builder.and(predicate, builder.equal(r.get("date"), date));
        for(String key : keywords.keySet()) {
            predicate = builder.and(predicate, builder.equal(r.get(key), keywords.get(key)));
        }
        query.where(predicate);
        query.multiselect(r.get("author"), r.get("artist"), r.get("bookPublisher"), r.get("service"), r.get("action"), r.get("album"), r.get("publisher"),
                r.get("aggregator"), r.get("date"),builder.count(r).alias("count"))
                .groupBy(r.get("author"), r.get("artist"), r.get("bookPublisher"), r.get("service"), r.get("action"),
                        r.get("album"), r.get("publisher"), r.get("aggregator"), r.get("date"));

        List<AggregatedActivity> result = entityManager.createQuery(query).getResultList();

        return result;
    }


    public Page<Activity> findPaginated(int page, int size) {
        return activityRepository.findAll(new PageRequest(page, size));
    }

    public Page<Activity> findPaginatedByKeyword(Set<String> keywords, int page, int size) {
        return activityRepository.findAllByKeyword(keywords, new PageRequest(page, size));

    }

}
