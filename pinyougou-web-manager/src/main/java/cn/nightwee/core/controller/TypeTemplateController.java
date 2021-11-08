package cn.nightwee.core.controller;

import cn.nightwee.core.pojo.template.TypeTemplate;
import cn.nightwee.core.service.TypeTemplateService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模块管理
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

    @Reference
    private TypeTemplateService typeTemplateService;

    /**
     * 分页搜索
     */
    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody(required = false) TypeTemplate typeTemplate) {
        return typeTemplateService.search(page, rows, typeTemplate);
    }

    /**
     * 添加
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TypeTemplate typeTemplate) {
        try {
            typeTemplateService.add(typeTemplate);
            return new Result(true, "保存成功");
        }catch (Exception e) {
            e.getStackTrace();
            return new Result(false, "保存失败");
        }
    }

    /**
     * 查询一个
     */
    @RequestMapping("/findOne")
    public TypeTemplate findOne(Long id) {
        return typeTemplateService.findOne(id);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TypeTemplate typeTemplate) {
        try {
            typeTemplateService.update(typeTemplate);
            return new Result(true, "修改成功");
        }catch (Exception e) {
            e.getStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            typeTemplateService.delete(ids);
            return new Result(true, "删除成功");
        }catch (Exception e) {
            e.getStackTrace();
            return new Result(false, "删除失败");
        }
    }
}
