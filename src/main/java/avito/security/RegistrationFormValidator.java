package avito.security;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import avito.data.UserRepository;

public class RegistrationFormValidator implements Validator {
	
	UserRepository userRepo;
	
	public RegistrationFormValidator(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegistrationForm form = (RegistrationForm) target;
		if (userRepo.existsByUsername(form.getUsername())) {
			errors.reject("User with this username already exists");
		}
	}

}
