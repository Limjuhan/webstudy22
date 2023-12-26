package com.example.zerobasestudy22;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class InfoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        WifiInfo info = new WifiInfo("안녕","asdf","xvcb","asdf","asdf","xvcb","asdf","asdf","xvcb","asdf","asdf","126.4544","123.44", LocalDateTime.now());
        WifiInfoService service = new WifiInfoService();
        service.register(info);


    }
}
