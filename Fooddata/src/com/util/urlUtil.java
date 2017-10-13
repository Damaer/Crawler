package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dao.Dao;
import com.model.Food;

public class urlUtil {
	public static void getBean(String url,String []clasStrings,int classid,int id){
		//String url = "http://webview.babytree.com/api/mobile_toolcms/can_eat_detail?id=600";
		Food food=new Food();
		food.setId(id);
		food.setClass_id(classid);
		food.setClass_name(clasStrings[classid]);
		String resultString=SendGet(url);
		//System.out.println(resultString);
		Pattern pattern=Pattern.compile("<title>.*</title>");
		Matcher matcher=pattern.matcher(resultString);
		boolean isFind = matcher.find();
		//System.out.println(isFind);
		if(isFind){
			String name=matcher.group(0).substring(7,matcher.group(0).length()-8).trim();
			//System.out.println(name);
			food.setName(name);
		}
		
		Pattern pattern1=Pattern.compile("����</h1>\\s*<div class=\"text\">([\\s\\S]+?)</div>");
		Matcher matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String anothername=matcher1.group(0).replace(" ", "");
			anothername=anothername.substring(24,anothername.length()-6);
			//System.out.println(anothername);
			food.setAnother_name(anothername);
		}else{
			food.setAnother_name("�ޱ���");
		}
		
		//�и��ܲ��ܳԣ�
		pattern1=Pattern.compile("�и��ܳ���([\\s\\S]+?)</div>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String pregnancy_can_eat=matcher1.group(0).replace(" ", "");
			pregnancy_can_eat=pregnancy_can_eat.substring(61,pregnancy_can_eat.length()-11);
			//System.out.println(pregnancy_can_eat);
			food.setPregnancy_can_eat(pregnancy_can_eat);
		}
		
		pattern1=Pattern.compile("�и��ܳ���</h1>\\s*<div class=\"status\">\\s*<i class=\"([\\s\\S]+?)\">\\s*</i>\\s*<em>([\\s\\S]+?)</em>\\s*</div>\\s*<div class=\"text\">([\\s\\S]+?)</div>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String pregnancy_instruction=matcher1.group(0).replace(" ", "");
			pregnancy_instruction=pregnancy_instruction.substring(91,pregnancy_instruction.length()-6);
			pregnancy_instruction=deleteCharString0(pregnancy_instruction,'>');
			//System.out.println(pregnancy_instruction);
			food.setPregnancy_instruction(pregnancy_instruction);
		}
		//�������ܳ���
		
		pattern1=Pattern.compile("�������ܳ���([\\s\\S]+?)</div>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String postpartum_can_eat=matcher1.group(0).replace(" ", "");
			postpartum_can_eat=postpartum_can_eat.substring(62,postpartum_can_eat.length()-11);
			//System.out.println(postpartum_can_eat);
			food.setPostpartum_can_eat(postpartum_can_eat);
		}
		pattern1=Pattern.compile("�������ܳ���</h1>\\s*<div class=\"status\">\\s*<i class=\"([\\s\\S]+?)\">\\s*</i>\\s*<em>([\\s\\S]+?)</em>\\s*</div>\\s*<div class=\"text\">([\\s\\S]+?)</div>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String postpartum_instruction=matcher1.group(0).replace(" ", "");
			postpartum_instruction=postpartum_instruction.substring(92,postpartum_instruction.length()-6);
			postpartum_instruction=deleteCharString0(postpartum_instruction,'>');
			//System.out.println(postpartum_instruction);
			food.setPostpartum_instruction(postpartum_instruction);
		}
		
		//�����ܲ��ܳ�
		
		pattern1=Pattern.compile("�����ܳ���([\\s\\S]+?)</div>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String lactation_can_eat=matcher1.group(0).replace(" ", "");
			lactation_can_eat=lactation_can_eat.substring(61,lactation_can_eat.length()-11);
			//System.out.println(lactation_can_eat);
			food.setLactation_can_eat(lactation_can_eat);
		}
		pattern1=Pattern.compile("�����ܳ���</h1>\\s*<div class=\"status\">\\s*<i class=\"([\\s\\S]+?)\">\\s*</i>\\s*<em>([\\s\\S]+?)</em>\\s*</div>\\s*<div class=\"text\">([\\s\\S]+?)</div>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String lactation_instruction=matcher1.group(0).replace(" ", "");
			lactation_instruction=lactation_instruction.substring(91,lactation_instruction.length()-6);
			lactation_instruction=deleteCharString0(lactation_instruction,'>');
			//System.out.println(lactation_instruction);
			food.setLactation_instruction(lactation_instruction);
		}
		
		//Ӥ���ܳ���
		pattern1=Pattern.compile("Ӥ���ܳ���([\\s\\S]+?)</div>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String baby_can_eat=matcher1.group(0).replace(" ", "");
			baby_can_eat=baby_can_eat.substring(61,baby_can_eat.length()-11);
			//System.out.println(baby_can_eat);
			food.setBaby_can_eat(baby_can_eat);
		}
		pattern1=Pattern.compile("Ӥ���ܳ���</h1>\\s*<div class=\"status\">\\s*<i class=\"([\\s\\S]+?)\">\\s*</i>\\s*<em>([\\s\\S]+?)</em>\\s*</div>\\s*<div class=\"text\">([\\s\\S]+?)</div>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String baby_instruction=matcher1.group(0).replace(" ", "");
			baby_instruction=baby_instruction.substring(91,baby_instruction.length()-6);
			baby_instruction=deleteCharString0(baby_instruction,'>');
			//System.out.println(baby_instruction);
			food.setBaby_instruction(baby_instruction);
		}
		
		//tips
		pattern1=Pattern.compile("С��ʿ\\s*<div class=\"shield\"></div>\\s*</h1>\\s*<div class=\"text\">\\s*([\\s\\S]+?)</div>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			//System.out.println(matcher1.group(0).trim());
			String tips=matcher1.group(0).replace(" ", "");
			tips=tips.substring(51,tips.length()-6);
			tips=deleteCharString0(tips,'>');
			//System.out.println(tips);
			food.setTips(tips);
		}
		//pictrue��url
		pattern1=Pattern.compile("id=\"image-url\"\\s([\\s\\S]+?)/>");
		matcher1=pattern1.matcher(resultString);
		isFind = matcher1.find();
		//System.out.println(isFind);
		if(isFind){
			System.out.println(matcher1.group(0).trim());
			String pictureurl=matcher1.group(0).replace(" ", "");
			pictureurl=pictureurl.substring(21,pictureurl.length()-3);
			pictureurl=deleteCharString0(pictureurl,'>');
			String pathString=pictureurl.substring(pictureurl.lastIndexOf('/')+1,pictureurl.length());
			try {
				food.setPictrueurl("C:\\Users\\xuwenhao\\Desktop\\pictrue\\"+pathString);
				download(pictureurl,"C:\\Users\\xuwenhao\\Desktop\\pictrue\\"+pathString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Dao dao=new Dao();
		try {
			dao.addGoddess(food);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void download(String _url,String path) throws Exception{


		try { 
		// ����URL  
		       URL url = new URL(_url);  
		       // ������  
		       URLConnection con = url.openConnection();  
		       //��������ʱΪ5s  
		       con.setConnectTimeout(5*1000);  
		       // ������  
		       InputStream is = con.getInputStream();  
		     
		       // 1K�����ݻ���  
		       byte[] bs = new byte[1024];  
		       // ��ȡ�������ݳ���  
		       int len;  
		       // ������ļ���  
		      File sf=new File(path);  
		     
		      OutputStream os = new FileOutputStream(sf);  
		       // ��ʼ��ȡ  
		       while ((len = is.read(bs)) != -1) {  
		         os.write(bs, 0, len);  
		       }  
		       // ��ϣ��ر���������  
		       os.close();  
		       is.close();  


		} catch (IOException e) {
		}        
		}
	public static String deleteCharString0(String sourceString, char chElemData) {
        String deleteString = "";
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != chElemData) {
                deleteString += sourceString.charAt(i);
            }
        }
        return deleteString;
    }
	public static String SendGet(String url) {
		// ����һ���ַ��������洢��ҳ����
		String result = "";
		// ����һ�������ַ�������
		BufferedReader in = null;
		try {
			// ��stringת��url����
			URL realUrl = new URL(url);
			// ��ʼ��һ�����ӵ��Ǹ�url������
			URLConnection connection = realUrl.openConnection();
			// ��ʼʵ�ʵ�����
			connection.connect();
			// ��ʼ�� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			// ������ʱ�洢ץȡ����ÿһ�е�����
			String line;
			while ((line = in.readLine()) != null) {
				// ����ץȡ����ÿһ�в�����洢��result����
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally���ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
