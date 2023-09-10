package com.crio.starter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import java.time.LocalTime;

@EntityScan
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "MemeList")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meme {
    @Field("owner_name")
    private String name;
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private String id;
    @Field("meme_caption")
    private String caption;
    @Field("meme-url")
    private String URL;

    @Field("meme_creation_time")
    @CreatedDate
    private LocalDateTime creationDate;

}