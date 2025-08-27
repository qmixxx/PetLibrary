package io.reflectoring.library;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


//@SpringBootTest
@Epic("Test Coverage")
@Feature("Smoke Test Feature")
class LibraryApplicationTests {

    @Test
    @Story("Smoke Test story")
    @Description("This is a simple test to check Allure report integration")
    @Severity(SeverityLevel.CRITICAL)
    public void testIfAllureIntegrationWorksSuccessfully() {
        int result = 2 + 3;
        Assertions.assertEquals(5, result, "Error result");
        System.out.println("Allure Integration Works Successfully");
    }
}
