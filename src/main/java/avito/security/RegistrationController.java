package avito.security;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import avito.data.UserRepository;
import avito.domain.Photo;
import avito.domain.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	public String registerForm() {
		return "registration";
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form, MultipartFile photoFile) throws IOException{
		User user = form.toUser(passwordEncoder);
		byte[] bytes = photoFile.getBytes();
		Photo photo = new Photo(bytes);
		user.setPhoto(photo);
		
		userRepo.save(user);
		return "redirect:/login";
	}
	
}
