package au.com.autogeneral.testAPI.controllers;

import au.com.autogeneral.testAPI.dto.ToDoItemAddRequest;
import au.com.autogeneral.testAPI.dto.ToDoItemUpdateRequest;
import au.com.autogeneral.testAPI.error.ToDoItemNotFoundError;
import au.com.autogeneral.testAPI.error.ToDoItemValidationError;
import au.com.autogeneral.testAPI.model.TodoItem;
import au.com.autogeneral.testAPI.repository.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
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
import java.io.IOException;

import static java.lang.String.format;

@RestController
@RequestMapping("/todo")
@Api(tags = "todo", description="To Do List endpoints")
@Validated
public class TodoController {

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
            @PathVariable("id") @Valid Long id) throws Exception {
        return todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(format("Item with %s not found", id)));
    }

    @ApiOperation(value = "Create a to do item")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Validation Error", response = ToDoItemValidationError.class)
    })
    @PostMapping
    public TodoItem createTodoItem(@RequestBody @Valid ToDoItemAddRequest body) {
        final TodoItem todoItem = modelMapper.map(body, TodoItem.class);
        return todoRepository.save(todoItem);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Validation error", response = ToDoItemValidationError.class),
            @ApiResponse(code = 404, message = "Not Found Error", response = ToDoItemNotFoundError.class)
    })
    @ApiOperation(value = "Modify an item")
    @PatchMapping("/{id}")
    public TodoItem updateTodoItem(@ApiParam(value = "id", required = true) @PathVariable("id") Long id,
                                   @RequestBody ToDoItemUpdateRequest body) throws IOException {
        final TodoItem todoItemItemToBePatched = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(format("Item with %s not found", id)));;
        modelMapper.map(body, todoItemItemToBePatched);
        return todoRepository.save(todoItemItemToBePatched);
    }

}
