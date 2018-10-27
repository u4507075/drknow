/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.webtoken.JsonWebToken.Payload;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Piyapong
 */
@WebServlet(name = "Signin", urlPatterns = {"/Signin"})
public class Signin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String tokenid = request.getParameter("tokenid");
            //out.println("555");
            String success = processToken(tokenid,request,response);
            out.println(success);
            /*
            if(success)
            {
                out.println("trueeee");
            }
            else
            {
                out.println("falseeee");
            }*/
        }
    }
    
    public String processToken(String idTokenString, HttpServletRequest request, HttpServletResponse response){
        
        //idTokenString = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjlmYjk4ZGY3NDg2ZTJjNTg4NjdjNzA0ODVmODM1MDMzNGQxMmQ5NzcifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDc3NjM1MzY0LCJleHAiOjE0Nzc2Mzg5NjQsImF0X2hhc2giOiI5YUk5R09jT1diWldXeTh6Ui1jR2VBIiwiYXVkIjoiMTA2NzMwNzIzNDY5NS1wc3FtNXBlc2dsMHRtZmMwY2MxN3NnZWtqY2dhb2RuMC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwOTMyNDIwODIyMzAxNjExODQ4MyIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDY3MzA3MjM0Njk1LXBzcW01cGVzZ2wwdG1mYzBjYzE3c2dla2pjZ2FvZG4wLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJzdHVkZW50LnVuaW1lbGIuZWR1LmF1IiwiZW1haWwiOiJwa2h1bXJpbkBzdHVkZW50LnVuaW1lbGIuZWR1LmF1IiwibmFtZSI6IlBpeWFwb25nIEtodW1yaW4iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDUuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy04NEtHWHVqWWpUay9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS1RhZUs5dnFtcWtlU05RTEpDNEF0a0NfUHBwdWNrUDdBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJQaXlhcG9uZyIsImZhbWlseV9uYW1lIjoiS2h1bXJpbiIsImxvY2FsZSI6ImVuIn0.4ETXNLS7KnwkyNgBZEH9Y-G1fE8dsH7rN1sTeCXbtLXfIgVsgZAXoQW2z8ZZd-cgkFu19U2NB9CueEQocjxpev0JmLUW8RuNLvn08rHn1oczeeaAs9z4KjVvhlVYIR7cjKS_90M2Ufr0FYRVA61b9IJwsUvUk2CMLI9W6F0xthPVgTaNMnLesFHBqBoJc6SHA0sVsStGkW2kWLDrchdM-tJhretZoEtpk_Yzd_pjG113NlU9xddd08huaG8dAShlRIOuRw1mKf6WUZQ7LT4-gOSK5nFa8NMUZDEWdf3WJm9NX6CVyDEVag6d65FRvw9vZ65U6m5PAFAkZtSgJX19zA";
                String returnVal="";
		NetHttpTransport transport = new NetHttpTransport();
		GsonFactory jsonFactory = new GsonFactory();
		
		if(idTokenString != null && !idTokenString.equals("")){
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Arrays.asList("1067307234695-psqm5pesgl0tmfc0cc17sgekjcgaodn0.apps.googleusercontent.com"))
				// To learn about getting a Server Client ID, see this link
				// https://developers.google.com/identity/sign-in/android/start
				// And follow step 4
                                // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
                                // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
                                // "accounts.google.com". If you need to verify tokens from multiple sources, build
                                // a GoogleIdTokenVerifier for each issuer and try them both.
				.setIssuer("accounts.google.com").build();
		
			try{
				GoogleIdToken idToken = verifier.verify(idTokenString);
				if (idToken != null) {
					Payload payload = idToken.getPayload();
                                        //userid
					String userid = payload.getSubject();
                                        //returnVal = "match";
                                        request.getSession().setAttribute("userid", userid);
                                        Database db = new Database();
                                        //db.getConnection111();
                                        db.init();
                                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        String sessionid = df.format(new Date());
                                        request.getSession().setAttribute("sessionid", sessionid);
                                        db.login("login", request.getSession().getAttribute("userid").toString(), request.getSession().getAttribute("sessionid").toString());
                                        boolean firstvisit = db.checkYearlevelrecord(userid);
                                        if(firstvisit)
                                        {
                                            returnVal = "firstvisit";
                                        }
                                        else
                                        {
                                            returnVal = "visited";
                                        }

					// You can also access the following properties of the payload in order
					// for other attributes of the user. Note that these fields are only
					// available if the user has granted the 'profile' and 'email' OAuth
					// scopes when requested.
					// String email = payload.getEmail();
					// boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
					// String name = (String) payload.get("name");
					// String pictureUrl = (String) payload.get("picture");
					// String locale = (String) payload.get("locale");
					// String familyName = (String) payload.get("family_name");
					// String givenName = (String) payload.get("given_name");
                                        
                                        //for scoring at the end
                                        
                                        //request.getSession().setAttribute("hxin", 0);
                                        //request.getSession().setAttribute("pein", 0);
                                        //request.getSession().setAttribute("labin", 0);
				} else {
                                        //return false;
					returnVal = "Invalid ID token.";
				}
			} catch (Exception ex){
                                //return false;
				returnVal = ex.getMessage();
			}
		}
		else{
                    //return false;
			returnVal = "Bad Token Passed In";
		}
		return returnVal;
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
