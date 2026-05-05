package com.learn.ecommerce;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.razorpay.RazorpayClient;

@SpringBootApplication
public class EcommerceApplication {
	
	@Value("${Razorpay.key}")
    private String razorpayKey;

    @Value("${Razorpay.secret}")
    private String razorpaySecret;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	
	@Bean
    public RazorpayClient razorpayClient() throws Exception {
        return new RazorpayClient(razorpayKey, razorpaySecret);
    }

}
