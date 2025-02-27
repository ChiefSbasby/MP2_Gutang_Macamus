package Models;

import java.sql.*;

public class loginCheck{
    String un, ps, un2, ps2;
    public loginCheck(String un, String ps, String un2, String ps2) {
        this.un = un;
        this.ps = ps;
        this.un2 = un2;
        this.ps2 = ps2;
    }
    public int logCheck() throws SQLException, ClassNotFoundException{
        if(un == null){
            return 1;
        }
        else if(un != null && un2 == null){
            return 2;
        }
        else if(un != null && !un.equals(un2)){
            return 2;
        }
        else if(un.equals(un2)){
            return -1;
        }
        return 0;
    }
}
