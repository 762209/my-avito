package avito;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import avito.data.AdRepository;
import avito.data.UserRepository;
import avito.domain.Ad;
import avito.domain.Apartments;
import avito.domain.Car;
import avito.domain.House;
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
//		User user1 = new User("ruslan", passwordEncoder.encode("123"), "Ruslan Alimovich", "Moscow", "Moscow", "123", "9389393");
//		userRepo.save(user1);
//		
//		Car car = new Car();
//		car.setDriveType("Полный привод");
//		car.setEnginesType("Бензин");
//		car.setTransmission("Автомат");
//		Transport transport = new Transport();
//		transport.setBrand("БМВ");
//		transport.setCarAd(car);
//		transport.setColor("Черный");
//		transport.setManufactureYear("2015");
//		transport.setMileage(56000);
//		transport.setModel("318i");
//		Ad carAd = new Ad();
//		carAd.setTransportAd(transport);
//		carAd.setAdCategory(AdCategory.CAR);
//		carAd.setAdress("Ленинский пр-т");
//		carAd.setCity("Москва");
//		carAd.setDescription("Хорошая тачка");
//		carAd.setName("Продам бэху");
//		carAd.setPrice(3000000);
//		carAd.setUser(user1);
//		adRepo.save(carAd);
//		
//		TruckSpecMach truck = new TruckSpecMach();
//		truck.setOperatingTime(3467);
//		Transport transport2 = new Transport();
//		transport2.setBrand("Komatsu");
//		transport2.setTruckSpecMachAd(truck);
//		transport2.setColor("Синий");
//		transport2.setManufactureYear("2013");
//		transport2.setMileage(2000);
//		transport2.setModel("PC300-8");
//		Ad truckAd = new Ad();
//		truckAd.setTransportAd(transport2);
//		truckAd.setAdCategory(AdCategory.TRUCK_AND_SPECIAL_MACHINERY);
//		truckAd.setAdress("Кантемировская пр-т");
//		truckAd.setCity("Москва");
//		truckAd.setDescription("Хорошая экскаватор");
//		truckAd.setName("Продам экскаватор KomatsuPC300-8");
//		truckAd.setPrice(12000000);
//		truckAd.setUser(user1);
//		adRepo.save(truckAd);
//		
//		Transport transport3 = new Transport();
//		transport3.setBrand("Hayabuza");
//		transport3.setColor("Красный");
//		transport3.setManufactureYear("208");
//		transport3.setMileage(2000);
//		transport3.setModel("340 RJ");
//		Ad motorcycleAd = new Ad();
//		motorcycleAd.setTransportAd(transport3);
//		motorcycleAd.setAdCategory(AdCategory.MOTORCYCLE);
//		motorcycleAd.setAdress("Кантемировская пр-т");
//		motorcycleAd.setCity("Москва");
//		motorcycleAd.setDescription("Хорошая байк");
//		motorcycleAd.setName("Продам мотоцикл Hayabuza");
//		motorcycleAd.setPrice(300000);
//		motorcycleAd.setUser(user1);
//		adRepo.save(motorcycleAd);
//		
//		Apartments apartments = new Apartments();
//		apartments.setFloorLevel(44);
//		apartments.setRoomsNumber(3);
//		RealEstate realEstate = new RealEstate();
//		realEstate.setApartmentsAd(apartments);
//		realEstate.setFloorArea(120);
//		Ad apartmentsAd = new Ad();
//		apartmentsAd.setRealEstateAd(realEstate);
//		apartmentsAd.setAdCategory(AdCategory.APARTMENTS);
//		apartmentsAd.setAdress("Ленинский пр-т");
//		apartmentsAd.setCity("Москва");
//		apartmentsAd.setDescription("Хорошая квартира");
//		apartmentsAd.setName("Продам квартиру в новостройке");
//		apartmentsAd.setPrice(15000000);
//		apartmentsAd.setUser(user1);
//		adRepo.save(apartmentsAd);
//		
//		House house = new House();
//		house.setFloorLevels(3);
//		house.setLandArea(300);
//		RealEstate realEstate2 = new RealEstate();
//		realEstate2.setHouseAd(house);
//		realEstate2.setFloorArea(250);
//		Ad houseAd = new Ad();
//		houseAd.setRealEstateAd(realEstate2);
//		houseAd.setAdCategory(AdCategory.HOUSES);
//		houseAd.setAdress("Улица пивоваров дом 65");
//		houseAd.setCity("Можайск");
//		houseAd.setDescription("Хороший дом");
//		houseAd.setName("Продам дачу в Можайске");
//		houseAd.setPrice(4000000);
//		houseAd.setUser(user1);
//		adRepo.save(houseAd);
//		
//		RealEstate realEstate3 = new RealEstate();
//		realEstate3.setFloorArea(20);
//		Ad garageAd = new Ad();
//		garageAd.setRealEstateAd(realEstate3);
//		garageAd.setAdCategory(AdCategory.GARAGE);
//		garageAd.setAdress("Улица пивоваров дом 65");
//		garageAd.setCity("Можайск");
//		garageAd.setDescription("Хороший гараж");
//		garageAd.setName("Продам гараж в Можайске");
//		garageAd.setPrice(80000);
//		garageAd.setUser(user1);
//		adRepo.save(garageAd);
//		
//		Ad animalAd = new Ad();
//		animalAd.setAdCategory(AdCategory.ANIMAL);
//		animalAd.setAdress("Ленинский пр-т");
//		animalAd.setCity("Москва");
//		animalAd.setDescription("Хорошая кошка");
//		animalAd.setName("Маруська");
//		animalAd.setPrice(3000);
//		animalAd.setUser(user1);
//		adRepo.save(animalAd);
//		
//		Ad serviceAd = new Ad();
//		serviceAd.setAdCategory(AdCategory.SERVICE);
//		serviceAd.setAdress("Ленинский пр-т");
//		serviceAd.setCity("Москва");
//		serviceAd.setDescription("Хорошие услуги");
//		serviceAd.setName("Уборка дома");
//		serviceAd.setPrice(2000);
//		serviceAd.setUser(user1);
//		adRepo.save(serviceAd);
//		
//		log.info("Fill with test data");
	}
}
