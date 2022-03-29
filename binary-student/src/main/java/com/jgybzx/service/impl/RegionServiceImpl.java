package com.jgybzx.service.impl;

import com.jgybzx.mappers.RegionMapper;
import com.jgybzx.entity.Region;
import com.jgybzx.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * des:
 *
 * @author guojy
 * @date 2021/6/10 17:49
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper mapper;

    @Override
    public List<Region> queryAll() {
        return mapper.queryAll();
    }

}
