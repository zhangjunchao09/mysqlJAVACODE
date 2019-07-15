package ${pakage}.service.iml;

import ${pakage}.common.util.CopyUtils;
import ${pakage}.po.${className}Po;
import ${pakage}.service.I${className}Service;
import ${pakage}.model.${className}Model;
import ${pakage}.dao.${className}Dao;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service("${lowclassName}Service")
public class ${className}Service implements I${className}Service {

    @Resource(name="${lowclassName}Dao")
    ${className}Dao ${lowclassName}Dao;

    @Override
    public boolean insert${className}(${className}Model ${lowclassName}Model) {
        if (null == ${lowclassName}Model) {
            return false;
        }
        boolean flag = true;
        ${className}Po ${lowclassName}Po = new ${className}Po();
        CopyUtils.copyPropertys(${lowclassName}Model, ${lowclassName}Po);
        int n = ${lowclassName}Dao.insert${className}(${lowclassName}Po);
        if (n <= 0) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean update${className}(${className}Model ${lowclassName}Model) {
         if (null == ${lowclassName}Model) {
             return false;
         }
         boolean flag = true;
         ${className}Po ${lowclassName}Po = new ${className}Po();
         CopyUtils.copyPropertys(${lowclassName}Model, ${lowclassName}Po);
         int n = ${lowclassName}Dao.update${className}(${lowclassName}Po);
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
        int n = ${lowclassName}Dao.delete${className}(${primaryKey});
        if (n <= 0) {
             flag = false;
        }
        return flag;
    }

    @Override
    public ${className}Model get${className}(String ${primaryKey}) {
         if (null == ${primaryKey} || "".equals(${primaryKey})) {
             return null;
         }
         ${className}Po ${lowclassName}Po = ${lowclassName}Dao.get${className}(${primaryKey});
         ${className}Model ${lowclassName}Model = new ${className}Model();
         CopyUtils.copyPropertys(${lowclassName}Po, ${lowclassName}Model);
         return ${lowclassName}Model;
    }

    @Override
    public List<${className}Model> get${className}s() {
        List<${className}Po> ${lowclassName}Pos = ${lowclassName}Dao.get${className}s();
        List<${className}Model> ${lowclassName}Models = new ArrayList<>();
        for (${className}Po ${lowclassName}Po: ${lowclassName}Pos) {
            ${className}Model ${lowclassName}Model = new ${className}Model();
            CopyUtils.copyPropertys(${lowclassName}Po, ${lowclassName}Model);
            ${lowclassName}Models.add(${lowclassName}Model);
        }
        return ${lowclassName}Models;
    }

}
