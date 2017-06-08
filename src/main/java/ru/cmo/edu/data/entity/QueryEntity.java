package ru.cmo.edu.data.entity;

import javax.persistence.*;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "query", schema = "public", catalog = "edu_forms_test")
public class QueryEntity {
    private int queryId;
    private String title;
    private String content;
    private FormEntity formByFormId;
    private CredentialsEntity credentialsByCredentialsId;

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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueryEntity that = (QueryEntity) o;

        if (queryId != that.queryId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = queryId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "form_id", nullable = false)
    public FormEntity getFormByFormId() {
        return formByFormId;
    }

    public void setFormByFormId(FormEntity formByFormId) {
        this.formByFormId = formByFormId;
    }

    @ManyToOne
    @JoinColumn(name = "credentials_id", referencedColumnName = "credentials_id", nullable = false)
    public CredentialsEntity getCredentialsByCredentialsId() {
        return credentialsByCredentialsId;
    }

    public void setCredentialsByCredentialsId(CredentialsEntity credentialsByCredentialsId) {
        this.credentialsByCredentialsId = credentialsByCredentialsId;
    }
}
