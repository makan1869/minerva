package ir.serenade.minerva.service;

import ir.serenade.minerva.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;
import java.util.Set;

public interface ActivityService {
    public Activity saveActivity(Activity activity);
    public DailyActivity saveDailyActivity(DailyActivity activity);
    public DataTablesOutput<DailyActivity> findAllDailyActivities(DataTablesInput input);
    public DataTablesOutput<DailyActivity> findAllDailyActivities(DataTablesInput input, User user);

    public DataTablesOutput<NightlyStatistics> findAllNightlyStatistics(DataTablesInput input);
    public DataTablesOutput<NightlyStatistics> findAllNightlyStatistics(DataTablesInput input, User user);

    public DataTablesOutput<NightlyStatistics> findAllNightlyStatisticsByDate(DataTablesInput input, String from, String to);
    public DataTablesOutput<NightlyStatistics> findAllNightlyStatisticsByDate(DataTablesInput input, String from, String to, User user);


}
