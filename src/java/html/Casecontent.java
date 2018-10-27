/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html;

import html.questionlist;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import machinelearning.Variable;
import servlet.Database;

/**
 *
 * @author Piyapong
 */
public class Casecontent {
    public static final int NUM_OF_FEATURE = 74;
//00@attribute age 		real
//01@attribute gender		{F,M}
//02@attribute pain-position	{N,'entire abdomen','upper abdomen','lower abdomen','left upper abdomen','right upper abdomen','left lower abdomen','right lower abdomen','posterior abdomen','left abdomen','right abdomen'}
//03@attribute pain-specific-position	{N,epigastrium,flank,periumbilical,suprapubic}
//04@attribute pain-duration	real
//05@attribute pain-raditation {N,back,epigastrium,flank,groin,leg,neck,suprapubic}
//06@attribute pain-quality	{aching,burning,colicky,crampying,dull,sharp,throbbing}
//07@attribute pain-severity	real
//08@attribute pain-progression	{constant,increase,decrease}
//09@attribute pain-speed		{gradually,suddenly}
//10@attribute pain-episodic	{intermittent,continual}
//11@attribute pain-position-before-migration	{N,'entire abdomen','upper abdomen','lower abdomen','left upper abdomen','right upper abdomen','left lower abdomen','right lower abdomen','posterior abdomen','left abdomen','right abdomen'}
//12@attribute pain-provoking-factor 	{N,movement,rest,sitting}
//13@attribute pain-relieving-factor	{N,rest,'supine position'}
//14@attribute pain-relieved-by-medication 	{Y,N}
//15@attribute nausea		{Y,N}
//16@attribute vomitting		{Y,N}
//17@attribute sweating		{Y,N}
//18@attribute loss-of-appetite		{Y,N}
//19@attribute fever		{Y,N}
//20@attribute consipation		{Y,N}
//21@attribute diarrhea		{Y,N}
//22@attribute melena		{Y,N}
//23 blood-stool		{Y,N}
//24@attribute rigor		{Y,N}
//25@attribute dysuria		{Y,N}
//26@attribute urine-color		{normal,brown,dark,pink,yellow}
//27@attribute urine-transparency	{cloudy}
//28@attribute urine-frequency	{N,increase}
//29@attribute urine-hesitation	{Y,N}
//30@attribute urgency-micturition	{Y,N}
//31@attribute urine-odor		{Y,N}
//32@attribute gross-hematuria	{Y,N}
//33@attribute pregnancy		{Y,N}
//34@attribute lmp			real
//35@attribute menstruation	{regular,irregular}
//36@attribute contraception	{Y,N}
//37@attribute vaginal-discharge	{Y,N}
//38@attribute vaginal-bleeding	{Y,N}
//39@attribute abdominal-tenderness	{N,'entire abdomen','upper abdomen','lower abdomen','left upper abdomen','right upper abdomen','left lower abdomen','right lower abdomen','posterior abdomen','left abdomen','right abdomen',epigastrium,flank,periumbilical,suprapubic,loid,pelvic}
//40@attribute guarding		{Y,N}
//41@attribute rebound-tenderness	{Y,N}
//42@attribute rovsing-sign	{Y,N}
//43@attribute psoas-sign		{Y,N}
//44@attribute obturator-sign	{Y,N}
//45@attribute murphy-sign		{Y,N}
//46@attribute bowel-sound		{Y,N}
//47@attribute pale		{Y,N}
//48@attribute temperature		real
//49@attribute pain-score-movement	real
//50@attribute pain-score-rest		real
//51@attribute wcc			real
//52@attribute neutrophil		real
//53@attribute band		real
//54@attribute ua-leukocyte	{Y,N}
//55@attribute ua-blood		{Y,N}
//56@attribute crp			real
//57@attribute chlamydia		{Y,N}
//58@attribute gonorrhoea		{Y,N}
//59@attribute upt			{Y,N}
//60@attribute hcg			{Y,N}
    
    /*
    ArrayList l = new ArrayList();
    public Casecontent()
    {
        l.add(case001);
        l.add(case002);
        l.add(case003);
        l.add(case004);
        l.add(case005);
    }
    public String[][] getCase(int num)
    {
        return (String[][])l.get(num);
    }
    */
    public String[][] getCasefromdb(HttpServletRequest request, int num) throws SQLException
    {
        HashMap hm = new HashMap();
        HashMap hmd = new HashMap();
        String[][] casex = null;
        Database db = new Database();
        try {
            db.init();
        } catch (ServletException ex) {
            Logger.getLogger(questionlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection c = null;
        String[][] q = null;
        String prefix = "";
        if(num<9)
        {
            prefix = "00";
        }
        else if(num<99)
        {
            prefix = "0";
        }
        else
        {
            prefix = "";
        }
        try {
            c = db.getConnection();
            Statement s = c.createStatement();
            String query = "SELECT `id`, `topic`, `"+prefix+(num+1)+"`, `"+prefix+(num+1)+"d` from `case`";
            ResultSet rs = s.executeQuery(query);
            casex = new String[Variable.ATTRIBUTENUMBER][2];
            int i=0;
            while(rs.next())
            {
                int id = rs.getInt("id");
                String topic = rs.getString("topic");
                hm.put(id, topic);
                
                casex[i][0] = rs.getString(prefix+(num+1));
                casex[i][1] = rs.getString(prefix+(num+1)+"d");
                hmd.put(id, casex[i][1]);
                i++;
            }
            
            Statement s2 = c.createStatement();
            String query2 = "SELECT `caseid`, `diagnosis` from `correctdiagnosis`";
            ResultSet rs2 = s2.executeQuery(query2);
            i=0;
            HashMap correctdx = new HashMap();
            HashMap casenumber = new HashMap();
            while(rs2.next())
            {
                String caseid = rs2.getString("caseid");
                String dx = rs2.getString("diagnosis");
                correctdx.put(caseid,dx);
                casenumber.put(dx,i);
                i++;
            }
            if(request!=null)
            {
                request.getSession().setAttribute("correctdx", correctdx);
                request.getSession().setAttribute("casenumberlist", casenumber);
            }
        } catch (SQLException ex) {
            Logger.getLogger(questionlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        if (c != null)
            try {c.close();} catch (SQLException e) {}
        }
        if(request!=null)
        {
            request.getSession().setAttribute("topic", hm);
            request.getSession().setAttribute("description", hmd);
        }
        return casex;
    }
    /*
    public int getTotalcasenumber()
    {
        return l.size();
    }
    */
    public String getAttribute(HttpServletRequest request, int n)
    {
        //return attribute[n];
        HashMap hm = (HashMap)request.getSession().getAttribute("topic");
        return hm.get(n).toString();
    }
    public String getDescription(HttpServletRequest request, int n)
    {
        //return attribute[n];
        HashMap hm = (HashMap)request.getSession().getAttribute("description");
        return hm.get(n).toString();
    }
    
    public String getCorrectdiagnosis(HttpServletRequest request, int n)
    {
        //return attribute[n];
        HashMap hm = (HashMap)request.getSession().getAttribute("correctdx");
        if(n<9)
        {
            return hm.get("00"+(n+1)).toString();
        }
        else if(n<99)
        {
            return hm.get("0"+(n+1)).toString();
        }
        else
        {
            return hm.get(""+(n+1)).toString();
        }
        
    }
    public int getCasenumber(HttpServletRequest request, String dx)
    {
        //return attribute[n];
        HashMap hm = (HashMap)request.getSession().getAttribute("casenumberlist");
        return (int)hm.get(dx);
    }
    /*
    public String getCorrectdiagnosis(int n)
    {
        return correctdx[n];
        //return correctdx.get("00"+(n+1)).toString();
    }
    
    public int getCasenumber(String dx)
    {

        for(int i=0;i<correctdx.length;i++)
        {
            if(correctdx[i].equals(dx))
            {
                return i;
            }
        }
        return -1;

        //return (int) casenumber.get(dx);
    }*/
    public String getDiagnosisbackground(HttpServletRequest request, String dx)
    {
        for(int i=0;i<Variable.BACKGROUND.length;i++)
        {
            if(Variable.BACKGROUND[i][0].equals(dx))
            {
                return Variable.BACKGROUND[i][1];
            }
        }
        return "#FFFFFF";
    }
    /*
    public String[] getCasecontent(int num)
    {
        String[][] selectedcase = (String[][])l.get(num);
        String[] content = new String[selectedcase.length];
        for(int i=0;i<selectedcase.length;i++)
        {
            content[i] = selectedcase[i][0];
        }
        return content;
    }
    */
    //String[] correctdx = {"Appendicitis","Urinary tract infection","Ectopic pregnancy","Pelvic inflammatory disease","Gastroenteritis"};
    //HashMap correctdx = new HashMap();
    //HashMap casenumber = new HashMap();

    /*
    String[] attribute = {
        "age",
        "gender",
        "left abdominal pain history",
        "right abdominal pain history",
        "upper abdominal pain history",
        "lower abdominal pain history",
        "posterior abdominal pain history",
        "pain specific position",
        "pain duration",
        "pain radiation",
        
        "pain quality",
        "pain severity",
        "pain progression",
        "pain speed",
        "pain episodic",
        "pain position before migration",
        "pain at left abdomen before migration",
        "pain at right abdomen before migration",
        "pain at upper abdomen before migration",
        "pain at lower abdomen before migration",
        
        "pain at posterior abdomen before migration",
        "pain provoking factor",
        "pain relieving factor",
        "pain relieved by medication",
        "nausea",
        "vomitting",
        "sweating",
        "loss of appetite",
        "fever",
        "constipation",
        
        "diarrhea",
        "melena",
        "blood stool",
        "rigor",
        "dysuria",
        "urine color",
        "urine transparency",
        "urine frequency",
        "urine hesitation",
        "urgency micturtion",
        
        "urine odor",
        "gross hematuria",
        "pregnancy",
        "last menstrual period",
        "mestruation",
        "contraception",
        "vaginal discharge",
        "vaginal bleeding",
        "left abdominal tenderness",
        "right abdominal tenderness",
        
        "upper abdominal tenderness",
        "lower abdominal tenderness",
        "posterior abdominal tenderness",
        "abdominal guarding",
        "abdominal rebound tenderness",
        "Rovsing sign",
        "Psoas sign",
        "Obturator sign",
        "Murphy sign",
        "bowel sound",
        
        "pale",
        "temperature",
        "pain score during movement",
        "pain score during rest",
        "WCC",
        "neutrophil in WCC",
        "band neutrophil in WCC",
        "UA leukkocyte",
        "UA blood",
        "CRP",
        
        "PCR for Chlamydia",
        "PCR for Gonorrhoea",
        "UPT",
        "serum HCG"
    };
    */
    
    /*
     //appendicitis
    //27,M,?,?,24,N,?,4,?,?,?,'right lower abdomen',?,?,?,Y,Y,?,Y,Y,?,N,?,?,?,N,?,?,?,?,?,?,?,?,?,?,?,?,?,'right lower abdomen',?,?,N,?,?,?,?,?,38.3,?,5,10.3,9.1,?,N,N,?,?,?,?,?,appendicitis
    String[][] case001 = {  {"27","27"},
                            {"F","woman"},
                            {"N","no pain at left abdomen"},
                            {"Y","pain at right abdomen"},
                            {"N","no pain at upper abdomen"},
                            {"Y","pain at lower abdomen"},
                            {"N","no pain at posterior abdomen"},
                            {"?","no information of pain specific position"},
                            {"24","pain duration was 24 hours"},
                            {"N","no pain radiation"},
                            
                            {"sharp","pain quality was sharp"},
                            {"4","pain score was 4/10"},
                            {"constant","pain level was constant"},
                            {"sudden","pain onset was sudden"},
                            {"continuous","pain pattern was continuous"},
                            {"N","no pain migration"},
                            {"N","no pain migration from left abdomen"},
                            {"N","no pain migration from right abdomen"},
                            {"N","no pain migration from upper abdomen"},
                            {"N","no pain migration from lower abdomen"},
                            
                            {"N","no pain migration from posterior abdomen"},
                            {"movement","pain was precipitated by movement"},
                            {"N","no pain relieving factor"},
                            {"N","pain was not relieved by medication"},
                            {"Y","felt nausea"},
                            {"Y","vomitted three times in the last 24 hours"},
                            {"N","no sweating"},
                            {"Y","felt loss of appetite"},
                            {"Y","had fever"},
                            {"N","no constipation"},
                            
                            {"N","no diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"N","no dysuria"},
                            {"normal","normal urine color"},
                            {"clear","clear urine color"},
                            {"normal","no increase of urine frequency"},
                            {"N","no urine hesitation"},
                            {"N","no urgency of micturition"},
                            
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"?","no information of LMP"},
                            {"?","no information of menstruation"},
                            {"?","no information of contraception"},
                            {"?","no information of vaginal discharge"},
                            {"?","no information of vaginal bleeding"},
                            {"N","no abdominal tenderness at left abdomen"},
                            {"Y","abdominal tenderness at right abdomen"},
                            
                            {"N","no abdominal tenderness at upper abdomen"},
                            {"Y","abdominal tenderness at lower abdomen"},
                            {"N","no abdominal tenderness at posterior abdomen"},
                            {"Y","tenderness with guarding"},
                            {"Y","abdominal rebound tenderness positive"},
                            {"N","Rovsing sign negative"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            
                            {"N","not pale"},
                            {"38.3","body temperature was 38.3 C"},
                            {"8","pain score during movement 8/10"},
                            {"6","pain score during rest 6/10"},
                            {"10.3","WCC (CBC) = 10.3"},
                            {"9.1","neutrophil (CBC) = 9.1"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"N","UA leucocyte negative"},
                            {"N","UA blood negative"},
                            {"?","no information of CRP"},
                            
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"?","no information of UPT"},
                            {"?","no information of HCG"}
                            };
    //UTI
    String[][] case002 = {  {"31","31"},
                            {"F","woman"},
                            {"Y","pain at left abdomen"},
                            {"Y","pain at right abdomen"},
                            {"N","no pain at upper abdomen"},
                            {"Y","pain at lower abdomen"},
                            {"N","no pain at posterior abdomen"},
                            {"?","no pain specific position"},
                            {"72","pain duration was 72 hours"},
                            {"N","no pain radiation"},
                            {"burning","pain quality was burning"},
                            {"4","pain score was 4/10"},
                            {"constant","pain level was constant"},
                            {"sudden","pain onset was sudden"},
                            
                            {"intermittent","pain pattern was intermittent"},
                            {"N","no pain migration"},
                            {"N","no pain migration from left abdomen"},
                            {"N","no pain migration from right abdomen"},
                            {"N","no pain migration from upper abdomen"},
                            {"N","no pain migration from lower abdomen"},
                            {"N","no pain migration from posterior abdomen"},
                            {"N","no pain precipitating factor"},
                            {"N","no pain relieving factor"},
                            {"N","pain was not relieved by medication"},
                            {"N","no nausea"},
                            {"N","no vomitting"},
                            {"N","no sweating"},
                            {"N","no loss of appetite"},
                            {"N","no fever"},
                            
                            {"N","no constipation"},
                            {"N","no diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"Y","had dysuria"},
                            {"normal","normal urine color"},
                            {"cloudy","cloudy urine color"},
                            {"increase","increased urine frequency"},
                            {"Y","had urine hesitation"},
                            
                            {"Y","had urgency of micturition"},
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"5","LMP was 5 days PTA"},
                            {"?","no information of menstruation"},
                            {"?","no information of contraception"},
                            {"N","no vaginal discharge"},
                            {"N","no vaginal bleeding"},
                            {"Y","abdominal tenderness at left abdomen"},
                            {"Y","abdominal tenderness at right abdomen"},
                            {"N","no abdominal tenderness at upper abdomen"},
                            {"Y","abdominal tenderness at lower abdomen"},
                            {"N","no abdominal tenderness at posterior abdomen"},
                            
                            {"N","tenderness without guarding"},
                            {"N","abdominal rebound tenderness negative"},
                            {"N","Rovsing sign negative"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            {"N","not pale"},
                            {"36.8","body temperature was 36.8 C"},
                            {"4","pain score during movement 4/10"},
                            
                            {"4","pain score during rest 4/10"},
                            {"?","WCC (CBC) = no information"},
                            {"?","neutrophil (CBC) = no information"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"Y","UA leucocyte positive"},
                            {"Y","UA blood positive"},
                            {"?","no information of CRP"},
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"N","UPT negative"},
                            {"0","HCG was less than 0.6 IU/L"}
                            };
    
    //20,F,'right lower abdomen',?,3,leg,sharp,9,increase,?,intermittent,?,movement,'rest',?,Y,Y,Y,?,N,N,N,?,?,?,N,?,?,?,?,?,?,?,?,44,?,?,?,?,'right lower abdomen',N,?,Y,?,?,?,?,?,36.5,?,5,17.9,15.6,?,?,?,?,?,?,?,Y,EP
    //EP
    String[][] case003 = {  {"20","20"},
                            {"F","woman"},
                            {"N","no pain at left abdomen"},
                            {"Y","pain at right abdomen"},
                            {"N","no pain at upper abdomen"},
                            {"Y","pain at lower abdomen"},
                            {"N","no pain at posterior abdomen"},
                            {"?","no pain specific position"},
                            {"3","pain duration was 3 hours"},
                            {"leg","pain radiated to both legs"},
                            {"sharp","pain quality was sharp"},
                            {"9","pain score was 9/10"},
                            {"increase","pain level was increase"},
                            {"?","no information of pain onset"},
                            
                            {"intermittent","pain pattern was intermittent"},
                            {"N","no pain migration"},
                            {"N","no pain migration from left abdomen"},
                            {"N","no pain migration from right abdomen"},
                            {"N","no pain migration from upper abdomen"},
                            {"N","no pain migration from lower abdomen"},
                            {"N","no pain migration from posterior abdomen"},
                            {"movement","pain was precipitated by movement"},
                            {"rest","pain was releived by rest"},
                            {"N","pain was not relieved by medication"},
                            {"Y","felt nausea"},
                            {"Y","vomitted several times in the last 24 hours"},
                            {"Y","felt sweating"},
                            {"N","no loss of appetite"},
                            {"N","no fever"},
                            
                            {"N","no constipation"},
                            {"N","no diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"N","no dysuria"},
                            {"normal","normal urine color"},
                            {"?","clear urine color"},
                            {"normal","normal urine frequency"},
                            {"N","no urine hesitation"},
                            
                            {"N","no urgency of micturition"},
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"44","LMP was 44 days PTA"},
                            {"?","no information of menstruation"},
                            {"?","no information of contraception"},
                            {"N","no vaginal discharge"},
                            {"N","no vaginal bleeding"},
                            {"N","no abdominal tenderness at left abdomen"},
                            {"Y","abdominal tenderness at right abdomen"},
                            {"N","no abdominal tenderness at upper abdomen"},
                            {"Y","abdominal tenderness at lower abdomen"},
                            {"N","no abdominal tenderness at posterior abdomen"},
                            
                            {"N","tenderness without guarding"},
                            {"N","abdominal rebound tenderness negative"},
                            {"Y","Rovsing sign positive"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            {"N","not pale"},
                            {"36.5","body temperature was 36.5 C"},
                            {"5","pain score during movement 5/10"},
                            
                            {"5","pain score during rest 5/10"},
                            {"17.9","WCC (CBC) = 17.9"},
                            {"15.6","neutrophil (CBC) = 15.6"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"N","UA leucocyte negative"},
                            {"N","UA blood negative"},
                            {"?","no information of CRP"},
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"Y","UPT positive"},
                            {"627","HCG was 627 IU/L"}
                            };
    //33,F,'right lower abdomen',?,12,N,crampying,?,increase,?,
    //?,?,?,?,?,?,Y,?,Y,N,
    //?,?,?,?,N,N,?,?,increase,?,
    //?,?,?,?,20,regular,?,?,?,'right lower abdomen',
    //?,?,?,?,?,?,?,?,35.6,?,
    //4,8.5,5.2,?,?,?,85.3,N,N,N ,?,PID
    //PID
    String[][] case004 = {  {"33","33"},
                            {"F","woman"},
                            {"N","no pain at left abdomen"},
                            {"Y","pain at right abdomen"},
                            {"N","no pain at upper abdomen"},
                            {"Y","pain at lower abdomen"},
                            {"N","no pain at posterior abdomen"},
                            {"?","no pain specific position"},
                            {"12","pain duration was 12 hours"},
                            {"N","no pain radiation"},
                            {"cramp","pain quality was crampying"},
                            {"?","pain score was not specified"},
                            {"increase","pain level was increase"},
                            {"?","no information of pain onset"},
                            
                            {"?","no information of pain pattern"},
                            {"N","no pain migration"},
                            {"N","no pain migration from left abdomen"},
                            {"N","no pain migration from right abdomen"},
                            {"N","no pain migration from upper abdomen"},
                            {"N","no pain migration from lower abdomen"},
                            {"N","no pain migration from posterior abdomen"},
                            {"?","no information of precipitating factor"},
                            {"?","no information of relieving factor"},
                            {"?","no information of relieving factor by medication"},
                            {"?","no information of nausea"},
                            {"Y","vomitted several times in the last 24 hours"},
                            {"?","no information of sweating"},
                            {"Y","felt loss of appetite"},
                            {"N","no fever"},
                            
                            {"N","no constipation"},
                            {"N","no diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"N","no dysuria"},
                            {"normal","normal urine color"},
                            {"?","clear urine color"},
                            {"increase","increased urine frequency"},
                            {"N","no urine hesitation"},
                            
                            {"N","no urgency of micturition"},
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"20","LMP was 20 days PTA"},
                            {"regular","menstruation was regular"},
                            {"?","no information of contraception"},
                            {"N","no vaginal discharge"},
                            {"N","no vaginal bleeding"},
                            {"N","no abdominal tenderness at left abdomen"},
                            {"Y","abdominal tenderness at right abdomen"},
                            {"N","no abdominal tenderness at upper abdomen"},
                            {"Y","abdominal tenderness at lower abdomen"},
                            {"N","no abdominal tenderness at posterior abdomen"},
                            
                            {"N","tenderness without guarding"},
                            {"N","abdominal rebound tenderness negative"},
                            {"N","Rovsing sign negative"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            {"N","not pale"},
                            {"35.6","body temperature was 35.6 C"},
                            {"?","no information of pain score during movement"},
                            
                            {"4","pain score during rest 4/10"},
                            {"8.5","WCC (CBC) = 8.5"},
                            {"5.2","neutrophil (CBC) = 5.2"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"N","UA leucocyte negative"},
                            {"N","UA blood negative"},
                            {"85.3","CRP = 85.3"},
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"N","UPT negative"},
                            {"0","HCG was less than 0.6 IU/L"}
                            };
    //83,F,?,?,2,?,?,?,?,?,
    //?,'upper abdomen',?,?,?,Y,Y,?,?,Y,
    //?,Y,?,N,?,N,?,?,?,?,
    //?,?,?,?,?,?,?,?,?,'epigastrium',
    //N,?,?,?,?,N,Y,?,38.3,0,
    //0,3.3,3.2,?,?,?,?,?,?,?,?,gastroenteritis
    //gastroenteritis
    String[][] case005 = {  {"83","83"},
                            {"F","woman"},
                            {"Y","pain at left abdomen"},
                            {"Y","pain at right abdomen"},
                            {"Y","pain at upper abdomen"},
                            {"N","no pain at lower abdomen"},
                            {"N","no pain at posterior abdomen"},
                            {"?","no pain specific position"},
                            {"2","pain duration was 2 hours"},
                            {"N","no pain radiation"},
                            {"?","no information of pain quality"},
                            {"?","pain score was not specified"},
                            {"?","no information of pain level"},
                            {"?","no information of pain onset"},
                            
                            {"?","no information of pain pattern"},
                            {"N","no pain migration"},
                            {"N","no pain migration from left abdomen"},
                            {"N","no pain migration from right abdomen"},
                            {"N","no pain migration from upper abdomen"},
                            {"N","no pain migration from lower abdomen"},
                            {"N","no pain migration from posterior abdomen"},
                            {"?","no information of precipitating factor"},
                            {"?","no information of relieving factor"},
                            {"?","no information of relieving factor by medication"},
                            {"Y","felt nausea"},
                            {"Y","vomitted several times in the last 24 hours"},
                            {"?","no information of sweating"},
                            {"?","no information of loss of appetite"},
                            {"Y","had fever"},
                            
                            {"N","no constipation"},
                            {"Y","had diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"N","no dysuria"},
                            {"normal","normal urine color"},
                            {"?","clear urine color"},
                            {"normal","no urine frequency"},
                            {"N","no urine hesitation"},
                            
                            {"N","no urgency of micturition"},
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"?","no information of LMP"},
                            {"?","no information of menstruation"},
                            {"?","no information of contraception"},
                            {"?","no information of vaginal discharge"},
                            {"?","no information of vaginal bleeding"},
                            {"Y","abdominal tenderness at left abdomen"},
                            {"Y","abdominal tenderness at right abdomen"},
                            {"Y","abdominal tenderness at upper abdomen"},
                            {"N","no abdominal tenderness at lower abdomen"},
                            {"N","no abdominal tenderness at posterior abdomen"},
                            
                            {"N","tenderness without guarding"},
                            {"N","abdominal rebound tenderness negative"},
                            {"N","Rovsing sign negative"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            {"N","not pale"},
                            {"38.3","body temperature was 38.3 C"},
                            {"0","pain score during movement 2/10"},
                            
                            {"0","pain score during rest 2/10"},
                            {"3.3","WCC (CBC) = 3.3"},
                            {"3.2","neutrophil (CBC) = 3.2"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"N","UA leucocyte negative"},
                            {"N","UA blood negative"},
                            {"?","no information of CRP"},
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"N","UPT negative"},
                            {"0","HCG was less than 0.6 IU/L"}
                            };
    
    
    */
    
    
    
    
    /*
    
    //NBabdominalpain2
    //appendicitis
    //27,M,?,?,24,N,?,4,?,?,?,'right lower abdomen',?,?,?,Y,Y,?,Y,Y,?,N,?,?,?,N,?,?,?,?,?,?,?,?,?,?,?,?,?,'right lower abdomen',?,?,N,?,?,?,?,?,38.3,?,5,10.3,9.1,?,N,N,?,?,?,?,?,appendicitis
    String[][] case001 = {  {"27","27"},
                            {"F","woman"},
                            {"right lower abdomen","pain position was at right lower abdomen"},
                            {"?","no pain specific position"},
                            {"24","pain duration was 24 hours"},
                            {"N","no pain radiation"},
                            {"sharp","pain quality was sharp"},
                            {"4","pain score was 4/10"},
                            {"constant","pain level was constant"},
                            {"suddenly","pain onset was sudden"},
                            
                            {"continual","pain pattern was continual"},
                            {"right lower abdomen","no pain migration"},
                            {"movement","pain was precipitated by movement"},
                            {"N","no pain relieving factor"},
                            {"N","pain was not relieved by medication"},
                            {"Y","felt nausea"},
                            {"Y","vomitted three times in the last 24 hours"},
                            {"N","no sweating"},
                            {"Y","felt loss of appetite"},
                            {"Y","had fever"},
                            
                            {"N","no constipation"},
                            {"N","no diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"N","no dysuria"},
                            {"normal","normal urine color"},
                            {"?","clear urine color"},
                            {"N","no increase of urine frequency"},
                            {"N","no urine hesitation"},
                            
                            {"N","no urgency of micturition"},
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"?","no information of LMP"},
                            {"?","no information of menstruation"},
                            {"?","no information of contraception"},
                            {"?","no information of vaginal discharge"},
                            {"?","no information of vaginal bleeding"},
                            {"right lower abdomen","abdominal tenderness at right lower abdomen"},
                            
                            {"Y","tenderness with guarding"},
                            {"Y","abdominal rebound tenderness positive"},
                            {"N","Rovsing sign negative"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            {"N","not pale"},
                            {"38.3","body temperature was 38.3 C"},
                            {"8","pain score during movement 8/10"},
                            
                            {"6","pain score during rest 6/10"},
                            {"10.3","WCC (CBC) = 10.3"},
                            {"9.1","neutrophil (CBC) = 9.1"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"N","UA leucocyte negative"},
                            {"N","UA blood negative"},
                            {"?","no information of CRP"},
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"?","no information of UPT"},
                            {"?","no information of HCG"}
                            };
    //UTI
    String[][] case002 = {  {"31","31"},
                            {"F","woman"},
                            {"lower abdomen","pain position was at lower abdomen"},
                            {"?","no pain specific position"},
                            {"72","pain duration was 72 hours"},
                            {"N","no pain radiation"},
                            {"burning","pain quality was burning"},
                            {"4","pain score was 4/10"},
                            {"constant","pain level was constant"},
                            {"suddenly","pain onset was sudden"},
                            
                            {"intermittent","pain pattern was intermittent"},
                            {"lower abdomen","no pain migration"},
                            {"N","no pain precipitating factor"},
                            {"N","no pain relieving factor"},
                            {"N","pain was not relieved by medication"},
                            {"N","no nausea"},
                            {"N","no vomitting"},
                            {"N","no sweating"},
                            {"N","no loss of appetite"},
                            {"N","no fever"},
                            
                            {"N","no constipation"},
                            {"N","no diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"Y","had dysuria"},
                            {"normal","normal urine color"},
                            {"cloudy","cloudy urine color"},
                            {"increase","increased urine frequency"},
                            {"Y","had urine hesitation"},
                            
                            {"Y","had urgency of micturition"},
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"5","LMP was 5 days PTA"},
                            {"?","no information of menstruation"},
                            {"?","no information of contraception"},
                            {"N","no vaginal discharge"},
                            {"N","no vaginal bleeding"},
                            {"suprapubic","abdominal tenderness at suprapubic"},
                            
                            {"N","tenderness without guarding"},
                            {"N","abdominal rebound tenderness negative"},
                            {"N","Rovsing sign negative"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            {"N","not pale"},
                            {"36.8","body temperature was 36.8 C"},
                            {"4","pain score during movement 4/10"},
                            
                            {"4","pain score during rest 4/10"},
                            {"?","WCC (CBC) = no information"},
                            {"?","neutrophil (CBC) = no information"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"Y","UA leucocyte positive"},
                            {"Y","UA blood positive"},
                            {"?","no information of CRP"},
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"N","UPT negative"},
                            {"N","HCG negative"}
                            };
    
    //20,F,'right lower abdomen',?,3,leg,sharp,9,increase,?,intermittent,?,movement,'rest',?,Y,Y,Y,?,N,N,N,?,?,?,N,?,?,?,?,?,?,?,?,44,?,?,?,?,'right lower abdomen',N,?,Y,?,?,?,?,?,36.5,?,5,17.9,15.6,?,?,?,?,?,?,?,Y,EP
    //EP
    String[][] case003 = {  {"20","20"},
                            {"F","woman"},
                            {"right lower abdomen","pain position was at right lower abdomen"},
                            {"?","no pain specific position"},
                            {"3","pain duration was 3 hours"},
                            {"leg","pain radiated to both legs"},
                            {"sharp","pain quality was sharp"},
                            {"9","pain score was 9/10"},
                            {"increase","pain level was increase"},
                            {"?","no information of pain onset"},
                            
                            {"intermittent","pain pattern was intermittent"},
                            {"lower abdomen","no pain migration"},
                            {"movement","pain was precipitated by movement"},
                            {"rest","pain was releived by rest"},
                            {"N","pain was not relieved by medication"},
                            {"Y","felt nausea"},
                            {"Y","vomitted several times in the last 24 hours"},
                            {"Y","felt sweating"},
                            {"N","no loss of appetite"},
                            {"N","no fever"},
                            
                            {"N","no constipation"},
                            {"N","no diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"N","no dysuria"},
                            {"normal","normal urine color"},
                            {"?","clear urine color"},
                            {"N","normal urine frequency"},
                            {"N","no urine hesitation"},
                            
                            {"N","no urgency of micturition"},
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"44","LMP was 44 days PTA"},
                            {"?","no information of menstruation"},
                            {"?","no information of contraception"},
                            {"N","no vaginal discharge"},
                            {"N","no vaginal bleeding"},
                            {"right lower abdomen","abdominal tenderness at right lower abdomen"},
                            
                            {"N","tenderness without guarding"},
                            {"N","abdominal rebound tenderness negative"},
                            {"Y","Rovsing sign positive"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            {"N","not pale"},
                            {"36.5","body temperature was 36.5 C"},
                            {"5","pain score during movement 5/10"},
                            
                            {"5","pain score during rest 5/10"},
                            {"17.9","WCC (CBC) = 17.9"},
                            {"15.6","neutrophil (CBC) = 15.6"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"N","UA leucocyte negative"},
                            {"N","UA blood negative"},
                            {"?","no information of CRP"},
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"Y","UPT positive"},
                            {"Y","HCG positive"}
                            };
    //33,F,'right lower abdomen',?,12,N,crampying,?,increase,?,
    //?,?,?,?,?,?,Y,?,Y,N,
    //?,?,?,?,N,N,?,?,increase,?,
    //?,?,?,?,20,regular,?,?,?,'right lower abdomen',
    //?,?,?,?,?,?,?,?,35.6,?,
    //4,8.5,5.2,?,?,?,85.3,N,N,N ,?,PID
    //PID
    String[][] case004 = {  {"33","33"},
                            {"F","woman"},
                            {"right lower abdomen","pain position was at right lower abdomen"},
                            {"?","no pain specific position"},
                            {"12","pain duration was 12 hours"},
                            {"N","no pain radiation"},
                            {"crampying","pain quality was crampying"},
                            {"?","pain score was not specified"},
                            {"increase","pain level was increase"},
                            {"?","no information of pain onset"},
                            
                            {"?","no information of pain pattern"},
                            {"right lower abdomen","no pain migration"},
                            {"?","no information of precipitating factor"},
                            {"?","no information of relieving factor"},
                            {"?","no information of relieving factor by medication"},
                            {"?","no information of nausea"},
                            {"Y","vomitted several times in the last 24 hours"},
                            {"?","no information of sweating"},
                            {"Y","felt loss of appetite"},
                            {"N","no fever"},
                            
                            {"N","no constipation"},
                            {"N","no diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"N","no dysuria"},
                            {"normal","normal urine color"},
                            {"?","clear urine color"},
                            {"increase","increased urine frequency"},
                            {"N","no urine hesitation"},
                            
                            {"N","no urgency of micturition"},
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"20","LMP was 20 days PTA"},
                            {"regular","menstruation was regular"},
                            {"?","no information of contraception"},
                            {"N","no vaginal discharge"},
                            {"N","no vaginal bleeding"},
                            {"right lower abdomen","abdominal tenderness at right lower abdomen"},
                            
                            {"N","tenderness without guarding"},
                            {"N","abdominal rebound tenderness negative"},
                            {"N","Rovsing sign negative"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            {"N","not pale"},
                            {"35.6","body temperature was 35.6 C"},
                            {"?","no information of pain score during movement"},
                            
                            {"4","pain score during rest 4/10"},
                            {"8.5","WCC (CBC) = 8.5"},
                            {"5.2","neutrophil (CBC) = 5.2"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"N","UA leucocyte negative"},
                            {"N","UA blood negative"},
                            {"85.3","CRP = 85.3"},
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"N","UPT negative"},
                            {"N","HCG negative"}
                            };
    //83,F,?,?,2,?,?,?,?,?,
    //?,'upper abdomen',?,?,?,Y,Y,?,?,Y,
    //?,Y,?,N,?,N,?,?,?,?,
    //?,?,?,?,?,?,?,?,?,'epigastrium',
    //N,?,?,?,?,N,Y,?,38.3,0,
    //0,3.3,3.2,?,?,?,?,?,?,?,?,gastroenteritis
    //gastroenteritis
    String[][] case005 = {  {"83","83"},
                            {"F","woman"},
                            {"upper abdomen","pain position was at upper abdomen"},
                            {"?","no pain specific position"},
                            {"2","pain duration was 2 hours"},
                            {"N","no pain radiation"},
                            {"?","no information of pain quality"},
                            {"?","pain score was not specified"},
                            {"?","no information of pain level"},
                            {"?","no information of pain onset"},
                            
                            {"?","no information of pain pattern"},
                            {"upper abdomen","no pain migration"},
                            {"?","no information of precipitating factor"},
                            {"?","no information of relieving factor"},
                            {"?","no information of relieving factor by medication"},
                            {"Y","felt nausea"},
                            {"Y","vomitted several times in the last 24 hours"},
                            {"?","no information of sweating"},
                            {"?","no information of loss of appetite"},
                            {"Y","had fever"},
                            
                            {"N","no constipation"},
                            {"Y","had diarrhea"},
                            {"N","no melena"},
                            {"N","no blood in stool"},
                            {"N","no rigor"},
                            {"N","no dysuria"},
                            {"normal","normal urine color"},
                            {"?","clear urine color"},
                            {"N","no urine frequency"},
                            {"N","no urine hesitation"},
                            
                            {"N","no urgency of micturition"},
                            {"N","no urine odor"},
                            {"N","no gross hematuria"},
                            {"?","no information of pregnancy"},
                            {"?","no information of LMP"},
                            {"?","no information of menstruation"},
                            {"?","no information of contraception"},
                            {"?","no information of vaginal discharge"},
                            {"?","no information of vaginal bleeding"},
                            {"epigastrium","abdominal tenderness at epigastrium"},
                            
                            {"N","tenderness without guarding"},
                            {"N","abdominal rebound tenderness negative"},
                            {"N","Rovsing sign negative"},
                            {"N","Psoas sign negative"},
                            {"N","Obturator sign negative"},
                            {"N","Murphy sign negative"},
                            {"Y","bowel sound positive"},
                            {"N","not pale"},
                            {"38.3","body temperature was 38.3 C"},
                            {"0","pain score during movement 2/10"},
                            
                            {"0","pain score during rest 2/10"},
                            {"3.3","WCC (CBC) = 3.3"},
                            {"3.2","neutrophil (CBC) = 3.2"},
                            {"?","no information of band neutrophil (CBC)"},
                            {"N","UA leucocyte negative"},
                            {"N","UA blood negative"},
                            {"?","no information of CRP"},
                            {"N","PCR for chlamydia negative"},
                            {"N","PCR for gonorrhoea negative"},
                            {"N","UPT negative"},
                            {"N","HCG negative"}
                            };
*/
}
