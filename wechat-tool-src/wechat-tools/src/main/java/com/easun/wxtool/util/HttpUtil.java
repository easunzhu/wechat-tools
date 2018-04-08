package com.easun.wxtool.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.easun.wxtool.log.ToolLog;

/**
 * Create by easun on 2017年5月18日
 */
public class HttpUtil {

	private static CloseableHttpClient httpClient = null;
	private static final int MAX_TIMEOUT = 30000;

	public static HttpClient getInstance() {
		if (null == httpClient) {
			synchronized (HttpUtil.class) {
				if (null == httpClient) {
					initHttpClient();
				}
			}
		}
		return httpClient;
	}

	private static void initHttpClient() {
		Registry<ConnectionSocketFactory> registry = null;
		try {
			ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
			LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
			registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", plainsf).register("https", sslsf).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 设置连接池
		PoolingHttpClientConnectionManager phcm = new PoolingHttpClientConnectionManager(registry);
		// 设置连接池大小
		phcm.setMaxTotal(20);
		phcm.setDefaultMaxPerRoute(phcm.getMaxTotal());

		// 请求重试处理
		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {

			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 3) {// 如果已经重试了3次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// SSL握手异常
					return false;
				}

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};
		httpClient = HttpClientBuilder.create().setConnectionManager(phcm).setRetryHandler(httpRequestRetryHandler).build();
	}

	/**
	 * 设置全局请求参数
	 */
	private static void config(HttpRequestBase httpRequestBase) {
		// 设置全局请求参数
		RequestConfig requestConfig =
				RequestConfig.custom().setSocketTimeout(MAX_TIMEOUT).setConnectTimeout(MAX_TIMEOUT).setConnectionRequestTimeout(MAX_TIMEOUT).build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * post form表单请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postForm(String url, Map<String, String> params) {
		return postForm(url, null, params);
	}

	/**
	 * post form表单上传文件
	 * 
	 * @param url
	 * @param file
	 * @return
	 */
	public static String postForm(String url, File file) {
		return postForm(url, file, null);
	}

	/**
	 * post form表单
	 * 
	 * @param url 地址
	 * @param file 文件
	 * @param params 参数
	 * @return
	 */
	public static String postForm(String url, File file, Map<String, String> params) {
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost(url);
			config(post);
			if (null != file) {
				FileBody bin = new FileBody(file);
				StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
				HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();
				post.setEntity(reqEntity);
			}
			if (null != params && params.size() > 0) {
				List<NameValuePair> formparams = new ArrayList<>();
				for (Entry<String, String> entry : params.entrySet()) {
					formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				post.setEntity(new UrlEncodedFormEntity(formparams, "utf-8"));
			}
			response = (CloseableHttpResponse) getInstance().execute(post);
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			return result;
		} catch (Exception e) {
			ToolLog.log("HttpUtil.postForm error={}", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					ToolLog.log("HttpUtil.postForm close response error={}", e);
				}
			}
		}
		return null;
	}

	/**
	 * post json请求
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
	public static String postJson(String url, String json) {
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost(url);
			config(post);
			StringEntity se = new StringEntity(json, "utf-8");
			se.setContentType("application/json");
			se.setContentEncoding("utf-8");
			post.setEntity(se);
			response = (CloseableHttpResponse) getInstance().execute(post);
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			return result;
		} catch (Exception e) {
			ToolLog.log("HttpUtil.postJson error={}", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					ToolLog.log("HttpUtil.postJson close response error={}", e);
				}
			}
		}
		return null;
	}

	/**
	 * post xml请求
	 * 
	 * @param url
	 * @param xml
	 * @return
	 */
	public static String postXML(String url, String xml) {
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost(url);
			config(post);
			StringEntity se = new StringEntity(xml, "utf-8");
			se.setContentType("text/xml;charset=utf-8");
			se.setContentEncoding("utf-8");
			post.setEntity(se);
			response = (CloseableHttpResponse) getInstance().execute(post);
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			return result;
		} catch (Exception e) {
			ToolLog.log("HttpUtil.postXML error={}", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					ToolLog.log("HttpUtil.postXML close response error={}", e);
				}
			}
		}
		return null;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		CloseableHttpResponse response = null;
		try {
			HttpGet get = new HttpGet(url);
			config(get);
			response = (CloseableHttpResponse) getInstance().execute(get);
			return EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			ToolLog.log("HttpUtil.doGet error={}", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					ToolLog.log("HttpUtil.doGet close response error={}", e);
				}
			}
		}
		return null;
	}

	/**
	 * get请求下载文件
	 * 
	 * @param url 地址
	 * @param filePath 文件保存路径
	 * @return
	 */
	public static void doGetDownload(String url, String filePath) {
		CloseableHttpResponse response = null;
		InputStream in = null;
		FileOutputStream fos = null;
		try {
			HttpGet get = new HttpGet(url);
			config(get);
			response = (CloseableHttpResponse) getInstance().execute(get);
			HttpEntity entity = response.getEntity();
			in = entity.getContent();
			File file = new File(filePath);
			fos = new FileOutputStream(file);
			int l;
			byte[] tmp = new byte[1024];
			while ((l = in.read(tmp)) != -1) {
				fos.write(tmp, 0, l);
			}
			fos.flush();
			EntityUtils.consume(entity);
		} catch (Exception e) {
			ToolLog.log("HttpUtil.doGetDownload error={}", e);
		}
	}

}
