package be.janschraepen.hellokitty.domain.cat;

import java.io.Serializable;

/**
 * CatPicture Data Transfer Object. Used for transferring data
 * throughout the layers.
 */
public class CatPictureDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String catId;

    private byte[] picture;

    private Long size;

    private String contentType;

    /**
     * Instantiates a new CatPersonDTO.
     */
    public CatPictureDTO() {

    }

    /**
     * @return the Cat id
     */
    public String getCatId() {
        return catId;
    }

    /**
     *
     * @param catId the Cat id to set
     */
    public void setCatId(String catId) {
        this.catId = catId;
    }

    /**
     *
     * @return the picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     *
     * @param picture the picture to set
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    /**
     *
     * @return the size
     */
    public Long getSize() {
        return size;
    }

    /**
     *
     * @param size the size to set
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     *
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     *
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
