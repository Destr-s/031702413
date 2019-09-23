package com.address;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private String phoneNumber="";
    private String name;
    private String s="";
    private String[] address=new String[10];
    public void setName(String _name){
        name=_name;
    }
    public void setPhoneNumber(String s0)
    {
        phoneNumber=s0;
    }

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
    public Main getFirstAddress(Main res){
        String first="";
        Pattern pattern1=Pattern.compile("北京|天津|河北|山西|辽宁|吉林|黑龙江|上海|江苏|浙江|安徽|福建|江西|山东|河南|湖北|湖南|广东|海南|重庆|四川|贵州|云南|陕西|甘肃|青海");
        Matcher matcher1=pattern1.matcher(res.s);
        if(matcher1.find()){
            first=matcher1.group();
            //System.out.println("1"+first);
            if(first.equals("上海")||first.equals("北京")||first.equals("天津"))
            {
                //System.out.println(1);
                if(res.s.charAt(2)=='市')
                {
                    res.s=res.s.substring(3,res.s.length()-1);
                }
                else{
                    res.s=res.s.substring(2,res.s.length()-1);
                }
            }
            else if(first.equals("黑龙江"))
           {
               if(res.s.charAt(3)=='省')
               {
                   first+=res.s.charAt(3);
                   res.s=res.s.substring(4,res.s.length()-1);
               }
               else
               {
                   first+='省';
                   res.s=res.s.substring(3,res.s.length()-1);
               }
           }
           else
           {
               //System.out.println(3);
               if(res.s.charAt(2)=='省'){
                   first+='省';
                   res.s=res.s.substring(3,res.s.length()-1);
               }
               else
               {
                   first+='省';
                   res.s=res.s.substring(2,res.s.length()-1);
               }
           }
        }
        Pattern pattern2=Pattern.compile("自治区");
        Matcher matcher2=pattern2.matcher(res.s);
        if(matcher2.find()){
            int index=res.s.indexOf("区");
            first=res.s.substring(0,index);
            res.s=res.s.substring(index+1,res.s.length()-1);
            //System.out.println("2"+first);
        }
        res.address[1]=first;
        return res;
    }
    public static void main(String[] args) throws IOException{
        Main ans =new Main();//用于保存答案
        String ss=ans.readFile();
        System.out.println(ss);
        String[] address=ss.split("\n");
        for(String loop:address){
            ans.s=loop;
            Pattern pattern=Pattern.compile("\\d{11}");
            Matcher matcher=pattern.matcher(ans.s);
            if(matcher.find())
            {
                ans.setPhoneNumber(matcher.group());
            }//寻找手机号并提取出来
            //System.out.println(ans.phoneNumber);
            String[] s1=ans.s.split("\\d{11}");
            ans.s=s1[0]+s1[1];
            String[] s2=ans.s.split(",");
            char level=ans.s.charAt(0);//获取难度等级
            //System.out.println(level);
            String _name="";
            for(int i=2;i<s2[0].length();i++){
                _name+=s2[0].charAt(i);
            }//提取姓名
            ans.setName(_name);
            //System.out.println(ans.name);
            ans.s=s2[1];
            ans=ans.getFirstAddress(ans);
            System.out.println(ans.name);
            System.out.println(ans.phoneNumber);
            System.out.println(ans.address[1]);
            System.out.println(ans.s);
        }
    }
}
