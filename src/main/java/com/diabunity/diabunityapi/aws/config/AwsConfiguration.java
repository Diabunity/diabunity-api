package com.diabunity.diabunityapi.aws.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Configuration;

import static com.amazonaws.regions.Regions.US_EAST_1;

@Configuration
public class AwsConfiguration {

    public AmazonS3 getAwsS3Config() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(US_EAST_1)
                .build();
    }

}
