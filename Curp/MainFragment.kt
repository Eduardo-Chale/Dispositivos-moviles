package com.example.viewmodel.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import com.example.viewmodel.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        activity?.application?.let {
            //****************************************************************************
            //Lenado de spinners
            val mes = arrayOf ("01","02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
            var adapterMes= ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,mes)
            spnMes.adapter=adapterMes

            val sexo = arrayOf("Hombre", "Mujer")
            var adapterSexo= ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,sexo)
            spnSexo.adapter=adapterSexo

            val dia = arrayOf("01","02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31")
            var adapterDia= ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,dia)
            spnDia.adapter=adapterDia

            val estado= arrayOf("Aguascalientes","Baja California","Baja California Sur","Campeche","Chiapas","Chihuahua","Ciudad de Mexico","Coahuila",
                "Colima","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","Mexico","Michoacan","Morelos","Nayarit","Nuevo Leon","Oaxaca","Puebla"
            ,"Queretaro","Quintana Roo","San Luis Potosi","Sinaloa","Sonora","Tabasco","Taumalipas","Tlaxcala","Veracruz","Yucatan","Zacatecas")
            var adapterEstado= ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,estado)
            spnEstado.adapter=adapterEstado

            //*******************************************************************************
            val factory = SavedStateViewModelFactory(it,this)
            viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
            //val resultObserver = Observer<Float>{result->message.text=result.toString()}
        }


        //val resultObserver = Observer<Float>{result->message.text=result.toString()}

        //viewModel.getResult().observe(viewLifecycleOwner,resultObserver)

        //Metodo para crear CURP
        convertBtn.setOnClickListener{
            val mes = spnMes.getSelectedItem().toString()
            val dia = spnDia.getSelectedItem().toString()
            val sexo = spnSexo.getSelectedItem().toString()
            val estado = spnEstado.getSelectedItem().toString()

            if (editTextAño.text.isNotEmpty() && editTextApePaterno.text.isNotEmpty() && editTextApeMaterno.text.isNotEmpty() && editTextName.text.isNotEmpty()){
                viewModel.calcularCurp(editTextAño.text.toString(),editTextName.text.toString(),editTextApePaterno.text.toString()
                    ,editTextApeMaterno.text.toString(),mes,dia,sexo,estado)
                message.text=viewModel.getResult().toString()
            }else{
                message.text="Faltan valores"
            }
        }

        //Metodo para limpiar variables
        limpiarBtn.setOnClickListener{
            editTextName.text.clear()
            editTextApeMaterno.text.clear()
            editTextApePaterno.text.clear()
            editTextAño.text.clear()
            message.text="CURP"
            spnDia.setSelection(0)
            spnSexo.setSelection(0)
            spnMes.setSelection(0)
            spnEstado.setSelection(0)
        }
        //
    }

}