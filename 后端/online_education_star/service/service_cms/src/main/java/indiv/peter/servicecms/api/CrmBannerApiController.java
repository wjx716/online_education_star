package indiv.peter.servicecms.api;


import indiv.peter.commonutils.Result;
import indiv.peter.servicecms.entity.CrmBanner;
import indiv.peter.servicecms.service.CrmBannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-26
 */
@Api(tags = {"前台banner展示管理"})
@RestController
@CrossOrigin
@RequestMapping("/service_cms/api_crm_banner")
public class CrmBannerApiController {

    private final CrmBannerService crmBannerService;

    @Autowired
    public CrmBannerApiController(CrmBannerService crmBannerService) {
        this.crmBannerService = crmBannerService;
    }

    @GetMapping("getBannerList")
    public Result getBannerList(){
        List<CrmBanner> bannerList = crmBannerService.list(null);
        return Result.ok().data("bannerList",bannerList);
    }
}

