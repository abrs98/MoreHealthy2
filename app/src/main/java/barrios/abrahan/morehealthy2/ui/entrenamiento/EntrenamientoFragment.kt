package barrios.abrahan.morehealthy.ui.entrenamiento

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import barrios.abrahan.morehealthy.Entrenamiento
import barrios.abrahan.morehealthy2.R
import barrios.abrahan.morehealthy2.RutinaActivity

class EntrenamientoFragment : Fragment() {

   // private lateinit var storage: FirebaseFirestore
   // private lateinit var usuario: FirebaseAuth

    private var adaptador: AdaptadorEntrenamientos?=null
    private lateinit var entrenamientoViewModel: EntrenamientoViewModel

    companion object{
        var entrenamientos= ArrayList<Entrenamiento>()
        var first = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        entrenamientoViewModel =
            ViewModelProvider(this).get(EntrenamientoViewModel::class.java)
        val root= inflater.inflate(R.layout.entrenamiento_fragment, container, false)

       // storage = FirebaseFirestore.getInstance()
       // usuario = FirebaseAuth.getInstance()

        if(first){
            fillEntrenamientos()
            first= false
        }

        adaptador= AdaptadorEntrenamientos(root.context,entrenamientos)

        val gridView : GridView = root.findViewById(R.id.trainings_catalog)

        gridView.adapter= adaptador

        return root
    }

    fun fillEntrenamientos(){
        entrenamientos.add(
            Entrenamiento("Entrenamiento inicial",R.drawable.entrenamiento_inicial,
        "35 minutos","Dificil","Principiante")
        )
    }

    private class AdaptadorEntrenamientos: BaseAdapter {
        var entrenamientos = ArrayList<Entrenamiento>()
        var contexto: Context?= null

        constructor(contexto: Context, entrenamientos: ArrayList<Entrenamiento>){
            this.contexto= contexto
            this.entrenamientos= entrenamientos
        }

        override fun getCount(): Int {
            return entrenamientos.size
        }

        override fun getItem(p0: Int): Any {
            return entrenamientos[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var entrenamiento = entrenamientos[p0]
            var inflador= LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.entrenamiento_element,null)

            var tv_title: TextView = vista.findViewById(R.id.tv_training_name)
            var imageTraining: ImageView = vista.findViewById(R.id.image_training_type)
            var tv_time: TextView = vista.findViewById(R.id.tv_training_time)
            var tv_intensity: TextView = vista.findViewById(R.id.tv_training_intensity)
            var tv_level: TextView = vista.findViewById(R.id.tv_training_level)

            tv_title.setText(entrenamiento.title)
            tv_time.setText(entrenamiento.time)
            tv_intensity.setText(entrenamiento.intensity)
            imageTraining.setImageResource(entrenamiento.image)
            tv_level.setText(entrenamiento.level)

            imageTraining.setOnClickListener {

                var intent: Intent= Intent(contexto,RutinaActivity::class.java)
                contexto!!.startActivity(intent)
            }

            return vista
        }
    }
}