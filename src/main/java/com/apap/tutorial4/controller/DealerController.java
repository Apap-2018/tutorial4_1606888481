package com.apap.tutorial4.controller;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping("/dealer")
	private String viewAll(Model model) {	
		List<DealerModel> dealerlst = dealerService.getDealerList();
		model.addAttribute("dealerlst",dealerlst);
		return "view-all-dealers";
	}

	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	
	@RequestMapping(value = "/dealer/delete/{dealerId}", method = RequestMethod.GET)
	private String deleteDealerSubmit(@PathVariable (value = "dealerId",required = true) Long dealerId) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		dealerService.deleteDealer(dealer);
		return "delete";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.GET)
	private String updateDealer(@PathVariable (value = "dealerId",required = true) Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		model.addAttribute("dealer",dealer);
		return "updateDealer";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.POST)
	private String updateDealerSubmit(@PathVariable (value = "dealerId") long dealerId, @ModelAttribute DealerModel dealer) {
		dealerService.updateDealer(dealerId, dealer);
		return "update";
	}
	
	@RequestMapping(value = "/dealer/{dealerId}", method = RequestMethod.GET)
	private String viewDealer(@PathVariable(value = "dealerId", required = true) Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		model.addAttribute("dealer", dealer);
		return "view-dealer";
	}
}
