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
import avito.forms.TransportForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/motorcycle_ad")
@AllArgsConstructor
@Slf4j
public class MotorcycleAdController {
	private final AdRepository adRepo;
	
	@ModelAttribute(name = "adForm")
	public TransportForm motorcycleForm() {
		return new TransportForm();
	}
	@ModelAttribute(name = "imgUtil")
	public ImageUtil imgUtil() {
		return new ImageUtil();
	}
	
	@GetMapping("/new")
	public String showNewForm(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("currUser", user);
		
		return "/new/motorcycle_ad";
	}
	@PostMapping("/new")
	public String saveAd(@ModelAttribute("adForm") @Valid TransportForm adForm, Errors errors,
			@RequestParam("photoFiles") List<MultipartFile> photoFiles,
			@AuthenticationPrincipal User user, Model model) {
		
		model.addAttribute("currUser", user);
		
		if (errors.hasErrors()) {
			return "new/motorcycle_ad";
		}
		
		Ad ad = adForm.toAd();
		ad.setUser(user);
		
		try {
			for (MultipartFile photoFile : photoFiles) {
				byte[] bytes = photoFile.getBytes();
				Photo photo= new Photo(bytes);
				ad.getPhotos().add(photo);
			}
		} catch (IOException e) {
			log.warn(e.getMessage());
		}
		
		
		adRepo.save(ad);
		
		return "redirect:/motorcycle_ad/new";
	}
	
	@GetMapping("/{id}/update")
	public String showUpdateForm(@PathVariable("id") long id, Model model,
			@AuthenticationPrincipal User user, TransportForm adForm) {
		Ad ad = adRepo.findById(id)
				.orElseThrow( () -> {
					log.error("Invalid user Id: " + id);
					throw new IllegalArgumentException("Invalid user Id: " + id);
				});
		
		adForm.loadData(ad);
		model.addAttribute("adForm", adForm);
		model.addAttribute("currUser", user);
		
		return "update/motorcycle_ad";
	}
	
	@PostMapping("/{id}/update")
	public String updateAd(@PathVariable("id") long id,  @ModelAttribute("adForm") @Valid TransportForm adForm,
			Errors errors, Model model, @AuthenticationPrincipal User user,
			@RequestParam("photoFiles")List<MultipartFile> photoFiles) {
		
		model.addAttribute("currUser", user);
		
		if (errors.hasErrors()) {
			adForm.setId(id);
			return "update/motorcycle_ad";
		}

		Ad ad = adForm.update();
		
		try {
			for (MultipartFile photoFile : photoFiles) {
				byte[] bytes = photoFile.getBytes();
				Photo photo = new Photo(bytes);
				ad.getPhotos().add(photo);
			}
		} catch (IOException e) {
			log.warn(e.getMessage());
		}
		
		ad.setCreatedAt(LocalDateTime.now());
		ad.setUser(user);
		adRepo.save(ad);
		return "redirect:/profile";
	}
}
