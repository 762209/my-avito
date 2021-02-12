package avito.web;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import avito.data.AdRepository;
import avito.data.ApartmentsRepository;
import avito.data.PhotoRepository;
import avito.data.RealEstateRepository;
import avito.domain.Ad;
import avito.domain.Apartments;
import avito.domain.Photo;
import avito.domain.RealEstate;
import avito.domain.Ad.AdCategory;
import avito.domain.RealEstate.RealEstateCategory;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/new/apartments_ad")
@AllArgsConstructor
public class ApartmentsAdController {
	private final AdRepository adRepo;
	private final PhotoRepository photoRepo;
	private final RealEstateRepository realEstateRepo;
	private final ApartmentsRepository apartmentsRepo;
	
	@ModelAttribute(name = "adObject")
	public Ad adObject() {
		return new Ad();
	}
	@ModelAttribute(name = "realEstateObject")
	public RealEstate realEstateObject() {
		return new RealEstate();
	}
	@ModelAttribute(name = "apartmentsObject")
	public Apartments apartmentsObject() {
		return new Apartments();
	}
	
	@GetMapping()
	public String showAdForm(Model model) {
		return "new/apartments_ad";
	}
	
	@PostMapping()
	public String processAd(Ad adObject, RealEstate realEstateObject, Apartments apartmentsObject,
			@RequestParam("photoFiles") List<MultipartFile> photoFiles) throws IOException {
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photoEntity = photoRepo.save(new Photo(bytes));
			adObject.getPhotos().add(photoEntity);
		}
		
		Apartments apartmentsEntity = apartmentsRepo.save(apartmentsObject);
		
		realEstateObject.setApartmentsAd(apartmentsEntity);
		realEstateObject.setRealEstateCategory(RealEstateCategory.APARTMENTS);
		RealEstate realEstateEntity = realEstateRepo.save(realEstateObject);
		
		adObject.setRealEstateAd(realEstateEntity);
		adObject.setAdCategory(AdCategory.REAL_ESTATE);
		adRepo.save(adObject);
		
		return "redirect:/new/car_ad";
	}
}
