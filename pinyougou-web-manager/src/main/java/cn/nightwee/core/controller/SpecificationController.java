package cn.nightwee.core.controller;


import cn.nightwee.core.pojo.specification.Specification;
import cn.nightwee.core.service.SpecificationService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojogroup.SpecificationVo;

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

    /**
     * 规格添加
     */
    @RequestMapping("/add")
    public Result add(@RequestBody SpecificationVo vo) {
        try {
            specificationService.add(vo);
            return new Result(true, "增加成功");
        }catch (Exception e) {
            e.getStackTrace();
            return new Result(true, "增加失败");
        }
    }

    /**
     * 查询一个规格
     */
    @RequestMapping("/findOne")
    public SpecificationVo findOne(Long id) {
        return specificationService.findOne(id);
    }
}
