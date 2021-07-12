package com.example.amigos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //Creacion de arreglos iniciales para crear a la matriz de personas
    var jhoedy = arrayListOf("Alan","Artemio","Adrian","Sheila");
    var alan = arrayListOf("Jhoedy","Artemio","Adrian","Sheila")
    var artemio = arrayListOf("Jhoedy","Alan","Adrian","Sheila");
    var adrian = arrayListOf("Jhoedy","Alan","Artemio","Sheila");
    var sheila = arrayListOf("Jhoedy","Alan","Artemio","Adrian");


    //Creacion de matriz de personas y arreglo que permite el rellenado de la matriz despues de la asignacion
    var amigosList = arrayListOf(jhoedy,alan,artemio,adrian,sheila);
    var amigosB = arrayListOf("Jhoedy","Alan","Artemio","Adrian","Sheila");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //Metodo para la asignacion de personas
    fun llenado (view: View){
        //Limpiado de texto para sobreescribir datos
        txtNombres1.text="De:"
        txtNombres2.text="Para:"

        //Muestra en consola el funcionamiento de llenado de los arreglos de la matriz
        for (a in 0 until amigosList.size){
            println(amigosB.get(a)+": ")
            for(b in 0 until amigosList[a].size){
                println(", "+amigosList[a].get(b))
            }
        }

        //LLenado del texto donde se determinan quines son los que daran regalo
        for (i in 0 until amigosB.size) {
            txtNombres1.text=txtNombres1.text.toString()+"\n"+ amigosB[i]
        }

        //Llenado del texto donde se determina a quienes se les dara el regalo
        for (i in 0 until amigosList.size){
            //Aleatoriamente se agarra alguna de las personas a las que les puede dar la persona de acuerdo al orden de la matriz
            var to=""

            if(amigosList[i].isNotEmpty()){
                val randomValue= Random.nextInt(0,amigosList[i].size);
                to = amigosList[i].get(randomValue);
            }

            //Se elimina de la matriz el nombre para que no haya repeticion
            for (j in 0 until amigosList.size){
                amigosList[j].remove(to)
            }
            if(i == amigosList.size-2 && amigosList[amigosList.size-1].isEmpty()){
                txtNombres2.text=txtNombres2.text.toString()+"\n"+ amigosList[i].get(0)

                txtNombres2.text=txtNombres2.text.toString()+"\n"+ to
                for(z in 0 until amigosList.size){
                    amigosList[z].clear()
                }
            }else{
                //Se imprime el resultado del nombre
                txtNombres2.text=txtNombres2.text.toString()+"\n"+ to
            }
        }

        //Se llena la matriz con los nombre una vez esta haya sido vaciada
        for (g in 0 until amigosList.size){
            for(h in 0 until amigosB.size){
                if(g!=h){
                    amigosList[g].add(amigosB.get(h))
                }
            }
        }

    }


    //Metodo para agregar una nueva persona
    fun agregar(view: View){
        //Creacion de variable para almacenar el nombre
        var nombreNuevo = nuevoAmigo.text;
        //Se agrega nuevo nombre en el arreglo y en la matriz
        for (i in 0 until amigosList.size) {
            amigosList[i].add(nombreNuevo.toString())
        }

        var list1 = ArrayList<String>()
        list1.addAll(amigosB)
        amigosList.add(list1)


        amigosB.add(nombreNuevo.toString())
    }
}