package ru.cmo.edu.data.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by to on 11.07.2017.
 */
@Entity
@Table(name = "file", schema = "public", catalog = "edu_forms_test")
public class File {
    private int id;
    private int codePage;
    private String filePath;
    private String fileName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code_page")
    public int getCodePage() {
        return codePage;
    }

    public void setCodePage(int codePage) {
        this.codePage = codePage;
    }

    @Basic
    @Column(name = "file_path")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id == file.id &&
                codePage == file.codePage &&
                Objects.equals(filePath, file.filePath) &&
                Objects.equals(fileName, file.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codePage, filePath, fileName);
    }
}
