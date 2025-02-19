package ru.app.rest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.app.rest.dto.User;

import java.util.List;

@Service
@Slf4j
public class RestTemplateServiceImpl implements RestTemplateService {
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final HttpHeaders headers = new HttpHeaders();

    public RestTemplateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.baseUrl = "http://94.198.50.185:7081/api/users";
        setCookie();
    }

    private void setCookie() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        List<String> cookies = response.getHeaders().get("Set-Cookie");
        if (cookies != null) {
            this.headers.set("Cookie", cookies.get(0));
            log.info("Set cookie successfully");
        } else {
            log.error("No cookie found");
        }
    }

    @Override
    public String saveUser(User user){
        HttpEntity<User> request = new HttpEntity<>(user, this.headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
        return response.getBody();
    }

    @Override
    public String updateUser(User user){
        HttpEntity<User> request = new HttpEntity<>(user, this.headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.PUT, request, String.class);
        return response.getBody();
    }

    @Override
    public String deleteUser(Long id){
        HttpEntity<User> request = new HttpEntity<>(this.headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE, request,
                String.class);
        return response.getBody();
    }
}
