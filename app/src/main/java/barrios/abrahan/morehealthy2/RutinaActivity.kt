package barrios.abrahan.morehealthy2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RutinaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina)

        var btn_iniciar_rutina: Button = findViewById(R.id.btn_iniciar_rutina)
        var btn_iniciar_rutina_2: Button = findViewById(R.id.btn_iniciar_rutina2)

        btn_iniciar_rutina.setOnClickListener {

            var intento: Intent= Intent(this, EjercicioActivity::class.java)
            startActivity(intento)

        }
        btn_iniciar_rutina_2.setOnClickListener {

            var intento: Intent= Intent(this, EjercicioActivity::class.java)
            startActivity(intento)

        }
    }
}