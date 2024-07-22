package com.cremedia.cremedia;

import com.cremedia.cremedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class CremediaApplication implements CommandLineRunner{


    public static void main(String[] args) {
		SpringApplication.run(CremediaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}








//    @Override
//	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setUsername("ilgar");
//		user.setName("Ilgar");
//		user.setSurname("Musayev");
//		user.setEmail("msyv@gmail.com");
//		user.setNumber("+994104242410");
//		user.setPassword("12344321");
//		user.setAvatar("avatar");
//		user.setBio("Musayev`s bio");
//		user.setTag("Developer");
//		user.setDateOfBirth("25 avqust 2005");
//		user.setGender("male");
//		user.setCountry("Azerbaijan");
//		user.setFollowers(100L);
//		user.setFollowings(200L);
//		user.setActive(true);
//		user.setPro(true);
//		user.setVerified(true);
//
//	}

}










//		UsersSecond usersSecond = new UsersSecond();
//		usersSecond.setName("Igar");
//		usersSecond.setSurname("Musayev");
//		usersSecond.setEmail("ms@ms.co");
//		usersSecond.setPassword("1234");
//		usersSecond.setStatus("active");
//		usersSecond.setUsername("ilgar1");
//
//		userTag.setUsersSecond(usersSecondRepository.save(usersSecond));
//
//
//
//		userTagRepository.save(userTag);