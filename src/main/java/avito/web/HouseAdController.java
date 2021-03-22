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
import avito.data.HouseRepository;
import avito.data.PhotoRepository;
import avito.data.RealEstateRepository;
import avito.data.UserRepository;
import avito.domain.Ad;
import avito.domain.House;
import avito.domain.Photo;
import avito.domain.RealEstate;
import avito.domain.User;
import avito.domain.Ad.AdCategory;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/new/house_ad")
@AllArgsConstructor
public class HouseAdController {
	private final UserRepository userRepo;
	private final AdRepository adRepo;
	private final PhotoRepository photoRepo;
	private final RealEstateRepository realEstateRepo;
	private final HouseRepository houseRepo;
	
	@ModelAttribute(name = "adObject")
	public Ad adObject() {
		return new Ad();
	}
	@ModelAttribute(name = "realEstateObject")
	public RealEstate realEstateObject() {
		return new RealEstate();
	}
	@ModelAttribute(name = "houseObject")
	public House houseObject() {
		return new House();
	}
	
	@GetMapping()
	public String showAdForm(Model model) {
		return "new/house_ad";
	}
	
	@PostMapping()
	public String processAd(Ad adObject, RealEstate realEstateObject, House houseObject,
			@RequestParam("photoFiles") List<MultipartFile> photoFiles,
			@AuthenticationPrincipal User user) throws IOException {
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photoEntity = photoRepo.save(new Photo(bytes));
			adObject.getPhotos().add(photoEntity);
		}
		
		House houseEntity = houseRepo.save(houseObject);
		
		realEstateObject.setHouseAd(houseEntity);
		RealEstate realEstateEntity = realEstateRepo.save(realEstateObject);
		
		adObject.setRealEstateAd(realEstateEntity);
		adObject.setAdCategory(AdCategory.HOUSES);
		adObject.setUser(user);
		Ad adEntity = adRepo.save(adObject);
		user.getAds().add(adEntity);
		userRepo.save(user);
		
		return "redirect:/new/house_ad";
	}
}
