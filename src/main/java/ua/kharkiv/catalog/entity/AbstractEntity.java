package ua.kharkiv.catalog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * An abstract entity.
 */
@Getter
@Setter
@MappedSuperclass
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 0L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}
