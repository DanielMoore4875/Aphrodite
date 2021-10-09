//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.voice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VoiceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VoiceViewModel() {
        mText = new MutableLiveData<>();
       // mText.setValue("THis is the VOice Fragment");
    }

    public LiveData<String> getText() { return  mText;}
}