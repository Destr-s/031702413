package com.address;
import java.io.*;
public class Main {
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
    }

    public static void main(String[] args) throws IOException{
        Main fileRW=new Main();
        String ss=fileRW.readFile();
        System.out.println(ss);
    }
}
