package ru.cmo.edu.data.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * Created by to on 11.07.2017.
 */
@Entity
public class File {
    private int id;
    private byte[] contents;
    private int codePage;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "contents")
    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    @Basic
    @Column(name = "code_page")
    public int getCodePage() {
        return codePage;
    }

    public void setCodePage(int codePage) {
        this.codePage = codePage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (id != file.id) return false;
        if (codePage != file.codePage) return false;
        if (!Arrays.equals(contents, file.contents)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Arrays.hashCode(contents);
        result = 31 * result + codePage;
        return result;
    }
}
