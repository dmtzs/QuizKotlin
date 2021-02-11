package com.example.ampliandoquiz

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.lifecycle.ViewModel
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.Random

private const val TAG= "AmpViewModel"
class AmpViewModel: ViewModel()
{
    var preguntaActual= 0
    private var modeloquiz= ModeloAmp()

    init
    {
        Log.d(TAG, "Se ha creado instancia del ViewModel")
    }

    override fun onCleared()
    {
        super.onCleared()
        Log.d(TAG, "Esto es el ViewModel")
    }

    /*
    * @Nombre:
    * @Descripción: .
    */
    fun pregunta(cate: Int): List<String>
    {
        var retorno= ""
        var num= 0
        if (cate==0)
        {
            val numAle= Random()
            num= numAle.nextInt(5)//Numero de 0 a 5
            retorno= modeloquiz.capitalesPreg[num]
        }
        else if (cate==1)
        {
            val numAle= Random()
            num= numAle.nextInt(5)//Numero de 0 a 5
            retorno= modeloquiz.serpientesPreg[num]
        }
        else if (cate==2)
        {
            val numAle= Random()
            num= numAle.nextInt(5)//Numero de 0 a 5
            retorno= modeloquiz.tolkienPreg[num]
        }
        else if (cate==3)
        {
            val numAle= Random()
            num= numAle.nextInt(5)//Numero de 0 a 4
            retorno= modeloquiz.nierPreg[num]
        }
        else if (cate==4)
        {
            val numAle= Random()
            num= numAle.nextInt(5)//Numero de 0 a 4
            retorno= modeloquiz.depredadorPreg[num]
        }
        val temp= num.toString()
        val tupla= listOf<String>(retorno, temp)
        return tupla//Retorno pregunta y su indice de la misma.
    }

    /*
    * @Nombre:
    * @Descripción: .
    */
    fun respuestas(cate: Int): List<String>
    {
        var lista= listOf<String>()
        if (cate==0)
        {
            lista= modeloquiz.capitalRes
        }
        else if (cate==1)
        {
            lista= modeloquiz.serpientesRes
        }
        else if (cate==2)
        {
            lista= modeloquiz.tolkienRes
        }
        else if (cate==3)
        {
            lista= modeloquiz.nierRes
        }
        else if (cate==4)
        {
            lista= modeloquiz.depredadorRes
        }
        return lista
    }

    /*
    * @Nombre: llenarBotones
    * @Descripción: Método que cambia el texto de los botones de respuesta a través de la llamada del método random-
    *               el cual nos ayudará a ordenar las respuestas de manera aleatoria procurando posteriormente que-
    *               siempre aparezca la respuesta correcta dentro de las opciones.
    */
    fun llenarBotones(lista: List<String>, bot1: Button, bot2: Button, bot3: Button, bot4: Button, identificador: kotlin.Int): Int
    {
        val arre: MutableList<kotlin.Int> = ArrayList()
        var ran= 0
        var valorCorrecto= 0

        for (i in 0..3)
        {
            ran= random(4)//Genera número random de 0 a 4
            //Log.d(TAG, "Entro for: ${i.toString()}")

            if (arre.isEmpty())
            {
                //Log.d(TAG, "Entro arre vacío")
                arre.add(i, ran)
                //Log.d(TAG, "Pase primer valor arre")
            }
            else if (ran in arre)
            {
                do
                {
                    ran= random(4)
                } while (ran in arre)
                arre.add(i, ran)
                Log.d(TAG, "Entro else if")
            }
            else
            {
                arre.add(i, ran)
            }
        }

        valorCorrecto= random(4)
        var temp= arre[valorCorrecto]
        arre.set(valorCorrecto, identificador)
        for (x in 0..3)
        {
            val eder= x.toString()
            Log.d(TAG, eder)
            if (identificador==arre[x])
            {
                if (x!=valorCorrecto)
                {
                    arre.set(x, temp)
                }
                else
                {}
            }
        }

        bot1.text = lista[arre[0]]
        bot2.text = lista[arre[1]]
        bot3.text = lista[arre[2]]
        bot4.text = lista[arre[3]]

        return valorCorrecto
    }
    /*
    * @Nombre: random
    * @Descripción: Método que genera un número random y retorna ese número generado.
    */
    private fun random(intervalo: Int): Int
    {
        val numAle= Random()
        val num= numAle.nextInt(intervalo)

        return num
    }

    /*
    * @Nombre: resetBotones
    * @Descripción: Método que pone las leyendas por default de los elementos.
    */
    fun resetBotones(bot1: Button, bot2: Button, bot3: Button, bot4: Button, preg: TextView)
    {
        bot1.text= "-"
        bot2.text= "-"
        bot3.text= "-"
        bot4.text= "-"
        preg.text= "¿¿??"
    }

    /*
    * @Nombre:
    * @Descripción: .
    */
    fun bloquearCates(bot1: Button, bot2: Button, bot3: Button, bot4: Button, bot5: Button)
    {
        bot1.isEnabled= false
        bot2.isEnabled= false
        bot3.isEnabled= false
        bot4.isEnabled= false
        bot5.isEnabled= false
    }

    /*
    * @Nombre:
    * @Descripción: .
    */
    fun desbloqCates(bot1: Button, bot2: Button, bot3: Button, bot4: Button, bot5: Button)
    {
        bot1.isEnabled= true
        bot2.isEnabled= true
        bot3.isEnabled= true
        bot4.isEnabled= true
        bot5.isEnabled= true
    }

    /*
    * @Nombre:
    * @Descripción: .
    */
    fun bloqRes(bot1: Button, bot2: Button, bot3: Button, bot4: Button)
    {
        bot1.isEnabled= false
        bot2.isEnabled= false
        bot3.isEnabled= false
        bot4.isEnabled= false

        bot1.setBackgroundColor(Color.parseColor("#EFF1F4"))
        bot2.setBackgroundColor(Color.parseColor("#EFF1F4"))
        bot3.setBackgroundColor(Color.parseColor("#EFF1F4"))
        bot4.setBackgroundColor(Color.parseColor("#EFF1F4"))

        bot1.setTextColor(Color.BLACK)
        bot2.setTextColor(Color.BLACK)
        bot3.setTextColor(Color.BLACK)
        bot4.setTextColor(Color.BLACK)
    }

    /*
    * @Nombre:
    * @Descripción: .
    */
    fun desbloqRes(bot1: Button, bot2: Button, bot3: Button, bot4: Button)
    {
        bot1.isEnabled= true
        bot2.isEnabled= true
        bot3.isEnabled= true
        bot4.isEnabled= true

        //Colores morados
        bot1.setBackgroundColor(Color.parseColor("#5b39c6"))
        bot2.setBackgroundColor(Color.parseColor("#5b39c6"))
        bot3.setBackgroundColor(Color.parseColor("#5b39c6"))
        bot4.setBackgroundColor(Color.parseColor("#5b39c6"))
    }

    /*
    * @Nombre:
    * @Descripción: .
    */
    fun revelarResCorrecta(bot1: Button, bot2: Button, bot3: Button, bot4: Button, ops: Int)
    {
        if (ops==0)
        {
            bot1.setBackgroundColor(Color.parseColor("#04FF0F"))
        }
        else if (ops==1)
        {
            bot2.setBackgroundColor(Color.parseColor("#04FF0F"))
        }
        else if (ops==2)
        {
            bot3.setBackgroundColor(Color.parseColor("#04FF0F"))
        }
        else if (ops==3)
        {
            bot4.setBackgroundColor(Color.parseColor("#04FF0F"))
        }
    }

    fun ocultarResCorrecta(bot1: Button, bot2: Button, bot3: Button, bot4: Button, ops: Int)
    {
        if (ops==0)
        {
            bot1.setBackgroundColor(Color.parseColor("#EFF1F4"))
        }
        else if (ops==1)
        {
            bot2.setBackgroundColor(Color.parseColor("#EFF1F4"))
        }
        else if (ops==2)
        {
            bot3.setBackgroundColor(Color.parseColor("#EFF1F4"))
        }
        else if (ops==3)
        {
            bot4.setBackgroundColor(Color.parseColor("#EFF1F4"))
        }
    }
}
