package ru.cmo.edu.data.entity;

import javax.persistence.*;

@Entity
@SqlResultSetMapping(
        name="formStatusMapping",
        classes={
                @ConstructorResult(
                        targetClass=FormStatus.class,
                        columns={
                                @ColumnResult(name="id", type = Integer.class),
                                @ColumnResult(name="ok", type = Integer.class),
                                @ColumnResult(name="expired", type = Integer.class),
                                @ColumnResult(name="errors", type = Integer.class),
                                @ColumnResult(name="expired_errors", type = Integer.class)
                        }
                )
        }
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "FormStatus.getMunicipalityStatusForEduView",
                query = "select " +
                        "eo.municipality_id as id, " +
                        "count(case when fd.status = 0 then 1 end) as ok, " +
                        "count(case when fd.status = 1 then 1 end) as expired, " +
                        "count(case when fd.status = 2 then 1 end) as errors, " +
                        "count(case when fd.status = 3 then 1 end) as expired_errors " +
                        "from edu_form_data fd " +
                        "join edu eo on eo.id = fd.edu_id " +
                        "where " +
                        "(extract(year from fd.send_date) = extract(year from now()) and false=?1) or " +
                        "(extract(year from fd.send_date) < extract(year from now()) and true=?1) " +
                        "group by eo.municipality_id", resultSetMapping = "formStatusMapping"),
        @NamedNativeQuery(
                name = "FormStatus.getEduKindStatusForEduView",
                query = "select " +
                        "eo.edu_kind_id as id, " +
                        "count(case when fd.status = 0 then 1 end) as ok, " +
                        "count(case when fd.status = 1 then 1 end) as expired, " +
                        "count(case when fd.status = 2 then 1 end) as errors, " +
                        "count(case when fd.status = 3 then 1 end) as expired_errors " +
                        "from edu_form_data fd " +
                        "join edu eo on eo.id = fd.edu_id " +
                        "where " +
                        "eo.municipality_id = ?1 and " +
                        "(" +
                        "(extract(year from fd.send_date) = extract(year from now()) and false=?2) or " +
                        "(extract(year from fd.send_date) < extract(year from now()) and true=?2)" +
                        ") " +
                        "group by eo.edu_kind_id", resultSetMapping = "formStatusMapping"),
        @NamedNativeQuery(
                name = "FormStatus.getEduStatus",
                query = "select " +
                        "eo.id as id, " +
                        "count(case when fd.status = 0 then 1 end) as ok, " +
                        "count(case when fd.status = 1 then 1 end) as expired, " +
                        "count(case when fd.status = 2 then 1 end) as errors, " +
                        "count(case when fd.status = 3 then 1 end) as expired_errors " +
                        "from edu_form_data fd " +
                        "join edu eo on eo.id = fd.edu_id " +
                        "where " +
                        "eo.municipality_id = ?1 and " +
                        "eo.edu_kind_id = ?2 and " +
                        "(" +
                        "(extract(year from fd.send_date) = extract(year from now()) and false=?3) or " +
                        "(extract(year from fd.send_date) < extract(year from now()) and true=?3)" +
                        ") " +
                        "group by eo.id", resultSetMapping = "formStatusMapping"),
        @NamedNativeQuery(
                name = "FormStatus.getMunicipalityStatus",
                query = "SELECT " +
                        "  fd.municipality_id AS id, " +
                        "  count(CASE WHEN fd.status = 0 THEN 1 END) AS ok, " +
                        "  count(CASE WHEN fd.status = 1 THEN 1 END) AS expired, " +
                        "  count(CASE WHEN fd.status = 2 THEN 1 END) AS errors, " +
                        "  count(CASE WHEN fd.status = 3 THEN 1 END) AS expired_errors " +
                        "FROM municipality_form_data fd " +
                        "WHERE " +
                        "(extract(year from fd.send_date) = extract(year from now()) and false=?1) or " +
                        "(extract(year from fd.send_date) < extract(year from now()) and true=?1) " +
                        "GROUP BY fd.municipality_id")
})
public class FormStatus {
    @Id
    private int id;
    private int ok;
    private int expired;
    private int errors;
    private int expiredErrors;

    public FormStatus(int id, int ok, int expired, int errors, int expiredErrors) {
        this.id = id;
        this.ok = ok;
        this.expired = expired;
        this.errors = errors;
        this.expiredErrors = expiredErrors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public int getExpiredErrors() {
        return expiredErrors;
    }

    public void setExpiredErrors(int expiredErrors) {
        this.expiredErrors = expiredErrors;
    }
}
