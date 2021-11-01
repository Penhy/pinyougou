package cn.nightwee.core.service;

import cn.nightwee.core.dao.good.BrandDao;
import cn.nightwee.core.pojo.good.Brand;
import cn.nightwee.core.pojo.good.BrandQuery;
import cn.nightwee.core.pojo.good.BrandQuery.Criteria;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 品牌管理
 * @author Penhy
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandDao brandDao;

    /**
     * 查询所有
     */
    @Override
    public List<Brand> findAll() {
        return brandDao.selectByExample(null);
    }

    /**
     * 分页查询
     */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        //分页插件
        PageHelper.startPage(pageNum,pageSize);
        //查询
        Page<Brand> p = (Page<Brand>) brandDao.selectByExample(null);
        return new PageResult(p.getTotal(), p.getResult());
    }

    /**
     * 添加品牌
     */
    @Override
    public void add(Brand brand) {
        brandDao.insertSelective(brand);
    }

    /**
     * 查询一个品牌
     */
    @Override
    public Brand findOne(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    /**
     * 修改品牌
     */
    @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除品牌
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            brandDao.deleteByPrimaryKey(id);
        }
    }

    /**
     * 根据条件查询分页对象
     */
    @Override
    public PageResult search(Integer pageNum, Integer pageSize, Brand brand) {
        //分页插件
        PageHelper.startPage(pageNum,pageSize);
        // 判断是否有条件需要查询
        BrandQuery brandQuery = new BrandQuery();
        if (null != brand) {
            Criteria createCriteria = brandQuery.createCriteria();
            if (null != brand.getName() && !"".equals(brand.getName().trim())) {
                createCriteria.andNameLike("%" + brand.getName().trim() + "%");
            }
            if(null != brand.getFirstChar() && !"".equals(brand.getFirstChar().trim())){
                createCriteria.andFirstCharEqualTo(brand.getFirstChar().trim());
            }
        }
        //查询
        Page<Brand> p = (Page<Brand>) brandDao.selectByExample(brandQuery);
        return new PageResult(p.getTotal(), p.getResult());
    }

    /**
     * 查询所有品牌结果集  返回List<Map>
     */
    @Override
    public List<Map> selectOptionList() {
        return brandDao.selectOptionList();
    }
}
