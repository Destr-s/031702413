package com.address;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private String phoneNumber="";
    public void setPhoneNumber(String s0)
    {
        phoneNumber=s0;
    }
    private String[] res=new String[10];
    public String readFile() throws IOException{
        String inputPath="D:\\IDEA2019\\IdeaProject\\addressbook\\input.txt";
        File file=new File(inputPath);
        FileReader reader =new FileReader(file);
        char[] ch=new char[10000];
        reader.read(ch);
        String st="";
        for(char c:ch){
            st+=c;
        }
        reader.close();
        return st;
    }//从文件中读取注释

    public static void main(String[] args) throws IOException{
        Main ans =new Main();//用于保存答案
        String ss=ans.readFile();
        System.out.println(ss);
        String[] address=ss.split("\n");
        for(String s:address){
            Pattern pattern=Pattern.compile("\\d{11}");
            Matcher matcher=pattern.matcher(s);
            if(matcher.find())
            {
                ans.setPhoneNumber(matcher.group());
            }//寻找手机号并提取出来
            System.out.println(ans.phoneNumber);

        }
    }
}
