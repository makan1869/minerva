package ir.serenade.minerva.view;

import ir.serenade.minerva.domain.Activity;
import ir.serenade.minerva.domain.Role;
import ir.serenade.minerva.domain.User;
import ir.serenade.minerva.exception.MyResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
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
}
