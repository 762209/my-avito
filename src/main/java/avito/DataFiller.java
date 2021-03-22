package avito;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import avito.data.AdRepository;
import avito.data.CarRepository;
import avito.data.TransportRepository;
import avito.data.UserRepository;
import avito.domain.Ad;
import avito.domain.Car;
import avito.domain.Transport;
import avito.domain.User;
import avito.domain.Ad.AdCategory;

@Component
public class DataFiller {
	@Autowired
	private AdRepository adRepo;
	@Autowired
	private TransportRepository transportRepo;
	@Autowired
	private CarRepository carRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() throws IOException{
		User user1 = new User("ruslan", passwordEncoder.encode("123"), "Ruslan Alimovich", "Moscow", "Moscow", "123", "9389393");
		userRepo.save(user1);
		Car carObject = new Car();
		carObject.setDriveType("Полный привод");
		carObject.setEnginesType("Бензин");
		carObject.setTransmission("Автомат");
		Car carEntity = carRepo.save(carObject);
		Transport transportObject = new Transport();
		transportObject.setBrand("БМВ");
		transportObject.setCarAd(carEntity);
		transportObject.setColor("Черный");
		transportObject.setManufactureYear("2015");
		transportObject.setMileage(56000);
		transportObject.setModel("318i");
		Transport transportEntity = transportRepo.save(transportObject);
		Ad adObject = new Ad();
		adObject.setTransportAd(transportEntity);
		adObject.setAdCategory(AdCategory.CAR);
		adObject.setAdress("Ленинский пр-т");
		adObject.setCity("Москва");
		adObject.setDescription("Хорошая тачка");
		adObject.setName("Продам бэху");
		adObject.setPrice(3000000);
		adObject.setUser(user1);
		Ad adEntity = adRepo.save(adObject);
		user1.getAds().add(adEntity);
		
		Ad animalObject = new Ad();
		animalObject.setAdCategory(AdCategory.ANIMAL);
		animalObject.setAdress("Ленинский пр-т");
		animalObject.setCity("Москва");
		animalObject.setDescription("Хорошая кошка");
		animalObject.setName("Маруська");
		animalObject.setPrice(3000);
		animalObject.setUser(user1);
		Ad animalEntity = adRepo.save(animalObject);
		user1.getAds().add(animalEntity);
		userRepo.save(user1);
		
	}
}
