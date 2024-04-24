package com.challenge.textarea;

import com.azure.spring.data.cosmos.core.mapping.Container;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "content", ru = "400")
public class AreaRecord {
    @Id
    private String id;
    private String content;

}
