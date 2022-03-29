package com.jgybzx.mappers;

import com.jgybzx.entity.Region;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RegionMapper {
    List<Region> queryAll();
}