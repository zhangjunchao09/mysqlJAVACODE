package ${pakage}.service;

import ${pakage}.model.${className}Model;

import java.util.List;

public interface I${className}Service {

    boolean insert${className}(${className}Model ${lowclassName}Model);

    boolean update${className}(${className}Model ${lowclassName}Model);

    boolean delete${className}(String ${primaryKey});

    ${className}Model get${className}(String ${primaryKey});

    List<${className}Model> get${className}s();

}
