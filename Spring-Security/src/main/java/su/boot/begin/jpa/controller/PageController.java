package su.boot.begin.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import su.boot.begin.jpa.repository.EmpRepository;
import jakarta.inject.Inject;

@Controller
public class PageController {
	@Inject
	private EmpRepository empRepository;

	@GetMapping("/su")
	public String home(Model model) {
		model.addAttribute("employees", empRepository.findAll());
		return "index";
	}

	@GetMapping("/president/president1")
	public String presidentPage() {
		return "emp/president1";
	}

	@GetMapping("/manager/manager1")
	public String managerPage() {
		return "emp/manager1";
	}

	@GetMapping("/analyst/analyst1")
	public String analystPage() {
		return "emp/analyst1";
	}

	@GetMapping("/clerk/clerk1")
	public String clerkPage(Model model) {
		return "emp/clerk1";
	}

	@GetMapping("/salesman/salesman1")
	public String salesmanPage(Model model) {
		return "emp/salesman1";
	}

	@GetMapping("/common/common1")
	public String commonPage(Model model) {
		return "emp/common1";
	}
}