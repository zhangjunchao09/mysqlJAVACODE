package ${pakage}.service.iml;

import ${pakage}.common.util.CopyUtils;
import ${pakage}.model.${className}Model;
import ${pakage}.service.I${className}Service;
import ${pakage}.dto.${className}Dto;
import ${pakage}.mapper.${className}Mapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service("${lowclassName}Service")
public class ${className}Service implements I${className}Service {

    @Resource(name="${lowclassName}Mapper")
    ${className}Mapper ${lowclassName}Mapper;

    @Override
    public boolean insert${className}(${className}Dto ${lowclassName}Dto) {
        if (null == ${lowclassName}Dto) {
            return false;
        }
        boolean flag = true;
        ${className}Model ${lowclassName}Model = new ${className}Model();
        CopyUtils.copyPropertys(${lowclassName}Dto, ${lowclassName}Model);
        int n = ${lowclassName}Mapper.insert${className}(${lowclassName}Model);
        if (n <= 0) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean update${className}(${className}Dto ${lowclassName}Dto) {
         if (null == ${lowclassName}Dto) {
             return false;
         }
         boolean flag = true;
         ${className}Model ${lowclassName}Model = new ${className}Model();
         CopyUtils.copyPropertys(${lowclassName}Dto, ${lowclassName}Model);
         int n = ${lowclassName}Mapper.update${className}(${lowclassName}Model);
         if (n <= 0) {
              flag = false;
         }
         return flag;
    }

    @Override
    public boolean delete${className}(String ${primaryKey}) {
        if (null == ${primaryKey} || "".equals(${primaryKey})) {
            return false;
        }
        boolean flag = true;
        int n = ${lowclassName}Mapper.delete${className}(${primaryKey});
        if (n <= 0) {
             flag = false;
        }
        return flag;
    }

    @Override
    public ${className}Dto get${className}(String ${primaryKey}) {
         if (null == ${primaryKey} || "".equals(${primaryKey})) {
             return null;
         }
         ${className}Model ${lowclassName}Model = ${lowclassName}Mapper.get${className}(${primaryKey});
         ${className}Dto ${lowclassName}Dto = new ${className}Dto();
         CopyUtils.copyPropertys(${lowclassName}Model, ${lowclassName}Dto);
         return ${lowclassName}Dto;
    }

    @Override
    public List<${className}Dto> get${className}s() {
        List<${className}Model> ${lowclassName}Models = ${lowclassName}Mapper.get${className}s();
        List<${className}Dto> ${lowclassName}Dtos = new ArrayList<>();
        for (${className}Model ${lowclassName}Model: ${lowclassName}Models) {
            ${className}Dto ${lowclassName}Dto = new ${className}Dto();
            CopyUtils.copyPropertys(${lowclassName}Model, ${lowclassName}Dto);
            ${lowclassName}Dtos.add(${lowclassName}Dto);
        }
        return ${lowclassName}Dtos;
    }

}
