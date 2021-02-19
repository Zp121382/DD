package com.xzy.entity;

/**
 * @author Invisible
 * @version 0.1
 * @date 2021/2/19 23:12
 */
public class Member {
    private Integer id;
    private String nickname;
    private String password;

    public Member(Integer id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public Member() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
