package kim.jerok.practice_spring_15.practice;

class User {
    private int id;
    private String username;
    private String password;
}

// select * from board where id = 1
class Board {
    private int id;
    private String title;
    private String content;
    private int userId;  // FK
}

public class Hello {
}
