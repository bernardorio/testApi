package au.com.autogeneral.testAPI.controllers;

import au.com.autogeneral.testAPI.error.ToDoItemValidationError;
import au.com.autogeneral.testAPI.model.BalanceTestResult;
import au.com.autogeneral.testAPI.validators.HasBalancedBrackets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/tasks")
@Api(tags = "tasks", description="General Algorithmic tasks")
@Validated
public class TasksController {

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Validation error", response = ToDoItemValidationError.class)
    })
    @GetMapping("/validateBrackets")
    @ApiOperation(value = "Checks if brackets in a string are balanced")
    public BalanceTestResult validateBrackets(
            @ApiParam(value="Input string (max length 100)", required = true)
            @Valid() @Size(min=1, max=50) @HasBalancedBrackets
            @RequestParam("input") String input) {
        return new BalanceTestResult(input, true);
    }

}
