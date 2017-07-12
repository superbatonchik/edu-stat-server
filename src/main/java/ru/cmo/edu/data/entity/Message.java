package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by to on 11.07.2017.
 */
@Entity
public class Message {
    private int id;
    private String subject;
    private String body;
    private int fromEduId;
    private Date sendDate;
    private boolean isViewed;
    private Edu edu;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "from_edu_id", insertable = false, updatable = false)
    public int getFromEduId() {
        return fromEduId;
    }

    public void setFromEduId(int fromEduId) {
        this.fromEduId = fromEduId;
    }

    @Basic
    @Column(name = "send_date")
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Basic
    @Column(name = "is_viewed")
    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (fromEduId != message.fromEduId) return false;
        if (isViewed != message.isViewed) return false;
        if (subject != null ? !subject.equals(message.subject) : message.subject != null) return false;
        if (body != null ? !body.equals(message.body) : message.body != null) return false;
        if (sendDate != null ? !sendDate.equals(message.sendDate) : message.sendDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + fromEduId;
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (isViewed ? 1 : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "from_edu_id", referencedColumnName = "id", nullable = false)
    public Edu getEdu() {
        return edu;
    }

    public void setEdu(Edu edu) {
        this.edu = edu;
    }
}
