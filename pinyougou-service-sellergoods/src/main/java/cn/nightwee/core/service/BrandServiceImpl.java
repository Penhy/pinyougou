package cn.nightwee.core.service;

import cn.nightwee.core.dao.good.BrandDao;
import cn.nightwee.core.pojo.good.Brand;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
