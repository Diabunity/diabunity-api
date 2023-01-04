package com.diabunity.diabunityapi.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.diabunity.diabunityapi.aws.config.AwsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;

import static org.apache.commons.codec.binary.Base64.decodeBase64;

@Service
public class UploadFileService {

    private static String BUCKET_NAME = System.getenv("S3_BUCKET_NAME");

    @Autowired
    private AwsConfiguration awsConfiguration;

    public String base64(String base64Data, String id) {
        LocalDateTime now = LocalDateTime.now();

        String path = "resources/images/" + now.getYear() + "/" + now.getMonthValue() + "/" + id + now + ".png";

        byte[] bI = decodeBase64((base64Data.substring(base64Data.indexOf(",") + 1))
                .getBytes());

        InputStream fis = new ByteArrayInputStream(bI);

        AmazonS3 s3 = awsConfiguration.getAwsS3Config();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bI.length);
        metadata.setContentType("image/png");
        metadata.setCacheControl("public, max-age=31536000");
        s3.putObject(BUCKET_NAME, path, fis, metadata);
        s3.setObjectAcl(BUCKET_NAME, path, CannedAccessControlList.PublicRead);
        return path;
    }
}
