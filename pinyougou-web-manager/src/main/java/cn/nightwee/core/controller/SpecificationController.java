package cn.nightwee.core.controller;


import cn.nightwee.core.pojo.specification.Specification;
import cn.nightwee.core.service.SpecificationService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 规格管理
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference
    private SpecificationService specificationService;

    /**
     * 分页搜索 带条件
     */
    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows , @RequestBody(required = false)Specification specification) {
        return specificationService.search(page, rows, specification);
    }
}
