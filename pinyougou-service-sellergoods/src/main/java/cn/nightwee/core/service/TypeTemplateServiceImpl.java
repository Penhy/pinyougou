package cn.nightwee.core.service;

import cn.nightwee.core.dao.template.TypeTemplateDao;
import cn.nightwee.core.pojo.template.TypeTemplate;
import cn.nightwee.core.pojo.template.TypeTemplateQuery;
import cn.nightwee.core.pojo.template.TypeTemplateQuery.Criteria;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 模板管理
 * @author Penhy
 */
@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService{

    @Autowired
    private TypeTemplateDao typeTemplateDao;

    @Override
    public PageResult search(Integer page, Integer rows, TypeTemplate typeTemplate) {
        //分页插件
        PageHelper.startPage(page, rows);
        PageHelper.orderBy("id desc");
        // 判断是否有条件需要查询
        TypeTemplateQuery typeTemplateQuery = new TypeTemplateQuery();
        if (null != typeTemplate) {
            Criteria criteria = typeTemplateQuery.createCriteria();
            if (null != typeTemplate.getName() && !"".equals(typeTemplate.getName().trim())) {
                criteria.andNameLike("%" + typeTemplate.getName().trim() + "%");
            }
        }
        //查询
        Page<TypeTemplate> p = (Page<TypeTemplate>) typeTemplateDao.selectByExample(typeTemplateQuery);

        return new PageResult(p.getTotal(), p.getResult());
    }
}
