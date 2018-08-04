package ir.serenade.minerva.messaging;


import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.service.ActivityService;
import org.apache.activemq.ActiveMQSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.jms.Session;
import java.util.Map;

@Component
public class ActivityListener {

    @Autowired
    ActivityService activityService;


    @JmsListener(destination = "activity.queue", containerFactory = "hamrahangListenerContainerFactory")
    public void receiveQueue(Session session, String action, @Headers Map<String, Object> headers) {
        System.out.println(((ActiveMQSession) session).getConnection().getBrokerInfo());
        Activity activity = new Activity();
        activity.setAction(action);
        activity.setName((String)headers.get("NAME"));
        activity.setAlbum((String)headers.get("ALBUM"));
        activity.setPublisher((String)headers.get("PUBLISHER"));
        activity.setAggregator((String)headers.get("AGGREGATOR"));

        activity = activityService.save(activity);
        System.out.println("Activity " + activity.getId());
    }

}
