package avito.security;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import avito.data.UserRepository;
import avito.domain.Photo;
import avito.domain.User;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@ModelAttribute("registrationForm")
	public RegistrationForm registrationForm() {
		return new RegistrationForm();
	}
	
	@GetMapping
	public String registerForm() {
		return "registration";
	}
	
	@PostMapping
	public String processRegistration(@ModelAttribute("registrationForm") @Valid RegistrationForm form,
			Errors errors, MultipartFile photoFile) {
		
		if (errors.hasErrors()) {
			return "registration";
		}
		User user = form.toUser(passwordEncoder);
		try {
			byte[] bytes = photoFile.getBytes();
			Photo photo = new Photo(bytes);
			user.setPhoto(photo);
		} catch (IOException e) {
			log.warn(e.getMessage());
		}
		
		
		userRepo.save(user);
		
		return "redirect:/login";
	}
	
}
