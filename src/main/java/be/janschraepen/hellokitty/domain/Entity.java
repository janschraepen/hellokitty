package be.janschraepen.hellokitty.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Abstract class Entity. This class is the base Entity class
 *  for other specific Entities.
 */
@MappedSuperclass
public abstract class Entity implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "UPDATED_ON")
    private Date updatedOn;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    /**
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     *
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     *
     * @return the updatedOn
     */
    public Date getUpdatedOn() {
        return updatedOn;
    }

    /**
     *
     * @return the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

}
