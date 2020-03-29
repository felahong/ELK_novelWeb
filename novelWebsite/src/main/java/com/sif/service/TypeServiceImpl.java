package com.sif.service;

import com.sif.mapper.TypeMapper;
import com.sif.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    TypeMapper typeMapper;

    @Override
    public List<Type> selectAllType() {
        return typeMapper.selectAllType();
    }
}
