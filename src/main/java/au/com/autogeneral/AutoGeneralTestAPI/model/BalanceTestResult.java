package au.com.autogeneral.AutoGeneralTestAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BalanceTestResult {

    @ApiModelProperty(dataType = "string", example  = "[(]")
    private String input;
    @ApiModelProperty(dataType = "boolean", example  = "false")
    private Boolean isBalanced;

    public BalanceTestResult(String input, Boolean isBalanced) {
        this.input = input;
        this.isBalanced = isBalanced;
    }

    public String getInput() {
        return input;
    }

    @JsonProperty("isBalanced")
    public Boolean getIsBalanced() {
        return isBalanced;
    }
}
