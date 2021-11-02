package cn.nightwee.core.controller;

import cn.nightwee.core.pojo.seller.Seller;
import cn.nightwee.core.service.SellerService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 品牌管理
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    /**
     * 注册商家用户
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Seller seller) {
        try {
            sellerService.add(seller);
            return new Result(true, "申请入驻成功，请等待审核");
        }catch (Exception e) {
            e.getStackTrace();
            return new Result(false, "申请入驻失败");
        }
    }

}
