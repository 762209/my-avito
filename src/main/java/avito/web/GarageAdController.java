package avito.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import avito.domain.RealEstate;
import avito.domain.User;
import avito.domain.Ad.AdCategory;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/garage_ad")
@AllArgsConstructor
public class GarageAdController {
	private final AdRepository adRepo;
	
	@ModelAttribute(name = "adObject")
	public Ad adObject() {
		return new Ad();
	}
	@ModelAttribute(name = "realEstateObject")
	public RealEstate realEstateObject() {
		return new RealEstate();
	}
	
	@GetMapping("/new")
	public String showNewForm(Model model) {
		return "new/garage_ad";
	}
	
	@PostMapping("/new")
	public String saveAd(Ad adObject, RealEstate realEstateObject,
			@RequestParam("photoFiles") List<MultipartFile> photoFiles,
			@AuthenticationPrincipal User user) throws IOException {
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			adObject.getPhotos().add(photo);
		}
		
		adObject.setRealEstateAd(realEstateObject);
		adObject.setAdCategory(AdCategory.GARAGE);
		adObject.setUser(user);
		adRepo.save(adObject);
		
		return "redirect:/garage_ad/new";
	}
	
	@GetMapping("/{id}/update")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Ad ad = adRepo.findById(id)
				.orElseThrow( () -> new IllegalArgumentException("Invalid user Id: " + id) );
		
		model.addAttribute("ad", ad);
		return "update/garage_ad";
	}
	
	@PostMapping("/{id}/update")
	public String updateAd(@PathVariable("id") long id, @Valid Ad ad,
			BindingResult result, Model model, @AuthenticationPrincipal User user,
			@RequestParam("photoFiles")List<MultipartFile> photoFiles) throws IOException{
		if (result.hasErrors()) {
			ad.setId(id);
			return "update/garage_ad";
		}
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			ad.getPhotos().add(photo);
		}
		ad.setAdCategory(AdCategory.GARAGE);
		ad.setCreatedAt(LocalDateTime.now());
		ad.setUser(user);
		adRepo.save(ad);
		return "redirect:/profile";
	}
}
