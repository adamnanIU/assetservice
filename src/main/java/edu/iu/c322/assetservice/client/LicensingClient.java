package edu.iu.c322.assetservice.client;

import edu.iu.c322.assetservice.model.License;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class LicensingClient {

    private RestTemplate restTemplate;

    public LicensingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<License> getOrganization(int organizationId){
        ResponseEntity<License> restExchange =
                restTemplate.exchange(
                        "http://organization-service/organizations/{organizationId}",
                        HttpMethod.GET,
                        null, License.class, organizationId);

        return Optional.ofNullable(restExchange.getBody());
}
