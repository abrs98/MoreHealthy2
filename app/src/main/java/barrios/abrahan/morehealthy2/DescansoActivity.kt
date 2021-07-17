package barrios.abrahan.morehealthy2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DescansoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descanso)

        var btn_siguiente: Button = findViewById(R.id.btn_siguiente)

        btn_siguiente.setOnClickListener {

            var intento: Intent = Intent(this, EjercicioActivity::class.java)
            startActivity(intento)

        }
    }
}