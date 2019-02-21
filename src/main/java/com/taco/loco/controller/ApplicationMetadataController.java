package com.taco.loco.controller;

import com.taco.loco.service.dto.ApplicationMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiRoute.BASE_MAPPING)
public class ApplicationMetadataController {

    @Value("${build.version}")
    private String serviceVersion;

    @Value("${api.version}")
    private String apiVersion;

    @RequestMapping(value = ApiRoute.METADATA, method = {RequestMethod.GET})
    public ResponseEntity<ApplicationMetadata> getApplicationMetadata() {
        ApplicationMetadata applicationMetadata = new ApplicationMetadata();
        applicationMetadata.setServiceVersion(serviceVersion);
        applicationMetadata.setApiVersion(apiVersion);
        return new ResponseEntity<>(applicationMetadata, HttpStatus.OK);
    }

}
