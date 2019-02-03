package au.com.autogeneral.testAPI;

import au.com.autogeneral.testAPI.dto.ToDoItemUpdateRequest;
import au.com.autogeneral.testAPI.model.TodoItem;
import au.com.autogeneral.testAPI.repository.TodoRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TodoApiIntegrationTests {

    @Autowired
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Autowired
    TodoRepository todoRepository;

    @After
    public void afterEach(){
        todoRepository.deleteAll();
    }

    @Test
    public void shouldReturnTodoItem_GivenId() {
        final TodoItem savedItem = todoRepository.save(new TodoItem("Lorem ipsum"));
        final ResponseEntity<String> response = testRestTemplate.getForEntity("/todo/"+savedItem.getId(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).contains("\"text\":\"Lorem ipsum\"");
    }

    @Test
    public void shouldReturnError_GivenInvalidTodoItemId() {
        final ResponseEntity<String> response = testRestTemplate.getForEntity("/todo/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
        assertThat(response.getBody()).contains("\"message\":\"Item with 1 not found\"");
    }

    @Test
    public void shouldCreateATodoItem() {
        final ResponseEntity<TodoItem> todoResponse = testRestTemplate.postForEntity("/todo", new TodoItem("Lorem ipsum"), TodoItem.class);
        final TodoItem persistedTodoItemItem = getTodoItemFromRepository(todoResponse.getBody());
        assertThat(todoResponse.getStatusCode()).isEqualTo(OK);
        assertThat(persistedTodoItemItem.getText()).isEqualTo("Lorem ipsum");
    }

    @Test
    public void shouldReturnError_WhenCreatingInvalidTodoItem() {
        final ResponseEntity<String> todoResponse = testRestTemplate.postForEntity("/todo", new TodoItem(""), String.class);
        assertThat(todoResponse.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(todoResponse.getBody()).contains("\"msg\":\"Must be between 1 and 50 chars long\"");
    }

    @Test
    public void shouldUpdateATodoItem() throws JSONException {
        final TodoItem savedItem = todoRepository.save(new TodoItem("Lorem ipsum"));
        assertThat(savedItem.getText()).isEqualTo("Lorem ipsum");

        ToDoItemUpdateRequest propertiesToUpdate = new ToDoItemUpdateRequest();
        propertiesToUpdate.setText("foo bar");

        final TodoItem todoItemResponse = testRestTemplate.patchForObject(format("/todo/%s", savedItem.getId()), propertiesToUpdate, TodoItem.class);

        final TodoItem patchedTodoItemItem = getTodoItemFromRepository(todoItemResponse);
        assertThat(patchedTodoItemItem.getText()).isEqualTo("foo bar");
    }

    private TodoItem getTodoItemFromRepository(final TodoItem todoItemResponse) {
        return todoRepository
                .findById(todoItemResponse.getId().longValue())
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

}

