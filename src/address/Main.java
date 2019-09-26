package address;
import java.io.*;
import java.io.IOException;
import com.google.gson.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private String phoneNumber="";
    private String name="";
    private String s="";
    private String[] address=new String[10];
    public static final String myFirstAddress=
            "北京|天津|河北|山西|辽宁|吉林|黑龙江|上海|江苏|浙江|安徽|福建|江西|山东|河南|湖北|湖南|广东|海南|重庆|四川|贵州|云南|陕西|甘肃|青海";
    public static final String mysecondAdress=
            "阿坝|阿拉善|阿里|安康|安庆|鞍山|安顺|安阳|澳门|北京|白银|保定|宝鸡|保山|包头|巴中|北海|蚌埠|本溪|毕节|滨州|百色|亳州|重庆|成都|长沙|长春|沧州|常德|昌都|长治|常州|巢湖|潮州|承德|郴州|赤峰|池州|崇左|楚雄|滁州|朝阳|大连|东莞|大理|丹东|大庆|大同|大兴安岭|德宏|德阳|德州|定西|迪庆|东营|鄂尔多斯|恩施|鄂州|福州|防城港|佛山|抚顺|抚州|阜新|阜阳|广州|桂林|贵阳|甘南|赣州|甘孜|广安|广元|贵港|果洛|杭州|哈尔滨|合肥|海口|呼和浩特|海北|海东|海南|海西|邯郸|汉中|鹤壁|河池|鹤岗|黑河|衡水|衡阳|河源|贺州|红河|淮安|淮北|怀化|淮南|黄冈|黄南|黄山|黄石|惠州|葫芦岛|呼伦贝尔|湖州|菏泽|济南|佳木斯|吉安|江门|焦作|嘉兴|嘉峪关|揭阳|吉林|金昌|晋城|景德镇|荆门|荆州|金华|济宁|晋中|锦州|九江|酒泉|昆明|开封|兰州|拉萨|来宾|莱芜|廊坊|乐山|凉山|连云港|聊城|辽阳|辽源|丽江|临沧|临汾|临夏|临沂|林芝|丽水|六安|六盘水|柳州|陇南|龙岩|娄底|漯河|洛阳|泸州|吕梁|马鞍山|茂名|眉山|梅州|绵阳|牡丹江|南京|南昌|南宁|宁波|南充|南平|南通|南阳|那曲|内江|宁德|怒江|盘锦|攀枝花|平顶山|平凉|萍乡|莆田|濮阳|青岛|黔东南|黔南|黔西南|庆阳|清远|秦皇岛|钦州|齐齐哈尔|泉州|曲靖|衢州|日喀则|日照|上海|深圳|苏州|沈阳|石家庄|三门峡|三明|三亚|商洛|商丘|上饶|山南|汕头|汕尾|韶关|绍兴|邵阳|十堰|朔州|四平|绥化|遂宁|随州|宿迁|宿州|天津|太原|泰安|泰州|台州|唐山|天水|铁岭|铜川|通化|通辽|铜陵|铜仁|台湾|武汉|乌鲁木齐|无锡|威海|潍坊|文山|温州|乌海|芜湖|乌兰察布|武威|梧州|厦门|西安|西宁|襄樊|湘潭|湘西|咸宁|咸阳|孝感|邢台|新乡|信阳|新余|忻州|西双版纳|宣城|许昌|徐州|香港|锡林郭勒|兴安|银川|雅安|延安|延边|盐城|阳江|阳泉|扬州|烟台|宜宾|宜昌|宜春|营口|益阳|永州|岳阳|榆林|运城|云浮|玉树|玉溪|玉林|枣庄|扎赉特旗|扎鲁特旗|张家港|张家界|张家口|张掖|漳州|湛江|肇庆|昭通|郑州|镇海区|镇江|中山|中卫|舟山|珠海|驻马店|准格尔旗|卓尼|株洲|淄博|自贡|资阳";
    public String myThirdAddress="区|县|市";
    public String myForthAddress="街道|镇|乡";
    public String myFifthAddress="路|街|巷";
    public String mySixthAddress="号";
    public  void setName(String _name){
        name=_name;
    }
    public  void setPhoneNumber(String s0)
    {
        phoneNumber=s0;
    }
    public void init(){
        phoneNumber="";
        name="";
        s="";
        for(int i=0;i<=8;i++)
            address[i]="";
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
    }//从文件中读取数据

    public void writeFile(JsonArray arr) throws IOException{
        String outPutPath="D:\\IDEA2019\\IdeaProject\\addressbook\\output.txt";
        File file=new File(outPutPath);
        FileWriter writer=new FileWriter(file);
        String ansjon=arr.toString();
        JsonParser jsonParser=new JsonParser();
        JsonArray jsonArray=jsonParser.parse(ansjon).getAsJsonArray();
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        writer.write(gson.toJson(jsonArray));
        writer.flush();
        writer.close();
    }//向文件中写入结果

    public  Main getFirstAddress(Main res){
        String first="";
        Pattern pattern1=Pattern.compile(myFirstAddress);
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
    }//提取一级地址

    public  Main getSecondAddress(Main res){
        if(res.address[1].equals("上海")||res.address[1].equals("北京")||res.address[1].equals("天津"))
        {
            res.address[2]=res.address[1]+'市';
            return res;
        }
        String second="";
        Pattern pattern =Pattern.compile(mysecondAdress);
        Matcher matcher=pattern.matcher(res.s);
        if(matcher.find()){
            second=matcher.group();
            //System.out.println("second="+second);
            res.s=res.s.substring(second.length(),res.s.length());
            second +='市';
            //System.out.println("second="+second);
            if(res.s.charAt(0)=='市'){
                res.s=res.s.substring(1,res.s.length());
            }
        }
        res.address[2]=second;
        return res;
    }//提取二级地址

    public Main getThirdAddress(Main res){

        String third="";
        Pattern pattern=Pattern.compile(myThirdAddress);
        Matcher matcher=pattern.matcher(res.s);
        if(matcher.find()){
            int index=-1;
            //System.out.println("index="+index);
            for(int i=0;i<res.s.length();i++)
            {
                if(res.s.charAt(i)=='区'||res.s.charAt(i)=='县'||res.s.charAt(i)=='市')
                {
                    index=i;
                    break;
                }
            }

            third=res.s.substring(0,index+1);
            res.s=res.s.substring(index+1,res.s.length());
        }
        res.address[3]=third;
        return res;
    }//提取三级地址

    public Main getForthAddress(Main res){
        String forth="";
        Pattern pattern=Pattern.compile(myForthAddress);
        Matcher matcher=pattern.matcher(res.s);
        if(matcher.find()){
            forth=matcher.group();
            String[] ss=res.s.split(myForthAddress);
            forth=ss[0]+forth;
            res.s=ss[1];
        }
        res.address[4]=forth;
        return res;
    }//提取四级地址

    public Main getFifthAddress(Main res){
        String fifth="";
        Pattern pattern=Pattern.compile(myFifthAddress);
        Matcher matcher=pattern.matcher(res.s);
        if(matcher.find()){
            fifth=matcher.group();
            String[] ss=res.s.split(myFifthAddress);
            fifth=ss[0]+fifth;
            res.s=ss[1];

        }
        res.address[5]=fifth;
        return res;
    }//提取五级地址

    public Main getSixthAddress(Main res){
        String sixth="";
        Pattern pattern=Pattern.compile(mySixthAddress);
        Matcher matcher=pattern.matcher(res.s);
        if(matcher.find()){
            sixth=matcher.group();
            String[] ss=res.s.split(mySixthAddress);
            sixth=ss[0]+sixth;
            res.s=ss[1];
        }
        res.address[6]=sixth;
        return res;
    }//提取六级地址

    public static void main(String[] args) throws IOException{
        Main ans =new Main();//用于保存答案

        String ss=ans.readFile();
        String[] address=ss.split("\n");
        JsonArray ansarr=new JsonArray();
        for(String loop:address){
            ans.init();
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
            char level=s1[0].charAt(0);//获取难度等级
            //System.out.println(level);
            String _name="";
            for(int i=2;i<s2[0].length();i++){
                _name+=s2[0].charAt(i);
            }//提取姓名
            ans.setName(_name);
            ans.s=s2[1];
            JsonObject object=new JsonObject();
            object.addProperty("姓名",ans.name);
            object.addProperty("手机",ans.phoneNumber);
            JsonArray array=new JsonArray();
            if(level=='1')
            {
                ans=ans.getFirstAddress(ans);
                ans=ans.getSecondAddress(ans);
                ans=ans.getThirdAddress(ans);
                ans=ans.getForthAddress(ans);
                for(int i=0;i<ans.s.length();i++)
                {
                    if(ans.s.charAt(i)=='.')break;
                    ans.address[5]+=ans.s.charAt(i);
                }
                for(int i=1;i<=5;i++)
                {
                    array.add(ans.address[i]);
                }
            }//1!难度  提取五级地址
            if(level=='2')
            {
                ans=ans.getFirstAddress(ans);
                ans=ans.getSecondAddress(ans);
                ans=ans.getThirdAddress(ans);
                ans=ans.getForthAddress(ans);
                ans=ans.getFifthAddress(ans);
                ans=ans.getSixthAddress(ans);
                for(int i=0;i<ans.s.length();i++)
                {
                    if(ans.s.charAt(i)=='.')break;
                    ans.address[7]+=ans.s.charAt(i);
                }
                for(int i=1;i<=7;i++)
                {
                    array.add(ans.address[i]);
                }
            }//二级难度  提取七级地址
            object.add("地址",array);
            ansarr.add(object);
        }
        ans.writeFile(ansarr);
    }
}
