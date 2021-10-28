package cn.nightwee.core.service;

import cn.nightwee.core.pojo.good.Brand;
import entity.PageResult;

import java.util.List;

/**
 * 品牌管理
 */
public interface BrandService {

    /**
     * 查询所有
     * @return List<Brand>
     */
    public List<Brand> findAll();

    public PageResult findPage(Integer pageNum, Integer pageSize);
}
