package cn.nightwee.core.controller;

import cn.nightwee.core.pojo.template.TypeTemplate;
import cn.nightwee.core.service.TypeTemplateService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
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

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody(required = false) TypeTemplate typeTemplate) {
        return typeTemplateService.search(page, rows, typeTemplate);
    }
}
