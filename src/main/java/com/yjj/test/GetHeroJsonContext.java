package com.yjj.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetHeroJsonContext {
    

    public static void main(String[] args) {
        GetHeroJsonContext t=new GetHeroJsonContext();
        try {
            String json="https://pvp.qq.com/web201605/js/herolist.json";
            String str= t.loadJson(json);
            t.loadJson2(json);
           System.out.println( str);;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String loadJson (String url) {  
        StringBuilder json = new StringBuilder();  
        try {  
            URL urlObject = new URL(url);  
            URLConnection uc = urlObject.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
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
    public void loadJson2(String jsonurl) throws IOException {
        // 构造URL
        URL url = new URL(jsonurl);
        // 构造连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
       //因为服务器的安全设置不接受Java程序作为客户端访问，解决方案是设置客户端的User Agent
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        conn.setDoOutput(true);  
        conn.setDoInput(true);
        //只可以设置为GET方式，不可以使用POST方式
        //conn.setRequestMethod("POST");
        conn.setRequestMethod("GET");
        //设置编码格式为UTF-8
        conn.setRequestProperty("contentType", "UTF-8");
        //得到输入流
        
        InputStreamReader inputStream = new InputStreamReader(conn.getInputStream(), "utf-8");  
        BufferedReader bufReader = new BufferedReader(inputStream);  
        String line = "";  
        StringBuilder contentBuf = new StringBuilder();  
        while ((line = bufReader.readLine()) != null) {  
            contentBuf.append(line);  
        } 
        if(inputStream!=null){  
            inputStream.close();  
        }
        String buf = contentBuf.toString();  
        System.out.println("结果：\n" + buf); 
        
    }    
}
