package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by to on 06.06.2017.
 */
@Entity
@Table(name = "file", schema = "public", catalog = "edu_forms_test")
public class FileEntity {
    private int fileId;
    private byte[] contents;
    private int codePage;

    @Id
    @Column(name = "file_id")
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
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

        FileEntity that = (FileEntity) o;

        if (fileId != that.fileId) return false;
        if (codePage != that.codePage) return false;
        if (!Arrays.equals(contents, that.contents)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileId;
        result = 31 * result + Arrays.hashCode(contents);
        result = 31 * result + codePage;
        return result;
    }

}
