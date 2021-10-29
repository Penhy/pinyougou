package cn.nightwee.core.service;

import cn.nightwee.core.pojo.good.Brand;
import entity.PageResult;

import java.util.List;

/**
 * 品牌管理
 */
public interface BrandService {

    public List<Brand> findAll();

    public PageResult findPage(Integer pageNum, Integer pageSize);

    public void add(Brand brand);

    public Brand findOne(Long id);

    public void update(Brand brand);
}
