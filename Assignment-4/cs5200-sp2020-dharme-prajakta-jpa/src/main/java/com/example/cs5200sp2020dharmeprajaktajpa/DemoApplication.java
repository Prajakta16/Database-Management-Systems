package com.example.cs5200sp2020dharmeprajaktajpa;

import com.example.cs5200sp2020dharmeprajaktajpa.dao.FacultyDao;
import com.example.cs5200sp2020dharmeprajaktajpa.models.Faculty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		FacultyDao facultyDao = new FacultyDao();
//		Faculty facultyAlan = new Faculty("alan","password","Alan","Turin","123A",true);
//		facultyDao.createFaculty(facultyAlan);
	}


@Override
	protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application) {
		return application.sources(
				DemoApplication.class);
	}


}