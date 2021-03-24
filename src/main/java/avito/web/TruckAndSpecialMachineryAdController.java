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
import avito.domain.Transport;
import avito.domain.Ad.AdCategory;
import avito.domain.TruckSpecMach;
import avito.domain.User;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/truck_and_special_machinery_ad")
@AllArgsConstructor
public class TruckAndSpecialMachineryAdController {
	private final AdRepository adRepo;
	
	@ModelAttribute(name = "adObject")
	public Ad adObject() {
		return new Ad();
	}
	@ModelAttribute(name = "transportObject")
	public Transport transportObject() {
		return new Transport();
	}
	@ModelAttribute(name = "truckSpecMachObject")
	public TruckSpecMach truckSpecMachObject() {
		return new TruckSpecMach();
	}
	
	@GetMapping("/new")
	public String showNewForm(Model model) {
		return "/new/truck_and_special_machinery_ad";
	}
	@PostMapping("/new")
	public String saveAd(Ad adObject, Transport transportObject, TruckSpecMach truckSpecMachObject,
			@RequestParam("photoFiles") List<MultipartFile> photoFiles,
			@AuthenticationPrincipal User user) throws IOException {
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			adObject.getPhotos().add(photo);
		}
		transportObject.setTruckSpecMachAd(truckSpecMachObject);
		adObject.setAdCategory(AdCategory.TRUCK_AND_SPECIAL_MACHINERY);
		adObject.setTransportAd(transportObject);
		adObject.setUser(user);
		adRepo.save(adObject);
		
		return "redirect:/truck_and_special_machinery_ad/new";
	}
	
	@GetMapping("/{id}/update")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Ad ad = adRepo.findById(id)
				.orElseThrow( () -> new IllegalArgumentException("Invalid user Id: " + id) );
		
		model.addAttribute("ad", ad);
		return "update/truck_and_special_machinery_ad";
	}
	@PostMapping("/{id}/update")
	public String updateAd(@PathVariable("id") long id, @Valid Ad ad,
			BindingResult result, Model model, @AuthenticationPrincipal User user,
			@RequestParam("photoFiles")List<MultipartFile> photoFiles) throws IOException{
		if (result.hasErrors()) {
			ad.setId(id);
			return "update/truck_and_special_machinery_ad";
		}
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			ad.getPhotos().add(photo);
		}
		ad.setAdCategory(AdCategory.TRUCK_AND_SPECIAL_MACHINERY);
		ad.setCreatedAt(LocalDateTime.now());
		ad.setUser(user);
		adRepo.save(ad);
		return "redirect:/profile";
	}
}
