package avito.web;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import avito.data.AdRepository;
import avito.data.PhotoRepository;
import avito.data.TransportRepository;
import avito.data.UserRepository;
import avito.domain.Ad;
import avito.domain.Photo;
import avito.domain.Transport;
import avito.domain.User;
import avito.domain.Ad.AdCategory;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/new/motorcycle_ad")
@AllArgsConstructor
public class MotorcycleAdController {
	private final UserRepository userRepo;
	private final AdRepository adRepo;
	private final PhotoRepository photoRepo;
	private final TransportRepository transportRepo;
	
	@ModelAttribute(name = "adObject")
	public Ad adObject() {
		return new Ad();
	}
	@ModelAttribute(name = "transportObject")
	public Transport transportObject() {
		return new Transport();
	}
	
	@GetMapping()
	public String showAdForm(Model model) {
		return "/new/motorcycle_ad";
	}
	@PostMapping()
	public String processAd(Ad adObject, Transport transportObject,
			@RequestParam("photoFiles") List<MultipartFile> photoFiles,
			@AuthenticationPrincipal User user) throws IOException {
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photoEntity = photoRepo.save(new Photo(bytes));
			adObject.getPhotos().add(photoEntity);
		}
		
		Transport transportEntity = transportRepo.save(transportObject);
		
		adObject.setAdCategory(AdCategory.MOTORCYCLE);
		adObject.setTransportAd(transportEntity);
		adObject.setUser(user);
		Ad adEntity = adRepo.save(adObject);
		user.getAds().add(adEntity);
		userRepo.save(user);
		
		return "redirect:/new/motorcycle_ad";
	}
	
}
