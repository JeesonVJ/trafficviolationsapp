package com.jeeson.trafficviolationsmonitor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
public class PublishSNS implements Alerter {

	@Value("${aws.sns.topicArn}")
	private String topicArn;
	
	@Value("${aws.trafficviolationsmonitor.accessKey}")
	private String accesskey;
	
	@Value("${aws.trafficviolationsmonitor.secretKey}")
	private String secretKey;
	
    public void alert(String message) {

        try {
        	AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accesskey,secretKey);
        	
        	SnsClient snsClient = SnsClient.builder()
                    .region(Region.US_EAST_2)
                    .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                    .build();
        	System.out.println(topicArn);
        	System.out.println(accesskey);
        	System.out.println(secretKey);
        	PublishRequest request = PublishRequest.builder()
                .message(message)
                .topicArn(topicArn)
                .build();

            PublishResponse result = snsClient.publish(request);
            System.out.println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

         } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
         }
    }
	
}
