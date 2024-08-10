package com.training;


import java.util.HashMap;
import java.util.Map;

import com.training.dto.RegisterStatus;
import com.training.entity.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import com.training.entity.Address;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

import org.springframework.web.client.RestTemplate;






@SpringBootTest
class ApplicationTests {

	@Test
	void register(){
		RestTemplate rt = new RestTemplate();
		String url = "http://localhost:8080/customer/register";
		Customer customer=new Customer();
		customer.setName("Jayansh Mamidi");
		customer.setEmail("mami@gmail.com");
		customer.setPassword("tets");


		Address address =new Address();
		address.setCity("Hyderabad");
		address.setPincode(500089);

		HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(customer);

		//RegisterStatus status = rt.postForObject(url, customer, RegisterStatus.class);
		//RegisterStatus status = rt.postForObject(url, requestEntity, RegisterStatus.class);
		ResponseEntity<RegisterStatus> response = rt.postForEntity(url, requestEntity, RegisterStatus.class);
		RegisterStatus status = response.getBody();

		System.out.println(response.getStatusCode());
		System.out.println(status.getStatusMessage());

	}

	@Test
	void registerV3() {

		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		String url = "http://localhost:8080/customer/registerV3";

		String customerData = "{\n"
				+ "    \"name\" : \"John\",\n"
				+ "    \"email\" : \"john@gmail.com\",\n"
				+ "    \"password\" : \"john123\",\n"
				+ "    \"address\" : {\n"
				+ "        \"city\" : \"Mumbai\",\n"
				+ "        \"pincode\" : 40001\n"
				+ "    }\n"
				+ "}";

		Resource profilePic = new ClassPathResource("sai.jpg");

		//Map<String, Object> map = new HashMap<>();
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("customer", customerData);
		map.add("profilePic", profilePic);

		HttpEntity<LinkedMultiValueMap<String,Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String,Object>>(map, headers);

		ResponseEntity<String> response = rt.postForEntity(url, requestEntity, String.class);
		System.out.println(response.getBody());



	}

}
