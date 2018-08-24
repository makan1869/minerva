package ir.serenade.minerva.service.impl;

import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.domain.AggregatedActivity;
import ir.serenade.minerva.domain.User;
import ir.serenade.minerva.repository.ActivityRepository;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public Activity save(Activity activity) {
        return activityRepository.save(activity);

    }

    @Override
    public DataTablesOutput<Activity> findAll(DataTablesInput input) {
        return activityRepository.findAll(input);
    }

    @Override
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


        countQuery.select(builder.count(countQuery.from(Activity.class)));
        Predicate predicate = builder.conjunction();
        Map<String, String> keywords = user.getKeywords();
        for(String key : keywords.keySet()) {
            predicate = builder.and(predicate, builder.equal(r.get(key), keywords.get(key)));
        }
        query.where(predicate);
        Long totalRecords = entityManager.createQuery(countQuery).getSingleResult();
        List<Activity> result = entityManager.createQuery(query).setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList();


        output.setData(result);
        output.setRecordsTotal(totalRecords);


        return output;
    }

    @Override
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

    @Override
    public DataTablesOutput<AggregatedActivity> findAllAggregatedActivities(DataTablesInput input, User user) {
        return null;
    }


    @Override
    public Page<Activity> findPaginated(int page, int size) {
        return activityRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public Page<Activity> findPaginatedByKeyword(Set<String> keywords, int page, int size) {
        return activityRepository.findAllByKeyword(keywords, new PageRequest(page, size));

    }

}
