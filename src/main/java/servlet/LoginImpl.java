package servlet;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginImpl implements HttpSessionBindingListener{
	String userID;
	String userPW;
	static int total_user = 0;
	       
    public LoginImpl(String userID, String userPW) {
       this.userID = userID;
       this.userPW = userPW;
    }
    
    @Override
    public void valueBound(HttpSessionBindingEvent arg0) {
    	System.out.println("사용자 접속");
    	++total_user;
    }
    
    @Override
    public void valueUnbound(HttpSessionBindingEvent arg0) {
    	System.out.println("사용자 접속 해제");
    	total_user--;
    }

}
