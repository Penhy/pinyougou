package cn.nightwee.core.service;

import cn.nightwee.core.dao.specification.SpecificationDao;
import cn.nightwee.core.pojo.specification.Specification;
import cn.nightwee.core.pojo.specification.SpecificationQuery;
import cn.nightwee.core.pojo.specification.SpecificationQuery.Criteria;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 规格管理
 * @author Penhy
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService{

    @Autowired
    private SpecificationDao specificationDao;

    @Override
    public PageResult search(Integer page, Integer rows, Specification specification) {
        //分页插件
        PageHelper.startPage(page,rows);
        // 判断是否有条件需要查询
        SpecificationQuery specificationQuery = new SpecificationQuery();
        if (null != specification) {
            Criteria criteria = specificationQuery.createCriteria();
            if (null != specification.getSpecName() && !"".equals(specification.getSpecName().trim())) {
                criteria.andSpecNameLike("%" + specification.getSpecName().trim() + "%");
            }
        }
        //查询
        Page<Specification> p = (Page<Specification>) specificationDao.selectByExample(specificationQuery);

        return new PageResult(p.getTotal(),p.getResult());
    }
}
