package shuliang.han.vertify.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shuliang.han.vertify.VerifyUtils;

public class VerifyServlet extends HttpServlet {

	private static final long serialVersionUID = 4440739483644L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//获取微信服务器发送给我们的四个参数
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		//创建一个出处流, 用于向微信服务器发送数据
		PrintWriter out = resp.getWriter();
		
		//如果校验通过, 向微信服务器发送echostr参数
		if(VerifyUtils.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
		
		//释放资源
		out.close();
		out = null;
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//注意这里不能有任何操作, 否则不能完成验证
	}
	
}
