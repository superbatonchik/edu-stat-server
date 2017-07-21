package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "query", schema = "public", catalog = "edu_forms_test")
public class Query {
    private int queryId;
    private String title;
    private int formId;
    private String content;
    private int credentialsId;
    private Form form;
    private Credentials credentials;

    @Id
    @Column(name = "query_id")
    public int getQueryId() {
        return queryId;
    }

    public void setQueryId(int queryId) {
        this.queryId = queryId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "form_id", insertable = false, updatable = false)
    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "credentials_id", insertable = false, updatable = false)
    public int getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(int credentialsId) {
        this.credentialsId = credentialsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query query = (Query) o;

        if (queryId != query.queryId) return false;
        if (formId != query.formId) return false;
        if (credentialsId != query.credentialsId) return false;
        if (title != null ? !title.equals(query.title) : query.title != null) return false;
        if (content != null ? !content.equals(query.content) : query.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = queryId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + formId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + credentialsId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false)
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @ManyToOne
    @JoinColumn(name = "credentials_id", referencedColumnName = "id", nullable = false)
    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
