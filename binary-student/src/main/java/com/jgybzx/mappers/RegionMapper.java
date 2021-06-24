package com.jgybzx.mappers;

import com.jgybzx.model.Region;
import com.jgybzx.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RegionMapper {
    List<Region> queryAll();
}