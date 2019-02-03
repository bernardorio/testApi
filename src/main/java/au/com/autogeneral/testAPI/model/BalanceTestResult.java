package au.com.autogeneral.testAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BalanceTestResult {

    @ApiModelProperty(dataType = "string", example  = "[(]")
    private final String input;
    @ApiModelProperty(dataType = "boolean", example  = "false")
    private final Boolean isBalanced;

    public BalanceTestResult(final String input, final Boolean isBalanced) {
        this.input = input;
        this.isBalanced = isBalanced;
    }

    public String getInput() {
        return input;
    }

    @JsonProperty("isBalanced")
    public Boolean isBalanced() {
        return isBalanced;
    }
}
