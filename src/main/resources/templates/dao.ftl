package ${pakage}.dao;

import ${pakage}.po.${className}Po;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("${lowclassName}")
@Mapper
public interface ${className} {

    int insert${className}(${className}Po ${lowclassName}Po);

    int update${className}(${className}Po ${lowclassName}Po);

    int delete${className}(@Param("pkId") String pkId);

    ${className}Po get${className}(@Param("pkId") String pkId);

    List<${className}Po> get${className}s();

}