package ir.serenade.minerva.view;

import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.domain.Role;
import ir.serenade.minerva.domain.User;
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

    

    @RequestMapping(value = "/rest/activities", method = RequestMethod.GET)
    public DataTablesOutput<Activity> list(@Valid DataTablesInput input) {
        return activityService.findAll(input);
    }

    @RequestMapping(value = "/rest/activities", method = RequestMethod.POST)
    public DataTablesOutput<Activity> listPOST(@Valid @RequestBody DataTablesInput input) {
        return activityService.findAll(input);
    }

}
