package avito.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import avito.domain.User;
import lombok.Data;

@Data
public class RegistrationForm {
	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String zip;
	private String phone;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(username, passwordEncoder.encode(password),
				fullname, street, city, zip, phone);
	}
}
