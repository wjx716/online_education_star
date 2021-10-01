package indiv.peter.serviceoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author PeterRays
 * @date 2021/9/19
 *
 * exclude = DataSourceAutoConfiguration.class
 * 让SpringBoot不去访问数据库
 */
@EnableDiscoveryClient
@ComponentScan(basePackages = "indiv.peter")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
