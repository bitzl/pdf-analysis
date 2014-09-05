package pdfanalysis.model;

public class PdfInfo {

    private String filename;
    private String creationSW;
    private boolean valid;
    private String version;
    private int errorGroup;
    private String errorCode;
    private String errorMessage;

    public PdfInfo() {
    }

    public PdfInfo(PdfInfo info) {
        this.filename = info.filename;
        this.creationSW = info.creationSW;
        this.valid = info.valid;
        this.version = info.version;
        this.errorGroup = info.errorGroup;
        this.errorMessage = info.errorMessage;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCreationSW() {
        return creationSW;
    }

    public void setCreationSW(String creationSW) {
        this.creationSW = creationSW;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getErrorGroup() {
        return errorGroup;
    }

    public void setErrorGroup(int errorGroup) {
        this.errorGroup = errorGroup;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
