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
		
		//��ȡ΢�ŷ��������͸����ǵ��ĸ�����
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		//����һ��������, ������΢�ŷ�������������
		PrintWriter out = resp.getWriter();
		
		//���У��ͨ��, ��΢�ŷ���������echostr����
		if(VerifyUtils.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
		
		//�ͷ���Դ
		out.close();
		out = null;
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ע�����ﲻ�����κβ���, �����������֤
	}
	
}
