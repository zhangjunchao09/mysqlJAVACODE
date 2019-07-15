package ${pakage}.dao;

import ${pakage}.po.${className}Po;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("${lowclassName}Dao")
@Mapper
public interface ${className} {

    int insert${className}(${className}Po ${lowclassName}Po);

    int update${className}(${className}Po ${lowclassName}Po);

    int delete${className}(@Param("${primaryKey}") String ${primaryKey});

    ${className}Po get${className}(@Param("${primaryKey}") String ${primaryKey});

    List<${className}Po> get${className}s();

}