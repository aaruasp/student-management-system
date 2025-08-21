package com.nit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nit.repository.IStudnetRepository;

@SpringBootApplication
public class StudnetManagementSystemApplication implements CommandLineRunner
{

	public static void main(String[] args) 
	{
		SpringApplication.run(StudnetManagementSystemApplication.class, args);
	}

	@Autowired
	private IStudnetRepository studentRepository;
	
	@Override
	public void run(String... args) throws Exception 
	{
//		Student student1 = new Student("Aarif", "Pathan", "aarif@gmail.com");
//		studentRepository.save(student1);
//		
//		Student student2 = new Student("sarfaraz", "shaikh", "sf@gmail.com");
//		studentRepository.save(student2);
//		
//		Student student3 = new Student("tony", "stark", "tony@gmail.com");
//		studentRepository.save(student3);
	}

}
