<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Wrapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.io.*,java.lang.*"%> 

<%-- 	<%String path = request.getSession().getServletContext().getRealPath("");%> --%>
<%-- 	<h1><%= path%></h1> --%>
	<%
	Date date=new Date();
	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMddhhmmss");
	String temp=myFormatter.format(date)+".jpg";
	//out.print(temp);
	File newfile=new File(this.getServletContext().getRealPath("/")+"/uploads/original"+File.separator+temp);
	//out.print(newfile.getParent());
	HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(request);
		//newfile.createNewFile();
		
		BufferedInputStream BufInput=null;
		BufferedOutputStream BufOutput=null;
	 byte[] buffer = new byte[1024];
	try {
		if(!newfile.getParentFile().exists()){
			newfile.getParentFile().mkdir();
		}else{
			//if(!newfile.exists()){
				if(newfile.createNewFile()){
// 					out.print("文件创建成功");
					
				}else{
// 					out.print("文件创建成功失败");
				}
				
			//}else{
// 				out.println("文件已存在");
			//}
		}
		
		//HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
		BufInput=	new BufferedInputStream(new DataInputStream(request.getInputStream()));
		BufOutput=	new BufferedOutputStream(new DataOutputStream(new FileOutputStream(newfile,true)));
		ServletInputStream in=wrapper.getInputStream();
		//PrintStream ps =new PrintStream(new FileOutputStream(newfile,true));
		int s=0;
		while((s=in.read(buffer,0,1024))!=-1){
			BufOutput.write(buffer,0,s);
			}	
		
		//ps.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//response.sendRedirect("NewFile.jsp");
		
		}catch(IOException e){
			//response.sendRedirect("NewFile.jsp");
		}
		finally{
			BufOutput.close();
			BufInput.close();
			//response.sendRedirect("NewFile.jsp");
			
		}
	//String content=wrapper.getQueryString();
	//String filepath=newfile.getPath();
	out.println("{\"status\":1,\"message\":\"Success!\",\"filename\":\"" + newfile.getName() + "\"}");
	//out.println(newfile.getPath());
	 %>
<%-- 	 <h1><%= filepath%></h1> --%>
<%-- 	 <h2><%=content %></h2> --%>
