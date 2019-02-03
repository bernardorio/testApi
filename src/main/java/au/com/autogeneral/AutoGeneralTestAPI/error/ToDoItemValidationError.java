package au.com.autogeneral.AutoGeneralTestAPI.error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class ToDoItemValidationError {
    @ApiModelProperty(dataType = "string", example  = "ValidationError")
    private String name = "ValidationError";
    private List<ToDoItemValidationError.ErrorDetails> details;

    public ToDoItemValidationError(List<ToDoItemValidationError.ErrorDetails> details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public List<ToDoItemValidationError.ErrorDetails> getDetails() {
        return details;
    }

    public static class ErrorDetails{

        @ApiModelProperty(example  = "text")
        private String param;
        @ApiModelProperty(example  = "params")
        private String location;
        @ApiModelProperty(example  = "Must be between 1 and 50 chars long")
        private String msg;
        @ApiModelProperty(example  = "")
        private String value;

        public ErrorDetails(String location, String param, String msg, String value) {
            this.location = location;
            this.param = param;
            this.msg = msg;
            this.value = value;
        }

        public String getParam() {
            return param;
        }

        public String getLocation() {
            return location;
        }

        public String getMsg() {
            return msg;
        }

        public String getValue() {
            return value;
        }

    }
}
