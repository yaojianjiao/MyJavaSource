package com.yjj.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestWzSkinFormJson {

	public static void main(String[] args) {
		TestWzSkinFormJson t = new TestWzSkinFormJson();
		try {
			 String json="https://pvp.qq.com/web201605/js/herolist.json";
			 String str= t.loadJson(json);
			 System.out.println("method1:"+str);
			// str= t.loadJson2(json);
			// System.out.println("method2:"+str);

//			 str = "[{\"ename\":105,\"cname\":\"廉颇\",\"title\":\"正义爆轰\",\"pay_type\":10,\"new_type\":0,\"hero_type\":3,\"skin_name\":\"正义爆轰|地狱岩魂\"},{\"ename\":106,\"cname\":\"小乔\",\"title\":\"恋之微风\",\"new_type\":0,\"hero_type\":2,\"skin_name\":\"恋之微风|万圣前夜|天鹅之梦|纯白花嫁|缤纷独角兽\"}]";
			t.parseJson(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseJson(String str) {
		/*
		 * json 格式如下 [{ "ename": 105, "cname": "廉颇", "title": "正义爆轰",
		 * "pay_type": 10, "new_type": 0, "hero_type": 3, "skin_name":
		 * "正义爆轰|地狱岩魂" }, { "ename": 106, "cname": "小乔", "title": "恋之微风",
		 * "new_type": 0, "hero_type": 2, "skin_name":
		 * "恋之微风|万圣前夜|天鹅之梦|纯白花嫁|缤纷独角兽" }]
		 */
		JSONArray jsonarray = new JSONArray(str);

		System.out.println("jsonarray  size :" + jsonarray.length());

		for (Object obj : jsonarray) {
			JSONObject json = new JSONObject(obj.toString());
//			System.out.println(json.get("cname") + "===" + json.get("ename"));
			// 调用下载方法
			if(json.has("skin_name")){
				getImg(json.get("ename").toString(), json.get("cname").toString(), json.get("title").toString(), json.get("skin_name").toString());
			}else{
				getImg(json.get("ename").toString(), json.get("cname").toString(), json.get("title").toString(), null);
			}
		}

	}

	/**
	 * 根据图片地址，下载图片到本地
	 * 
	 * @param heroNo
	 *            英雄编号
	 * @param heroTitle
	 *            英雄介绍
	 * @param heroName
	 *            英雄名称
	 * @param HeroSkin
	 *            英雄皮肤数量
	 */
	public void getImg(String heroNo, String heroName, String heroTitle, String HeroSkin) {
		String url = "http://game.gtimg.cn/images/yxzj/img201606/skin/hero-info/111/111-bigskin-1.jpg";
		int HeroSkinCount = 1;
		if (HeroSkin.indexOf("|") > 0) {
			String[] split = HeroSkin.split("\\|");
			HeroSkinCount = split.length;
			for (int i = 0; i < HeroSkinCount; i++) {
				url = "http://game.gtimg.cn/images/yxzj/img201606/skin/hero-info/" + heroNo + "/" + heroNo + "-bigskin-" + (i + 1) + ".jpg";
				System.out.println(url);
//				System.out.println(heroName);
//				System.out.println(heroTitle);
//				System.out.println(split[i]);
//				System.out.println("----");
				downImg(heroNo,url,heroName,split[i]);
			}
		}else{
			downImg(heroNo,url,heroName,heroTitle);
//			System.out.println("----");
		}
	}

	public void downImg(String heroNo,String urlpic,String heroName,String HeroSkinName) {
		try {
			URL url2 = new URL(urlpic);
			InputStream inputStream = url2.openStream();
			File file = new File("C://王者荣耀//all//"+heroNo+"_"+heroName+"_"+HeroSkinName+".jpg");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream out = new FileOutputStream(file);
			// 需要创建一个byte数组
			byte[] b = new byte[1024];
			// 读取到的数据临时存放在b字节数组内
			int count = inputStream.read(b);// count 指的是读取的有效字节个数
			while (count != -1) {
				out.write(b, 0, count);// 一个字节一个字节得往里写，直到count==-1
				// 清空流管道
				out.flush();
				// 再读取下一次
				count = inputStream.read(b);// 重新计算count的值
			}
			out.close();
			inputStream.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据url地址加载json内容
	 * 
	 * @param url
	 * @return
	 */
	public static String loadJson(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL urlObject = new URL(url);
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * 根据url地址加载json内容
	 * 
	 * @param jsonurl
	 * @return
	 * @throws IOException
	 */
	public static String loadJson2(String jsonurl) throws IOException {
		// 构造URL
		URL url = new URL(jsonurl);
		// 构造连接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 因为服务器的安全设置不接受Java程序作为客户端访问，解决方案是设置客户端的User Agent
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// 只可以设置为GET方式，不可以使用POST方式
		// conn.setRequestMethod("POST");
		conn.setRequestMethod("GET");
		// 设置编码格式为UTF-8
		conn.setRequestProperty("contentType", "UTF-8");
		// 得到输入流

		InputStreamReader inputStream = new InputStreamReader(conn.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(inputStream);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		if (inputStream != null) {
			inputStream.close();
		}
		String buf = contentBuf.toString();
		return buf;
	}
}
