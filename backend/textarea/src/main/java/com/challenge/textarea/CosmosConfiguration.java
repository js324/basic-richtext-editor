package com.challenge.textarea;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Configuration
@EnableCosmosRepositories
public class CosmosConfiguration extends AbstractCosmosConfiguration {

    @Value("${cosmos.masterkey}")
    private String masterkey;
    @Value("${cosmos.endpoint}")
    private String endpoint;

    @Autowired
    public CosmosConfiguration(@Value("${cosmos.endpoint}") String endpoint, @Value("${cosmos.masterkey}") String masterkey) {
        this.endpoint = endpoint;
        this.masterkey = masterkey;
    }
    @Bean
    public CosmosClientBuilder getCosmosClientBuilder() {
//        System.out.println("testing " + environment.getRequiredProperty("cosmos_endpoint"));
        return new CosmosClientBuilder()
                .endpoint(endpoint)
                .key(masterkey);

    }
    @Override
    protected String getDatabaseName() {
        return "textarea";
    }
}