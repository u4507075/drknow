/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import html.Casecontent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import machinelearning.Machinelearning;
import machinelearning.Variable;
import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

/**
 *
 * @author Piyapong
 */
@WebServlet(name = "Database", urlPatterns = {"/Database"})
public class Database extends HttpServlet {

    //private DataSource datasource = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //public static String dbname = "jdbc/sql6141281";
    //DataSource pool;  // Database connection pool
    
    @Override
    public void init( ) throws ServletException {

        
            /*
            // Create a JNDI Initial context to be able to lookup the DataSource
            InitialContext ctx;
            try {
            ctx = new InitialContext();
            // Lookup the DataSource, which will be backed by a pool
            //   that the application server provides.
            pool = (DataSource)ctx.lookup("jdbc/sql6141281");
            //pool = (DataSource)ctx.lookup("jdbc/heroku_8bf06d32b6e4f42");
            
            
            
            //pool = (DataSource)ctx.lookup("jdbc/myDatasource");
            } catch (NamingException ex) {
            
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }*/


    }
    
    public void getConnection111()
    {
        //Connection c = (Connection)request.getSession().getAttribute("connection");
        //if(c==null)
        //{
        /*
        String dbtype = System.getenv("DRKNOW_DATABASE_TYPE");
        String url = System.getenv("DRKNOW_DATABASE_URL");
        String username = System.getenv("DRKNOW_USERNAME");
        String password = System.getenv("DRKNOW_PASSWORD");
        String port = System.getenv("DRKNOW_PORT");
        String dbname = System.getenv("DRKNOW_DATABASE_NAME");
        String dbUrl = "jdbc:"+dbtype+"://" + url+ ':' + port + "/"+dbname;
        connectionPool = new BasicDataSource();
        connectionPool.setUsername(username);
        connectionPool.setPassword(password);
        connectionPool.setDriverClassName("com.mysql.jdbc.Driver");
        connectionPool.setUrl(dbUrl);
        connectionPool.setInitialSize(5);
        /*
        Context ctx;
        try {
            ctx = new InitialContext();
            ctx.bind("jdbc/mypool", connectionPool);
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        try {
            Connection con = connectionPool.getConnection();
            //request.getSession().setAttribute("connection", con);
            //return con;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        //else
        {
            //return c;
        }*/
        //return c;
    }
    /*
    public String getConnection1(){
        try {
            //String dbUrl = System.getenv("CLEARDB_DATABASE_URL");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        String dbtype = System.getenv("DRKNOW_DATABASE_TYPE");
        String url = System.getenv("DRKNOW_DATABASE_URL");
    String username = System.getenv("DRKNOW_USERNAME");
    String password = System.getenv("DRKNOW_PASSWORD");
    String port = System.getenv("DRKNOW_PORT");
    String dbname = System.getenv("DRKNOW_DATABASE_NAME");
    String dbUrl = "jdbc:"+dbtype+"://" + url+ ':' + port + "/"+dbname;

        try {
            DriverManager.getConnection(dbUrl, username, password);
            return "good";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }
    public Connection getConnection2(){
    try {
            //String dbUrl = System.getenv("CLEARDB_DATABASE_URL");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        String dbtype = System.getenv("DRKNOW_DATABASE_TYPE");
        String url = System.getenv("DRKNOW_DATABASE_URL");
    String username = System.getenv("DRKNOW_USERNAME");
    String password = System.getenv("DRKNOW_PASSWORD");
    String port = System.getenv("DRKNOW_PORT");
    String dbname = System.getenv("DRKNOW_DATABASE_NAME");
    String dbUrl = "jdbc:"+dbtype+"://" + url+ ':' + port + "/"+dbname;

        try {
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
        }
        return null;
    }
*/
    //public static BasicDataSource connectionPool;
    /*
    public Connection getConnection() throws SQLException
    {
        //return pool.getConnection();
        /*
        DataSource source = null;
        try {
            source = (DataSource)new InitialContext().lookup("DataSource");
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = source.getConnection();
        return con;*/
        
        /*
        Context ctx = null;
        DataSource ds = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ds = (DataSource)ctx.lookup("jdbc/mypool");
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //return ds.getConnection();
        //return pool.getConnection();
        
        //return getConnection2();
        
    
    
    
    
    /*
        if(connectionPool==null)
        {
            getConnection111();
        }
        return connectionPool.getConnection();
    }*/
    
    
    
    public static Connection conn;
    public static DataSource ds;
    public Connection getConnection() {

    try {
        if (ds == null) {
            //ds = setupDataSource();
            //ds = setuplocalDataSource();
            ds = setupMelbuniDataSource();
        }
        if (conn == null || conn.isClosed()) {
            conn = ds.getConnection();
        }

        return conn;
    } catch (SQLException e) {

        e.printStackTrace();
    }
    return null;

}

private DataSource setupDataSource() {
    ComboPooledDataSource cpds = new ComboPooledDataSource();
    String dbtype = System.getenv("DRKNOW_DATABASE_TYPE");
    String url = System.getenv("DRKNOW_DATABASE_URL");
    String username = System.getenv("DRKNOW_USERNAME");
    String password = System.getenv("DRKNOW_PASSWORD");
    String port = System.getenv("DRKNOW_PORT");
    String dbname = System.getenv("DRKNOW_DATABASE_NAME");
    String driver = "com.mysql.jdbc.Driver";
    String dbUrl = "jdbc:"+dbtype+"://" + url + ":" + port + "/" + dbname
            + "?autoReconnect=true";
    try {
        cpds.setDriverClass(driver);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    }
    cpds.setJdbcUrl(dbUrl);
    cpds.setUser(username);
    cpds.setPassword(password);
    cpds.setMinPoolSize(1);
    cpds.setInitialPoolSize(1);
    cpds.setAcquireIncrement(1);
    cpds.setMaxPoolSize(20);

    return cpds;

}

private DataSource setuplocalDataSource() {
    ComboPooledDataSource cpds = new ComboPooledDataSource();
    String dbtype = "mysql";
    String url = "localhost";
    String username = "root";
    String password = "root";
    String port = "3307";
    String dbname = "drknow";
    String driver = "com.mysql.jdbc.Driver";
    String dbUrl = "jdbc:"+dbtype+"://" + url + ":" + port + "/" + dbname
            + "?autoReconnect=true";
    try {
        cpds.setDriverClass(driver);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    }
    cpds.setJdbcUrl(dbUrl);
    cpds.setUser(username);
    cpds.setPassword(password);
    cpds.setMinPoolSize(1);
    cpds.setInitialPoolSize(1);
    cpds.setAcquireIncrement(1);
    cpds.setMaxPoolSize(20);

    return cpds;

}

private DataSource setupMelbuniDataSource() {
    ComboPooledDataSource cpds = new ComboPooledDataSource();
    String dbtype = "mysql";
    String url = "128.250.106.78";
    String username = "pkhumrin";
    String password = "pkhumrin!x";
    String port = "3306";
    String dbname = "drknow";
    String driver = "com.mysql.jdbc.Driver";
    String dbUrl = "jdbc:"+dbtype+"://" + url + ":" + port + "/" + dbname
            + "?autoReconnect=true&useSSL=true";
    try {
        cpds.setDriverClass(driver);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    }
    cpds.setJdbcUrl(dbUrl);
    cpds.setUser(username);
    cpds.setPassword(password);
    cpds.setMinPoolSize(1);
    cpds.setInitialPoolSize(1);
    cpds.setAcquireIncrement(1);
    cpds.setMaxPoolSize(20);

    return cpds;

}
/*
    @Override
    public void destroy() {
     if(connectionPool==null)
        {
         try {
             connectionPool.close();
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }
*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");

            if(action.equals("yearlevel"))
            {
                String yearlevel = request.getParameter("yearlevel");
                submityearlevel(request, yearlevel);
            }
            else if(action.equals("survey"))
            {
                String[] data = request.getParameter("data").split(",");
                submitsurvey(request, data);
            }
            else if(action.equals("test"))
            {
                
            }
            
        }
    }
    
    private java.sql.Timestamp getCurrentTimeStamp() {

	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());

    }
    public void login(String action, String userid, String sessionid)
    {
        Connection c = null;
        try {
                //c = pool.getConnection();
                c = getConnection();


                // the mysql insert statement
                String query = " insert into login (userid, sessionid, time, action)"
                  + " values (?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = c.prepareStatement(query);
                //userid 12 digits
                preparedStmt.setString (1, userid);
                //sessionid
                preparedStmt.setString (2, sessionid);
                //time stamp
                preparedStmt.setTimestamp (3, getCurrentTimeStamp());
                //login,logout
                preparedStmt.setString   (4, action);

                // execute the preparedstatement
                preparedStmt.executeUpdate();
                //System.out.println("done");
                 
             } catch (SQLException ex) {
                 Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
             }
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
    }
    public void insertfinishedcase(String userid, String sessionid, String caseid, String correctdiagnosis, String totalscore, String htmlcontent)
    {
        Connection c = null;
        try {
                //c = pool.getConnection();
                c = getConnection();


                // the mysql insert statement
                String query = " insert into caselist (userid, sessionid, time, caseid, diagnosis, totalscore, html)"
                  + " values (?, ?, ?, ?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = c.prepareStatement(query);
                //userid 12 digits
                preparedStmt.setString (1, userid);
                //sessionid
                preparedStmt.setString (2, sessionid);
                //time stamp
                preparedStmt.setTimestamp (3, getCurrentTimeStamp());
                //login,logout
                preparedStmt.setString   (4, ""+(Integer.parseInt(caseid)+1));
                //correct diagnosis
                preparedStmt.setString   (5, correctdiagnosis);
                //total score
                preparedStmt.setString   (6, totalscore);
                //html content for the whole case
                preparedStmt.setString   (7, htmlcontent);

                // execute the preparedstatement
                preparedStmt.executeUpdate();
                //System.out.println("done");
                 
             } catch (SQLException ex) {
                 Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
             }
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
    }
    public String getfinisedcase(String userid, String caseid)
    {
        //StringBuffer b = new StringBuffer();
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = "SELECT html FROM `caselist` WHERE `userid` = ? AND `caseid` = ?";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, userid);
            //caseid from 0
            preparedStmt.setString (2, caseid);
            
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                return rs.getString("html");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
        
        return "";
    }
    public String getfinisedcaselist(String userid)
    {
        /*
          <ul class='sticky'>
            <li class='sticky'>
              <a class='sticky' href='' onclick='return getPerformancehistory(0);'>
                <h2 class='sticky'>Case 001</h2>
                <p class='sticky'>Appendicitis</p>
                <p class='sticky'>Your score: 1200</p>
                <p2 class='sticky'>Click to view full result</p2>
              </a>
            </li>
        </ul>
        */
        String template = "<li class='sticky'>\n" +
                "              <a class='sticky' href='' onclick=\"%clickevent\">\n" +
                "                <h2 class='sticky'>%title</h2>\n" +
                "                <p class='sticky'>%diagnosis</p>\n" +
                "                <p class='sticky'>%totalscore</p>\n" +
                "                <p class='sticky'>%star</p>\n" +
                "                <p class='sticky'></p>\n" +
                "                <p2 class='sticky'>%option</p2>\n" +
                "              </a>\n" +
                "            </li>\n";
        StringBuilder b = new StringBuilder();
        Connection c = null;
        int lastfinishedcase = 0;
        b.append("<ul class='sticky'>");
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = "SELECT * FROM `caselist` WHERE `userid` = ? ORDER BY `caseid` ASC";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, userid);
            
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                
                String s = template;
                s = s.replace("%clickevent", "getPerformancehistory("+rs.getString("caseid")+"); return false;");
                lastfinishedcase = Integer.parseInt(rs.getString("caseid"));
                s = s.replace("%title", "Case "+(lastfinishedcase));
                s = s.replace("%diagnosis", rs.getString("diagnosis"));
                try
                {
                int totalscore = Integer.parseInt(rs.getString("totalscore"));
                s = s.replace("%totalscore", "Total score: "+totalscore);
                
                String stars = "";
                int times = 0;
                if(totalscore>90)
                {
                    times = 3;
                }
                else if(totalscore>40)
                {
                    times = 2;
                }
                else if(totalscore>20)
                {
                    times = 1;
                }
                for(int i=0;i<times;i++)
                {
                    stars = stars+"<div style='margin-right: 5px; display: inline;'><img src='img/star.png'></div>";
                }
                s = s.replace("%star", stars);
                }
                catch(Exception e)
                {
                    s = s.replace("%totalscore", "");
                    s = s.replace("%star", "");
                }
                s = s.replace("%option", "Click to view full result");

                /*
                if(lastfinishedcase%2==0)
                {
                    s = s.replace("%style", "style='"+
                                "-o-transform:rotate(4deg);\n" +
                                "  -webkit-transform:rotate(4deg);\n" +
                                "  -moz-transform:rotate(4deg);\n" +
                                "  position:relative;\n" +
                                "  top:5px;\n" +
                                "  background:#cfc;"
                                +"'");
                }
                else if(lastfinishedcase%3==0)
                {
                    s = s.replace("%style","style='"+
                                "-o-transform:rotate(-3deg);\n" +
                                "  -webkit-transform:rotate(-3deg);\n" +
                                "  -moz-transform:rotate(-3deg);\n" +
                                "  position:relative;\n" +
                                "  top:-5px;\n" +
                                "  background:#ccf;"
                            +"'");
                }
                else
                {
                    s = s.replace("%style", "");
                }
                */
                b.append(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
        
        if(lastfinishedcase<Variable.TOTALCASE)
        {
            String s = template;
            s = s.replace("%clickevent", "selectcase("+(lastfinishedcase)+")");
            s = s.replace("%title", "Case "+(lastfinishedcase+1));
            s = s.replace("%diagnosis", "??????");
            s = s.replace("%totalscore", "");
            s = s.replace("%star", "");
            s = s.replace("%option", "Click to play");
            b.append(s);
            //b.append("<button class=\"myButton\" style=\"display: inline; width: 100%; background:#4CB5F5\" onclick='selectcase(").append(lastfinishedcase+1).append(")'>Go to case ").append(lastfinishedcase+2).append("</button>");
        }
        b.append("</ul>");
        
        if(lastfinishedcase==Variable.TOTALCASE)
        {
            b.append("<div class='context' style='position: absolute; bottom:0; width:800px'>"
                    + "<br><div>Congratulations! You have done all cases. Thank you very much for your participation. Hope you have a good time in my hospital. If you have any suggestions, please let me know through the 'feedback' link.</div>"
                    + "<br><div class='context' style='float:right;'>-- Dr Know --</div>"
                    + "</div>");
        }
        
        return b.toString();
    }
    public void startorendcase(String action, String userid, String sessionid, String caseid)
    {
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = " insert into selectcase (userid, sessionid, time, caseid, action)"+ " values (?, ?, ?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, userid);
            //sessionid
            preparedStmt.setString (2, sessionid);
            //time stamp
            preparedStmt.setTimestamp (3, getCurrentTimeStamp());
            //caseid
            preparedStmt.setString   (4, ""+(Integer.parseInt(caseid)+1));
            //start,end
            preparedStmt.setString   (5, action);
            
            // execute the preparedstatement
            preparedStmt.executeUpdate();
            //System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
    }
    
    public void selectfeature(String userid, String sessionid, String caseid, String step, String featureid)
    {
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = " insert into selectfeature (userid, sessionid, time, caseid, step, featureid)"+ " values (?, ?, ?, ?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, userid);
            //sessionid
            preparedStmt.setString (2, sessionid);
            //time stamp
            preparedStmt.setTimestamp (3, getCurrentTimeStamp());
            //caseid
            preparedStmt.setString   (4, ""+(Integer.parseInt(caseid)+1));
            //hx,pe,lab
            preparedStmt.setString   (5, step);
            //featureid
            preparedStmt.setString(6, featureid);
            // execute the preparedstatement
            preparedStmt.executeUpdate();
            //System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
    }
    
    private void submityearlevel(HttpServletRequest request, String yearlevel)
    {
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = " insert into yearlevel (userid, time, yearlevel)"+ " values (?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, request.getSession().getAttribute("userid").toString());
            //time stamp
            preparedStmt.setTimestamp (2, getCurrentTimeStamp());
            //yearlevel
            preparedStmt.setString   (3, yearlevel);

            // execute the preparedstatement
            preparedStmt.executeUpdate();
            //System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
    }
    private void submitsurvey(HttpServletRequest request, String[] data)
    {
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = " insert into survey (userid, sessionid, time, caseid, q1, q2, q3, q4, q5, q6, q7, q8, q9)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, request.getSession().getAttribute("userid").toString());
            //sessionid
            preparedStmt.setString (2, request.getSession().getAttribute("sessionid").toString());
            //time stamp
            preparedStmt.setTimestamp (3, getCurrentTimeStamp());
            //caseid
            preparedStmt.setString (4, ""+(Integer.parseInt(request.getSession().getAttribute("caseid").toString())+1));
            
            for(int i=0;i<data.length;i++)
            {
                preparedStmt.setString (5+i, data[i]);
            }

            // execute the preparedstatement
            preparedStmt.executeUpdate();
            //System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
    }

    public boolean checkYearlevelrecord(String userid)
    {
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = " insert into yearlevel (userid, time, yearlevel)"+ " values (?, ?, ?)";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS COUNT FROM `yearlevel` WHERE `userid`='"+userid+"'");
            int num = 0;
            while(rs.next())
            {
                num = rs.getInt("COUNT");
            }
            if(num==0)
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
        return false;
    }
    public void selectdiagnosis(String userid, String sessionid, String caseid, String step, String[] ddx, String[] p, String value)
    {
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = " insert into selectdiagnosis (userid, sessionid, time, caseid, step, dx1, dx2, dx3, p1, p2, p3, value)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, userid);
            //sessionid
            preparedStmt.setString (2, sessionid);
            //time stamp
            preparedStmt.setTimestamp (3, getCurrentTimeStamp());
            //caseid
            preparedStmt.setString   (4, ""+(Integer.parseInt(caseid)+1));
            //cc,hx,pe,lab,final
            preparedStmt.setString   (5, step);
            //ddx 1 - 3
            for(int i=0;i<ddx.length;i++)
            {
                preparedStmt.setString(6+i, ddx[i]);
            }
            for(int i=0;i<p.length;i++)
            {
                preparedStmt.setString(9+i, p[i]);
            }
            //record array of value
            preparedStmt.setString   (12, value);
            // execute the preparedstatement
            preparedStmt.executeUpdate();
            //System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
    }
    public void selectlevelofconfidence(String userid, String sessionid, String caseid, String confident)
    {
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = " insert into confident (userid, sessionid, time, caseid, confident)"+ " values (?, ?, ?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, userid);
            //sessionid
            preparedStmt.setString (2, sessionid);
            //time stamp
            preparedStmt.setTimestamp (3, getCurrentTimeStamp());
            //caseid
            preparedStmt.setString   (4, ""+(Integer.parseInt(caseid)+1));
            //level of confident
            preparedStmt.setString   (5, confident);
            
            // execute the preparedstatement
            preparedStmt.executeUpdate();
            //System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
    }
    public void savefeedback(String userid, String sessionid, String caseid, String topic, String value, String score)
    {
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = " insert into feedback (userid, sessionid, time, caseid, topic, value, score)"+ " values (?, ?, ?, ?, ?, ?, ?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, userid);
            //sessionid
            preparedStmt.setString (2, sessionid);
            //time stamp
            preparedStmt.setTimestamp (3, getCurrentTimeStamp());
            //caseid
            preparedStmt.setString   (4, ""+(Integer.parseInt(caseid)+1));
            //topic
            preparedStmt.setString   (5, topic);
            //value
            preparedStmt.setString   (6, value);
            //score
            preparedStmt.setString   (7, score);
            // execute the preparedstatement
            preparedStmt.executeUpdate();
            //System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
    }

    public HashMap getmyrecord(String userid)
    {
        //StringBuffer b = new StringBuffer();
        HashMap<String,String> map = new HashMap<>();
        Connection c = null;
        try {
            //c = pool.getConnection();
            c = getConnection();
            
            // the mysql insert statement
            String query = "SELECT * FROM `caselist` WHERE userid = ? ORDER BY caseid";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = c.prepareStatement(query);
            //userid 12 digits
            preparedStmt.setString (1, userid);
            
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                map.put(rs.getString("caseid"), rs.getString("totalscore"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }  
        finally {
            if (c != null) 
              try {c.close();} catch (SQLException e) {}
            }
        
        return map;
    }
    /*
    public String[][] getCase(int num)
    {
        Casecontent c = new Casecontent();
        try {
            return c.getCasefromdb(num, pool.getConnection());
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLdatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    */
    public static void main(String[] args)
    {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
