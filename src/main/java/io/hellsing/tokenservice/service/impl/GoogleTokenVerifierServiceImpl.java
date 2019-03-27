package io.hellsing.tokenservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.hellsing.tokenservice.service.GoogleTokenVerifierService;

@Component
public class GoogleTokenVerifierServiceImpl implements GoogleTokenVerifierService {

	private final String TOKENINFO_ENDPOINT = "https://oauth2.googleapis.com/tokeninfo?";
	
	public boolean verifyOauthToken(String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		Map<String, String> args = new HashMap<String, String>();
		args.put("id_token", token);
		String response;
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
		
		try {
			ResponseEntity<String> reviewResponse = restTemplate.exchange(TOKENINFO_ENDPOINT, HttpMethod.GET, entity, String.class, args);
			Map<String, Object> map 
			  = objectMapper.readValue(reviewResponse.getBody(), new TypeReference<Map<String,Object>>(){});
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println(e.getLocalizedMessage());
}

		return true;
	}

}
