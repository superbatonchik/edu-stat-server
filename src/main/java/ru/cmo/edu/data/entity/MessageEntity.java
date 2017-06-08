package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "message", schema = "public", catalog = "edu_forms_test")
public class MessageEntity {
    private int messageId;
    private String subject;
    private String body;
    private Timestamp sendDate;
    private boolean isViewed;
    private EduEntity edu;

    @Id
    @Column(name = "message_id")
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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
    @Column(name = "send_date")
    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
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

        MessageEntity that = (MessageEntity) o;

        if (messageId != that.messageId) return false;
        if (isViewed != that.isViewed) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (sendDate != null ? !sendDate.equals(that.sendDate) : that.sendDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (isViewed ? 1 : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "from_edu_id", referencedColumnName = "edu_id", nullable = false)
    public EduEntity getEdu() {
        return edu;
    }

    public void setEdu(EduEntity eduByFromEduId) {
        this.edu = eduByFromEduId;
    }
}
