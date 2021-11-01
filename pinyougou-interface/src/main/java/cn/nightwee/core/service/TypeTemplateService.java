package cn.nightwee.core.service;

import cn.nightwee.core.pojo.template.TypeTemplate;
import entity.PageResult;

import java.util.List;

public interface TypeTemplateService {


    PageResult search(Integer page, Integer rows, TypeTemplate typeTemplate);

    void add(TypeTemplate typeTemplate);
}
