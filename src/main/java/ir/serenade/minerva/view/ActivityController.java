package ir.serenade.minerva.view;

import ir.serenade.minerva.domain.*;
import ir.serenade.minerva.exception.MyResourceNotFoundException;
import ir.serenade.minerva.exception.ResourceNotAuthorizedException;
import ir.serenade.minerva.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by serenade on 8/6/18.
 */
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    

    @RequestMapping(value = "/rest/activities/list", method = RequestMethod.GET)
    public DataTablesOutput<DailyActivity> list(@Valid DataTablesInput input) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()) {
            User currentUser = (User) auth.getPrincipal();
            if (currentUser.getAuthorities().contains(new Role("ROLE_ADMIN"))) {
                return activityService.findAllDailyActivities(input);
            } else {
                return activityService.findAllDailyActivities(input, currentUser);
            }
        } else {
            throw new ResourceNotAuthorizedException();
        }


    }

    @RequestMapping(value = "/rest/activities/aggregated", method = RequestMethod.GET)
    public DataTablesOutput<NightlyStatistics> aggregatedList(@Valid DataTablesInput input) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()) {
            User currentUser = (User) auth.getPrincipal();
            if (currentUser.getAuthorities().contains(new Role("ROLE_ADMIN"))) {
                return activityService.findAllNightlyStatistics(input);
            } else {
                return activityService.findAllNightlyStatistics(input, currentUser);
            }
        } else {
            throw new ResourceNotAuthorizedException();
        }

    }

    @RequestMapping(value = "/rest/activities/daily", method = RequestMethod.GET)
    public DataTablesOutput<NightlyStatistics> dailyList(@Valid DataTablesInput input) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()) {
            User currentUser = (User) auth.getPrincipal();
            if (currentUser.getAuthorities().contains(new Role("ROLE_ADMIN"))) {
                return activityService.findAllNightlyStatisticsByDate(input);
            } else {
                return activityService.findAllNightlyStatisticsByDate(input, currentUser);
            }
        } else {
            throw new ResourceNotAuthorizedException();
        }

    }


    /*
    @RequestMapping(value = "/rest/activities", method = RequestMethod.POST)
    public DataTablesOutput<Activity> listPOST(@Valid @RequestBody DataTablesInput input) {
        return activityService.findAll(input);
    }
    */

}
