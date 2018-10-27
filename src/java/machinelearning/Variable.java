/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

/**
 *
 * @author Piyapong
 */
public class Variable {
    public static final String[] DDX = {"Appendicitis","Gastroenteritis","Urinary tract infection","Ectopic pregnancy","Pelvic inflammatory disease"};
    public static final String[] DDXSHORT = {"AP","GE","UTI","EP","PID"};
    public static final String[] DDXCOLOR = {"#DE7A22","#6AB187","#F52549","#FFD64D","#283655"};
    public static final int TOTALCASE = 20;
    public static final String[][] BACKGROUND = {{"Appendicitis","#DE7A22"},{"Gastroenteritis","#6AB187"},{"Urinary tract infection","#F52549"},{"Ectopic pregnancy","#FFD64D"},{"Pelvic inflammatory disease","#283655"}};
    public static final int ATTRIBUTENUMBER = 74;
    
    String dd = "";
}
