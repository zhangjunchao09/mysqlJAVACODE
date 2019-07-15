package ${pakage}.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${lowclassName}Dto implements Serializable{

<#list typeMap?keys as key>
    private ${typeMap[key]} ${key};
</#list>

}