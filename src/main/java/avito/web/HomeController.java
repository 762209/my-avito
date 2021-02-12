package avito.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import avito.data.AdRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	private AdRepository adRepository;

	public HomeController(AdRepository adRepository) {
		this.adRepository = adRepository;
	}
	
	@GetMapping
	public String home(Model model) {
		PageRequest page = PageRequest.of(0, 3, Sort.by("createdAt").descending());
		model.addAttribute("ads", adRepository.findAll(page));
		model.addAttribute("imgUtil", new ImageUtil());
		return "home";
	}
}
