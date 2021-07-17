package barrios.abrahan.morehealthy2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread.sleep(3000)
        val intent = Intent(this,LoginActivity::class.java)

        startActivity(intent)
    }
}