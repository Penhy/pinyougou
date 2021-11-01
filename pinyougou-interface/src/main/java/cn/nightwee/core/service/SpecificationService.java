package cn.nightwee.core.service;

import cn.nightwee.core.pojo.specification.Specification;
import entity.PageResult;
import pojogroup.SpecificationVo;

import java.util.List;
import java.util.Map;

/**
 * 规格管理
 */
public interface SpecificationService {


    PageResult search(Integer page, Integer rows, Specification specification);

    void add(SpecificationVo vo);

    SpecificationVo findOne(Long id);

    void update(SpecificationVo vo);

    void delete(Long[] ids);

    List<Map> selectOptionList();
}
