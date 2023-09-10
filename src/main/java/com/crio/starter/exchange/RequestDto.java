package com.crio.starter.exchange;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RequestDto {

    @JsonIgnore
    private String id;

    @JsonProperty("Name")
    private String name;
    @JsonProperty("caption")
    private String caption;
    @JsonProperty("URL")
    private String URL;
}
