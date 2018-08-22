package ir.serenade.minerva.messaging;


import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.service.ActivityService;
import org.apache.activemq.ActiveMQSession;
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

    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd");


    @JmsListener(destination = "activity.queue", containerFactory = "hamrahangListenerContainerFactory")
    public void receiveQueue(Session session, String action, @Headers Map<String, Object> headers) {
        if(action != null &&
                (action.toUpperCase().equals("PURCHASE") ||  action.toUpperCase().equals("PLAY"))
                ) {
            Activity activity = new Activity();
            activity.setAction(action);
            activity.setName((String)headers.get("NAME"));
            activity.setAlbum((String)headers.get("ALBUM"));
            activity.setPublisher((String)headers.get("PUBLISHER"));
            activity.setAggregator((String)headers.get("AGGREGATOR"));
            activity.setService((String)headers.get("SERVICE"));
            activity.setDateCreated(new Date());
            activity.setDate(dateFormat.format(activity.getDateCreated()));

            activity = activityService.save(activity);
        }

    }

}
