package com.redbus;

import com.redbus.Operator.util.PdfService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedbusApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedbusApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){

		return  new ModelMapper();

	}


}
