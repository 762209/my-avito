package avito.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import avito.forms.AdForm;
import avito.domain.Ad.AdCategory;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/animal_ad")
@AllArgsConstructor
public class AnimalAdController {
	private final AdRepository adRepo;
	
	@ModelAttribute(name = "adForm")
	public AdForm adForm() {
		return new AdForm();
	}
	
	@GetMapping("/new")
	public String showNewForm(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("currUser", user);
		
		return "new/animal_ad";
	}
	@PostMapping("/new")
	public String saveAd(@ModelAttribute("adForm") @Valid AdForm adForm, Errors errors,
			@RequestParam("photoFiles")List<MultipartFile> photoFiles, 
			@AuthenticationPrincipal User user,  Model model) throws IOException {
		
		model.addAttribute("currUser", user);
		
		if (errors.hasErrors()) {
			return "new/animal_ad";
		}
		
		Ad ad = adForm.toAd();
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			ad.getPhotos().add(photo);
		}
		
		ad.setUser(user);
		ad.setAdCategory(AdCategory.ANIMAL);
		adRepo.save(ad);
		
		return "redirect:/animal_ad/new";
	}
	
	@GetMapping("/{id}/update")
	public String showUpdateForm(@PathVariable("id") long id, Model model,
			@AuthenticationPrincipal User user) {
		Ad ad = adRepo.findById(id)
				.orElseThrow( () -> new IllegalArgumentException("Invalid user Id: " + id) );
		
		model.addAttribute("ad", ad);
		model.addAttribute("currUser", user);
		
		return "update/animal_ad";
	}
	
	@PostMapping("/{id}/update")
	public String updateAd(@PathVariable("id") long id, @Valid Ad ad,
			BindingResult result, Model model, @AuthenticationPrincipal User user,
			@RequestParam("photoFiles")List<MultipartFile> photoFiles) throws IOException{
		if (result.hasErrors()) {
			ad.setId(id);
			return "update/animal_ad";
		}
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			ad.getPhotos().add(photo);
		}
		ad.setAdCategory(AdCategory.ANIMAL);
		ad.setCreatedAt(LocalDateTime.now());
		ad.setUser(user);
		adRepo.save(ad);
		return "redirect:/profile";
	}
}
