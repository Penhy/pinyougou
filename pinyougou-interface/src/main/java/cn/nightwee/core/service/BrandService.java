package cn.nightwee.core.service;

import cn.nightwee.core.pojo.good.Brand;
import entity.PageResult;

import java.util.List;

/**
 * 品牌管理
 */
public interface BrandService {

    List<Brand> findAll();

    PageResult findPage(Integer pageNum, Integer pageSize);

    void add(Brand brand);

    Brand findOne(Long id);

    void update(Brand brand);

    void delete(Long[] ids);
}
