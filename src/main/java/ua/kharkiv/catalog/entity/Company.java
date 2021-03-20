package ua.kharkiv.catalog.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "companies")
public class Company extends AbstractEntity {
    private static final long serialVersionUID = 0L;

    public Company() {
    }

    @Column(name = "name")
    private String name;


    @Column(name = "title")
    private String description;

    @Column(name = "founded")
    private LocalDateTime creationDate;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
