import request from "@/utils/request";

export default {
  //查询所有banner信息
  getBannerList() {
    return request({
      url: `/service_cms/api_crm_banner/getBannerList`,
      method: "get"
    });
  }
};
