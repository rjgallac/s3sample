package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
@Slf4j
public class S3ClientSample {
    public static final String ROB_TEST_BUCKET_123 = "rob-test-bucket123";
    public static final String FILE_NAME = "readme.txt";
    private final S3Client s3Client;

    S3ClientSample(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void readFile() throws IOException {
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(
                request -> request.bucket("rob-test-bucket123").key("readme.txt"));

        String fileContent = StreamUtils.copyToString(response, StandardCharsets.UTF_8);

        System.out.println(fileContent);
    }

    public void list(){
        ListBucketsResponse listBucketsResponse = s3Client.listBuckets();
        System.out.printf("asd");
    }


    public void writeFile() throws IOException {
        String fileName = FILE_NAME;

        S3Client client = S3Client.builder().region(Region.EU_WEST_1).build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(ROB_TEST_BUCKET_123).key(fileName).build();
        ClassLoader classLoader = getClass().getClassLoader();
        client.putObject(request, RequestBody.fromFile(new File(classLoader.getResource(fileName).getFile())));
        System.out.println("writing");
    }

    public void deleteFile() {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(ROB_TEST_BUCKET_123)
                .key(FILE_NAME)
                .build();

        s3Client.deleteObject(request);    }
}
