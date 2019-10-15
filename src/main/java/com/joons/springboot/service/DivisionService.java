package com.joons.springboot.service;

import com.joons.springboot.mapper.DivisionMapper;
import com.joons.springboot.model.Division;
import org.springframework.stereotype.Service;

@Service
public class DivisionService {

    private DivisionMapper divisionMapper;

    public DivisionService(DivisionMapper mapper) {
        this.divisionMapper = mapper;
    }

    public Division findById(long id) {
        return divisionMapper.findById(id);
    }

    public long save(Division division) {
        divisionMapper.save(division);
        return division.getId();
    }

    public void update(Division division) {
        divisionMapper.update(division);
    }

    public void delete(long id) {
        divisionMapper.delete(id);
    }
}
