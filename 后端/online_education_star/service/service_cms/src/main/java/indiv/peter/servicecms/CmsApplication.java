package indiv.peter.servicecms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author PeterRays
 * @date 2021/9/26
 */
@ComponentScan({"indiv.peter"})
@SpringBootApplication
@MapperScan("indiv.peter.servicecms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
