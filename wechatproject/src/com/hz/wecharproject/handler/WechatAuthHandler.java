package com.hz.wecharproject.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WechatAuthHandler {

	@RequestMapping(value = "wechatAuth")
	@ResponseBody
	public String wechatAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		/** ��ȡ���յ���xml��Ϣ */
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String xml = sb.toString(); // �μ�Ϊ���յ�΢�Ŷ˷��͹�����xml����

		String result = "";
		/** �ж��Ƿ���΢�Ž��뼤����֤��ֻ���״ν�����֤ʱ�Ż��յ�echostr��������ʱ��Ҫ����ֱ�ӷ��� */
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			result = echostr;
		} else {
			// ������΢�Ŵ�������
		}
		return result;
	}

	@RequestMapping(value = "showBody")
	@ResponseBody
	public String showBody(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		int size = request.getContentLength();

		InputStream is;
		String res = "";
		try {
			is = request.getInputStream();
			byte[] reqBodyBytes = readBytes(is, size);
			res = new String(reqBodyBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static final byte[] readBytes(InputStream is, int contentLen) {
		if (contentLen > 0) {
			int readLen = 0;

			int readLengthThisTime = 0;

			byte[] message = new byte[contentLen];

			try {

				while (readLen != contentLen) {

					readLengthThisTime = is.read(message, readLen, contentLen
							- readLen);

					if (readLengthThisTime == -1) {// Should not happen.
						break;
					}

					readLen += readLengthThisTime;
				}

				return message;
			} catch (IOException e) {
				// Ignore
				// e.printStackTrace();
			}
		}

		return new byte[] {};
	}

}
