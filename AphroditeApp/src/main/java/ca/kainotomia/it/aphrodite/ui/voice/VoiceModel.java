package ca.kainotomia.it.aphrodite.ui.voice;

public class VoiceModel {
    String voiceCmdTitle;
    String voiceCmdDesc;

    public VoiceModel() {
    }

    public VoiceModel(String voiceCmdTitle, String voiceCmdDesc) {
        this.voiceCmdTitle = voiceCmdTitle;
        this.voiceCmdDesc = voiceCmdDesc;
    }

    public String getVoiceCmdDesc() {
        return voiceCmdDesc;
    }

    public String getVoiceCmdTitle() {
        return voiceCmdTitle;
    }

    public void setVoiceCmdDesc(String voiceCmdDesc) {
        this.voiceCmdDesc = voiceCmdDesc;
    }

    public void setVoiceCmdTitle(String voiceCmdTitle) {
        this.voiceCmdTitle = voiceCmdTitle;
    }
}
