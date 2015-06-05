package gowatchit.com.matcher.dto;


public class MatchedPair {
  private int gwiId = -1;
  private String xboxId = null;

  public MatchedPair() {
  }

  public MatchedPair(int gwiId, String xboxId) {
    this.gwiId = gwiId;
    this.xboxId = xboxId;
  }

  public int getGwiId() {
    return gwiId;
  }

  public void setGwiId(int gwiId) {
    this.gwiId = gwiId;
  }

  public String getXboxId() {
    return xboxId;
  }

  public void setXboxId(String xboxId) {
    this.xboxId = xboxId;
  }
}
