package com.hz.wecharproject.handler;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WechatAuthHandler {

	@RequestMapping(value = "showBody")
	@ResponseBody
	public String showBody(HttpServletRequest request,
			HttpServletResponse response) {
			response.setCharacterEncoding("UTF-8");

		int size = request.getContentLength();

		InputStream is;
		String res="";
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
