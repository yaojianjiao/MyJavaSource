package com.yjj.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetWzHeroPic {
	public static void main(String[] args) throws IOException {
		String url = "https://pvp.qq.com/web201605/herolist.shtml";
		long t1 = System.currentTimeMillis();
		// 1.������Ҫ��ȡ���ݵ�ҳ�潨������
		Connection connection = Jsoup.connect(url);
		long t2 = System.currentTimeMillis();
		System.out.println("�������ӵ�ʱ�䣺" + (t2 - t1) + "����");
		// 2.ͨ��connection�����ȡ��ַ�е�html���ݣ����֡�String����ǩ
		// ��Document������ȫ���ĵ�����ģ��
		Document document = connection.get();
		long t3 = System.currentTimeMillis();
		System.out.println("��ȡdocument��Ϣ����ʱ��:" + (t3 - t2) + "����");
		// �ҵ���һ��classΪherolist clearfix���ĵ�����
		Element elementUl = document.selectFirst("[class=herolist clearfix]");
		// ͨ��ul��ǩ�ҵ����������е�li�ӱ�ǩ�����ݱ�ǩ������
		Elements elementsLis = elementUl.select("li");
		// size����������¼�ܹ����ص�ͼƬ��������ѭ�����������޹�
		int size = 0;
		// ѭ��������ȡ������elementLis����
		for (Element elementLi : elementsLis) {

			// ѡ��li��ǩ�е�a��ǩ
			Element elementA = elementLi.selectFirst("a");
			// ��ȡa��ǩ�е�href����ֵ--->ָ����һ����ַ���������������Ҫ��ȡ�ĸ����ͼ
			String href = elementA.attr("href");// ��ȡ��ǩ�е�����ֵ����������õ������·����д����
			// ��ȡa��ǩ�е�����---������Ϊ����ͼƬ������
			String imgName = elementA.text();// ��ȡa��ǩ�е�text�ı�ֵ
			// ����hrefץȡ�����·������һ���ַ���ƴ�ӣ�ƴ�ӳ�����·��
			String netPath = "https://pvp.qq.com/web201605/" + href;
			// ������ƴ�ӵ�path������һ���µ����ӣ���Ϊ����µ����Ӳ������ǵ�Ҫ��ȡ��ͼƬ
			Connection newConnection = Jsoup.connect(netPath);
			// ͨ�������ӻ�ȡ��һ���µ�document����
			Document newDocument = newConnection.get();
			// ��Ѱ��ͼƬ���ڵ�λ��---div��� class="zk-con1 zk-con"
			Element div = newDocument.selectFirst("[class=zk-con1 zk-con]");
			// ��ȡdiv��style����ֵ
			String divStyle = div.attr("style");
			String backgroundUrl = divStyle.substring(divStyle.indexOf("'") + 1, divStyle.lastIndexOf("'"));
			// ------�ָ���-------
			// 3.�õ����ͼƬ�ĵ�ַ�󣬽������������� I/O ����������
			System.out.println("���أ�<<" + imgName + ">>��ͼƬ·����-->" + backgroundUrl);
			// ����ͼƬ�ľ���·�������Ǵ���һ��URL����
			URL url1 = new URL("https:" + backgroundUrl);
			// �������url��ַ������һ��������
			InputStream inputStream = url1.openStream();
			// ͨ����������������ǿ��Ի�ȡ���ļ����ֽ���Ϣ
			// ��ȡ�ļ����ֽ���Ϣ���ɽ�ͼƬ���浽����
			// ��Ҫһ�����ص��ļ�����������ղŵ�inputStream��ȡ�����ֽ���Ϣ��д�ڱ��أ�������Ҫ������E�����ֶ�����img�ļ���
			FileOutputStream fileOutputStream = new FileOutputStream("C://������ҫ//" + imgName + ".jpg");
			// ��Ҫ����һ��byte����
			byte[] b = new byte[1024];
			// ��ȡ����������ʱ�����b�ֽ�������
			int count = inputStream.read(b);// count ָ���Ƕ�ȡ����Ч�ֽڸ���
			while (count != -1) {
				fileOutputStream.write(b, 0, count);// һ���ֽ�һ���ֽڵ�����д��ֱ��count==-1
				// ������ܵ�
				fileOutputStream.flush();
				// �ٶ�ȡ��һ��
				count = inputStream.read(b);// ���¼���count��ֵ
			}
			fileOutputStream.close();
			inputStream.close();
			size++;
		}
		long t4 = System.currentTimeMillis();
		double time = (double) (t4 - t1) / 1000;
		System.out.println("��ϲ�������ȫ�����أ�����ʱ��" + time + "�룬����ͼƬ" + size + "��");
	}
}
