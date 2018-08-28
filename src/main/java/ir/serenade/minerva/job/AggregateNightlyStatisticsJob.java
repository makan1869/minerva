package ir.serenade.minerva.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by serenade on 8/28/18.
 */
public class AggregateNightlyStatisticsJob extends QuartzJobBean {

    private String name;

    // Invoked if a Job data map entry with that name
    public void setName(String name) {
        this.name = name;
    }



    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(String.format("Job ran %s", name));
    }
}
