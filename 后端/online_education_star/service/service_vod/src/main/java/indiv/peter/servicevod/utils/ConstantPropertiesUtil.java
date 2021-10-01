package indiv.peter.servicevod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author PeterRays
 * @date 2021/9/19
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${aliyun.vod.regionid}")
    private String regionId;

    @Value("${aliyun.vod.accesskeyid}")
    private String keyId;

    @Value("${aliyun.vod.accesskeysecret}")
    private String keySecret;

    public static String REGION_ID;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        REGION_ID = regionId;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
