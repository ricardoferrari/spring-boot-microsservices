//snippet-sourcedescription:[GetItem.java demonstrates how to get an item from a DynamoDB table.]
//snippet-keyword:[Java]
//snippet-sourcesyntax:[java]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon DynamoDB]
//snippet-service:[dynamodb]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[2018-01-15]
//snippet-sourceauthor:[soo-aws]
/*
   Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

   This file is licensed under the Apache License, Version 2.0 (the "License").
   You may not use this file except in compliance with the License. A copy of
   the License is located at

    http://aws.amazon.com/apache2.0/

   This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
   CONDITIONS OF ANY KIND, either express or implied. See the License for the
   specific language governing permissions and limitations under the License.
*/
package com.autopeca.client.utils;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import java.util.HashMap;
import java.util.Map;


public class GetItem
{
    public String item()
    {

        String tableName = "Client";
        String name = "Ricardo";

        HashMap<String,AttributeValue> keyToGet =
            new HashMap<String,AttributeValue>();

        keyToGet.put("Client", new AttributeValue(name));

        GetItemRequest request = null;
            request = new GetItemRequest()
                .withKey(keyToGet)
                .withTableName(tableName);

        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder
        .standard()
        .withCredentials(new ProfileCredentialsProvider("default"))
        .withEndpointConfiguration(
            new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "sa-east-1"))
        .build();

        try {
            Map<String,AttributeValue> returnedItem =
               ddb.getItem(request).getItem();
            if (returnedItem != null) {
                return "ok";
            } else {
                return "vazio";
            }
        } catch (AmazonServiceException e) {
            System.out.println(e.getErrorMessage());
            return "erro";
        }
    }
}