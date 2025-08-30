package io.reflectoring.library;

import io.qameta.allure.*;
import io.reflectoring.library.models.TestSecrets;

import io.reflectoring.library.utils.AllureUtils;
import io.reflectoring.library.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@SpringBootTest
@Slf4j
@Epic("Test Coverage")
@Feature("Smoke Test Feature")
class LibraryApplicationTest {


    @Test
    @Deprecated
    @Story("Smoke Test story")
    @Description("This is a simple test to check Allure report integration")
    @Severity(SeverityLevel.CRITICAL)
    public void testIfAllureIntegrationWorksSuccessfully() {
        TestSecrets secrets = TestUtils.getSecrets();

        log.info("A={}, B={}", secrets.getMath().get("A"), secrets.getMath().get("B"));

        int result = secrets.getMath().get("A") + secrets.getMath().get("B");
        assertEquals(5, result, "Error result");
        System.out.println("Allure Integration Works Successfully");
    }

    @Test
    @Story("Test Allure Report Attachments Story")
    @Severity(SeverityLevel.NORMAL)
    public void TestAllureReportAttachments() {
        AllureUtils.attachText("Info", "Test was started");
        int res = Math.max(2, 3);
        AllureUtils.attachJson("Calc input", "{ \"a\": 2, \"b\": 3 }");
        assertEquals(3, res);
    }
}
