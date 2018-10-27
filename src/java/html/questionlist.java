/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import servlet.Database;

/**
 *
 * @author Piyapong
 */
public class questionlist {

    //NBabdominalpain3
    /*
    String[][] painlist = { {"2","Pain at left abdomen"},
                            {"3","Pain at right abdomen"},
                            {"4","Pain at upper abdomen"},
                            {"5","Pain at lower abdomen"},
                            {"6","Pain at posterior abdomen"},
                            {"7","Pain at a specific position"},
                            {"8","Pain duration (hr)"},
                            {"9","Pain radiation"},
                            {"10","Pain quality"},
                            {"11","Pain severity"},
                            {"12","Pain progression"},
                            {"13","Pain speed"},
                            {"14","Pain episodic pattern"},
                            {"15","Pain position before migration"},
                            {"16","Pain position at left abdomen before migration"},
                            {"17","Pain position at right abdomen before migration"},
                            {"18","Pain position at upper abdomen before migration"},
                            {"19","Pain position at lower abdomen before migration"},
                            {"20","Pain position at posterior abdomen before migration"},
                            {"21","Pain characterised by provoking factor"},
                            {"22","Pain characterised by relieving factor"},
                            {"23","Pain characterised relieving by medication"}
                            };
    String[][] gilist = {   {"24","Nausea"},
                            {"25","Vomitting"},
                            {"27","Loss of appetite"},
                            {"29","Constipation"},
                            {"30","Diarrhea"},
                            {"31","Melena"},
                            {"32","Blood stool"}
                            };
    String[][] kublist = {
        {"34","Dysuria"},
        {"35","Urine color"},
        {"36","Urine transparency"},
        {"37","Frequency of micturition"},
        {"38","Urinary hestiation"},
        {"39","Urgency of micturition"},
        {"40","Urine odor"},
        {"41","Gross hematuria"}
    };
    String[][] obgynlist = {    
        {"42","Pregnancy"},
        {"43","LMP"},
        {"44","Menstruation"},
        {"45","Contraception"},
        {"46","Vaginal discharge"},
        {"47","Vaginal bleeding"}     
    };
    String[][] syslist = {  {"26","Sweating"},
        {"28","Fever"},
        {"33","Rigor"}
    };

    String[][] pelist = {  {"60","Pallor"},
        {"48","Abdominal tenderness at left abdomen"},
        {"49","Abdominal tenderness at right abdomen"},
        {"50","Abdominal tenderness at upper abdomen"},
        {"51","Abdominal tenderness at lower abdomen"},
        {"52","Abdominal tenderness at posterior abdomen"},
        {"53","Abdominal guarding"},
        {"54","Rebound tenderness"},
        {"55","Rovsing sign"},
        {"56","Psoas sign"},
        {"57","Obturator sign"},
        {"58","Murphy sign"},
        {"59","Bowel sound"}
    };
    
    String[][] vslist = {  {"61","Body temperature"},
        {"62","Pain score during movement"},
        {"63","Pain score during rest"}
    };
    
    String[][] cbclist = {  {"64","WCC"},
        {"65","Neutrophil"},
        {"66","Band neutrophil"}
    };
    
    String[][] ualist = {  {"67","UA leukocyte"},{"68","UA blood"}
    };

    String[][] lablist = {  {"69","CRP"},
        {"70","PCR for chlamydia"},
        {"71","PCR for gonorrhoea"},
        {"72","Urine pregnancy test"},
        {"73","Serum hcg"}
    };

    /*
    String[][] painlist;
    String[][] gilist;
    String[][] kublist;
    String[][] obgynlist;
    String[][] syslist;
    String[][] pelist;
    String[][] vslist;
    String[][] cbclist;
    String[][] ualist;
    String[][] lablist;
    */
    String[] group = {"pain","gi","kub","obgyn","sys","pe","vs","cbc","ua","lab"};
    String[][][] list = new String[group.length][][];
    public void getQuestionfromdb()
    {
        
        Database db = new Database();
        try {
            db.init();
        } catch (ServletException ex) {
            Logger.getLogger(questionlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection c = null;
        c = db.getConnection();

        try {
            Statement st = c.createStatement();
            //p = c.prepareStatement(query);
            //p2 = c.prepareStatement(query2);
            //for(int i=0;i<group.length;i++)
            
            //p.setString(1, group[i]);
            //ResultSet rs = p.executeQuery();
            //ResultSet rs = st.executeQuery("SELECT COUNT(`group`) FROM `case` WHERE `group`='pain'");
            //list[i] = new String[rs.getInt(1)][2];
            //String[][] ppp = new String[22][2];
            
            //p2.setString(1, group[i]);
            //ResultSet rs2 = p2.executeQuery();
            
            for(int j=0;j<group.length;j++)
            {
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS COUNT FROM `case` WHERE `group`='"+group[j]+"'");
            while(rs.next())
            {
                list[j] = new String[rs.getInt("COUNT")][2];
            }
            
            ResultSet rs2 = st.executeQuery("SELECT `id`,`topic` FROM `case` WHERE `group`='"+group[j]+"'");
            int i = 0;
            while(rs2.next())
            {
                list[j][i][0] = ""+rs2.getInt("id");
                list[j][i][1] = rs2.getString("topic");
                i++;
            }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        if (c != null)
            try {c.close();} catch (SQLException e) {}
        }

    }
    //public questionlist(Connection c)
    {
        //String query = "SELECT COUNT(`group`) FROM `case` WHERE `group` = ?";
        //PreparedStatement p;
        
        //String query2 = "SELECT `id`,`topic` FROM `case` WHERE `group` = ?";
        //PreparedStatement p2;
        /*
        try {
            Statement st = c.createStatement();
            //p = c.prepareStatement(query);
            //p2 = c.prepareStatement(query2);
            //for(int i=0;i<group.length;i++)
            {
                //p.setString(1, group[i]);
                //ResultSet rs = p.executeQuery();
                //ResultSet rs = st.executeQuery("SELECT COUNT(`group`) FROM `case` WHERE `group`='pain'");
                //list[i] = new String[rs.getInt(1)][2];
                String[][] ppp = new String[22][2];
                
                //p2.setString(1, group[i]);
                //ResultSet rs2 = p2.executeQuery();
                ResultSet rs2 = st.executeQuery("SELECT id,topic FROM case WHERE group='pain'");
                int j=0;
                while(rs2.next())
                {
                    ppp[j][0] = ""+rs2.getInt("id");
                    ppp[j][1] = rs2.getString("topic");
                    j++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(questionlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    /*
    //NBabdominalpain2
    String[][] painlist = { {"2","Pain position"},
                            {"4","Pain duration (hr)"},
                            {"5","Pain radiation"},
                            {"6","Pain quality"},
                            {"7","Pain severity"},
                            {"8","Pain progression"},
                            {"10","Pain episodic"},
                            {"11","Pain position before migration"},
                            {"12","Pain characterised by provoking factor"},
                            {"13","Pain characterised by relieving factor"},
                            {"14","Pain characterised relieving by medication"}
                            };
    String[][] gilist = {   {"15","Nausea"},
                            {"16","Vomitting"},
                            {"18","Loss of appetite"},
                            {"20","Constipation"},
                            {"21","Diarrhea"},
                            {"22","Melena"},
                            {"23","Blood stool"}
                            };
    String[][] kublist = {
        {"25","Dysuria"},
        {"26","Urine color"},
        {"27","Urine transparency"},
        {"28","Frequency of micturition"},
        {"29","Urinary hestiation"},
        {"30","Urgency of micturition"},
        {"31","Urine odor"},
        {"32","Gross hematuria"}
    };
    String[][] obgynlist = {    
        {"33","Pregnancy"},
        {"34","LMP"},
        {"35","Menstruation"},
        {"36","Contraception"},
        {"37","Vaginal discharge"},
        {"38","Vaginal bleeding"}     
    };
    String[][] syslist = {  {"17","Sweating"},
        {"19","Fever"},
        {"24","Rigor"}
    };

    String[][] pelist = {  {"47","Pallor"},
        {"39","Abdominal tenderness position"},
        {"40","Abdominal guarding"},
        {"41","Rebound tenderness"},
        {"42","Rovsing sign"},
        {"43","Psoas sign"},
        {"44","Obturator sign"},
        {"45","Murphy sign"},
        {"46","Bowel sound"}
    };
    
    String[][] vslist = {  {"48","Body temperature"},
        {"49","Pain score during movement"},
        {"50","Pain score during rest"}
    };
    
    String[][] cbclist = {  {"51","WCC"},
        {"52","Neutrophil"},
        {"53","Band neutrophil"}
    };
    
    String[][] ualist = {  {"54","UA leukocyte"},{"55","UA blood"}
    };

    String[][] lablist = {  {"56","CRP"},
        {"57","PCR for chlamydia"},
        {"58","PCR for gonorrhoea"},
        {"59","Urine pregnancy test"},
        {"60","Serum hcg"}
    };
*/
    public void writeHistorylist(PrintWriter out, String[][] casecontent)
    {
        //out.println("<div id = \"pilist\" style=\"overflow-y: scroll; height:400px; border: 3px solid #dddddd;\">");
        out.println("<div id = \"pilist\"\">");
        out.println("<ul class='list'>");
  	out.println("<li class='list'><a id = \"titlehistory\" class = \"titlelist\"></a></li>");
            addTopic(out, "pilist", "pi-1", casecontent, "Pain", list[0],"gethistory1");
            addTopic(out, "pilist", "pi-2", casecontent, "GI symptoms", list[1],"gethistory1");
            addTopic(out, "pilist", "pi-3", casecontent, "KUB symptoms", list[2],"gethistory1");
            addTopic(out, "pilist", "pi-4", casecontent, "OB & GYN history", list[3],"gethistory1");
            addTopic(out, "pilist", "pi-5", casecontent, "Systemic symptoms", list[4],"gethistory1");
            //addTopic(out, casecontent, "Physical examination", pelist);
            //addTopic(out, casecontent, "Vital signs", vslist);
            //addTopic(out, casecontent, "Complete blood count", cbclist);
            //addTopic(out, casecontent, "Urine analysis", ualist);
            //addTopic(out, casecontent, "Laboratory", lablist);
        out.println("</ul>");
        out.println("</div>");
    }
    public void writePElist(PrintWriter out, String[][] casecontent)
    {
        //out.println("<div id = \"pelist\" style=\"display: none; overflow-y: scroll; height:400px;\">");
        out.println("<div id = \"pelist\" style=\"display: none;\">");
        out.println("<ul class='list'>");
  	out.println("<li class='list'><a id = \"titlephysicalexam\" class = \"titlelist\"></a></li>");
            addTopic(out, "pelist", "pe-1", casecontent, "Physical examination", list[5],"gethistory2");
            addTopic(out, "pelist", "pe-2", casecontent, "Vital signs", list[6],"gethistory2");
        out.println("</ul>");
        out.println("</div>");
    }
    public void writeLablist(PrintWriter out, String[][] casecontent)
    {
        out.println("<div id = \"lablist\" style=\"display: none;\">");
        out.println("<ul class='list'>");
  	out.println("<li class='list'><a id = \"titlelab\" class = \"titlelist\"></a></li>");
            addTopic(out, "lablist", "lab-1", casecontent, "Complete blood count", list[7],"gethistory3");
            addTopic(out, "lablist", "lab-2", casecontent, "Urine analysis", list[8],"gethistory3");
            addTopic(out, "lablist", "lab-3", casecontent, "Laboratory", list[9],"gethistory3");
        out.println("</ul>");
        out.println("</div>");
    }
    private void addTopic(PrintWriter out, String id, String group, String[][] casecontent, String topic, String[][] topiclist, String method)
    {
        out.println("<ul class='list'>");
        out.println("<li class='list titlelist' style='cursor:pointer;' onclick=\"togglelist('"+id+"','"+group+"')\"><a class = \"title\">"+topic+" <span id='togglelist"+group+"' style='display:none; color:yellow; cursor:pointer; font-size:12px; position:relative; float:right; margin-right:10px;'>show</span></a></li>");
        for(int i=0;i<topiclist.length;i++)
        {
            int num = Integer.parseInt(topiclist[i][0]);
            String subtopic = topiclist[i][1];
            String answercode = casecontent[num][0];
            String answertext = casecontent[num][1];
            
            out.println("<li class='list "+group+"' style='display:none;'><a class = 'historylist' href='#' onclick='"+method+"(this, \""+answertext+"\","+num+",\""+answercode+"\");return false;'>"+subtopic+"</a></li>");
        }
        out.println("</ul>");
    }
}
