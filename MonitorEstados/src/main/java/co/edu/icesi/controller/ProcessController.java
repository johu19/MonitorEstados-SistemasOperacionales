package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.icesi.services.ProcessService;

@Controller
public class ProcessController {
	
	@Autowired
	private ProcessService service;
	
	
	@GetMapping("/linux/")
	public String indexLinuxProcess(Model model) {
		model.addAttribute("processes", service.findLinuxProcess());
		return "linux/index";
	}
	
	@GetMapping("/linux/stop/{id}")
	public String stopLinuxProcess(@PathVariable("id") String id) {
		service.stopLinuxProcess(id);
		return "redirect:/linux/";
	}
	
	@GetMapping("/windows/")
	public String indexWindowsProcess(Model model) {
		model.addAttribute("processes",service.findWindowsProcess());
		return "windows/index";
	}
	
	@GetMapping("windows/stop/{id}")
	public String stopWindowsProcess(@PathVariable("id") String id) {
		service.stopWindowsProcess(id);
		return "redirect:/windows/";
	}

	
}
