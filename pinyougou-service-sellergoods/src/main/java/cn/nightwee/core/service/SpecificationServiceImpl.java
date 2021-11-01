package cn.nightwee.core.service;

import cn.nightwee.core.dao.specification.SpecificationDao;
import cn.nightwee.core.dao.specification.SpecificationOptionDao;
import cn.nightwee.core.pojo.specification.Specification;
import cn.nightwee.core.pojo.specification.SpecificationOption;
import cn.nightwee.core.pojo.specification.SpecificationOptionQuery;
import cn.nightwee.core.pojo.specification.SpecificationQuery;
import cn.nightwee.core.pojo.specification.SpecificationQuery.Criteria;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pojogroup.SpecificationVo;

import java.util.List;
import java.util.Map;

/**
 * 规格管理
 * @author Penhy
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService{

    @Autowired
    private SpecificationDao specificationDao;
    @Autowired
    private SpecificationOptionDao specificationOptionDao;

    /**
     * 分页搜索 带条件
     */
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

    /**
     * 规格添加
     */
    @Override
    public void add(SpecificationVo vo) {
        //规格表   返回id
        specificationDao.insertSelective(vo.getSpecification());
        //规格选项结果集
        List<SpecificationOption> specificationOptionList = vo.getSpecificationOptionList();
        for (SpecificationOption specificationOption : specificationOptionList) {
            //外键
            specificationOption.setSpecId(vo.getSpecification().getId());
            //保存
            specificationOptionDao.insertSelective(specificationOption);
        }
    }

    /**
     * 查询一个规格
     */
    @Override
    public SpecificationVo findOne(Long id) {
        SpecificationVo vo = new SpecificationVo();
        //规格表
        Specification specification = specificationDao.selectByPrimaryKey(id);
        vo.setSpecification(specification);
        //规格选项结果集
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        query.createCriteria().andSpecIdEqualTo(id);
        query.setOrderByClause("orders desc");        //排序query
        List<SpecificationOption> specificationOptionList = specificationOptionDao.selectByExample(query);
        vo.setSpecificationOptionList(specificationOptionList);
        return vo;
    }

    /**
     * 规格修改
     */
    @Override
    public void update(SpecificationVo vo) {
        //规格修改
        specificationDao.updateByPrimaryKeySelective(vo.getSpecification());
        //规格选项
        //1:先删除 外键
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        query.createCriteria().andSpecIdEqualTo(vo.getSpecification().getId());
        specificationOptionDao.deleteByExample(query);
        //2:再添加
        List<SpecificationOption> specificationOptionList = vo.getSpecificationOptionList();
        for (SpecificationOption specificationOption : specificationOptionList) {
            //外键
            specificationOption.setSpecId(vo.getSpecification().getId());
            //保存
            specificationOptionDao.insertSelective(specificationOption);
        }
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            //1:删除规格表
            specificationDao.deleteByPrimaryKey(id);
            //2:删除规格选项结果集
            SpecificationOptionQuery query = new SpecificationOptionQuery();
            query.createCriteria().andSpecIdEqualTo(id);
            specificationOptionDao.deleteByExample(query);
        }
    }

    /**
     * 查询所有规格结果集  返回List<Map>
     */
    @Override
    public List<Map> selectOptionList() {
        return specificationDao.selectOptionList();
    }
}
