package org.fao.geonet.domain;

import com.vividsolutions.jts.util.Assert;

import javax.persistence.*;

/**
 * Represents data used by a particular harvester to ensure that only the minimum required data is obtained from the Remote Server.
 * <p/>
 * Often this is not required because the harvested metadata has information such as changeDate etc... that can be used for
 * synchronization but it is equally possible for the change data in the metadata to not be the same as the reported changeDate.
 * <p/>
 * For example, a file based harvester might harvest files from a file system but (depending on the harvester configuration) will not
 * synchronize the file data with the metadata-change date (often a good idea because the two may not be the same).  In this case
 * if the metadata-change date is compared to the file change date then the file is always harvested.
 * <p/>
 *
 * Created by Jesse on 1/23/14.
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "HarvesterData")
public class HarvesterData {
    private HarvesterDataId id;
    private String value;

    /**
     * Get the id of the data.
     * @return the id of the data.
     */
    @Id
    public HarvesterDataId getId() {
        return id;
    }

    /**
     * Set the id of the data.
     * @param id the id of the data.
     */
    public void setId(HarvesterDataId id) {
        this.id = id;
    }

    /**
     * Get the value for the entity.  Maximum length is 255.
     * @return the value of the data entity.
     */
    @Column(nullable = false, length = 255)
    public String getValue() {
        return value;
    }

    /**
     * Set the value for the entity.  Maximum length is 255.
     * @param value the new value.
     */
    public void setValue(String value) {
        Assert.isTrue(value.length() <= 255);
        this.value = value;
    }

    /**
     * Return the value as an int.
     * @return the value as an int.
     */
    @Transient
    public int getValueAsInt() {
        return Integer.parseInt(value);
    }

    /**
     * Return the value as an long.
     * @return the value as an long.
     */
    @Transient
    public long getValueAsLong() {
        return Integer.parseInt(value);
    }

    /**
     * Set the value.
     * @param value the new value
     */
    public void setValue(long value) {
        setValue(Long.toString(value));
    }
    /**
     * Return the value as a boolean.
     * @return the value as a boolean.
     */
    @Transient
    public boolean getValueAsBoolean() {
        return Boolean.parseBoolean(value);
    }

    /**
     * Set the value.
     * @param value the new value
     */
    public void setValue(boolean value) {
        setValue(Boolean.toString(value));
    }

    @Transient
    public ISODate getValueAsDate() {
        return new ISODate(value);
    }

    /**
     * Set the value.
     * @param value the new value
     */
    public void setValue(ISODate value) {
        setValue(value.getDateAndTime());
    }
}
