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
import avito.data.PhotoRepository;
import avito.domain.Ad;
import avito.domain.Photo;
import avito.domain.Ad.AdCategory;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/new/service_ad")
@AllArgsConstructor
public class ServiceAdController {
	private final AdRepository adRepo;
	private final PhotoRepository photoRepo;
	
	@ModelAttribute(name = "adObject")
	public Ad adObject() {
		return new Ad();
	}
	
	@GetMapping()
	public String showAdForm(Model model) {
		return "new/service_ad";
	}
	@PostMapping()
	public String processAd(Ad adObject,@RequestParam("photoFiles")List<MultipartFile> photoFiles) 
			throws IOException {
		
		for (MultipartFile photoFile : photoFiles) {
			byte[] bytes = photoFile.getBytes();
			Photo photoEntity = photoRepo.save(new Photo(bytes));
			adObject.getPhotos().add(photoEntity);
		}
		
		adObject.setAdCategory(AdCategory.SERVICE);
		adRepo.save(adObject);
		
		return "redirect:/new/service_ad";
	}
	
}
