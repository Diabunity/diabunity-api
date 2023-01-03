package com.diabunity.diabunityapi.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.amazonaws.regions.Regions.US_EAST_1;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

@Service
public class UploadFileService {

    private static String BUCKET_NAME = "elasticbeanstalk-us-east-1-999156988530";

    public void base64(String base64Data, String filename) {
        byte[] bI = decodeBase64((base64Data.substring(base64Data.indexOf(",") + 1))
                .getBytes());

        InputStream fis = new ByteArrayInputStream(bI);

        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(US_EAST_1)
                .build();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bI.length);
        metadata.setContentType("image/png");
        metadata.setCacheControl("public, max-age=31536000");
        s3.putObject(BUCKET_NAME, filename, fis, metadata);
        s3.setObjectAcl(BUCKET_NAME, filename, CannedAccessControlList.PublicRead);
    }
}
