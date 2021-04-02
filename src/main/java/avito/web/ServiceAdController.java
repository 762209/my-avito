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
import avito.domain.Ad.AdCategory;
import avito.forms.AdForm;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/service_ad")
@AllArgsConstructor
public class ServiceAdController {
	private final AdRepository adRepo;
	
	@ModelAttribute(name = "adForm")
	public AdForm adForm() {
		return new AdForm();
	}
	
	@GetMapping("/new")
	public String showNewForm(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("currUser", user);
		
		return "new/service_ad";
	}
	@PostMapping("/new")
	public String saveAd(@ModelAttribute("adForm") @Valid AdForm adForm, Errors errors,
			@RequestParam("photoFiles")List<MultipartFile> photoFiles, 
			@AuthenticationPrincipal User user,  Model model) 
			throws IOException {
		
		model.addAttribute("currUser", user);
		
		if (errors.hasErrors()) {
			return "new/service_ad";
		}
		
		Ad ad = adForm.toAd();
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			ad.getPhotos().add(photo);
		}
		
		ad.setUser(user);
		ad.setAdCategory(AdCategory.SERVICE);
		adRepo.save(ad);
		
		return "redirect:/service_ad/new";
	}
	
	@GetMapping("/{id}/update")
	public String showUpdateForm(@PathVariable("id") long id, Model model,
			@AuthenticationPrincipal User user, AdForm adForm) {
		Ad ad = adRepo.findById(id)
				.orElseThrow( () -> new IllegalArgumentException("Invalid user Id: " + id) );
		
		adForm.loadData(ad);
		model.addAttribute("adForm", adForm);
		model.addAttribute("currUser", user);
		
		return "update/service_ad";
	}
	
	@PostMapping("/{id}/update")
	public String updateAd(@PathVariable("id") long id, @Valid AdForm adForm,
			Errors errors, Model model, @AuthenticationPrincipal User user,
			@RequestParam("photoFiles")List<MultipartFile> photoFiles) throws IOException{
		
		model.addAttribute("currUser", user);
		
		if (errors.hasErrors()) {
			adForm.setId(id);
			return "update/service_ad";
		}
		
		Ad ad = adForm.update();
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			ad.getPhotos().add(photo);
		}
		ad.setAdCategory(AdCategory.SERVICE);
		ad.setCreatedAt(LocalDateTime.now());
		ad.setUser(user);
		adRepo.save(ad);
		return "redirect:/profile";
	}
}
