package Models;

import java.sql.*;

public class loginCheck{
    String username;
    String password;
    String un, ps;
    public loginCheck(String username, String password, String un, String ps) {
        this.username = username;
        this.password = password;
        this.un = un;
        this.ps = ps;
    }
    public int logCheck() throws SQLException, ClassNotFoundException{
        
        if(!username.equals(un) && !password.equals(ps)){
            return 3;
        }
        else if(!username.equals(un)){
            return 1;
        }
        else if(username.equals(un) && !password.equals(ps)){
            return 2;
        }
        
        return -1;
    }
}
