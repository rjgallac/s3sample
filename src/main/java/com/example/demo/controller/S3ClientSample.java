package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class S3ClientSample {
    private final S3Client s3Client;

    S3ClientSample(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void readFile() throws IOException {
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(
                request -> request.bucket("bucket-name").key("file-name.txt"));

        String fileContent = StreamUtils.copyToString(response, StandardCharsets.UTF_8);

        System.out.println(fileContent);
    }

    public void list(){
        ListBucketsResponse listBucketsResponse = s3Client.listBuckets();
        System.out.printf("asd");
    }
}