package com.arpanm.catalogservice.web;

import com.arpanm.catalogservice.config.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WelcomeController {
    private final ApplicationProperties applicationProperties;

    @GetMapping("/")
    public String welcome() {
        return applicationProperties.getGreeting();
    }
}
