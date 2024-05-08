package com.tabletech.service;

import com.tabletech.awssqspublishe.utils.AwsClientFactory;
import com.tabletech.awssqspublishe.utils.AwsSqsUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.util.Arrays;
import java.util.List;

@Service
public class AwsUtilsService {
    public String process(String jsonFilePath, String queueName) {
        List<String> jsonS3FilePath = Arrays.stream(jsonFilePath.split((","))).toList();
        SqsClient sqsClient = AwsClientFactory.getSQSClient();
        for (String jsonFile : jsonS3FilePath) {
            AwsSqsUtils.sendMessage(sqsClient, queueName, jsonFile);
        }
        return "Done";
    }
}
