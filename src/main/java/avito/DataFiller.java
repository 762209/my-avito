package avito;

import java.io.IOException;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import avito.data.AdRepository;
import avito.data.UserRepository;
import avito.domain.Ad;
import avito.domain.Apartments;
import avito.domain.Car;
import avito.domain.House;
import avito.domain.Photo;
import avito.domain.RealEstate;
import avito.domain.Transport;
import avito.domain.TruckSpecMach;
import avito.domain.User;
import lombok.extern.slf4j.Slf4j;
import avito.domain.Ad.AdCategory;

@Component
@Slf4j
public class DataFiller {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AdRepository adRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() throws IOException{
		User user1 = new User("sveta", passwordEncoder.encode("123"), "Светлана Владимировна",
				"улица Металлургов дом 5", "Москва", "119042", "89108506942");
		User user2 = new User("nika", passwordEncoder.encode("123"), "Николай Алексеевич",
				"улица Дорохова дом 54", "Санкт-Петербург", "120358", "89055556982");
		User user3 = new User("olga", passwordEncoder.encode("123"), "Ольга Ярославна",
				"улица Мневники дом 3", "Москва", "119068", "89647256984");
		User user4 = new User("dmitrii", passwordEncoder.encode("123"), "Дмитрий Анатольевич",
				"Щёлковское шоссе дом 50", "Санкт-Петербург", "120869", "89076593296");
		
		userRepo.save(user1); userRepo.save(user2);
		userRepo.save(user3); userRepo.save(user4);
		
		
		byte[] img1 = new ClassPathResource("/static/images/test1.png")
				.getInputStream()
				.readAllBytes();
		byte[] img2 = new ClassPathResource("/static/images/test2.png")
				  .getInputStream()
				  .readAllBytes();
		byte[] img3 = new ClassPathResource("/static/images/test3.png")
				  .getInputStream()
				  .readAllBytes();
		
		
		Ad ad1 = new Ad("Продам машину BMW 318i", "Машина не бита не крашена, в хорошем состоянии, обслуживалась у официального дилера",
				3000000l, "Москва", "улица Металлургов дом 5", AdCategory.CAR);
		Car car1 = new Car("Автомат", "Полный привод", "Бензин");
		Transport transport1 = new Transport("318i", "BMW", "Чёрный", "2015", 32000);
		transport1.setCarAd(car1);
		ad1.setTransportAd(transport1);
		Collections.addAll(ad1.getPhotos(), new Photo(img1), new Photo(img2), new Photo(img3));
		
		Ad ad2 = new Ad("Продам собаку", "Хороший пёс, дрессированный",
				5000l, "Москва", "улица Металлургов дом 5", AdCategory.ANIMAL);
		Collections.addAll(ad2.getPhotos(), new Photo(img2), new Photo(img1), new Photo(img3));
		
		Ad ad3 = new Ad("Продам кота", "Хороший кот, в хорошем состоянии",
				3000l, "Москва", "улица Металлургов дом 5", AdCategory.ANIMAL);
		Collections.addAll(ad3.getPhotos(), new Photo(img3), new Photo(img2), new Photo(img1));
		
		Ad ad4 = new Ad("Продам квартиру", "Квартира с евроремонтом. Находится рядом с метро Беляево",
				7000000l, "Санкт-Петербург", "улица Дорохова дом 54", AdCategory.APARTMENTS);
		Apartments apartments1 = new Apartments(3, 7);
		RealEstate realEstate1 = new RealEstate(150);
		realEstate1.setApartmentsAd(apartments1);
		ad4.setRealEstateAd(realEstate1);
		Collections.addAll(ad4.getPhotos(), new Photo(img1), new Photo(img2), new Photo(img3));
		
		Ad ad5 = new Ad("Продам гараж", "Гараж на 2 машиноместа. Находится рядом с метро Беляево",
				300000l, "Санкт-Петербург", "улица Дорохова дом 54", AdCategory.GARAGE);
		RealEstate realEstate2 = new RealEstate(50);
		ad5.setRealEstateAd(realEstate2);
		Collections.addAll(ad5.getPhotos(), new Photo(img3), new Photo(img2), new Photo(img1));
		
		Ad ad6 = new Ad("Продам мотоцикл", "Мотоцикл в хорошем состоянии",
				600000l, "Санкт-Петербург", "улица Дорохова дом 54", AdCategory.MOTORCYCLE);
		Transport transport2 = new Transport("R301", "Hayabuza", "Зёлёный", "2018", 48000);
		ad6.setTransportAd(transport2);
		Collections.addAll(ad6.getPhotos(), new Photo(img2), new Photo(img1), new Photo(img3));
		
		Ad ad7 = new Ad("Продам дом", "Частный дом с участком в Москве",
				3600000l, "Москва", "улица Мневники дом 3", AdCategory.HOUSES);
		House house1 = new House(1000, 4);
		RealEstate realEstate3 = new RealEstate(30);
		realEstate3.setHouseAd(house1);
		ad7.setRealEstateAd(realEstate3);
		Collections.addAll(ad7.getPhotos(), new Photo(img1), new Photo(img2), new Photo(img3));
		
		Ad ad8 = new Ad("Продам экскаватор", "Экскаватор Komatsu PC300-8. В хорошем состоянии работает без нареканий",
				15000000l, "Москва", "улица Мневники дом 3", AdCategory.TRUCK_AND_SPECIAL_MACHINERY);
		Transport transport4 = new Transport("PC300-8", "Komatsu", "Жёлтый", "2013", 1500);
		TruckSpecMach truckSpecMach1 = new TruckSpecMach(9054);
		transport4.setTruckSpecMachAd(truckSpecMach1);
		ad8.setTransportAd(transport4);
		Collections.addAll(ad8.getPhotos(), new Photo(img2), new Photo(img3), new Photo(img1));
		
		Ad ad9 = new Ad("Экскватор в аренду", "Экскаватор Komatsu PC300-8. В хорошем состоянии работает без нареканий",
				18000l, "Москва", "улица Мневники дом 3", AdCategory.SERVICE);
		Collections.addAll(ad9.getPhotos(), new Photo(img3), new Photo(img2), new Photo(img1));
		
		
		
		for (int i = 0; i < 30; i++) {
			Ad ad10 = new Ad("Прочие услуги " + i, "Оказываем прочие услуги " + i,
					5000l, "Санкт-Петербург", "Щёлковское шоссе дом 50", AdCategory.SERVICE);
			ad10.setUser(user4);
			Collections.addAll(ad10.getPhotos(), new Photo(img1), new Photo(img2), new Photo(img3));
			adRepo.save(ad10);
		}
		
		ad1.setUser(user1); ad2.setUser(user1); ad3.setUser(user1);
		ad4.setUser(user2); ad5.setUser(user2); ad6.setUser(user2);
		ad7.setUser(user3); ad8.setUser(user3); ad9.setUser(user3);
		
		adRepo.save(ad9); adRepo.save(ad8); adRepo.save(ad7);
		adRepo.save(ad6); adRepo.save(ad5); adRepo.save(ad4);
		adRepo.save(ad3); adRepo.save(ad2); adRepo.save(ad1);
		
		log.info("Fill with test data");
	}
}
