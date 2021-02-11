package com.example.ampliandoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Timer
import kotlin.concurrent.schedule

private const val TAG= "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var botonPreg1: Button
    private lateinit var botonPreg2: Button
    private lateinit var botonPreg3: Button
    private lateinit var botonPreg4: Button
    private lateinit var botonPreg5: Button

    private lateinit var botonRes1: Button
    private lateinit var botonRes2: Button
    private lateinit var botonRes3: Button
    private lateinit var botonRes4: Button

    private lateinit var campoPreg: TextView

    private val ampViewModel= AmpViewModel()
    var pregActual= 0
    var respCorrecta= 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listaRespuestas= listOf<String>()
        var valores: List<String>

        botonPreg1= findViewById(R.id.botonPreg1)
        botonPreg2= findViewById(R.id.botonPreg2)
        botonPreg3= findViewById(R.id.botonPreg3)
        botonPreg4= findViewById(R.id.botonPreg4)
        botonPreg5= findViewById(R.id.botonPreg5)

        botonRes1= findViewById(R.id.botonRes1)
        botonRes2= findViewById(R.id.botonRes2)
        botonRes3= findViewById(R.id.botonRes3)
        botonRes4= findViewById(R.id.botonRes4)

        campoPreg= findViewById(R.id.PregActual)

        ampViewModel.bloqRes(botonRes1, botonRes2, botonRes3, botonRes4)


        botonPreg1.setOnClickListener{
            do
            {
                valores= ampViewModel.pregunta(0)
            } while (valores[1].toInt()==pregActual)
            pregActual= valores[1].toInt()
            campoPreg.text= valores[0]//Uso el valor de la pregunta
            ampViewModel.bloquearCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)//Inhabilitar botones
            listaRespuestas= ampViewModel.respuestas(0)//Regreso la lista entera de las 5 respuestas
            val aux= valores[1].toInt()//Índice de la pregunta para su respuesta correcta.
            respCorrecta= ampViewModel.llenarBotones(listaRespuestas, botonRes1, botonRes2, botonRes3, botonRes4, aux)
            ampViewModel.desbloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
        }


        botonPreg2.setOnClickListener{
            do
            {
                valores= ampViewModel.pregunta(1)
            } while (valores[1].toInt()==pregActual)
            pregActual= valores[1].toInt()
            campoPreg.text= valores[0]
            ampViewModel.bloquearCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
            listaRespuestas= ampViewModel.respuestas(1)
            val aux= valores[1].toInt()//Índice de la pregunta para su respuesta correcta.
            respCorrecta= ampViewModel.llenarBotones(listaRespuestas, botonRes1, botonRes2, botonRes3, botonRes4, aux)
            ampViewModel.desbloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
        }


        botonPreg3.setOnClickListener{
            do
            {
                valores= ampViewModel.pregunta(2)
            } while (valores[1].toInt()==pregActual)
            pregActual= valores[1].toInt()
            campoPreg.text= valores[0]
            ampViewModel.bloquearCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
            listaRespuestas= ampViewModel.respuestas(2)
            val aux= valores[1].toInt()
            respCorrecta= ampViewModel.llenarBotones(listaRespuestas, botonRes1, botonRes2, botonRes3, botonRes4, aux)
            ampViewModel.desbloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
        }


        botonPreg4.setOnClickListener{
            do
            {
                valores= ampViewModel.pregunta(3)
            } while (valores[1].toInt()==pregActual)
            pregActual= valores[1].toInt()
            campoPreg.text= valores[0]
            ampViewModel.bloquearCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
            listaRespuestas= ampViewModel.respuestas(3)
            val aux= valores[1].toInt()
            respCorrecta= ampViewModel.llenarBotones(listaRespuestas, botonRes1, botonRes2, botonRes3, botonRes4, aux)
            ampViewModel.desbloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
        }


        botonPreg5.setOnClickListener{
            do
            {
                valores= ampViewModel.pregunta(4)
            } while (valores[1].toInt()==pregActual)
            pregActual= valores[1].toInt()
            campoPreg.text= valores[0]
            ampViewModel.bloquearCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
            listaRespuestas= ampViewModel.respuestas(4)
            val aux= valores[1].toInt()
            respCorrecta= ampViewModel.llenarBotones(listaRespuestas, botonRes1, botonRes2, botonRes3, botonRes4, aux)
            ampViewModel.desbloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
        }


        botonRes1.setOnClickListener{
            if (respCorrecta==0)
            {
                ampViewModel.bloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
                botonRes1.setBackgroundColor(Color.parseColor("#04FF0F"))//Verde

                val delayedHandler = Handler()
                delayedHandler.postDelayed({
                    ampViewModel.resetBotones(botonRes1, botonRes2, botonRes3, botonRes4, campoPreg)
                    ampViewModel.desbloqCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
                    botonRes1.setBackgroundColor(Color.parseColor("#EFF1F4"))
                }, 3000)
                Toast.makeText(this, "Felicidades, acertaste", Toast.LENGTH_LONG).show()
            }
            else
            {
                ampViewModel.bloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
                botonRes1.setBackgroundColor(Color.parseColor("#FF0000"))//Rojo
                ampViewModel.revelarResCorrecta(botonRes1, botonRes2, botonRes3, botonRes4, respCorrecta)//Muestra la respuesta correcta.

                val delayedHandler= Handler()//Lo que está dentro se ejecuta después de que pase el tiempo designado
                delayedHandler.postDelayed({
                    ampViewModel.ocultarResCorrecta(botonRes1, botonRes2, botonRes3, botonRes4, respCorrecta)//Oculta la respuesta correcta.
                    ampViewModel.resetBotones(botonRes1, botonRes2, botonRes3, botonRes4, campoPreg)
                    ampViewModel.desbloqCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
                    botonRes1.setBackgroundColor(Color.parseColor("#EFF1F4"))
                }, 3000)
                Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_LONG).show()
                /*Timer().schedule(3000)//Esta era otra forma pero crea un thread que no es el principal entonces no me sirve pra este caso
                {

                }*/
            }
        }


        botonRes2.setOnClickListener{
            if (respCorrecta==1)
            {
                ampViewModel.bloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
                botonRes2.setBackgroundColor(Color.parseColor("#04FF0F"))

                val delayedHandler = Handler()
                delayedHandler.postDelayed({
                    ampViewModel.resetBotones(botonRes1, botonRes2, botonRes3, botonRes4, campoPreg)
                    ampViewModel.desbloqCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
                    botonRes2.setBackgroundColor(Color.parseColor("#EFF1F4"))
                }, 3000)
                Toast.makeText(this, "Felicidades, acertaste", Toast.LENGTH_LONG).show()
            }
            else
            {
                ampViewModel.bloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
                botonRes2.setBackgroundColor(Color.parseColor("#FF0000"))
                ampViewModel.revelarResCorrecta(botonRes1, botonRes2, botonRes3, botonRes4, respCorrecta)
                val delayedHandler = Handler()
                delayedHandler.postDelayed({
                    ampViewModel.ocultarResCorrecta(botonRes1, botonRes2, botonRes3, botonRes4, respCorrecta)
                    ampViewModel.resetBotones(botonRes1, botonRes2, botonRes3, botonRes4, campoPreg)
                    ampViewModel.desbloqCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
                    botonRes2.setBackgroundColor(Color.parseColor("#EFF1F4"))
                }, 3000)
                Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_LONG).show()
            }
        }


        botonRes3.setOnClickListener{
            if (respCorrecta==2)
            {
                ampViewModel.bloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
                botonRes3.setBackgroundColor(Color.parseColor("#04FF0F"))

                val delayedHandler = Handler()
                delayedHandler.postDelayed({
                    ampViewModel.resetBotones(botonRes1, botonRes2, botonRes3, botonRes4, campoPreg)
                    ampViewModel.desbloqCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
                    botonRes3.setBackgroundColor(Color.parseColor("#EFF1F4"))
                }, 3000)
                Toast.makeText(this, "Felicidades, acertaste", Toast.LENGTH_LONG).show()
            }
            else
            {
                ampViewModel.bloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
                botonRes3.setBackgroundColor(Color.parseColor("#FF0000"))
                ampViewModel.revelarResCorrecta(botonRes1, botonRes2, botonRes3, botonRes4, respCorrecta)

                val delayedHandler = Handler()
                delayedHandler.postDelayed({
                    ampViewModel.ocultarResCorrecta(botonRes1, botonRes2, botonRes3, botonRes4, respCorrecta)
                    ampViewModel.resetBotones(botonRes1, botonRes2, botonRes3, botonRes4, campoPreg)
                    ampViewModel.desbloqCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
                    botonRes3.setBackgroundColor(Color.parseColor("#EFF1F4"))
                }, 3000)
                Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_LONG).show()
            }
        }


        botonRes4.setOnClickListener{
            if (respCorrecta==3)
            {
                ampViewModel.bloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
                botonRes4.setBackgroundColor(Color.parseColor("#04FF0F"))

                val delayedHandler = Handler()
                delayedHandler.postDelayed({
                    ampViewModel.resetBotones(botonRes1, botonRes2, botonRes3, botonRes4, campoPreg)
                    ampViewModel.desbloqCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
                    botonRes4.setBackgroundColor(Color.parseColor("#EFF1F4"))
                }, 3000)
                Toast.makeText(this, "Felicidades, acertaste", Toast.LENGTH_LONG).show()
            }
            else
            {
                ampViewModel.bloqRes(botonRes1, botonRes2, botonRes3, botonRes4)
                botonRes4.setBackgroundColor(Color.parseColor("#FF0000"))
                ampViewModel.revelarResCorrecta(botonRes1, botonRes2, botonRes3, botonRes4, respCorrecta)

                val delayedHandler = Handler()
                delayedHandler.postDelayed({
                    ampViewModel.ocultarResCorrecta(botonRes1, botonRes2, botonRes3, botonRes4, respCorrecta)
                    ampViewModel.resetBotones(botonRes1, botonRes2, botonRes3, botonRes4, campoPreg)
                    ampViewModel.desbloqCates(botonPreg1, botonPreg2, botonPreg3, botonPreg4, botonPreg5)
                    botonRes4.setBackgroundColor(Color.parseColor("#EFF1F4"))
                }, 3000)
                Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart()
    {
        super.onStart()
        Log.d(TAG, "Se ejecuto onStart")
    }

    override fun onResume()
    {
        super.onResume()
        Log.d(TAG, "Se ejecuto onResume")
    }

    override fun onPause()
    {
        super.onPause()
        Log.d(TAG, "Se ejecuto onPause")
    }

    override fun onStop()
    {
        super.onStop()
        Log.d(TAG, "Se ejecuto onStop")
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d(TAG, "Se ejecuto onDestroy")
    }

    /*override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "En onSaveInstanceState")
        outState.putInt("PREGUNTA_ACTUAL", modeloquiz.PreguntaActual)
        outState.putCharSequence("CAMPO_PREGUNTA", campoPregunta.text)
        outState.putCharSequence("CAMPO_RESPUESTA", CajaRes.text)
    }*/
}