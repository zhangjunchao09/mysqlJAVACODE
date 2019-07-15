package ${pakage}.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${lowclassName}Model {

<#list typeMap?keys as key>
    private ${typeMap[key]} ${key};
</#list>

}
