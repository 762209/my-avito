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
import avito.data.TruckSpecMachRepository;
import avito.data.UserRepository;
import avito.domain.Ad;
import avito.domain.Photo;
import avito.domain.Transport;
import avito.domain.Ad.AdCategory;
import avito.domain.TruckSpecMach;
import avito.domain.User;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/new/truck_and_special_machinery_ad")
@AllArgsConstructor
public class TruckAndSpecialMachineryAdController {
	private final UserRepository userRepo;
	private final AdRepository adRepo;
	private final PhotoRepository photoRepo;
	private final TransportRepository transportRepo;
	private final TruckSpecMachRepository truckSpeclMachRepo;
	
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
	
	@GetMapping()
	public String showAdForm(Model model) {
		return "/new/truck_and_special_machinery_ad";
	}
	@PostMapping()
	public String processAd(Ad adObject, Transport transportObject, TruckSpecMach truckSpecMachObject,
			@RequestParam("photoFiles") List<MultipartFile> photoFiles,
			@AuthenticationPrincipal User user) throws IOException {
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photoEntity = photoRepo.save(new Photo(bytes));
			adObject.getPhotos().add(photoEntity);
		}
		
		TruckSpecMach truckSpeclMachEntity = truckSpeclMachRepo.save(truckSpecMachObject);
		
		transportObject.setTruckSpecMachAd(truckSpeclMachEntity);
		Transport transportEntity = transportRepo.save(transportObject);
		
		adObject.setAdCategory(AdCategory.TRUCK_AND_SPECIAL_MACHINERY);
		adObject.setTransportAd(transportEntity);
		adObject.setUser(user);
		Ad adEntity = adRepo.save(adObject);
		user.getAds().add(adEntity);
		userRepo.save(user);
		
		return "redirect:/new/truck_and_special_machinery_ad";
	}
}
