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

//			 str = "[{\"ename\":105,\"cname\":\"����\",\"title\":\"���屬��\",\"pay_type\":10,\"new_type\":0,\"hero_type\":3,\"skin_name\":\"���屬��|�����һ�\"},{\"ename\":106,\"cname\":\"С��\",\"title\":\"��֮΢��\",\"new_type\":0,\"hero_type\":2,\"skin_name\":\"��֮΢��|��ʥǰҹ|���֮��|���׻���|�ͷ׶�����\"}]";
			t.parseJson(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseJson(String str) {
		/*
		 * json ��ʽ���� [{ "ename": 105, "cname": "����", "title": "���屬��",
		 * "pay_type": 10, "new_type": 0, "hero_type": 3, "skin_name":
		 * "���屬��|�����һ�" }, { "ename": 106, "cname": "С��", "title": "��֮΢��",
		 * "new_type": 0, "hero_type": 2, "skin_name":
		 * "��֮΢��|��ʥǰҹ|���֮��|���׻���|�ͷ׶�����" }]
		 */
		JSONArray jsonarray = new JSONArray(str);

		System.out.println("jsonarray  size :" + jsonarray.length());

		for (Object obj : jsonarray) {
			JSONObject json = new JSONObject(obj.toString());
//			System.out.println(json.get("cname") + "===" + json.get("ename"));
			// �������ط���
			if(json.has("skin_name")){
				getImg(json.get("ename").toString(), json.get("cname").toString(), json.get("title").toString(), json.get("skin_name").toString());
			}else{
				getImg(json.get("ename").toString(), json.get("cname").toString(), json.get("title").toString(), null);
			}
		}

	}

	/**
	 * ����ͼƬ��ַ������ͼƬ������
	 * 
	 * @param heroNo
	 *            Ӣ�۱��
	 * @param heroTitle
	 *            Ӣ�۽���
	 * @param heroName
	 *            Ӣ������
	 * @param HeroSkin
	 *            Ӣ��Ƥ������
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
			File file = new File("C://������ҫ//all//"+heroNo+"_"+heroName+"_"+HeroSkinName+".jpg");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream out = new FileOutputStream(file);
			// ��Ҫ����һ��byte����
			byte[] b = new byte[1024];
			// ��ȡ����������ʱ�����b�ֽ�������
			int count = inputStream.read(b);// count ָ���Ƕ�ȡ����Ч�ֽڸ���
			while (count != -1) {
				out.write(b, 0, count);// һ���ֽ�һ���ֽڵ�����д��ֱ��count==-1
				// ������ܵ�
				out.flush();
				// �ٶ�ȡ��һ��
				count = inputStream.read(b);// ���¼���count��ֵ
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
	 * ����url��ַ����json����
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
	 * ����url��ַ����json����
	 * 
	 * @param jsonurl
	 * @return
	 * @throws IOException
	 */
	public static String loadJson2(String jsonurl) throws IOException {
		// ����URL
		URL url = new URL(jsonurl);
		// ��������
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// ��Ϊ�������İ�ȫ���ò�����Java������Ϊ�ͻ��˷��ʣ�������������ÿͻ��˵�User Agent
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// ֻ��������ΪGET��ʽ��������ʹ��POST��ʽ
		// conn.setRequestMethod("POST");
		conn.setRequestMethod("GET");
		// ���ñ����ʽΪUTF-8
		conn.setRequestProperty("contentType", "UTF-8");
		// �õ�������

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
