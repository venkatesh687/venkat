package com.cognizant;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        try {
               
               Class.forName("com.mysql.jdbc.Driver");
               
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
               
               out.println("Enter the table name:");
               
               String s = request.getParameter("user");
               String str = "SELECT * FROM "+s;
               PreparedStatement pst = conn.prepareStatement(str);
               
               ResultSet rs = pst.executeQuery(str);
               
               
               ResultSetMetaData rsmd = rs.getMetaData();
               int cols = rsmd.getColumnCount();
               out.println("<table border='2'><tr>");
               for(int i=1;i<=cols;i++)
               {
                     out.print("<th>"+rsmd.getColumnName(i)+"</th>");
               }
               out.println("</tr>");
               while(rs.next())
               {
                     out.println("<tr>");
                     
               for(int i=1;i<=cols;i++)
               {       
                         
                            out.print("<td>"+rs.getString(i)+"</td>");
                            
                     
               }
               out.println("</tr>");
               }
               out.println("</table>");
               rs.close();
               conn.close();
               pst.close();
        }
        catch(Exception e)
        {
            out.print(e.getMessage());
        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
