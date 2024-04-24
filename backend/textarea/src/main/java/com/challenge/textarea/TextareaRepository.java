package com.challenge.textarea;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextareaRepository extends CosmosRepository<AreaRecord, String> {
}
