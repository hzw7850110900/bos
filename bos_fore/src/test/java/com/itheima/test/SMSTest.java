package com.itheima.test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SMSTest {

	public static void main(String[] args) throws Exception {
		String url = "http://gw.api.taobao.com/router/rest"; //短信发送的网关（固定的）
		String appkey = "23670810"; //App证书
		String secret = "c54b7b38d9dc852951e3991ed5052698";// App证书密码
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//扩展信息，可以不写
		req.setExtend("");
		//短信类型（必须）
		req.setSmsType("normal");
		
		//短信签名
		req.setSmsFreeSignName("物流系统");
		//设置短信模块里的参数
		req.setSmsParamString("{\"name\":\"老王\",\"product\":\"bos物流\",\"code\":\"1234\"}");
		//接收短信的手机号码
		req.setRecNum("13631426422");
		//短信模块的ID
		req.setSmsTemplateCode("SMS_53470018");
		
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	}
}
