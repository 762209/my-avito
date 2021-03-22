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
import avito.data.CarRepository;
import avito.data.PhotoRepository;
import avito.data.TransportRepository;
import avito.data.UserRepository;
import avito.domain.Ad;
import avito.domain.Car;
import avito.domain.Photo;
import avito.domain.Transport;
import avito.domain.User;
import lombok.AllArgsConstructor;
import avito.domain.Ad.AdCategory;

@Controller
@RequestMapping("/new/car_ad")
@AllArgsConstructor
public class CarAdController {
	private final UserRepository userRepo;
	private final AdRepository adRepo;
	private final PhotoRepository photoRepo;
	private final TransportRepository transportRepo;
	private final CarRepository carRepo;
	
	@ModelAttribute(name = "adObject")
	public Ad adObject() {
		return new Ad();
	}
	@ModelAttribute(name = "transportObject")
	public Transport transportObject() {
		return new Transport();
	}
	@ModelAttribute(name = "carObject")
	public Car carObject() {
		return new Car();
	}
	
	@GetMapping()
	public String showAdForm(Model model) {
		return "new/car_ad";
	}
	@PostMapping()
	public String processAd(Ad adObject, Transport transportObject, Car carObject,
			@RequestParam("photoFiles") List<MultipartFile> photoFiles,
			@AuthenticationPrincipal User user) throws IOException {
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photoEntity = photoRepo.save(new Photo(bytes));
			adObject.getPhotos().add(photoEntity);
		}
		
		Car carEntity = carRepo.save(carObject);
		
		transportObject.setCarAd(carEntity);
		Transport transportEntity = transportRepo.save(transportObject);
		
		adObject.setAdCategory(AdCategory.CAR);
		adObject.setTransportAd(transportEntity);
		adObject.setUser(user);
		Ad adEntity = adRepo.save(adObject);
		user.getAds().add(adEntity);
		userRepo.save(user);
		
		
		return "redirect:/new/car_ad";
	}
	
}
