package com.ivan_jefferson.entrega_3

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val description = view.findViewById<EditText>(R.id.description)
        val dateStart = view.findViewById<EditText>(R.id.dateStart)
        val hourStart = view.findViewById<EditText>(R.id.hourStart)
        val dateFinish = view.findViewById<EditText>(R.id.dateFinish)
        val hourFinish = view.findViewById<EditText>(R.id.hourFinish)
        val eventType = view.findViewById<EditText>(R.id.eventType)
        val eventName = view.findViewById<EditText>(R.id.eventName)
        val allDay = view.findViewById<Switch>(R.id.allDay)

        val titleDateStart = view.findViewById<TextView>(R.id.dateStartTitle)
        val titleDateFinish = view.findViewById<TextView>(R.id.dateFinishTitle)


        if(arguments !== null) {
            val params:Bundle? = arguments

            actionView("description", description,params)
            actionView("dateStart",dateStart,params)
            actionView("hourStart",hourStart,params)
            actionView("dateFinish",dateFinish,params)
           val respHorFinish = actionView("hourFinish", hourFinish, params)
            actionView("eventType",eventType,params)
            actionView("name",eventName,params)
            if(params?.get("allDay" ) === true){
                allDay.isChecked = true
            }

            if(respHorFinish){
                titleDateFinish.isVisible = false
            }

        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun actionView(param:String,element: EditText, params:Bundle?): Boolean{
        var isNull = true
        if(params?.get(param) !== null) {
            isNull = false
            element.setText(params.get(param).toString())
        } else element.isVisible = false

        return isNull
    }
}