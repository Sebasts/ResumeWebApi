package io.hellsing.tokenservice.service;

import org.springframework.stereotype.Service;

@Service
public interface GoogleTokenVerifierService {

	boolean verifyOauthToken(String token);
}
