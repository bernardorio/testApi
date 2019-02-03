package au.com.autogeneral.testAPI.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;

public class ToDoItemUpdateRequest {

    @Size(min=1, max=50, message="Must be between 1 and 50 chars long")
    @ApiModelProperty(dataType = "string", example  = "Uulwi ifis halahs gag erh'ongg w'ssh.")
    private String text;

    @ApiModelProperty(dataType = "boolean", example  = "true")
    private boolean isCompleted;

    public ToDoItemUpdateRequest() {
    }

    public ToDoItemUpdateRequest(String text, boolean isCompleted) {
        this.text = text;
        this.isCompleted = isCompleted;
    }

    public String getText() {
        return text;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
