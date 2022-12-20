package org.codemash.runnerz.beans;

import org.springframework.web.client.RestTemplate;

public class TrackService {

    private final RestTemplate restTemplate;

    public TrackService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
