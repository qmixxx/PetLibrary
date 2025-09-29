package io.reflectoring.library.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TestSecrets {
    @JsonProperty("math")
    private Map<String, Integer> math;
    @JsonProperty("A")
    private int A;
    @JsonProperty("B")
    private int B;
}
