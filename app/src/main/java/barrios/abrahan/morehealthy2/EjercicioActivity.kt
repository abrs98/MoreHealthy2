package barrios.abrahan.morehealthy2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EjercicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio)

        var btn_hecho: Button = findViewById(R.id.btn_hecho)

        btn_hecho.setOnClickListener {
            var intent : Intent= Intent(this, DescansoActivity::class.java)
            startActivity(intent)
        }
    }
}