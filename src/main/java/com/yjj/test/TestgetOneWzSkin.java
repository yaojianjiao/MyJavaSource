package com.yjj.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.FieldPosition;

import javax.xml.ws.spi.http.HttpContext;

public class TestgetOneWzSkin {
    public static void main(String[] args) {
        TestgetOneWzSkin t=new TestgetOneWzSkin();
        try {
            t.getImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // ��Ҫ���̣�
    // 1��HttpURLConnection������ͼƬ����ַ����һ��InputStream��
    // 2����InputStream�����ݶ�ȡ��ByteArrayOutputStream�У���ʱByteArrayOutputStream�洢��ͼƬ���ݵ�byte���顣
    // 3��ͨ���ļ�������byte������䵽һ��jpg�ļ��С�
    
    public void getImg() throws Exception {
        String strUrl = "http://game.gtimg.cn/images/yxzj/img201606/skin/hero-info/111/111-bigskin-1.jpg";
        // ����URL
        URL url = new URL(strUrl);
        // ��������
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // �����վҪģ�����������
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");
        // ������
        conn.connect();
        // ����վ�����
        InputStream inStream = conn.getInputStream();
        // ���������תվ ����ͼƬ���ݶ�����������ٵ���toByteArray()���ɻ�����ݵ�byte����
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        // ������Ǻܺõģ�����һ�ξͰ�ͼƬ�������ļ���
        // Ҫ����Ҫ��ͼƬ����������;�أ�����ֱ�Ӱ�ͼƬ������Ū��һ��������ʮ������
        // �൱�ڲ�������������ܲ���ͼƬ��
        
        byte[] buf = new byte[1024];
        // Ϊʲô��1024��
        // 1024Byte=1KB������1KB�Ļ���
        // �������ѭ����ȡ����һ����ʱ�ռ䣬���û��ϵ
        // ��û��ʲô��Ĺ�ϵ���������999����������Ҳû�����⣬����ÿ�ζ�ȡ������ֽ�����
        // byte[]�Ĵ�С��˵����һ�β�������ֽ��Ƕ���
        // ��Ȼ������9M���ļ�����ʵ����ڴ�ֻ��1M��������ʡ�˺ܶ�ռ䣮
        // ��Ȼ�����С��˵��I/O������Ƚ�Ƶ����I/O������ʱ�Ƚϳ���
        // ����ٻ��е������ϵ�Ӱ�죮�⿴�������ÿռ任ʱ�䣬��������ʱ�任�ռ��ˣ�
        // ʱ�����ܱ��ڴ�����������ǿ������ڴ��㹻�Ļ����һῼ�����㣮
        int len = 0;
        // ��ȡͼƬ����
        while((len=inStream.read(buf))!=-1){
//            System.out.println(len);
            outputstream.write(buf, 0, len);
        }
        inStream.close();
        outputstream.close();
      //��ͼƬ���������ļ���
        File file=new File("C:\\test\\test.jpg");
        FileOutputStream out=new FileOutputStream(file);
        out.write(outputstream.toByteArray());
        out.close();
    }
}
