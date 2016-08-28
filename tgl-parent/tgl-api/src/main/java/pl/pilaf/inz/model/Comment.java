package pl.pilaf.inz.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pl.pilaf.enums.ReferanceType;

@Entity
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @NotNull
    private long refID;
    
    @NotNull
    private ReferanceType refType;
    
    @Size(max=255)
    private String description;
    
    @NotNull
    private Date createdDate;
    
    @NotNull
    private User createdBy;
    

    public Comment() {
	super();
    }

    public Comment(long refID, ReferanceType refType, String description,
	    Date createdDate, User createdBy) {
	super();
	this.refID = refID;
	this.refType = refType;
	this.description = description;
	this.createdDate = createdDate;
	this.createdBy = createdBy;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public long getRefID() {
	return refID;
    }

    public void setRefID(long refID) {
	this.refID = refID;
    }

    public ReferanceType getRefType() {
	return refType;
    }

    public void setRefType(ReferanceType refType) {
	this.refType = refType;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Date getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public User getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((createdBy == null) ? 0 : createdBy.hashCode());
	result = prime * result
		+ ((createdDate == null) ? 0 : createdDate.hashCode());
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + (int) (refID ^ (refID >>> 32));
	result = prime * result + ((refType == null) ? 0 : refType.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Comment other = (Comment) obj;
	if (createdBy == null) {
	    if (other.createdBy != null)
		return false;
	} else if (!createdBy.equals(other.createdBy))
	    return false;
	if (createdDate == null) {
	    if (other.createdDate != null)
		return false;
	} else if (!createdDate.equals(other.createdDate))
	    return false;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (id != other.id)
	    return false;
	if (refID != other.refID)
	    return false;
	if (refType != other.refType)
	    return false;
	return true;
    }

}
