package au.com.autogeneral.AutoGeneralTestAPI.controllers;

import au.com.autogeneral.AutoGeneralTestAPI.dto.ToDoItemAddRequest;
import au.com.autogeneral.AutoGeneralTestAPI.dto.ToDoItemUpdateRequest;
import au.com.autogeneral.AutoGeneralTestAPI.error.ToDoItemNotFoundError;
import au.com.autogeneral.AutoGeneralTestAPI.error.ToDoItemValidationError;
import au.com.autogeneral.AutoGeneralTestAPI.model.TodoItem;
import au.com.autogeneral.AutoGeneralTestAPI.repository.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.IOException;

import static java.lang.Long.valueOf;
import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/todo")
@Api(tags = "todo", description="To Do List endpoints")
@Validated
public class TodoController {

    private static final Logger logger = getLogger(TodoController.class);

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Validation error", response = ToDoItemValidationError.class),
            @ApiResponse(code = 404, message = "Not Found Error", response = ToDoItemNotFoundError.class)
    })
    @GetMapping("/{id}")
    public TodoItem getTodoItem(
            @ApiParam(value = "id", type = "number", required = true)
            @PathVariable("id") @Valid @Size(min=1, max=50, message="Must be between 1 and 50 chars long") String id) throws Exception {
        TodoItem todoItem = todoRepository.findById(valueOf(id)).orElseThrow(() -> {
            logger.info("Attempt to retrieve TodoItem with invalid id: {}", id);
            return new ResourceNotFoundException(format("Item with %s not found", id));
        });
        logger.debug("Request made for resource id: {}", id);
        return todoItem;
    }

    @ApiOperation(value = "Create a to do item")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Validation Error", response = ToDoItemValidationError.class)
    })
    @PostMapping
    public TodoItem createTodoItem(@RequestBody @Valid ToDoItemAddRequest body) {
        TodoItem todoItem = modelMapper.map(body, TodoItem.class);
        TodoItem createdTodoItem = todoRepository.save(todoItem);
        logger.debug("Request made for creating resource with content: {}", createdTodoItem);
        return createdTodoItem;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Validation error", response = ToDoItemValidationError.class),
            @ApiResponse(code = 404, message = "Not Found Error", response = ToDoItemNotFoundError.class)
    })
    @ApiOperation(value = "Modify an item")
    @PatchMapping("/{id}")
    public TodoItem updateTodoItem(@ApiParam(value = "id", required = true) @PathVariable("id") String id,
                                   @RequestBody @Valid ToDoItemUpdateRequest body) throws IOException {
        logger.debug("Request made for updating resource with id: {}, content: {}", id, body);
        TodoItem todoItemItemToBePatched = todoRepository.findById(valueOf(id)).orElseThrow(() -> {
            logger.info("Attempt to patch TodoItem with invalid id: {}", id);
            return new ResourceNotFoundException(format("Item with %s not found", id));
        });
        modelMapper.map(body, todoItemItemToBePatched);
        TodoItem patchedTodoItem = todoRepository.save(todoItemItemToBePatched);

        logger.debug("TodoItem resource updated: {}", patchedTodoItem);
        return patchedTodoItem;
    }

}
