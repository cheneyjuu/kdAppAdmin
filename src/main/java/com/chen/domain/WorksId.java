package com.chen.domain;

import com.chen.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * @author chen
 */
@Embeddable
public class WorksId implements ValueObject<WorksId> {
    @Column(columnDefinition = "varchar(20)")
    private final String worksId;

    public WorksId(String worksId) {
        this.worksId = worksId;
    }

    protected WorksId() {
        this.worksId = null;
    }

    public String getWorksId() {
        return worksId;
    }

    @Override
    public boolean sameValueAs(WorksId other) {
        assert worksId != null;
        return worksId.equals(other.worksId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorksId worksId1 = (WorksId) o;
        return Objects.equals(worksId, worksId1.worksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worksId);
    }

    @Override
    public String toString() {
        return "WorksId{" +
            "worksId='" + worksId + '\'' +
            '}';
    }
}
