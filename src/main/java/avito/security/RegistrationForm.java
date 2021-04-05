package avito.security;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;

import avito.domain.User;
import lombok.Data;

@Data
public class RegistrationForm {
	
	@NotBlank(message="Поле 'Логин' не может быть пустым")
	private String username;
	@NotBlank(message="Поле 'Пароль' не может быть пустым")
	private String password;
	@NotBlank(message="Поле 'Полное имя' не может быть пустым")
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
