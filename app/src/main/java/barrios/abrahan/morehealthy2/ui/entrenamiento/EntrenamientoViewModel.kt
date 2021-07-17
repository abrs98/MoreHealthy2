package barrios.abrahan.morehealthy.ui.entrenamiento

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EntrenamientoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Entrenamiento Fragment"
    }
    val text: LiveData<String> = _text
}