<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.lang.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>jfkdsjflskd</h1>
	<%

     //得到web根路径//绝对路径
     //getServletContext().getRealPath("/")得到web应用的根路径
     // D:\web\excel，“D:\web”是web应用的根路径，“excel”是根目录下的文件夹
     String Save_Location=getServletContext().getRealPath("");

     try{
           if (!(new java.io.File(Save_Location).isDirectory())) //如果文件夹不存在
            {
                   new java.io.File(Save_Location).mkdir();       //不存在 excel 文件夹，则建立此文件夹
                   new java.io.File(Save_Location+"gmcc\\").mkdir();     //创建excel文件夹下名为 gmcc 的文件夹
            }
            else   //存在excel文件夹，则直接建立此文件夹
            {    
                   new java.io.File(Save_Location+"gmcc\\").mkdir();      //创建 excel 文件夹下名为gmcc的文件夹
            }
       }catch(Exception e){
         e.printStackTrace();         //创建文件夹失败  

         //在链接中使用URLEncoder编码，传递中文参数。
         //接收页面可以使用getParameter()取得该参数，页面的charset=GB2312。
        
     }

     //在 gmcc 文件夹下新建 myfile.txt 文件
     java.io.File myFile = new java.io.File(Save_Location+"gmcc\\myfile.txt"); 
     java.io.FileOutputStream fout = null;
     try {
             fout = new java.io.FileOutputStream(myFile);
             byte b[]= "你好！".getBytes();
             fout.write(b);
             fout.flush();   //写入文件
             fout.close();   //关闭
     }
     catch (java.io.FileNotFoundException e) {
           e.printStackTrace();
     }
     catch (java.io.IOException ex) {
           ex.printStackTrace();
     }

%>
</body>
</html>