package com.lan.main;

import com.lan.dao.PowerMapper;
import com.lan.model.Power;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * package com.lan.main
 *
 * @author zzc
 * @date 2017/3/2
 */
public class Collector {

    static ApplicationContext ac = new ClassPathXmlApplicationContext(
            "spring.xml");
    static PowerMapper powerMapper=(PowerMapper)ac.getBean(PowerMapper.class);

    public static void main(String[] args) {
        for (int i=16000;i<17000;i++){
            System.out.println("http://www.zjzwfw.gov.cn/art/2014/5/30/art_48623_"+i+".html");
            collecting("http://www.zjzwfw.gov.cn/art/2015/12/4/art_48623_"+i+".html");
        }
    }

    public static void collecting(String url){
        Power power=getArticleInfo(url);
        if(power!=null){
            System.out.println("success!");
            powerMapper.insert(power);
        }

    }

    public static Power getArticleInfo(String url) {
        try {
            Connection connect = Jsoup.connect(url);

            Document document;
            document = connect.get();
            if(document.baseUri().equals("http://www.zjzwfw.gov.cn/error_404.html")){
                return null;
            }
            String name=document.select("#container > table:nth-child(3) > tbody > tr > td > table:nth-child(4) > tbody > tr > td > table:nth-child(1) > tbody > tr > td").first().text();
            String danwei=document.select("#container > table:nth-child(3) > tbody > tr > td > table:nth-child(4) > tbody > tr > td > table:nth-child(3) > tbody > tr:nth-child(3) > td:nth-child(2)").text();
            String chushi=document.select("#container > table:nth-child(3) > tbody > tr > td > table:nth-child(4) > tbody > tr > td > table:nth-child(3) > tbody > tr:nth-child(4) > td:nth-child(2)").text();
            String yiju=document.select("#focusCInfo5 > tbody > tr > td").text();
            String neirong=document.select("#focusCInfo1 > tbody > tr > td").text();
            String liuchengtudizhi=document.select("#focusCInfo3 > tbody > tr > td > img").attr("src");
            String liucheng=saveToFile("http://www.zjzwfw.gov.cn"+liuchengtudizhi);
            String zhuyi=document.select("#focusCInfo2 > tbody > tr > td").html();
            Power power=new Power(name,danwei,chushi,yiju,neirong,liucheng,zhuyi);
            return power;
        } catch (IOException e) {
            System.err.println("访问文章页失败:" + url + "  原因" + e.getMessage());
            return null;
        }
    }


    public static String saveToFile(String destUrl) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        String uuid = UUID.randomUUID().toString();
        String fileAddress = "d:\\project/upload/process/" + uuid;// 存储本地文件地址
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        try {
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            String Type = httpUrl.getHeaderField("Content-Type");
            if (Type.equals("image/gif")) {
                fileAddress += ".gif";
            } else if (Type.equals("image/png")) {
                fileAddress += ".png";
            } else if (Type.equals("image/jpeg")) {
                fileAddress += ".jpg";
            } else {
                System.err.println("未知图片格式");
                return "unknown";
            }
            bis = new BufferedInputStream(httpUrl.getInputStream());
            fos = new FileOutputStream(fileAddress);
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.flush();
            System.out.println("图片保存成功！地址：" + fileAddress);
            return "/upload/process/"+uuid;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return "unsuccesfully";
    }
}
