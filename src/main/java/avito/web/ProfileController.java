package avito.web;

import static avito.specifications.AdSpecification.hasCategory;
import static avito.specifications.AdSpecification.hasCity;
import static avito.specifications.AdSpecification.hasName;
import static avito.specifications.AdSpecification.hasUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import avito.data.AdRepository;
import avito.domain.Ad;
import avito.domain.Apartments;
import avito.domain.Car;
import avito.domain.House;
import avito.domain.RealEstate;
import avito.domain.Transport;
import avito.domain.TruckSpecMach;
import avito.domain.User;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	private AdRepository adRepo;

	public ProfileController(AdRepository adRepo) {
		this.adRepo = adRepo;
	}
	
	@ModelAttribute("search")
	public Ad search() {
		Ad ad = new Ad();
		ad.setTransportAd(new Transport());
		ad.setRealEstateAd(new RealEstate());
		ad.getTransportAd().setCarAd(new Car());
		ad.getTransportAd().setTruckSpecMachAd(new TruckSpecMach());
		ad.getRealEstateAd().setApartmentsAd(new Apartments());
		ad.getRealEstateAd().setHouseAd(new House());
		return ad;
	}
	
	@GetMapping()
	public String search(@ModelAttribute Ad search, Model model, @AuthenticationPrincipal User user) {
		return listByPage(search, model, user , 1);
	}
	@GetMapping("/page/{pageNumber}")
	public String listByPage(@ModelAttribute Ad search, Model model, @AuthenticationPrincipal User user,
			@PathVariable("pageNumber") int currentPage) {
		
		Specification<Ad> spec = Specification.where(hasName(search))
				  								.and(hasCity(search))
				  								.and(hasCategory(search))
				  								.and(hasUser(user));
		PageRequest pageRequest = PageRequest.of( (currentPage-1), 3, Sort.by("createdAt").descending());
		Page<Ad> page = adRepo.findAll(spec, pageRequest);
		
		model.addAttribute("page", page);
		model.addAttribute("imgUtil", new ImageUtil());
		model.addAttribute("currUser", user);
		
		return "profile";
	}
	@GetMapping("/delete/{id}")
	public String deleteAd(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
		Ad ad = adRepo.findById(id)
				.orElseThrow( () -> new IllegalArgumentException("Invalid user Id: " + id) );
		adRepo.delete(ad);
		
		return "redirect:/profile";
	}
	
}
