package ir.serenade.minerva.service.impl;

import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.repository.ActivityRepository;
import ir.serenade.minerva.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public Activity save(Activity activity) {
        return activityRepository.save(activity);

    }

    @Override
    public Page<Activity> findPaginated(int page, int size) {
        return activityRepository.findAll(new PageRequest(page, size));
    }

}
