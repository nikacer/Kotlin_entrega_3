package com.ivan_jefferson.entrega_3

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.regex.Pattern
import kotlin.concurrent.timerTask

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val buttonSave = view.findViewById<Button>(R.id.save)
        val switch = view.findViewById<Switch>(R.id.allDay)
        val name = view.findViewById<TextView>(R.id.name)
        val eventType = view.findViewById<Spinner>(R.id.event_type)
        val dateStart = view.findViewById<EditText>(R.id.dateStart)
        val hourStart = view.findViewById<EditText>(R.id.hourStart)
        val dateFinish = view.findViewById<EditText>(R.id.dateFinish)
        val hourFinish = view.findViewById<EditText>(R.id.hourFinish)
        val description = view.findViewById<EditText>(R.id.description)
        val dateStartTitle = view.findViewById<TextView>(R.id.dateStartTitle)
        val dateFinishtTitle = view.findViewById<TextView>(R.id.dateFinishTitle)
        val errorsInput = view.findViewById<EditText>(R.id.errors)
        val scroll = view.findViewById<ScrollView>(R.id.scroll)




            buttonSave.setOnClickListener {
                var bundle:Bundle? = null
                 val errors = mutableListOf<String>()
                val regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]"
                val p: Pattern = Pattern.compile(regex)


                if (name.text.toString().trim().isEmpty()) errors.add("Debe tener un Nombre el Evento")
                if(!validDate(dateStart.text.toString(), "dd/MM/yyyy")) errors.add("La fecha ${ if (!switch.isChecked ) "de Inicio" else ""} es incorrecta")


                if(switch.isChecked  ){

                    if(errors.isEmpty()){
                        bundle = bundleOf(
                                "description" to description.text,
                                "dateStart" to dateStart.text,
                                "allDay" to switch.isChecked,
                                "name" to name.text,
                                "eventType" to eventType.selectedItem
                        )
                    }


                }else {
                    if(!validDate(dateFinish.text.toString(), "dd/MM/yyyy")) errors.add("La fecha final es Incorrecta")
                    if(p.matcher(hourStart.text.toString()).matches() || hourStart.text.isEmpty()) errors.add("La Hora de inicio es Incorrecta")
                    if(p.matcher(hourFinish.text.toString()).matches() || hourFinish.text.isEmpty()) errors.add("La Hora Final es Incorrecta")



                    if(errors.isEmpty()){
                        bundle = bundleOf(
                                "description" to description.text,
                                "dateStart" to dateStart.text,
                                "hourStart" to hourStart.text,
                                "dateFinish" to dateFinish.text,
                                "hourFinish" to hourFinish.text,
                                "allDay" to switch.isChecked,
                                "name" to name.text,
                                "eventType" to eventType.selectedItem
                        )
                    }
                }

                if(bundle !== null) {
                    errorsInput.isVisible = false
                    findNavController().navigate(
                            R.id.action_FirstFragment_to_SecondFragment,
                            bundle
                    )
                }else{
                    errorsInput.isVisible=true
                    errorsInput.setText(errors.joinToString("\n"))

                    Timer().schedule(timerTask { scroll.smoothScrollTo(0,30000) },200)

                }
        }

        switch.setOnClickListener {
            errorsInput.isVisible = false
            if(switch.isChecked){
                hourStart.isVisible = false
                dateFinishtTitle.isVisible =false
                dateFinish.isVisible = false
                hourFinish.isVisible = false
                dateStartTitle.text = "Fecha"
            }else {
                hourStart.isVisible = true
                dateFinishtTitle.isVisible =true
                dateFinish.isVisible = true
                hourFinish.isVisible = true
                dateStartTitle.text = resources.getString(R.string.dateStartTitle)
            }
        }


    }


    private fun validDate(date: String, format: String):Boolean{
        var isValid = false
        try {
            SimpleDateFormat(format).parse(date)
            isValid = true
        }catch (e: Exception){
            isValid=false
        }

        return isValid
    }




}