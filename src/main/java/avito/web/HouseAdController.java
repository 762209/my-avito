package avito.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import avito.data.AdRepository;
import avito.domain.Ad;
import avito.domain.Photo;
import avito.domain.User;
import avito.forms.HouseForm;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/house_ad")
@AllArgsConstructor
public class HouseAdController {
	private final AdRepository adRepo;
	
	@ModelAttribute("adForm")
	public HouseForm adForm() {
		return new HouseForm();
	}
	
	@GetMapping("/new")
	public String showNewForm(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("currUser", user);
		
		return "new/house_ad";
	}
	
	@PostMapping("/new")
	public String saveAd(@ModelAttribute("adForm") @Valid HouseForm adForm,
			Errors errors, @RequestParam("photoFiles") List<MultipartFile> photoFiles,
			@AuthenticationPrincipal User user, Model model) throws IOException {
		
		model.addAttribute("currUser", user);
		
		if (errors.hasErrors()) {
			return "new/house_ad";
		}
		
		Ad ad = adForm.toAd();
		ad.setUser(user);
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			ad.getPhotos().add(photo);
		}
		adRepo.save(ad);
		
		return "redirect:/house_ad/new";
	}
	
	@GetMapping("/{id}/update")
	public String showUpdateForm(@PathVariable("id") long id, Model model,
			@AuthenticationPrincipal User user, HouseForm adForm) {
		Ad ad = adRepo.findById(id)
				.orElseThrow( () -> new IllegalArgumentException("Invalid user Id: " + id) );
		
		adForm.loadData(ad);
		model.addAttribute("adForm", adForm);
		model.addAttribute("currUser", user);
		
		return "update/house_ad";
	}
	
	@PostMapping("/{id}/update")
	public String updateAd(@PathVariable("id") long id, @ModelAttribute("adForm") @Valid HouseForm adForm,
			Errors errors, Model model, @AuthenticationPrincipal User user,
			@RequestParam("photoFiles")List<MultipartFile> photoFiles) throws IOException{
		
		model.addAttribute("currUser", user);
		
		if (errors.hasErrors()) {
			adForm.setId(id);
			return "update/house_ad";
		}
		
		Ad ad = adForm.update();
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			ad.getPhotos().add(photo);
		}
		ad.setCreatedAt(LocalDateTime.now());
		ad.setUser(user);
		adRepo.save(ad);
		
		return "redirect:/profile";
	}
}
