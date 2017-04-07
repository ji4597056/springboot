package com.study.spring.entity.mybatis;

import org.apache.ibatis.type.Alias;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:47
 */
@Alias("User")
public class User {

    private Integer id;

    private String userName;

    private String password;

    private String nickName;

    private String email;

    public User() {
    }

    public User(String userName, String password, String nickName, String email) {
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
    }

    public User(Integer id, String userName, String password, String nickName, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
