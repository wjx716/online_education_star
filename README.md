# online_education_star
一个在线课程学习平台。

- 运行环境需要安装并运行：nginx + spring cloud alibaba nacos 
  （nginx的配置，请参见相关资源文件夹）

- 这个项目的我在B站上，跟着里面的视频，一步一步敲出来的

- 由于
  没有申请到 阿里云短信验证码的权限，就没有做 短信验证码登录；
  也没有申请到 微信开发造资格，就没有做 微信登录 和 微信支付；
  后面的数据报表的那一块，由于微信支付那一块没有做，就没有相应的数据，所以也没有做，
  如果要做的话，也是使用JSON封装数据，然后使用相应的JS插件渲染而已；

- 里面使用的图片，均是从网络上寻找的素材，部分做了简单的修改，纯学习练手，若侵则删；

- 使用到的技术包括：
  - Vue
  - SpringBoot
  - SpringCloud Alibaba Nacos
  - MyBatis Plus
  - OpenFeign
  - Nginx
