package com.main;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;  

import com.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import org.omg.CORBA.Current;

import com.jfinal.json.Jackson;
import com.jfinal.kit.HttpKit;
import com.model.Food;
import com.util.urlUtil;


public class Main {
	public static int id=0;
    public static void main(String []args) throws UnsupportedEncodingException{
        //String url="http://webview.babytree.com/api/mobile_toolcms/can_eat_list?atype=ajax&pg=13&cat_id=29";
        String Url="http://webview.babytree.com/api/mobile_toolcms/can_eat_list?atype=ajax&pg=";
        String []clasStrings={"主食","蔬菜菌类","水果","零食小吃","肉/蛋类","饮品","豆/奶制品","加工食品","水产品","调味品","补品药材","坚果类"};
        int classid;
        for(int i=28,j=0;i<=49;i++,j++){
            if(i==34)
                i=44;
            ArrayList<String>results=new ArrayList<String>();
            for(int page=1;;page++){
                String url=Url+page+"&cat_id="+i;
                //HttpKit.setCharSet("unicode");
                String body= HttpKit.get(url);
                String result=decodeUnicode(body);
                JSONObject jsonObject = JSONObject.fromObject(result);// 把json字符串转化成json对象
                if(jsonObject.get("data").toString().equals("{\"_\":\"\"}")){
                    //System.out.println("长度为0");
                    break;
                }
                JSONArray jsonArray=(JSONArray)jsonObject.get("data");

                for(int size=0;size<jsonArray.size();size++ )
                {
                    JSONObject object= (JSONObject) jsonArray.get(size);
                    if(object!=null){
                        System.out.println(object.get("url"));
                        results.add("http://webview.babytree.com"+object.get("url"));
                        try {
							Thread.currentThread().sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }
            } 
            if(i>=28&&i<=33)
                	classid=i-28;
            else {
					classid=i-38;
			}         
            for(int k=0;k<results.size();k++,id++){
            	String urlString=results.get(k);
            	com.util.urlUtil.getBean(urlString,clasStrings,classid,id);
            	try {
					Thread.currentThread().sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }

    }
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
