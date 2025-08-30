package io.reflectoring.library.utils;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import io.reflectoring.library.models.TestSecrets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
public class TestUtils {

    public static TestSecrets getSecrets() {
        TestSecrets secrets = new TestSecrets();

        log.info("Reading secrets");

        try (InputStream is = TestUtils.class.getResourceAsStream("/secrets.yaml")) {
            if (is != null) {
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                secrets = mapper.readValue(is, TestSecrets.class);
                return secrets;
            } else {
                log.warn("secrets.yaml file not found.");
            }
        } catch (Exception e) {
            log.error("Error reading secrets.yaml", e);
        };

        log.info("Reading secrets from Env");

        secrets.setA(Integer.parseInt(System.getenv("SECRET_A")));
        secrets.setB(Integer.parseInt(System.getenv("SECRET_B")));
        return secrets;
    }
};