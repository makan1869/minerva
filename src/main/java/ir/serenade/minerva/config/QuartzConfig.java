package ir.serenade.minerva.config;

import ir.serenade.minerva.job.AggregateNightlyStatisticsJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by serenade on 8/28/18.
 */
@Configuration
public class QuartzConfig {

    /*
    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(AggregateNightlyStatisticsJob.class).withIdentity("sampleJob")
                .usingJobData("name", "World").storeDurably().build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever();

        return TriggerBuilder.newTrigger().forJob(sampleJobDetail())
                .withIdentity("sampleTrigger").withSchedule(scheduleBuilder).build();
    }
    */

}
