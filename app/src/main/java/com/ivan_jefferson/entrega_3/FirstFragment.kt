package com.ivan_jefferson.entrega_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController

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
        val EventType = view.findViewById<Spinner>(R.id.event_type)
        val dateStart = view.findViewById<EditText>(R.id.dateStart)
        val hourStart = view.findViewById<EditText>(R.id.hourStart)
        val dateFinish = view.findViewById<EditText>(R.id.dateFinish)
        val hourFinish = view.findViewById<EditText>(R.id.hourFinish)
        val description = view.findViewById<EditText>(R.id.description)



        val dateStartTitle = view.findViewById<TextView>(R.id.dateStartTitle)
        val dateFinishtTitle = view.findViewById<TextView>(R.id.dateFinishTitle)

            buttonSave.setOnClickListener {
                var bundle:Bundle? = null
                if(switch.isChecked){
                   bundle = bundleOf(
                       "description"  to  description.text,
                       "dateStart" to dateStart.text,
                       "allDay" to switch.isChecked,
                       "name" to name.text
                   )
                }else {
                    bundle = bundleOf(
                        "description" to description.text,
                        "dateStart" to dateStart.text,
                        "hourStart" to hourStart.text,
                        "dateFinish" to dateFinish.text,
                        "hourFinish" to hourFinish.text,
                        "allDay" to switch.isChecked,
                        "name" to name.text
                    )
                }

                if(bundle !== null) {
                    findNavController().navigate(
                        R.id.action_FirstFragment_to_SecondFragment,
                        bundle
                    )
                }
        }

        switch.setOnClickListener {
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


        val eventType = view.findViewById<Spinner>(R.id.event_type)
        //val list = resources.getStringArray(R.array.eventOptions)

        val list = ArrayList<String>()

        //val adapter = ArrayAdapter(this , android.R.layout.simple_spinner_item, list)
        //eventType.adapter = adapter
    }



}