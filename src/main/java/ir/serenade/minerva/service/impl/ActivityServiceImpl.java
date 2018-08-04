package ir.serenade.minerva.service.impl;

import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.repository.ActivityRepository;
import ir.serenade.minerva.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public Activity save(Activity activity) {
        return activityRepository.save(activity);

    }
}
