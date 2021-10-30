package cn.nightwee.core.service;

import cn.nightwee.core.pojo.specification.Specification;
import entity.PageResult;

/**
 * 规格管理
 */
public interface SpecificationService {


    PageResult search(Integer page, Integer rows, Specification specification);
}
