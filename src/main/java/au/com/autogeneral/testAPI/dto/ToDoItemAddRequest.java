package au.com.autogeneral.testAPI.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;

@ApiModel
public class ToDoItemAddRequest {

    @Size(min=1, max=50, message="Must be between 1 and 50 chars long")
    @ApiModelProperty(dataType = "string", example  = "Uulwi ifis halahs gag erh'ongg w'ssh.")
    private String text;

    public ToDoItemAddRequest(final String text) {
        this.text = text;
    }

    private ToDoItemAddRequest() {
    }

    public String getText() {
        return text;
    }
}
