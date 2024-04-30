package com.cremedia.cremedia;

import com.cremedia.cremedia.models.entity.UserTag;
import com.cremedia.cremedia.models.entity.UsersSecond;
import com.cremedia.cremedia.repository.UserTagRepository;
import com.cremedia.cremedia.repository.UsersSecondRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CremediaApplication implements CommandLineRunner{

	private final UserTagRepository userTagRepository;
	private final UsersSecondRepository usersSecondRepository;
    public static void main(String[] args) {
		SpringApplication.run(CremediaApplication.class, args);
	}
    @Override
	public void run(String... args) throws Exception {

		System.out.println(usersSecondRepository.findByUsername("ilgar2"));

		UserTag userTag = new UserTag();
		userTag.setTagName("Developer");


		UsersSecond usersSecond = new UsersSecond();
		usersSecond.setName("Igar");
		usersSecond.setSurname("Musayev");
		usersSecond.setEmail("ms@ms.co");
		usersSecond.setPassword("1234");
		usersSecond.setStatus("active");
		usersSecond.setUsername("ilgar1");

		userTag.setUsersSecond(usersSecondRepository.save(usersSecond));



		userTagRepository.save(userTag);


		 // save prosesi erzinde bu managed statededir. (persistent context)
		// kecir detached stateye
	}

}
