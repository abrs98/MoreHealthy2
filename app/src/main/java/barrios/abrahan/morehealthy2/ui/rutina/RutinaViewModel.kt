package barrios.abrahan.morehealthy2.ui.rutina

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RutinaViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Rutina Fragment"
    }
    val text: LiveData<String> = _text
}