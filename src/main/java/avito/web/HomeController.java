package avito.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import avito.data.AdRepository;
import avito.domain.Ad;
import avito.domain.Ad.AdCategory;
import avito.domain.Apartments;
import avito.domain.Car;
import avito.domain.House;
import avito.domain.RealEstate;
import avito.domain.Transport;
import avito.domain.TruckSpecMach;

import static avito.specifications.AdSpecification.*;

@Controller
@RequestMapping("/home")
public class HomeController {
	private AdRepository adRepository;

	public HomeController(AdRepository adRepository) {
		this.adRepository = adRepository;
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
	
	@GetMapping
	public String search(@ModelAttribute Ad search, Model model) {
		System.out.println(search.getAdCategory());
		
		Specification<Ad> spec = Specification.where(hasName(search))
											  .and(hasCity(search));
		PageRequest page = PageRequest.of(0, 3, Sort.by("createdAt").descending());
		Page<Ad> ads = adRepository.findAll(spec, page);
		model.addAttribute("ads", ads);
		model.addAttribute("imgUtil", new ImageUtil());
		return "home";
	}
}
