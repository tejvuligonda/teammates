package teammates.ui.template;

public class StudentProfileUploadPhotoModal {

    private String googleId;
    private String pictureUrl;
    private String pictureKey;
    
    public StudentProfileUploadPhotoModal(final String googleId, final String pictureUrl, final String pictureKey) {
        this.googleId = googleId;
        this.pictureUrl = pictureUrl;
        this.pictureKey = pictureKey;
    }
    
    public String getGoogleId() {
        return googleId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getPictureKey() {
        return pictureKey;
    }

}
