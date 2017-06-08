package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "credentials", schema = "public", catalog = "edu_forms_test")
public class CredentialsEntity {
    private String login;
    private String passwd;
    private Integer refId;
    private int loginType;
    private int credentialsId;
    private String alias;

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "ref_id")
    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    @Basic
    @Column(name = "login_type")
    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    @Id
    @Column(name = "credentials_id")
    public int getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(int credentialsId) {
        this.credentialsId = credentialsId;
    }

    @Basic
    @Column(name = "alias")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CredentialsEntity that = (CredentialsEntity) o;

        if (loginType != that.loginType) return false;
        if (credentialsId != that.credentialsId) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (passwd != null ? !passwd.equals(that.passwd) : that.passwd != null) return false;
        if (refId != null ? !refId.equals(that.refId) : that.refId != null) return false;
        if (alias != null ? !alias.equals(that.alias) : that.alias != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        result = 31 * result + (refId != null ? refId.hashCode() : 0);
        result = 31 * result + loginType;
        result = 31 * result + credentialsId;
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        return result;
    }
}
