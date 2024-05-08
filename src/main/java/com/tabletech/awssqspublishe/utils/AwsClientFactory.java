package com.tabletech.awssqspublishe.utils;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

public class AwsClientFactory {
    public static SqsClient getSQSClient() {
        return SqsClient.builder().region(Region.US_WEST_2).build();
    }
}
