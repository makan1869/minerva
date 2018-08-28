package ir.serenade.minerva.messaging;


import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.domain.DailyActivity;
import ir.serenade.minerva.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.jms.Session;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class ActivityListener {

    @Autowired
    ActivityService activityService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");


    @JmsListener(destination = "activity.queue", containerFactory = "hamrahangListenerContainerFactory")
    public void receiveQueueAfrahamrahang(Session session, String action, @Headers Map<String, Object> headers) {
        saveActivity("AFRANET",action, headers);

    }

    @JmsListener(destination = "activity.queue", containerFactory = "herminiusListenerContainerFactory")
    public void receiveQueueHerminius(Session session, String action, @Headers Map<String, Object> headers) {
        saveActivity("HERMINIUS",action, headers);

    }

    private void saveActivity(String source, String action, @Headers Map<String, Object> headers) {
        if(action != null &&
                (action.toUpperCase().equals("PURCHASE") ||  action.toUpperCase().equals("PLAY"))
                ) {
            Activity activity = new Activity();
            activity.setAction(action);
            activity.setAuthor((String)headers.get("AUTHOR"));
            activity.setArtist((String)headers.get("ARTIST"));
            activity.setName((String)headers.get("TITLE"));
            activity.setAlbum((String)headers.get("ALBUM"));
            activity.setPublisher((String)headers.get("PUBLISHER"));
            activity.setAggregator((String)headers.get("AGGREGATOR"));
            activity.setService((String)headers.get("SERVICE"));
            activity.setBookPublisher((String)headers.get("BOOT_PUBLISHER"));
            activity.setDateCreated(new Date());
            activity.setSource(source);
            activity.setDate(dateFormat.format(activity.getDateCreated()));

            activity = activityService.saveActivity(activity);
            activityService.saveDailyActivity(new DailyActivity(activity));
        }
    }

    @JmsListener(destination = "activity.queue", containerFactory = "tiasbListenerContainerFactory")
    public void receiveQueueTiasb(Session session, String action, @Headers Map<String, Object> headers) {
        saveActivity("TIASB", action, headers);

    }

}
