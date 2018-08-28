package ir.serenade.minerva.view;

import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.domain.AggregatedActivity;
import ir.serenade.minerva.domain.Role;
import ir.serenade.minerva.domain.User;
import ir.serenade.minerva.exception.MyResourceNotFoundException;
import ir.serenade.minerva.exception.ResourceNotAuthorizedException;
import ir.serenade.minerva.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value="/activities/index", method = RequestMethod.GET)
    public ModelAndView activityIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("activity/index");
        return modelAndView;
    }

    @RequestMapping(value="/activities/aggregated", method = RequestMethod.GET)
    public ModelAndView activityAggregated(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("activity/aggregated");
        return modelAndView;
    }

    @RequestMapping(value="/activities/daily", method = RequestMethod.GET)
    public ModelAndView activityDaily(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("activity/daily");
        return modelAndView;
    }

}
