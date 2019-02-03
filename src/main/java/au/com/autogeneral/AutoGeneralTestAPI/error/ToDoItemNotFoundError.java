package au.com.autogeneral.AutoGeneralTestAPI.error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import static java.util.Arrays.asList;

@ApiModel
public class ToDoItemNotFoundError {

    @ApiModelProperty(dataType = "string", example  = "NotFoundError")
    private String name = "NotFoundError";

    private List<ToDoItemNotFoundError.NotFoundErrorDetails> details;

    public ToDoItemNotFoundError(String message) {
        this.details = asList(new ToDoItemNotFoundError.NotFoundErrorDetails(message));
    }

    public List<ToDoItemNotFoundError.NotFoundErrorDetails> getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }

    public class NotFoundErrorDetails {

        @ApiModelProperty
        public String message;

        public NotFoundErrorDetails(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
