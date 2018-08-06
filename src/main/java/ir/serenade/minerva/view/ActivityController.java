package ir.serenade.minerva.view;

import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.exception.MyResourceNotFoundException;
import ir.serenade.minerva.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by serenade on 8/6/18.
 */
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/rest/activity/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
    public Page<Activity> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {

        Page<Activity> resultPage = activityService.findPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        return resultPage;
    }

}
