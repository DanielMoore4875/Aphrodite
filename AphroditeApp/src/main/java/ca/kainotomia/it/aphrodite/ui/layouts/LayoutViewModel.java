//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.layouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LayoutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LayoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is sample text I am typing");
    }

    public LiveData<String> getText() {
        return mText;
    }
}