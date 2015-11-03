package com.hz.wechatproject.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hz.wechatproject.pojo.ModelSystemFilesPOJO;

public class CommonUtil {

	public static void main(String args[]) {
		System.out.println(getSystemFiles(null));
		System.out.println(getSystemFiles(new ModelSystemFilesPOJO("C:\\work",2, "C:\\work")));
	}

	private static Logger logger = Logger.getLogger(CommonUtil.class);
	
	public static final String WECHAT_CONTEXT_PATH = "/wechatproject";

	public static final String WECHAT_URL = WECHAT_CONTEXT_PATH+"/wechat/wechatProcess";
	public static final String SITE_MANAGE_LOGIN_URL = WECHAT_CONTEXT_PATH+"/siteManage";
	public static final String SITE_MANAGE_URL = WECHAT_CONTEXT_PATH+"/siteManage";
	public static final String ERRPR_PAGES_URI_PREFIX = WECHAT_CONTEXT_PATH+"/errorpages";
	public static final String SECURITY_CHECK_URI = WECHAT_CONTEXT_PATH+"/j_spring_security_check";

	public static final String STATIC_RESOURCE_UTI_CSS_PREFIX = WECHAT_CONTEXT_PATH+"/css";
	public static final String STATIC_RESOURCE_UTI_JS_PREFIX = WECHAT_CONTEXT_PATH+"/js";
	public static final String STATIC_RESOURCE_UTI_IMAGES_PREFIX = WECHAT_CONTEXT_PATH+"/images";

	public static final String SCRIPT_UPDATE_METHOD = "gitupdate";
	public static final String SCRIPT_UPDATE = "/root/gitupdate";
	// public static final String SCRIPT_UPDATE = "C:\\work\\test.bat";
	public static final String SCRIPT_DEPLOY_METHOD = "deploy";
	public static final String SCRIPT_DEPLOY = "/root/atdeploy";
	// public static final String SCRIPT_DEPLOY = "C:\\work\\test.bat";
	public static final String SCRIPT_DEPLOY_CHK_METHOD = "deployQCheck";
	public static final String SCRIPT_DEPLOY_CHK = "/root/deployQCheck";
	
	public static final String WEBSERVICE_URI = WECHAT_CONTEXT_PATH+"/ws";

	public static Object getObjFromSpringContainer(HttpServletRequest req,
			String name) {
		// ApplicationContext ac1 =
		// WebApplicationContextUtils.getRequiredWebApplicationContext(req.getServletContext());
		ApplicationContext ac2 = WebApplicationContextUtils
				.getWebApplicationContext(req.getServletContext());
		Object objBean = ac2.getBean(name);
		if (objBean == null) {
			logger.debug(name + " bean is null");
		} else {
			System.out.println(name + " bean is not null");
		}
		return objBean;
	}

	public static boolean isEmptyString(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}

	public static String getEmptyString(String str) {
		if (str == null || str.isEmpty()) {
			return "";
		}
		return str;
	}

	public static boolean isValidURI(String uri) {
		boolean retVal = false;
		if (CommonUtil.WECHAT_URL.equals(uri)) {
			retVal = true;
		} else if (uri.startsWith(CommonUtil.SITE_MANAGE_URL)) {
			retVal = true;
		} else if (CommonUtil.isResourceRequest(uri)) {
			retVal = true;
		} else if (uri.startsWith(CommonUtil.ERRPR_PAGES_URI_PREFIX)) {
			retVal = true;
		} else if (uri.startsWith(CommonUtil.SECURITY_CHECK_URI)) {
			retVal = true;
		}else if(uri.startsWith(CommonUtil.WEBSERVICE_URI)){
			retVal = true;
		}

		return retVal;
	}

	public static boolean isResourceRequest(String uri) {
		if (!isEmptyString(uri)) {
			if (uri.startsWith(STATIC_RESOURCE_UTI_CSS_PREFIX)
					|| uri.startsWith(STATIC_RESOURCE_UTI_JS_PREFIX)
					|| uri.startsWith(STATIC_RESOURCE_UTI_IMAGES_PREFIX)) {
				return true;
			}
		}
		return false;
	}

	public static List<String> splitStringAsList(String str, String seperator) {
		List<String> retList = new ArrayList<>();
		if (isEmptyString(str)) {
			return retList;
		}
		for (String part : str.split(seperator)) {
			retList.add(part);
		}

		return retList;
	}

	/**
	 * 运行shell脚本
	 * 
	 * @param shell
	 *            需要运行的shell脚本
	 */
	public static List<String> execShell(final String shell, boolean needToWait)
			throws Exception {
		List<String> strList = new ArrayList<String>();

		if (needToWait) {
			Process process;
			Runtime rt = Runtime.getRuntime();
			process = rt.exec(shell);
			process.waitFor();
			InputStreamReader isr = new InputStreamReader(
					process.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				strList.add(line);
				logger.debug(line);
			}
		} else {
			new Thread() {
				public void run() {
					try {
						Thread.sleep(2000);
						Runtime rt = Runtime.getRuntime();
						rt.exec(shell);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		return strList;
	}

	public static List<String> execShellMethod(String method) {
		List<String> strList = new ArrayList<String>();
		if (method == null || method.isEmpty()) {
			return strList;
		}
		try {
			if (SCRIPT_UPDATE_METHOD.equals(method)) {
				strList.addAll(CommonUtil.execShell(SCRIPT_UPDATE, true));
			} else if (SCRIPT_DEPLOY_METHOD.equals(method)) {
				// unregisteJDBCDrivers();
				strList.addAll(CommonUtil.execShell(SCRIPT_DEPLOY, true));
			} else if (SCRIPT_DEPLOY_CHK_METHOD.equals(method)) {
				strList.addAll(CommonUtil.execShell(SCRIPT_DEPLOY_CHK, true));
			}
		} catch (Exception e) {
			logger.error("Error when execute script, maybe did not find script");
			strList.add("Error when execute script, maybe did not find script");
		}
		return strList;
	}

	public static void unregisteJDBCDrivers() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		if (drivers != null) {
			while (drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				try {
					DriverManager.deregisterDriver(driver);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 运行shell
	 * 
	 * @param shStr
	 *            需要执行的shell
	 * @return
	 * @throws IOException
	 */
	public static List<String> runShell(String shStr) throws Exception {
		List<String> strList = new ArrayList<String>();

		Process process;
		process = Runtime.getRuntime().exec(
				new String[] { "/bin/sh", "-c", shStr }, null, null);
		process.waitFor();
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;
		while ((line = input.readLine()) != null) {
			strList.add(line);
		}

		return strList;
	}

	public static List<ModelSystemFilesPOJO> getSystemFiles(
			ModelSystemFilesPOJO data) {
		List<ModelSystemFilesPOJO> result = new ArrayList<>();
		if (data == null || isEmptyString(data.getFilePath())) {
			File[] rootFiles = File.listRoots();
			for (File file : rootFiles) {
				Integer type = file.isDirectory() ? ModelSystemFilesPOJO.FILE_TYPE_DIR
						: ModelSystemFilesPOJO.FILE_TYPE_FILE;
				result.add(new ModelSystemFilesPOJO(file.getPath(), type, file
						.getAbsolutePath()));
			}
		} else {
			String path = data.getFilePath();
			logger.debug(path);
			File file = new File(path);
			if (file.isFile()) {
				result.add(getSystemFileContent(file));
			} else {
				File[] tempList = null;
				tempList = file.listFiles();
				for (File tempFile : tempList) {
					Integer type = tempFile.isDirectory() ? ModelSystemFilesPOJO.FILE_TYPE_DIR
							: ModelSystemFilesPOJO.FILE_TYPE_FILE;
					String name = tempFile.getName();
					result.add(new ModelSystemFilesPOJO(name, type, tempFile
							.getAbsolutePath()));
				}
			}
		}
		return result;
	}

	public static boolean isSystemFileContentPOJO(
			List<ModelSystemFilesPOJO> itemList) {
		if (itemList == null || itemList.size() > 1) {
			return false;
		}
		if (itemList.size() == 1
				&& ModelSystemFilesPOJO.FILE_TYPE_FILE.equals(itemList.get(0)
						.getType())) {
			return true;
		}

		return false;
	}

	public static ModelSystemFilesPOJO getSystemFileContent(File file) {
		Integer type = file.isDirectory() ? ModelSystemFilesPOJO.FILE_TYPE_DIR
				: ModelSystemFilesPOJO.FILE_TYPE_FILE;
		ModelSystemFilesPOJO ret = new ModelSystemFilesPOJO(file.getName(),
				type, file.getAbsolutePath());
		if (file.isFile() && file.exists()) {
			try {
				InputStreamReader read;
				read = new InputStreamReader(new FileInputStream(file),
						Charset.forName("Utf8"));
				// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					sb.append(line + "\n");
				}
				bufferedReader.close();
				read.close();
				ret.setContent(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;

	}

	public static ModelSystemFilesPOJO getParentSysFilePath(
			ModelSystemFilesPOJO data) {
		if (data == null || isEmptyString(data.getFilePath())
				|| data.getFilePath().equals("/")) {
			return data;
		}

		String path = data.getFilePath();
		logger.debug("getParentSysFilePath " + path);
		File file = new File(path).getParentFile();
		if (file == null) {
			return null;
		}
		Integer type = file.isDirectory() ? ModelSystemFilesPOJO.FILE_TYPE_DIR
				: ModelSystemFilesPOJO.FILE_TYPE_FILE;
		ModelSystemFilesPOJO ret = new ModelSystemFilesPOJO(file.getName(),
				type, file.getAbsolutePath());
		return ret;
	}

	public static class UtilHttpClient {
		/**
		 * send get request
		 */
		public static String get(String url) {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			StringBuilder sb = new StringBuilder();
			try {
				// 创建httpget.

				HttpGet httpget = new HttpGet(url);
				httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
				httpget.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
				logger.debug("executing request " + httpget.getURI());
				// 执行get请求.
				CloseableHttpResponse response = httpclient.execute(httpget);
				try {
					// 获取响应实体
					HttpEntity entity = response.getEntity();
					logger.debug("--------------------------------------");
					// 打印响应状态
					logger.debug(response.getStatusLine());
					if (entity != null) {
						logger.debug("Response content length: "
								+ entity.getContentLength());
						if (entity != null) {
							String charset = getContentCharSet(entity);
							if (isEmptyString(charset)) {
								charset = "GB2312";
							}
							sb.append(EntityUtils.toString(entity, charset));
						}
					}
					logger.debug("------------------------------------");
				} finally {
					response.close();
				}
			} catch (Exception e) {
				logger.error("error when excute httpget", e);
			} finally {
				// 关闭连接,释放资源
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return sb.toString();
		}

		/**
		 * 默认编码utf -8 Obtains character set of the entity, if known.
		 * 
		 * @param entity
		 *            must not be null
		 * @return the character set, or null if not found
		 * @throws ParseException
		 *             if header elements cannot be parsed
		 * @throws IllegalArgumentException
		 *             if entity is null
		 */
		public static String getContentCharSet(final HttpEntity entity)
				throws ParseException {

			if (entity == null) {
				throw new IllegalArgumentException(
						"HTTP entity may not be null");
			}
			String charset = null;
			if (entity.getContentType() != null) {
				HeaderElement values[] = entity.getContentType().getElements();
				if (values.length > 0) {
					NameValuePair param = values[0]
							.getParameterByName("charset");
					if (param != null) {
						charset = param.getValue();
					}
				}
			}

			return charset;
		}
	}

	public static class UtilHTMLParse {
		public static String getContentOnClass(String html,String tagName,String className) throws ParserException {
			Parser parser = new Parser();
			parser.setInputHTML(html);
			
			NodeFilter filter=new AndFilter(new NodeFilter[]{new TagNameFilter(tagName),new HasAttributeFilter("class",className)});
			NodeList nodeList = parser.parse(filter);
			return nodeList.asString();			
//			PrototypicalNodeFactory pnfPrototypicalNodeFactory = new PrototypicalNodeFactory();
//			pnfPrototypicalNodeFactory.registerTag(new Div());
//			parser.setNodeFactory(pnfPrototypicalNodeFactory);
//
//			NodeFilter filter1 = new NodeClassFilter(LinkTag.class);
//			NodeList nodelist = parser.extractAllNodesThatMatch(filter1);
//			for (Node node : nodelist.toNodeArray()) {
//				if (node instanceof LinkTag) {
//					LinkTag link = (LinkTag) node;
//					if (link != null) {
//						System.out.println("地址:" + link.getLink() + "\t标题:"
//								+ link.getLinkText());
//					}
//				}
//			}
		}

	}

	public static class UtilSecurity {
		public static final String ACCESS_STRING_SEPERATOR = ",";
		public static final String ACCESS_ROLE_ROLE_USER = "ROLE_USER";
		public static final String ACCESS_NUM_ROLE_USER = "1";
		public static final String ACCESS_ROLE_ROLE_ADMIN = "ROLE_ADMIN";
		public static final String ACCESS_NUM_ROLE_ADMIN = "999";
	}

}
