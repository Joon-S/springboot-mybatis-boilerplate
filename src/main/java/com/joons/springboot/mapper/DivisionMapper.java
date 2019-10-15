package com.joons.springboot.mapper;

import com.joons.springboot.model.Division;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DivisionMapper {

    Division findById(long id);
    void save(Division division);
    void update(Division division);
    void delete(long id);
}
