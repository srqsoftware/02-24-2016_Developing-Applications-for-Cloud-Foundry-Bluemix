package com.crowleyworks.basicweb.control;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.crowleyworks.basicweb.model.Activity;
import com.crowleyworks.basicweb.service.ActivityService;

@Controller
public class DefaultController {

	@Autowired
	ActivityService activityService;

	@RequestMapping(value="/environment.htm", method = RequestMethod.GET)
	public String environment(Model model) {
		Map<String,String> allEnv = System.getenv();
		model.addAttribute("environment", allEnv);
		return "environment";
	}
	
	@RequestMapping(value="/list.htm", method = RequestMethod.GET)
	public String basicList(Model model) {
		try {
			List<Activity> values = activityService.getMany();
			for(Activity curValue : values) {
				System.out.println(curValue.getLocation());
			}
			model.addAttribute("activities", values);
		} catch(Exception e) {
			
		}
		Map<String,String> allEnv = System.getenv();
		model.addAttribute("environment", allEnv);
		return "list";
	}

	/*
	 * This method provides a new (blank) Activity object available for 
	 * editing within the view.
	 */
	@RequestMapping(value="/create.htm", method = RequestMethod.GET)
	public String preCreate(Model model) {
		Activity a = new Activity();
		a.setDate(new Date());
		model.addAttribute("activity", a);
		return "create";
	}
	
	/*
	 * This method gathers the existing Activity object and makes it available for 
	 * editing within the view.
	 */
	@RequestMapping(value="/edit.htm", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, Model model) {
		Activity a;
		try {
			a = activityService.get(id);
		} catch (Exception e) {
			return "list";
		}
		model.addAttribute("activity", a);
		return "create";
	}

	/*
	 * The user has POSTed either an update or create request.
	 */
	@RequestMapping(value="/create.htm", method = RequestMethod.POST)
	public String create(@ModelAttribute("activity") Activity activity, BindingResult result, SessionStatus status) {
		
		//new ActivityValidator().validate(activity, result);
		if (result.hasErrors()) {
			return "create";
		}
		
		if (activity.getId() < 1) {
			// This is an add.

			try {
				activity.setUserId("timothy");
				activity.setDate(new Date());
				activityService.create(activity);
				status.setComplete();
				return "redirect:list.htm";
			} catch(Exception e) {
				e.printStackTrace();
				return "create";
			}
		} else {
			// This is an update
			try {
				activityService.update(activity);
				status.setComplete();
				return "redirect:list.htm";
			} catch(Exception e) {
				e.printStackTrace();
				return "create";
			}
		}
	}
	


}
