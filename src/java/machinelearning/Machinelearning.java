package machinelearning;


import html.Casecontent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import servlet.Database;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.CorrelationAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.LogitBoost;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ArffLoader.ArffReader;
import weka.filters.Filter;

public class Machinelearning {
    String classvalue = "abdominalpain3";
    String modelname = "NBabdominalpain3";
    String modeltype = "NB";
    Classifier model;
    //String modelname = "LBabdominalpain3";
    //LogitBoost model;
    public Machinelearning()
    {
        /*
        try {
            model = (LogitBoost) readModel(modelname);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        try {
            model = (NaiveBayes) readModel(modelname);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public String getFeedback(String name, String[] s)
    {
        //appendicitis
        //String[] s = {"43","F","?","?","72","?","?","7","?","?","?","right lower abdomen","?","?","?","?","?","?","?","?","N","N","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","right lower abdomen","N","N","?","?","?","?","?","?","36.5","?","?","?","?","?","?","?","?","?","?","?","?","?"};
        //String[] s = {"43","M","right lower abdomen","?","12","?","crampying","?","?","?","?","entire abdomen","?","?","?","Y","Y","?","?","?","N","N","?","?","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","right lower abdomen","Y","?","?","?","?","?","?","?","36.4","5","2","17.9","16.3","0.7","N","N","?","?","?","?","?","?"};
        
        //gastroenteritis
        //String[] s = {"90","F","?","?","24","?","crampying","?","?","?","?","lower abdomen","?","?","?","?","Y","?","?","Y","?","Y","N","N","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","left lower abdomen","?","?","?","?","?","?","?","?","38.2","?","2","26.5","20.7","3.7","N","N","?","?","?","?","?","?"};
        //only history
        //String[] s = {"90","F","?","?","24","?","crampying","?","?","?","?","lower abdomen","?","?","?","?","Y","?","?","Y","?","Y","N","N","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?"};
        //String[] s = {"90","F","?","?","24","?","crampying","?","?","?","?","lower abdomen","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?"};
        
        try {
            NaiveBayes model = (NaiveBayes) readModel(name);
            String num = getProbability(model, s, name);
            return num;
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
                
    }
    */
    public void getTotalfeedback(HttpServletRequest request, String step, String[] s, PrintWriter out, String feedbackid, String[] selectedddx)
    {
        //appendicitis
        //String[] s = {"43","F","?","?","72","?","?","7","?","?","?","right lower abdomen","?","?","?","?","?","?","?","?","N","N","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","right lower abdomen","N","N","?","?","?","?","?","?","36.5","?","?","?","?","?","?","?","?","?","?","?","?","?"};
        //String[] s = {"43","M","right lower abdomen","?","12","?","crampying","?","?","?","?","entire abdomen","?","?","?","Y","Y","?","?","?","N","N","?","?","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","right lower abdomen","Y","?","?","?","?","?","?","?","36.4","5","2","17.9","16.3","0.7","N","N","?","?","?","?","?","?"};
        
        //gastroenteritis
        //String[] s = {"90","F","?","?","24","?","crampying","?","?","?","?","lower abdomen","?","?","?","?","Y","?","?","Y","?","Y","N","N","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","left lower abdomen","?","?","?","?","?","?","?","?","38.2","?","2","26.5","20.7","3.7","N","N","?","?","?","?","?","?"};
        //only history
        //String[] s = {"90","F","?","?","24","?","crampying","?","?","?","?","lower abdomen","?","?","?","?","Y","?","?","Y","?","Y","N","N","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?"};
        //String[] s = {"90","F","?","?","24","?","crampying","?","?","?","?","lower abdomen","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?"};
        
        try {
            
            
            //gettotalProbability(request, step, s, out, feedbackid, selectedddx);
            gettotalbinaryProbability(request, step, s, out, feedbackid, selectedddx);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
            //return ex.getMessage();
        }
    }
    public Classifier readModel(String name) throws Exception
    {
        return (Classifier) weka.core.SerializationHelper.read(Machinelearning.class.getClassLoader().getResource("model/"+name+".model").getPath());
    }
    public String getProbability(String[] value, String dx) throws IOException
    {
        
        BufferedReader reader = new BufferedReader(new FileReader(Machinelearning.class.getClassLoader().getResource("model/"+classvalue+".arff").getPath()));
        ArffReader arff = new ArffReader(reader);
        Instances structure = arff.getStructure();
        structure.setClassIndex(structure.numAttributes() - 1);
        
        Instance newInstance  = new DenseInstance(structure.numAttributes());
        newInstance.setDataset(structure);
        //newInstance.setClassValue(classvalue);
        for(int i = 0 ; i < value.length ; i++)
        {
            Attribute att = structure.attribute(i);
            
            if(value[i].equals("?") || value[i].isEmpty())
            {
                newInstance.setValue(i, Utils.missingValue());
            }
            else if(att.isNumeric())
            {
                newInstance.setValue(i,Double.parseDouble(value[i]));
            }
            else
            {
                newInstance.setValue(i,value[i]);
            }
        }
        
        
        
        double pred = 0;
        try {
            pred = model.classifyInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
                //get the predicted probabilities 
                double[] prediction = null;
        try {
            prediction = model.distributionForInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }

                String predict = ""+newInstance.classAttribute().value((int) pred);

                System.out.println(", predicted: " +predict+ " "+(int)(prediction[(int)pred]*100)+"%");
                //return ""+round(prediction[0]*100,0);
                int casenumber = 0;
                //order from arff class attribute
                if(dx.equals("Appendicitis"))
                {
                    casenumber = 0;
                }
                else if(dx.equals("Gastroenteritis"))
                {
                    casenumber = 1;
                }
                else if(dx.equals("Urinary tract infection"))
                {
                    casenumber = 2;
                }
                else if(dx.equals("Ectopic pregnancy"))
                {
                    casenumber = 3;
                }
                else if(dx.equals("Pelvic inflammatory disease"))
                {
                    casenumber = 4;
                }
                return ""+Math.round(prediction[casenumber]*100);

    }
    public double getbinaryProbability(String[] value, String dx) throws IOException, Exception
    {
        String name = "";
        if(dx.equals("Appendicitis"))
        {
            name = "abdominalpain3_appendicitis";
            model = (NaiveBayes) readModel(modeltype+"appendicitis3");
            //model = (LogitBoost) readModel(modeltype+"appendicitis3");

        }
        else if(dx.equals("Gastroenteritis"))
        {
            name = "abdominalpain3_gastroenteritis";
                model = (NaiveBayes) readModel(modeltype+"gastroenteritis3");
                //model = (LogitBoost) readModel(modeltype+"gastroenteritis3");

        }
        else if(dx.equals("Urinary tract infection"))
        {
            name = "abdominalpain3_UTI";
                model = (NaiveBayes) readModel(modeltype+"UTI3");
                //model = (LogitBoost) readModel(modeltype+"UTI3");

        }
        else if(dx.equals("Ectopic pregnancy"))
        {
            name = "abdominalpain3_EP";
               model = (NaiveBayes) readModel(modeltype+"EP3");
               //model = (LogitBoost) readModel(modeltype+"EP3"); 

        }
        else if(dx.equals("Pelvic inflammatory disease"))
        {
            name = "abdominalpain3_PID";
                model = (NaiveBayes) readModel(modeltype+"PID3");
                //model = (LogitBoost) readModel(modeltype+"PID3");

        }
        BufferedReader reader = new BufferedReader(new FileReader(Machinelearning.class.getClassLoader().getResource("model/"+name+".arff").getPath()));
        ArffReader arff = new ArffReader(reader);
        Instances structure = arff.getStructure();
        structure.setClassIndex(structure.numAttributes() - 1);
        
        Instance newInstance  = new DenseInstance(structure.numAttributes());
        newInstance.setDataset(structure);
        //newInstance.setClassValue(classvalue);
        for(int i = 0 ; i < value.length ; i++)
        {
            Attribute att = structure.attribute(i);
            
            if(value[i].equals("?") || value[i].isEmpty())
            {
                newInstance.setValue(i, Utils.missingValue());
            }
            else if(att.isNumeric())
            {
                newInstance.setValue(i,Double.parseDouble(value[i]));
            }
            else
            {
                newInstance.setValue(i,value[i]);
            }
        }
        
        
        
        double pred = 0;
        try {
            pred = model.classifyInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
                //get the predicted probabilities 
                double[] prediction = null;
        try {
            prediction = model.distributionForInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }

                String predict = ""+newInstance.classAttribute().value((int) pred);

                System.out.println(", predicted: " +predict+ " "+(int)(prediction[(int)pred]*100)+"%");
                //return ""+round(prediction[0]*100,0);

                //return ""+Math.round(prediction[0]*100);
                return prediction[0];

    }
    
    public void gettotalProbability(HttpServletRequest request, String step, String[] value, PrintWriter out, String feedbackid, String[] selectedddx) throws IOException
    {
        
        BufferedReader reader = new BufferedReader(new FileReader(Machinelearning.class.getClassLoader().getResource("model/"+classvalue+".arff").getPath()));
        ArffReader arff = new ArffReader(reader);
        Instances structure = arff.getStructure();
        structure.setClassIndex(structure.numAttributes() - 1);
        
        Instance newInstance  = new DenseInstance(structure.numAttributes());
        newInstance.setDataset(structure);
        //newInstance.setClassValue(classvalue);
        for(int i = 0 ; i < value.length ; i++)
        {
            Attribute att = structure.attribute(i);
            
            if(value[i].equals("?") || value[i].isEmpty())
            {
                newInstance.setValue(i, Utils.missingValue());
            }
            else if(att.isNumeric())
            {
                newInstance.setValue(i,Double.parseDouble(value[i]));
            }
            else
            {
                newInstance.setValue(i,value[i]);
            }
        }
        

        double pred = 0;
        try {
            pred = model.classifyInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
                //get the predicted probabilities 
                double[] prediction = null;
        try {
            prediction = model.distributionForInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
                String[] predictions = new String[newInstance.classAttribute().numValues()];
                for(int i=0;i<predictions.length;i++)
                {
                    predictions[i] = newInstance.classAttribute().value(i);
                }
                
                displayfeedback(request, out, step, prediction, selectedddx, value);

    }
    public void gettotalbinaryProbability(HttpServletRequest request, String step, String[] value, PrintWriter out, String feedbackid, String[] selectedddx)
    {
        
        double[] prediction = new double[Variable.DDX.length];
        for(int i=0;i<Variable.DDX.length;i++)
        {
            try {
                prediction[i] = getbinaryProbability(value, Variable.DDX[i]);
            } catch (Exception ex) {
                prediction[i] = 0.3;
                Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        displayfeedback(request, out, step, prediction, selectedddx, value);
    }
    private double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}
    private void displayfeedback(HttpServletRequest request, PrintWriter out, String step, double[] probability, String[] selectedddx, String[] value)
    {
        
        //String[] ddx = {"Appendicitis","Gastroenteritis","Urinary tract infection","Ectopic pregnancy","Pelvic inflammatory disease"};
        
        StringBuilder text = new StringBuilder();
        text.append("<div class=\"feedback\">");
        
        ArrayList ddx = displayRelevantattribute2(request, text, step, value, selectedddx);
        
        addDogmessage2(text, selectedddx, ddx);
        
        /*
        //cloud feedback
        //double[] selectedp = new double[selectedddx.length];
        String[] p = new String[selectedddx.length];
        text.append("<div id ='drknowidea"+step+"' style='display:none;'>");
        for(int i=0;i<ddx.size();i++)
        {
            text.append("<div>"+ddx.get(i).toString()+"</div>");
        }
        text.append("</div>");
        
        
        
        
        addCloudfeedback(text, "cloud1"+step, "cloud2"+step);
        */
        
        //old dog feedback
        /*
        for(int j=0;j<selectedddx.length;j++)
        {
            for(int i=0;i<Variable.DDX.length;i++)
            {
                if(Variable.DDX[i].equals(selectedddx[j]))
                {
                            text.append("<div style='color: "+getBackgroundcolor(Variable.DDX, selectedddx[j])+"'>"+selectedddx[j]+" ("+Math.round(probability[i]*100)+" %)</div>");
                            selectedp[j] = probability[i];
                            p[j] = ""+Math.round(probability[i]*100);
                            break;
                            //out.println("alert(\""+selectedddx[0]+"\")");
                            //out.println("var ddx = document.createElement(\"div\"); ");
                            //out.println("ddx.style.display = "inline";");
                            //out.println("ddx.className = \"context\";");
                            //out.println("ddx.innerHTML = \""+selectedddx[j]+"\"+' '+\""+Math.round(probability[i]*100)+"\"+ '%';");

                            //out.println("$('#"+feedbackid+"').append(ddx);");

                    
                    //selectedp[num] = ""+probability[i];
                    //num++;
                }
            }
        }
        */
        //addDogmessage(text,selectedddx,selectedp);
        String[] p = new String[selectedddx.length];
        for(int j=0;j<selectedddx.length;j++)
        {
            for(int i=0;i<Variable.DDX.length;i++)
            {
                if(Variable.DDX[i].equals(selectedddx[j]))
                {
                    p[j] = ""+Math.round(probability[i]*100);
                }
            }
        }
        
        
        text.append("</div>");
        out.print(text.toString());
        
        savediagnosis(request, step, selectedddx, p, Arrays.toString(value));
 
    }
    private String getBackgroundcolor(String[] ddx, String dx)
    {
        
        for(int i=0;i<ddx.length;i++)
        {
            if(ddx[i].equals(dx))
            {
                return Variable.DDXCOLOR[i];
            }
        }
        return "";
    }
    private int getDiagnosisnumber(String d)
    {
        //String[] ddx = {"Appendicitis","Gastroenteritis","Urinary tract infection","Ectopic pregnancy","Pelvic inflammatory disease"};
        for(int i=0;i<Variable.DDX.length;i++)
        {
            if(Variable.DDX[i].equals(d))
            {
                return i;
            }
        }
        return 0;
    }
    private void addDogmessage2(StringBuilder text, String[] selectedddx, ArrayList predictedddx)
    {
        text.append("<table width=\"100%\">");
        text.append("<col width=\"50%\"><col width=\"50%\">");
        text.append("<tr>");
            text.append("<td class=\"idea\">");
                text.append("<div style=\"position: relative; height: 280px;\">");
                    text.append("<img src=\"img/dog2.png\" style=\"position: absolute; z-index: 10; left: 120px; top: 170px\"/>");
                    text.append("<img src=\"img/"+selectedddx[0]+".png\" style=\"position: absolute; z-index: 4; left: 110px; top: 20px\"/>");
                    text.append("<img src=\"img/"+selectedddx[1]+".png\" style=\"position: absolute; z-index: 3; left: 230px; top: 140px; width: 138px; height: 110px\"/>");
                    text.append("<img src=\"img/"+selectedddx[2]+".png\" style=\"position: absolute; z-index: 3; left: 40px; top: 150px; width: 98px; height: 79px\"/>");
                text.append("</div>");
            text.append("</td>");
            text.append("<td class=\"idea\">");
                text.append("<div style=\"position: relative; height: 280px;\">");
                    text.append("<img src=\"img/drknow.png\" style=\"position: absolute; z-index: 10; left: 160px; top: 160px\"/>");
                    text.append("<img src=\"img/"+predictedddx.get(0).toString()+".png\" style=\"position: absolute; z-index: 4; left: 95px; top: 20px\"/>");
                    text.append("<img src=\"img/"+predictedddx.get(1).toString()+".png\" style=\"position: absolute; z-index: 3; left: 25px; top: 140px; width: 138px; height: 110px\"/>");
                    text.append("<img src=\"img/"+predictedddx.get(2).toString()+".png\" style=\"position: absolute; z-index: 3; left: 260px; top: 150px; width: 98px; height: 79px\"/>");
                text.append("</div>");
            text.append("</td>");
        text.append("</tr>");
        text.append("</table>");
    }
    private void addDogmessage(StringBuilder text, String[] selectedddx, double[] p)
    {
        int screenwidth = 760;
        int dogleft = screenwidth - 150;
        int dogbottom = 0;
        int width = 197;
        int height = 158;
        
        text.append("<div>");
        text.append("<div style=\"position:relative;\">");
        text.append("<img src=\"img/dog.png\"");
           text.append("style=\"position:absolute; bottom:"+dogbottom+"px; left:"+dogleft+"px; z-index:10; width:99px; height:97px; border:none;\"");
           //text.append("alt=\"Dr Know's feedback\"");
           text.append("title=\"Dr Know interim feedback\" />");
        text.append("<img src=\"img/"+selectedddx[0]+".png\"");
           text.append("style=\"position:absolute; bottom:50px; left:"+(dogleft-(width*p[0]))+"px; z-index:3; width:"+(width*p[0])+"px; height:"+(height*p[0])+"px; border:none;\"");
           text.append("alt=\"No image\"");
           text.append("title=\""+selectedddx[0]+" "+Math.round(p[0]*100)+"%\" />");
        text.append("<img src=\"img/"+selectedddx[1]+".png\" id='epimage'");
           text.append("style=\"position:absolute; bottom:100px; left:"+(dogleft)+"px; z-index:4; width:"+(width*p[1])+"px; height:"+(height*p[1])+"px; border:none;\"");
           text.append("alt=\"No image\"");
           text.append("title=\""+selectedddx[1]+" "+Math.round(p[1]*100)+"%\" />");
        text.append("<img src=\"img/"+selectedddx[2]+".png\"");
           text.append("style=\"position:absolute; bottom:50px; left:"+(dogleft+80)+"px; z-index:2; width:"+(width*p[2])+"px; height:"+(height*p[2])+"px; border:none;\"");
           text.append("alt=\"No image\"");
           text.append("title=\""+selectedddx[2]+" "+Math.round(p[2]*100)+"%\" />");
        text.append("</div>");
        text.append("</div>");

    }
    private void addCloudfeedback(StringBuilder text, String cloudid1, String cloudid2)
    {
        text.append("<table width=\"100%\" class=\"idea\">");
        text.append("<col width=\"50%\"><col width=\"50%\">");
        text.append("<tr>");
            text.append("<td class=\"idea\"><div class=\"idea\" id=\""+cloudid1+"\" style=\"width: 395px; height: 150px; position: relative;\"></div></td>");
            text.append("<td class=\"idea\"><div class=\"idea\" id=\""+cloudid2+"\" style=\"width: 395px; height: 150px; position: relative;\"></div></td>");
        text.append("</tr>");
        text.append("<tr>");
            text.append("<td class=\"idea\"><center>Your idea</center></td>");
            text.append("<td class=\"idea\"><center>Dr Know idea</center></td>");
        text.append("</tr>");
        text.append("</table>");
    }
    public void addFinalfeedback2(HttpServletRequest request, String[] value, PrintWriter out, String feedbackid, String selectedddx, String selectedcasenumber)
    {
        Casecontent c = new Casecontent();
        String correctdx = c.getCorrectdiagnosis(request,Integer.parseInt(selectedcasenumber));
        
        out.println("<div class='feedbacktitle' id='finalfeedbacktitle'>Dr Know final feedback</div>");
        String s1 = "";
        int totalscore = 0;
        int dxin = (int)request.getSession().getAttribute("dxin");
        int hxin = (int)request.getSession().getAttribute("hxin");
        int pein = (int)request.getSession().getAttribute("pein");
        int labin = (int)request.getSession().getAttribute("labin");
        
        totalscore = totalscore + (dxin*10);
        totalscore = totalscore +hxin+pein+labin;
        if(dxin>1)
        {
            s1 = "<span><strong>"+dxin+"/4</strong></span> times";
        }
        else
        {
            s1 = "<span><strong>"+dxin+"/4</strong></span> time";
        }
        
        String s2 = "";
        if(selectedddx.equals(correctdx))
        {
            totalscore = totalscore + 50;
            //s2 = "Finally, you got the correct diagnosis <span><strong>\""+correctdx+"\"</strong></span>. Well done!";
            s2 = "You identified the correct diagnosis at the final step.";
            savefeedback(request, "correct diagnosis","yes","");
        }
        else
        {
            //s2 = "Finally, you did not get the correct answer <span><strong>\""+correctdx+"\"</strong></span>. Don't giveup and try again next time!";
            s2 = "You did not identify the correct diagnosis (<span><strong>\""+correctdx+"\"</strong></span>) at the final step.";
            savefeedback(request, "correct diagnosis","no","");
        }
        String s3 = "";
        String s4 = "";
        if(totalscore>90)
        {
            s3 = "Excellent";
            s4 = "Well done! Your overall performance on this case was";
        }
        else if(totalscore>40)
        {
            s3 = "Good";
            s4 = "Your overall performance on this case was";
        }
        else if(totalscore>20)
        {
            s3 = "Fair";
            s4 = "While you did not arrive at the correct diagnosis, your overall performance on this case was";
        }
        else
        {
            s3 = "Limited";
            s4 = "You appear to have had some difficulties with this case. Your overall performance on this case was";
        }
        out.println("<div id='totalscore' style='display: none;'>"+totalscore+"</div>");     
        /*
        out.println("<div class=\"drknownnote\">This is the summary of your performance for this case. You have considered to add the correct diagnosis "+s1+" in the differential diagnosis list. \n" +
        "        You got <span><strong>"+hxin+"/12</strong></span> patient information in the patient history step, <span><strong>"+pein+"/16</strong></span> patient information in the physical examination step, and <span><strong>"+labin+"/20</strong></span> patient information in the laboratory and investigation step \n" +
        "        which were relevant to the correct diagnosis. "+s2+" The overall performance of this case is</div>\n" +
        "    <div class=\"drknownnote\" style=\"font-size: 5em;\"><center><b>"+s3+"</b></center></div>\n" +
        "    <div class=\"drknownnote\">The cloud words below shows which patient information is relevant to the correct diagnosis. This might be helpful for making future decisions.</div>\n" +
        "    ");*/
        
        out.println("<div class=\"drknownnote\">\n" +
"        <div class=\"drknownnote\">This section summarises your overall performance on the case.</div>\n" +
"        <ul style=\"padding: 15px; padding-left: 25px;\">\n" +
"            <li style=\"color: #1e4347; padding: 3px;\">You included the correct diagnosis in your differential diagnosis on "+dxin+" of 4 occasions.</li>\n" +
"            <li style=\"color: #1e4347; padding: 3px;\">You identified "+hxin+" of a possible 12 relevant pieces of patient information at the patient history stage.</li>\n" +
"            <li style=\"color: #1e4347; padding: 3px;\">You identified "+pein+" of a possible 16 relevant pieces of patient information at the physical examination stage.</li>\n" +
"            <li style=\"color: #1e4347; padding: 3px;\">You identified "+labin+" of a possible 20 relevant pieces of patient information at the laboratory and investigation stage.</li>\n" +
"            <li style=\"color: #1e4347; padding: 3px;\">"+s2+"</li>\n" +
"        </ul>\n" +
"    </div>");
        out.println("<div class=\"drknownnote\">"+s4+"</div>");
        out.println("<div class=\"drknownnote\" style=\"font-size: 5em;\"><center><b>"+s3+"</b></center></div>");
        out.println("<div class=\"drknownnote\">The word cloud below illustrates which items of patient information were most relevant to the correct diagnosis. Please keep these in mind as they may be helpful for other cases.</div>");
        try {
            ArrayList list = getbinaryRelevantattributelevel2(value, correctdx);
            ArrayList taken = new ArrayList();
            double[] tempvalue = new double[list.size()];
            double[] tempid = new double[list.size()];
            double[] temprecordid = new double[list.size()];
            double[] temprecordvalue = new double[list.size()];
            for(int i=0;i<list.size();i++)
            {
                tempvalue[i] = (((double[])list.get(i))[1]);
            }
            Arrays.sort(tempvalue);
            int num = 0;
            for(int j=tempvalue.length-1;j>=0;j--)
            {
                for(int i=0;i<list.size();i++)
                {
                    if((((double[])list.get(i))[1])==tempvalue[j])
                    {
                        if(!hasValue(taken, i))
                        {
                            tempid[num] = (((double[])list.get(i))[0]);
                            temprecordid[num] = tempid[num];
                            temprecordvalue[num] = tempvalue[j];
                            num++;
                            taken.add(i);
                            break;
                        }
                    }
                }
            }
            out.println("<div id='relevantfeature' style='display:none;'>");
            out.println("<div>"+correctdx+"</div>");
            for(int i=0;i<tempid.length;i++)
            {
                String des = c.getDescription(request, (int)tempid[i]);
                if(tempid[i]==0)
                {
                    des = des+" year old";
                }
                out.println("<div>"+des+"</div>");
            }
            out.println("</div>");
            out.println("<div id='relevantfeaturechart' style='width: 100%; height: 400px; position: relative;'></div>");
            
            savefeedback(request, "dxin","",""+dxin);
            savefeedback(request, "hxin","",""+hxin);
            savefeedback(request, "pein","",""+pein);
            savefeedback(request, "labin","",""+labin);
            savefeedback(request, "relevantfeature",Arrays.toString(temprecordid),""+Arrays.toString(temprecordvalue));
            
            savefeedback(request, "totalscore", "",""+totalscore);
            
            out.println("<button class='myButton' style='width:100%;' onclick=\"redirectsurvey(this)\">Next</button>");
            
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean hasValue(ArrayList arr, int v) {
	for(int i=0;i<arr.size();i++){
		if((int)arr.get(i)==v)
			return true;
	}
	return false;
    }
    public void addFinalfeedback(HttpServletRequest request, String[] s, PrintWriter out, String feedbackid, String selectedddx, String selectedcasenumber)
    {
        
        //out.println("<div>Yeah<div>");
        
        //int[] student = getRelevantattributelevel(s, getDiagnosisnumber(selectedddx));
        int ss = Integer.parseInt(selectedcasenumber);
        String probbasedonselectedinfo = "0";
        Casecontent c = new Casecontent();
        int totalscore = 0;
        
        try {
            //probbasedonselectedinfo = getProbability(s, c.getCorrectdiagnosis(request,ss));
            probbasedonselectedinfo = ""+Math.round(getbinaryProbability(s, c.getCorrectdiagnosis(request,ss))*100);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //int[][] standard = getRelevantattributelevel(new Casecontent().getCasecontent(ss),ss);
        int[][] standard = null;
        try {
            standard = getbinaryRelevantattributelevel(selectedddx);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        ArrayList selected = new ArrayList();
        ArrayList selectedvalue = new ArrayList();
        ArrayList missed = new ArrayList();
        ArrayList missedvalue = new ArrayList();
        for(int i=2;i<standard.length;i++)
        {
            if(!s[i].equals("?") && standard[i][1]>2)
            {
                System.out.println("You selected item: "+c.getAttribute(request,i)+" value = "+standard[i]);
                selected.add(c.getAttribute(request,i));
                selectedvalue.add(standard[i][0]);
            }
        }
        for(int i=2;i<standard.length;i++)
        {
            if(s[i].equals("?") && standard[i][1]>2)
            {
                System.out.println("You missed item: "+c.getAttribute(request,i)+" value = "+standard[i]);
                missed.add(c.getAttribute(request,i));
                missedvalue.add(standard[i][0]);
            }
        }
        

        int cutpoint = findcutpointtopfive(missedvalue);

        if(out!=null)
        {
            out.println("<div class='feedbacktitle' id='finalfeedbacktitle'>Dr Know final feedback</div>");
            out.println("<div class='feedback'>");
            
            out.println("<div class='title'>Correct diagnosis</div>");
            
            out.println("<div class='title' style='border-radius: 5px; text-align: center; color: white; background-color: "+c.getDiagnosisbackground(request,c.getCorrectdiagnosis(request,ss))+";'>"+c.getCorrectdiagnosis(request,ss)+"</div>");
            
            String rs1 = "";
            int score1 = 0;
            if(selectedddx.equals(c.getCorrectdiagnosis(request,ss)))
            {
                rs1 = "yes";
                score1 = 100;
                totalscore = totalscore + score1;
            }
            else
            {
                rs1 = "no";
            }
            savefeedback(request, "correct diagnosis", rs1, ""+score1);
            out.println("<table class='finalfeedback'>\n" +
                        "  <tr>\n" +
                        "    <th>Feedback</th>\n" +
                        "    <th>Value</th>\n" +
                        "    <th>Score</th>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Got the correct diagnosis</td>\n" +
                        "    <td>"+rs1+"</td>\n" +
                        "    <td>"+score1+"</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n");
            for(int i=0;i<selected.size();i++)
            {
                out.println("  <tr>\n" +
                            "    <td>Selected relevant feature</td>\n" +
                            "    <td>"+selected.get(i).toString()+"</td>\n" +
                            "    <td>"+selectedvalue.get(i).toString()+"</td>\n" +
                            "  </tr>\n");
                totalscore = totalscore + Integer.parseInt(selectedvalue.get(i).toString());
                savefeedback(request, "selected relevant feature", selected.get(i).toString(), selectedvalue.get(i).toString());
            }
            totalscore = totalscore + Integer.parseInt(probbasedonselectedinfo);
            out.println("  <tr>\n" +
                        "    <td>Prediction for the correct diagnosis based on selected information</td>\n" +
                        "    <td></td>\n" +
                        "    <td>"+probbasedonselectedinfo+"</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "      <td><b>Total score</b></td>\n" +
                        "    <td></td>\n" +
                        "    <td id='totalscore' style='background-color: yellow'>"+totalscore+"</td>\n" +
                        "  </tr>\n" +
                        "</table>");
            savefeedback(request, "prediction for correct diagnosis", c.getCorrectdiagnosis(request,ss),probbasedonselectedinfo);
            savefeedback(request, "totalscore", "",""+totalscore);
            
            out.println("<div class='context'><b>Other relevant features</b></div>");
            out.println("<ul class ='feedbacklist'>");
            for(int i=0;i<missed.size();i++)
            {
                if((int)missedvalue.get(i)>=cutpoint)
                {
                    out.println("<li class ='feedbacklist'>"+missed.get(i).toString()+"</li>");
                }
            }
            out.println("</ul>");
            
            out.println("</div>");
            
            out.println("<button class='myButton' onclick=\"redirectsurvey()\">Next</button>");
        

        //System.out.println(Arrays.toString(student));
        //System.out.println(Arrays.toString(standard));
        //String ddd = "";
        String[] dx = {selectedddx,"",""};
        String[] p = {"","",""};
        try {
            //p[0] = getProbability(s, c.getCorrectdiagnosis(request,ss));
            p[0] =  ""+Math.round(getbinaryProbability(s, c.getCorrectdiagnosis(request,ss))*100);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
        savediagnosis(request,"final",dx,p,Arrays.toString(s));
        }
            
    }
    private void savediagnosis(HttpServletRequest request,String step, String[] dx, String[] p, String value)
    {
        Database db = new Database();
        try {
            db.init();
        } catch (ServletException ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
            db.selectdiagnosis( request.getSession().getAttribute("userid").toString(), 
                                request.getSession().getAttribute("sessionid").toString(), 
                                request.getSession().getAttribute("caseid").toString(), 
                                step, 
                                dx,
                                p,
                                value);
    }
    private void savefeedback(HttpServletRequest request, String topic, String value, String score)
    {
        Database db = new Database();
        try {
            db.init();
        } catch (ServletException ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
            db.savefeedback(    request.getSession().getAttribute("userid").toString(), 
                                request.getSession().getAttribute("sessionid").toString(), 
                                request.getSession().getAttribute("caseid").toString(), 
                                topic,
                                value,
                                score);
    }
    private int findcutpointtopfive(ArrayList list)
    {
        ArrayList newlist = new ArrayList();
        for(int i=0;i<list.size();i++)
        {
            newlist.add(list.get(i));
        }
        Collections.sort(newlist);
        if(newlist.size()>=5)
        {
            return (int) newlist.get(newlist.size()-5);
        }
        else if(newlist.size()>0)
        {
            return (int) newlist.get(0);
        }
        else
        {
            return 0;
        }
    }
    public String displayRelevantattribute(String[] value, int casenumber)
    {
        int[][] relevant = getRelevantattributelevel(value, casenumber);
        int[] array = new int[relevant.length];
        for(int i=0;i<array.length;i++)
        {
            array[i] = relevant[i][1];
        }
        String text = Arrays.toString(array);
        text = text.replace("[", "");
        text = text.replace("]", "");
        text = text.replace(" ", "");
        return text;
    }
    public ArrayList displayRelevantattribute2(HttpServletRequest request, StringBuilder text, String step, String[] value, String[] selectedddx)
    {
        Casecontent c = new Casecontent();
        if(request.getSession().getAttribute("dxin")==null)
        {
            request.getSession().setAttribute("dxin", 0);
        }
        if(request.getSession().getAttribute(step+"in")==null)
        {
            request.getSession().setAttribute(step+"in", 0);
        }
        if(step.equals("cc"))
        {
            request.getSession().setAttribute("dxin", 0);
            request.getSession().setAttribute("ccin", 0);
            request.getSession().setAttribute("hxin", 0);
            request.getSession().setAttribute("pein", 0);
            request.getSession().setAttribute("labin", 0);
        }
        int dxin = (int)request.getSession().getAttribute("dxin");
        String correctdx = c.getCorrectdiagnosis(request,Integer.parseInt(request.getSession().getAttribute("caseid").toString()));
        
        for (String selectedddx1 : selectedddx) {
            if (selectedddx1.equals(correctdx)) {
                dxin++;
                request.getSession().setAttribute("dxin", dxin);
                break;
            }
        }
        
        
        ArrayList relevant = new ArrayList();
        int[][] score = new int[Variable.DDX.length][2];
        int[] score1 = new int[Variable.DDX.length];
        ArrayList dxlist = new ArrayList();
        
        text.append("<table class=\"finalfeedback\">");
        text.append("<col width='64%'><col width='12%'><col width='12%'><col width='12%'>");
        text.append("<tr>");
        text.append("<th>Selected features</th>");
        for (int i=0; i<selectedddx.length; i++) {
            text.append("<th style=\"text-align: center\">" + getShortDDX(selectedddx[i]) + "</th>");
            try {
                relevant.add(getbinaryRelevantattributelevel2(value, selectedddx[i]));
                dxlist.add(selectedddx[i]);
            } catch (Exception ex) {
                Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ArrayList totaldx = new ArrayList(Arrays.asList(Variable.DDX));

        totaldx.removeAll(new ArrayList(Arrays.asList(selectedddx)));
        dxlist.addAll(totaldx);
        
        for(int i=0;i<totaldx.size();i++)
        {
            try {
                relevant.add(getbinaryRelevantattributelevel2(value, totaldx.get(i).toString()));
            } catch (Exception ex) {
                Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        text.append("</tr>");
        for(int j=0;j<((ArrayList) relevant.get(0)).size();j++)
        {
            ArrayList relevantlist = (ArrayList) relevant.get(0);
            double id = ((double[])relevantlist.get(j))[0];
            //if(!value[j].equals("?"))
            {
                text.append("<tr>");
                String des = c.getDescription(request, (int)id);
                if(id==0)
                {
                    des = des+" year old";
                }
                text.append("<td>"+des+"</td>");
                for(int i=0;i<Variable.DDX.length;i++)
                {
                    ArrayList list = (ArrayList) relevant.get(i);
                    String sign = getRelevant((int)id,list);
                    if(i<selectedddx.length)
                    {
                        text.append("<td style=\"text-align: center; background-color: "+getSigncolor(sign)+";\">"+sign+"</td>");
                    }
                    if(sign.equals("+"))
                    {
                        score[i][0]++;
                        score1[i]++;
                    }
                }
                
                text.append("</tr>");
            }
        }
        text.append("</table>");
        ArrayList ddx = new ArrayList();
        Arrays.sort(score1);
        for(int i=score1.length-1;i>=0;i--)
        {
            for(int j=0;j<score.length;j++)
            {
                if(score[j][1]!=1 && score[j][0]==score1[i])
                {
                    ddx.add(dxlist.get(j).toString());
                    if(correctdx.equals(dxlist.get(j).toString()) && j<3)
                    {
                        request.getSession().setAttribute(step+"in", score1[i]);
                    }
                    score[j][1] = 1;
                    break;
                }
            }
        }
        
        return ddx;
    }
    private String getShortDDX(String selectedddx)
    {
        for(int i=0;i<Variable.DDXSHORT.length;i++)
        {
            if(selectedddx.equals(Variable.DDX[i]))
            {
                return Variable.DDXSHORT[i];
            }
        }
        return "";
    }
    private String getRelevant(int id, ArrayList relevantlist)
    {
        
        String sign = "";
        for(int i=0;i<relevantlist.size();i++)
        {
            //int[] relevant = (int[])relevantlist.get(i);
            if(((double[])relevantlist.get(i))[0]==id)
            {
                double value = ((double[])relevantlist.get(i))[1];
                if(value>0)
                {
                    /*
                    if(value<10)
                    {
                        sign = "+";
                    }
                    else
                    {
                        sign = "++";
                    }
                    */
                    sign = "+";
                }
                else if(value<0)
                {
                    /*
                    if(value>-10)
                    {
                        sign = "-";
                    }
                    else
                    {
                        sign = "--";
                    }
                    */
                    sign = "-";
                }
                //sign = ""+value;
                return sign;
            }
        }
        return sign;
    }
    private String getSigncolor(String sign)
    {
        if(sign.equals("+"))
        {
            return "#B3DE81";
        }
        else if(sign.equals("-"))
        {
            return "#FF8040";
        }
        return "";
    }
    private int[][] getRelevantattributelevel(String[] value, int casenumber)
    {
        int[][] relevant = new int[value.length][2];
    try {
            //String[] value = {"43","M","N","Y","N","Y","?","?","12","?","cramp","?","?","?","?","Y","Y","Y","Y","Y","?","?","?","?","Y","Y","?","?","?","N","N","?","?","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","N","Y","N","Y","?","Y","?","?","?","?","?","?","?","36.4","5","2","17.9","16.3","0.7","N","N","?","?","?","?","?","?"};
            //String[] value = {"43","M","?","?","?","?","?","?","12","?","cramp","?","?","?","?","?","?","?","?","Y","?","?","?","?","?","?","?","?","?","N","?","?","?","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","Y","?","?","?","?","?","?","?","?","?","36.4","?","?","?","16.3","?","N","?","?","?","?","?","?","?"};

            Machinelearning m = new Machinelearning();
            //NaiveBayes c = (NaiveBayes) m.readModel(modelname);
            //LogitBoost c = (LogitBoost)m.readModel(modelname);
            
            BufferedReader reader = new BufferedReader(new FileReader(Machinelearning.class.getClassLoader().getResource("model/"+classvalue+".arff").getPath()));
            ArffReader arff = new ArffReader(reader);
            Instances structure = arff.getStructure();
            structure.setClassIndex(structure.numAttributes() - 1);
            


            double full = 0;
            double[] prob = new double[value.length];
            ArrayList collection = new ArrayList();
            DecimalFormat df = new DecimalFormat("#.####");
            
    for(int j=-1;j<value.length-1;j++)
    {
            Instance newInstance  = new DenseInstance(structure.numAttributes());
            newInstance.setDataset(structure);
            //newInstance.setClassValue(classvalue);
            for(int i = 0 ; i < value.length ; i++)
            {
                Attribute att = structure.attribute(i);
                if(i==j)
                {
                    newInstance.setValue(i, Utils.missingValue());
                }
                else
                {
                if(value[i].equals("?") || value[i].isEmpty())
                {
                    newInstance.setValue(i, Utils.missingValue());
                }
                else if(att.isNumeric())
                {
                    newInstance.setValue(i,Double.parseDouble(value[i]));
                }
                else
                {
                    newInstance.setValue(i,value[i]);
                }
                }
            }
        

        double pred = 0;
        try {
            pred = model.classifyInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
                //get the predicted probabilities 
                double[] prediction = null;
        try {
            prediction = model.distributionForInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
                String[] predictions = new String[newInstance.classAttribute().numValues()];
                for(int i=0;i<predictions.length;i++)
                {
                    predictions[i] = newInstance.classAttribute().value(i);
                }
            
            if(j==-1)
            {
                full = prediction[casenumber];
                System.out.println("prediction: "+predictions[casenumber]+" = "+df.format(prediction[casenumber]));
            }
            else
            {
                if(!value[j].equals("?"))
                {
                    //DecimalFormat df = new DecimalFormat("#.####");
                    //System.out.println("remove: "+value[j]+" "+j+" "+predictions[0]+" = "+df.format((prediction[0]-full)*1));
                    //text.append(df.format(Math.abs((prediction[casenumber]-full))));
                    prob[j] = Math.abs((prediction[casenumber]-full));
                    //System.out.println("prediction "+j+" "+(full-prediction[casenumber]));
                    collection.add(prob[j]);
                    //text.append(j);
                }
                else
                {
                    //text.append("0");
                    prob[j]=0;
                    //text.append(j);
                }
                if(j<value.length-2)
                {
                    //text.append(",");
                }
            }
            
            
    }

    double[] cut = cutpoint(collection);
            for(int i=0;i<value.length;i++)
            {
                if(prob[i]==0)
                {
                    //text.append("0");
                    relevant[i][0] = 0;
                    relevant[i][1] = 0;
                }
                else
                {
                for(int j=0;j<cut.length;j++)
                {
                   if(prob[i]>cut[j])
                    {
                        //text.append((cut.length-j));
                        relevant[i][0] = (int) (prob[i]*1000);
                        relevant[i][1] = cut.length-j;
                        
                        break;
                    } 
                }
                }
                /*
                if(i<value.length-1)
                {
                    text.append(",");
                }*/
            }

        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
            //return(ex.getMessage());
        }
        
    
        
        
        return relevant;
    }
    
    private int[][] getbinaryRelevantattributelevel(String dx) throws Exception
    {
        
        String name = "";
        if(dx.equals("Appendicitis"))
        {
            name = "abdominalpain3_appendicitis";
            model = (NaiveBayes) readModel(modeltype+"appendicitis3");
            //model = (LogitBoost) readModel(modeltype+"appendicitis3");

        }
        else if(dx.equals("Gastroenteritis"))
        {
            name = "abdominalpain3_gastroenteritis";
                model = (NaiveBayes) readModel(modeltype+"gastroenteritis3");
                //model = (LogitBoost) readModel(modeltype+"gastroenteritis3");

        }
        else if(dx.equals("Urinary tract infection"))
        {
            name = "abdominalpain3_UTI";
                model = (NaiveBayes) readModel(modeltype+"UTI3");
                //model = (LogitBoost) readModel(modeltype+"UTI3");

        }
        else if(dx.equals("Ectopic pregnancy"))
        {
            name = "abdominalpain3_EP";
               model = (NaiveBayes) readModel(modeltype+"EP3");
               //model = (LogitBoost) readModel(modeltype+"EP3"); 

        }
        else if(dx.equals("Pelvic inflammatory disease"))
        {
            name = "abdominalpain3_PID";
                model = (NaiveBayes) readModel(modeltype+"PID3");
                //model = (LogitBoost) readModel(modeltype+"PID3");

        }
        BufferedReader reader = new BufferedReader(new FileReader(Machinelearning.class.getClassLoader().getResource("model/"+name+".arff").getPath()));
        Instances data = new Instances(reader);
        data.setClassIndex(data.numAttributes() - 1);

        
        CorrelationAttributeEval eval = new CorrelationAttributeEval();
        Ranker search = new Ranker();
        
        AttributeSelection attsel = new AttributeSelection();
        attsel.setEvaluator(eval);
        attsel.setSearch(search);
        
        attsel.SelectAttributes(data);
        // obtain the attribute indices that were selected
        double[][] indices = attsel.rankedAttributes();
        ArrayList collection = new ArrayList();
        for(int i=0;i<indices.length;i++)
        {
            collection.add(indices[i][1]);
        }
        
        double[] cut = cutpoint(collection);
        int[][] relevant = new int[data.numAttributes()-1][2];
        
        for(int i=0;i<relevant.length;i++)
            {
                if(indices[i][1]==0)
                {
                    //text.append("0");
                    relevant[(int)indices[i][0]][0] = 0;
                    relevant[(int)indices[i][0]][1] = 0;
                }
                else
                {
                for(int j=0;j<cut.length;j++)
                {
                   if(indices[i][1]>cut[j])
                    {
                        //text.append((cut.length-j));
                        relevant[(int)indices[i][0]][0] = (int) (indices[i][1]*1000);
                        relevant[(int)indices[i][0]][1] = cut.length-j;
                        
                        break;
                    } 
                }
                }
                /*
                if(i<value.length-1)
                {
                    text.append(",");
                }*/
            }
        
        return relevant;
    }
    private ArrayList getbinaryRelevantattributelevel2(String[] value, String dx) throws Exception
    {
        
        String name = "";
        if(dx.equals("Appendicitis"))
        {
            name = "abdominalpain3_appendicitis";
            //model = (BayesNet) readModel("BNAP3_replacemissing");
            model = (NaiveBayes) readModel(modeltype+"appendicitis3");
            //model = (LogitBoost) readModel(modeltype+"appendicitis3");

        }
        else if(dx.equals("Gastroenteritis"))
        {
            name = "abdominalpain3_gastroenteritis";
            //model = (BayesNet) readModel("BNGE3_replacemissing");
                model = (NaiveBayes) readModel(modeltype+"gastroenteritis3");
                //model = (LogitBoost) readModel(modeltype+"gastroenteritis3");

        }
        else if(dx.equals("Urinary tract infection"))
        {
            name = "abdominalpain3_UTI";
            //model = (BayesNet) readModel("BNUTI3_replacemissing");
                model = (NaiveBayes) readModel(modeltype+"UTI3");
                //model = (LogitBoost) readModel(modeltype+"UTI3");

        }
        else if(dx.equals("Ectopic pregnancy"))
        {
            name = "abdominalpain3_EP";
            //model = (BayesNet) readModel("BNEP3_replacemissing");
               model = (NaiveBayes) readModel(modeltype+"EP3");
               //model = (LogitBoost) readModel(modeltype+"EP3"); 

        }
        else if(dx.equals("Pelvic inflammatory disease"))
        {
            name = "abdominalpain3_PID";
            //model = (BayesNet) readModel("BNPID3_replacemissing");
                model = (NaiveBayes) readModel(modeltype+"PID3");
                //model = (LogitBoost) readModel(modeltype+"PID3");

        }
        //BufferedReader reader = new BufferedReader(new FileReader(Machinelearning.class.getClassLoader().getResource("model/"+name+".arff").getPath()));
        //Instances data = new Instances(reader);
        //data.setClassIndex(data.numAttributes() - 1);

        
        //CorrelationAttributeEval eval = new CorrelationAttributeEval();
        //Ranker search = new Ranker();
        
        //AttributeSelection attsel = new AttributeSelection();
        //attsel.setEvaluator(eval);
        //attsel.setSearch(search);
        
        //attsel.SelectAttributes(data);
        // obtain the attribute indices that were selected
        //return attsel.rankedAttributes();
        

        ArrayList collection = new ArrayList();
        ArrayList test = new ArrayList();
    try {
            //String[] value = {"43","M","N","Y","N","Y","?","?","12","?","cramp","?","?","?","?","Y","Y","Y","Y","Y","?","?","?","?","Y","Y","?","?","?","N","N","?","?","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","N","Y","N","Y","?","Y","?","?","?","?","?","?","?","36.4","5","2","17.9","16.3","0.7","N","N","?","?","?","?","?","?"};
            //String[] value = {"43","M","?","?","?","?","?","?","12","?","cramp","?","?","?","?","?","?","?","?","Y","?","?","?","?","?","?","?","?","?","N","?","?","?","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","Y","?","?","?","?","?","?","?","?","?","36.4","?","?","?","16.3","?","N","?","?","?","?","?","?","?"};

            Machinelearning m = new Machinelearning();
            //NaiveBayes c = (NaiveBayes) m.readModel(modelname);
            //LogitBoost c = (LogitBoost)m.readModel(modelname);
            
            BufferedReader reader = new BufferedReader(new FileReader(Machinelearning.class.getClassLoader().getResource("model/"+name+".arff").getPath()));
            ArffReader arff = new ArffReader(reader);
            Instances structure = arff.getStructure();
            structure.setClassIndex(structure.numAttributes() - 1);
            


            double full = 0;
            //double[] prob = new double[value.length];
            

            DecimalFormat df = new DecimalFormat("#.####");
            
    for(int j=-1;j<value.length;j++)
    {
            Instance newInstance  = new DenseInstance(structure.numAttributes());
            newInstance.setDataset(structure);
            //newInstance.setClassValue(classvalue);
            for(int i = 0 ; i < value.length ; i++)
            {
                Attribute att = structure.attribute(i);
                if(i==j)
                {
                    newInstance.setValue(i, Utils.missingValue());
                }
                else
                {
                if(value[i].equals("?") || value[i].isEmpty())
                {
                    newInstance.setValue(i, Utils.missingValue());
                }
                else if(att.isNumeric())
                {
                    newInstance.setValue(i,Double.parseDouble(value[i]));
                }
                else
                {
                    newInstance.setValue(i,value[i]);
                }
                }
            }
        
                //get the predicted probabilities 
                double[] prediction = null;
        try {
            prediction = model.distributionForInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
                String[] predictions = new String[newInstance.classAttribute().numValues()];
                for(int i=0;i<predictions.length;i++)
                {
                    predictions[i] = newInstance.classAttribute().value(i);
                }
            
            if(j==-1)
            {
                full = prediction[0];
                System.out.println("prediction: "+predictions[0]+" = "+df.format(prediction[0]));
            }
            else
            {
                if(!value[j].equals("?"))
                {
                    //DecimalFormat df = new DecimalFormat("#.####");
                    //System.out.println("remove: "+value[j]+" "+j+" "+predictions[0]+" = "+df.format((prediction[0]-full)*1));
                    //text.append(df.format(Math.abs((prediction[casenumber]-full))));
                    //prob[j] = (full-prediction[0]);
                    
                    double[] d = new double[2];
                    d[0] = j;
                    BigDecimal d1 = new BigDecimal(full);
                    BigDecimal d2 = new BigDecimal(prediction[0]);
                    //System.out.println(((d1.subtract(d2)).multiply(new BigDecimal(100))).setScale(0, RoundingMode.UP).intValue());
                    
                    d[1] = d1.subtract(d2).doubleValue();
                    //d[1] = ((d1.subtract(d2)).multiply(new BigDecimal(100))).setScale(0, RoundingMode.HALF_UP).intValue();
                    //System.out.println("prediction "+j+" "+d[1] + " "+(full-prediction[0]));
                    test.add(prediction[0]);
                    collection.add(d);
                    //text.append(j);
                }
                else
                {
                    //text.append("0");
                    //prob[j]=0;
                    //text.append(j);
                }
                if(j<value.length-2)
                {
                    //text.append(",");
                }
            }
            
            
    }

        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
            //return(ex.getMessage());
        }
        return collection;
    }
    
    public double[] cutpoint(ArrayList m) {
        Collections.sort(m);
        
        double[] d = new double[3];
        d[0] = (double)m.get(m.size()*6/10);
        d[1] = (double)m.get(m.size()*3/10);
        d[2] = 0;

        return d;
    }
    /*
    public void getgoldstandardddx()
    {
        try {
            Casecontent c = new Casecontent();
            getRelevantattributelevel(c.getCasecontent(2), 2);

        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public static void main(String[] args)
    {
        /*
        StringBuilder text = new StringBuilder();
        try {
            //String[] value = {"43","M","N","Y","N","Y","?","?","12","?","cramp","?","?","?","?","Y","Y","Y","Y","Y","?","?","?","?","Y","Y","?","?","?","N","N","?","?","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","N","Y","N","Y","?","Y","?","?","?","?","?","?","?","36.4","5","2","17.9","16.3","0.7","N","N","?","?","?","?","?","?"};
            String[] value = {"43","M","?","?","?","?","?","?","12","?","cramp","?","?","?","?","?","?","?","?","Y","?","?","?","?","?","?","?","?","?","N","?","?","?","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","Y","?","?","?","?","?","?","?","?","?","36.4","?","?","?","16.3","?","N","?","?","?","?","?","?","?"};
            String classvalue = "abdominalpain3";
            Machinelearning m = new Machinelearning();
            NaiveBayes c = (NaiveBayes) m.readModel(classvalue);
            
            BufferedReader reader = new BufferedReader(new FileReader(Machinelearning.class.getClassLoader().getResource("model/"+classvalue+".arff").getPath()));
            ArffReader arff = new ArffReader(reader);
            Instances structure = arff.getStructure();
            structure.setClassIndex(structure.numAttributes() - 1);
            


            double full = 0;
            double[] prob = new double[value.length];
            ArrayList collection = new ArrayList();
    for(int j=-1;j<value.length;j++)
    {
            Instance newInstance  = new DenseInstance(structure.numAttributes());
            newInstance.setDataset(structure);
            //newInstance.setClassValue(classvalue);
            for(int i = 0 ; i < value.length ; i++)
            {
                Attribute att = structure.attribute(i);
                if(i==j)
                {
                    newInstance.setValue(i, Utils.missingValue());
                }
                else
                {
                if(value[i].equals("?") || value[i].isEmpty())
                {
                    newInstance.setValue(i, Utils.missingValue());
                }
                else if(att.isNumeric())
                {
                    newInstance.setValue(i,Double.parseDouble(value[i]));
                }
                else
                {
                    newInstance.setValue(i,value[i]);
                }
                }
            }
        

        double pred = 0;
        try {
            pred = c.classifyInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
                //get the predicted probabilities 
                double[] prediction = null;
        try {
            prediction = c.distributionForInstance(newInstance);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
                String[] predictions = new String[newInstance.classAttribute().numValues()];
                for(int i=0;i<predictions.length;i++)
                {
                    predictions[i] = newInstance.classAttribute().value(i);
                }
            
            if(j==-1)
            {
                full = prediction[0];
                System.out.println("full: "+predictions[0]+" = "+prediction[0]);
            }
            else
            {
                if(!value[j].equals("?"))
                {
                    DecimalFormat df = new DecimalFormat("#.####");
                    System.out.println("remove: "+value[j]+" "+j+" "+predictions[0]+" = "+df.format((prediction[0]-full)*1));
                    prob[j] = Math.abs((prediction[0]-full));
                    collection.add(Math.abs((prediction[0]-full)));
                }
                else
                {
                    prob[j] = 0;
                }
            }
    }
    double[] cut = m.cutpoint(collection);
            for(int i=0;i<value.length;i++)
            {
                if(prob[i]==0)
                {
                    text.append("0");
                }
                else
                {
                for(int j=0;j<cut.length;j++)
                {
                   if(prob[i]>cut[j])
                    {
                        text.append((cut.length-j));
                        break;
                    } 
                }
                }

                if(i<value.length-1)
                {
                    text.append(",");
                }
            }
            String text1 = new String(Arrays.toString(value));
            text1 = text1.replace("[", "");
            text1 = text1.replace("]", "");
            text1 = text1.replace(" ", "");
            System.out.println(text1);
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        */
        //String[] value = {"43","M","Y","?","?","?","?","?","12","?","cramp","?","?","?","?","?","?","?","?","Y","?","?","?","?","?","?","?","?","?","N","?","?","?","?","N","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","?","Y","?","?","?","?","?","?","?","?","?","36.4","?","?","?","16.3","?","N","?","?","?","?","?","?","?"};
        /*
        String[] value = {  "56","M","Y","Y","N","Y","N","suprapubic-pain","96","N",
                            //"?","?","?","?","?","?","?","?","?","?",
                            //"burning","3","constant","gradual","intermittent","N","N","N","N","N",
                            "?","?","?","?","?","?","?","?","?","?",
                            "N","N","N","N","N","N","N","N","N","N",
                            //"?","?","?","?","?","?","?","?","?","?",
                            "N","N","N","N","Y","normal","cloudy","increase","Y","Y",
                            //"?","?","?","?","?","?","?","?","?","?",
                            //"Y","N","N","?","?","?","?","?","Y","Y",
                            "?","?","?","?","?","?","?","?","?","?",
                            //"N","Y","N","N","N","N","N","N","N","Y",
                            "?","?","?","?","?","?","?","?","?","?",
                            //"N","37.0","5","5","13.2","9.9","?","Y","Y","4.3",
                            //"?","37.5","?","?","?","?","?","Y","Y","?",
                            "?","?","?","?","?","?","?","?","?","?",
                            "N","N","N","0","?"};*/
         String[] value = {  "33","F","N","Y","N","Y","N","?","12","N",
                            //"?","?","?","?","?","?","?","?","?","?",
                            "cramp","?","increase","?","?","N","N","N","N","N",
                            //"?","?","?","?","?","?","?","?","?","?",
                            "N","?","?","?","?","Y","?","Y","N","N",
                            //"?","?","?","?","?","?","?","?","?","?",
                            "N","N","N","N","N","normal","clear","increase","N","N",
                            //"?","?","?","?","?","?","?","?","?","?",
                            "N","N","?","20","regular","?","N","N","N","Y",
                            //"?","?","?","?","?","?","?","?","?","?",
                            "N","Y","N","N","N","N","N","N","N","Y",
                            //"?","?","?","?","?","?","?","?","?","?",
                            "N","35.6","?","4","8.5","5.2","?","N","N","9.8",
                            //"?","37.5","?","?","?","?","?","Y","Y","?",
                            //"?","?","?","?","?","?","?","?","?","?",
                            "N","N","N","0","?"};
//System.out.println(value.length);

        Casecontent c = new Casecontent();
        Machinelearning m = new Machinelearning();
        try {
            for(int j=0;j<20;j++)
            {
            System.out.println("CASE "+(j+1));    
            String[][] s = c.getCasefromdb(null, j);
            String[] v = new String[value.length];
            v[value.length-1] = "?";
            for(int i=0;i<s.length;i++)
            {
                v[i] = s[i][0];
            }
            for(int i=0;i<Variable.DDX.length;i++)
            {
                try {
                    m.getbinaryRelevantattributelevel2(v,Variable.DDX[i]);
                } catch (Exception ex) {
                    Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("");
            System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        try {
            String[] ddx = {"Appendicitis","Gastroenteritis","Urinary tract infection","Ectopic pregnancy","Pelvic inflammatory disease"};
            

            
            for(int i=0;i<ddx.length;i++)
            {
                
            //m.getbinaryProbability(value, ddx[i]);
            //m.getRelevantattributelevel(value, i);
             //m.getbinaryRelevantattributelevel2(value,ddx[i]);
             /*
             for(int j=0;j<dd.length;j++)
             {
                 if(dd[j][1]>0.1)
                 {
                     System.out.println(ddx[i]+" attribute number "+dd[j][0]+" = "+dd[j][1]);
                 }
             }
             */
            }
            /*
            //predict test case
            
            Machinelearning m = new Machinelearning();
            m.getgoldstandardddx();
            */
            
            /*
            // Get DataSource
            Context initContext;
            try {
            initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource)envContext.lookup("jdbc/sql6141281");
            } catch (NamingException ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
        } catch (Exception ex) {
            Logger.getLogger(Machinelearning.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
