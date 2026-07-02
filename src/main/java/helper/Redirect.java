package helper;

public class Redirect {

    private String stdoutFile;
    private String stderrFile;
    private boolean stdoutAppend;
    private boolean stderrAppend;

    public String getStdoutFile() {
        return stdoutFile;
    }

    public void setStdoutFile(String stdoutFile) {
        this.stdoutFile = stdoutFile;
    }

    public boolean isStdoutAppend() {
        return stdoutAppend;
    }

    public void setStdoutAppend(boolean stdoutAppend) {
        this.stdoutAppend = stdoutAppend;
    }

    public boolean hasStdoutRedirect() {
        return stdoutFile != null;
    }

    public boolean hasStderrRedirect() {
        return stderrFile != null;
    }

    public String getStderrFile() {
        return stderrFile;
    }

    public void setStderrFile(String stderrFile) {
        this.stderrFile = stderrFile;
    }

    public boolean isStderrAppend() {
        return stderrAppend;
    }

    public void setStderrAppend(boolean stderrAppend) {
        this.stderrAppend = stderrAppend;
    }
}
