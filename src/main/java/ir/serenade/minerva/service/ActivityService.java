package ir.serenade.minerva.service;

import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.domain.AggregatedActivity;
import ir.serenade.minerva.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;
import java.util.Set;

public interface ActivityService {
    public Activity save(Activity activity);
    public DataTablesOutput<Activity> findAll(DataTablesInput input);
    public DataTablesOutput<Activity> findAll(DataTablesInput input, User user);

    public DataTablesOutput<AggregatedActivity> findAllAggregatedActivities(DataTablesInput input);
    public DataTablesOutput<AggregatedActivity> findAllAggregatedActivities(DataTablesInput input, User user);

    public DataTablesOutput<AggregatedActivity> findAllDailyActivities(DataTablesInput input);
    public DataTablesOutput<AggregatedActivity> findAllDailyActivities(DataTablesInput input, User user);

    public List<AggregatedActivity> findAllDailyActivities(String date);
    public List<AggregatedActivity> findAllDailyActivities(User user, String date);


    public Page<Activity> findPaginated(int page, int size);
    public Page<Activity> findPaginatedByKeyword(Set<String> keyword, int page, int size);

}
