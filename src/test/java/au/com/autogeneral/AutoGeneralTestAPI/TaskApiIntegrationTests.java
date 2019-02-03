package au.com.autogeneral.AutoGeneralTestAPI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TaskApiIntegrationTests {

    @Autowired
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void returnsExpectedMessage_WhenTestingForBalancedBrackets() {
        final String input = "[]{}";
        final ResponseEntity<String> response = testRestTemplate.getForEntity("/tasks/validateBrackets?input={input}", String.class, input);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo(format("{\"input\":\"%s\",\"isBalanced\":true}", input));
    }

    @Test
    public void returnsError_WhenNoInputCharacters() {
        final String input = "";
        final ResponseEntity<String> response = testRestTemplate.getForEntity("/tasks/validateBrackets?input={input}", String.class, input);
        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(response.getBody()).contains("size must be between 1 and 50");
    }

    @Test
    public void returnsError_WhenInputExceedsMaximum() {
        final String input = "123456789_123456789_123456789_123456789_123456789_1";
        final ResponseEntity<String> response = testRestTemplate.getForEntity("/tasks/validateBrackets?input={input}", String.class, input);
        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(response.getBody()).contains("size must be between 1 and 50");
    }

    @Test
    public void returnsExpectedMessage_WhenTestingForUnbalancedBrackets() {
        final String input = "[]}";
        final ResponseEntity<String> response = testRestTemplate.getForEntity("/tasks/validateBrackets?input={input}", String.class, input);
        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(response.getBody()).contains("Unbalanced brackets");
    }

}

