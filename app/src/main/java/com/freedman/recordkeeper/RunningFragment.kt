package com.freedman.recordkeeper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.freedman.recordkeeper.databinding.FragmentRunningBinding

//var firstRun = true

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get information
        //Update Information
        //button click listeners
        setupClickListeners()

    }

    override fun onResume() {
        super.onResume()
        displayRecords()
        /*if (firstRun == true){ firstRun = false}
        else {displayRecords()}*/
    }

    private fun displayRecords() {
        val runningPreferences: SharedPreferences =
            requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)
        binding.textView5kmTime.text = runningPreferences.getString("5km record", null)
        binding.textView5kmDate.text = runningPreferences.getString("5km date", null)
        binding.textView10kmTime.text = runningPreferences.getString("10km record", null)
        binding.textView10kmDate.text = runningPreferences.getString("10km date", null)
        binding.textViewHalfmarathonTime.text =
            runningPreferences.getString("Half Marathon record", null)
        binding.textViewHalfmarathonDate.text =
            runningPreferences.getString("Half Marathon date", null)
        binding.textViewMarathonTime.text = runningPreferences.getString("Marathon record", null)
        binding.textViewMarathonDate.text = runningPreferences.getString("Marathon", null)
    }

    private fun setupClickListeners() {
        binding.container5km.setOnClickListener { launchRunningRecordScreen(binding.textView5kmHeading.text.toString()) }
        binding.container10km.setOnClickListener { launchRunningRecordScreen(binding.textView10kmHeading.text.toString()) }
        binding.containerHalfmarathon.setOnClickListener { launchRunningRecordScreen(binding.textViewHalfmarathonHeading.text.toString()) }
        binding.containerMarathon.setOnClickListener { launchRunningRecordScreen(binding.textViewMarathonHeading.text.toString()) }

    }

    private fun launchRunningRecordScreen(record: String) {
        val screenDataUsed: EditRecordActivity.ScreenData =
            EditRecordActivity.ScreenData(record, FILENAME, "time")

        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, screenDataUsed)
        startActivity(intent)
    }

    companion object{
        const val FILENAME = "running"
    }


}


/*private lateinit var binding: FragmentRunningBinding

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View {

    binding = FragmentRunningBinding.inflate(inflater, container, false)
    return binding.root

}

override fun onViewCreated(view: View, savedInstanceState: Bundle?    ) { //use this method for adding to this file, rather than the onCreateView as it can cause crashes
    super.onViewCreated(view, savedInstanceState) //write code after this line
    setupClickListeners()

}

override fun onResume() {
    super.onResume()
    displayRecords()
}

private fun setupClickListeners() {
    binding.container5km.setOnClickListener { launchRunningRecordScreen("5km") }
    binding.container10km.setOnClickListener { launchRunningRecordScreen("10km") }
    binding.containerHalfmarathon.setOnClickListener { launchRunningRecordScreen("Half Marathon") }
    binding.containerMarathon.setOnClickListener { launchRunningRecordScreen("Marathon") }
}


private fun displayRecords() {
    val runningPreferences = requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)
    binding.textView5kmTime.text = runningPreferences.getString("5km record", null)
    binding.textView5kDate.text = runningPreferences.getString("5km date", null)
    binding.textView10kmTime.text = runningPreferences.getString("10km record", null)
    binding.textView10kmDate.text = runningPreferences.getString("10km date", null)
    binding.textViewHalfmarathonTime.text = runningPreferences.getString("Half Marathon record", null)
    binding.textViewHalfmarathonDate.text = runningPreferences.getString("Half Marathon date", null)
    binding.textViewMarathonTime.text = runningPreferences.getString("Marathon record", null)
    binding.textViewMarathonDate.text = runningPreferences.getString("Marathon", null)
}

private fun launchRunningRecordScreen(record: String) {
    //context because in Fragment instead of class
    val theScreenDataUsed = EditRecordActivity.ScreenData(record, "running", "Time")

    val intent = Intent(context, EditRecordActivity::class.java) //move from this fragment to another
    intent.putExtra("screen_data", theScreenDataUsed) //EditRunningRecordActivity.Sc
    startActivity(intent)
}


}*/


/*
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View {

    binding = FragmentRunningBinding.inflate(inflater,container,false)
    return  binding.root
    //return inflater.inflate(R.layout.fragment_running, container, false)
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //use this method for adding to this file, rather than the onCreateView as it can cause crashes
    super.onViewCreated(view, savedInstanceState) //write code after this line
    //changeText()
    setupClickListeners()

    //changeText ()
    //we can change the 5km -> Marathon views here, just need inputs

}

private fun setupClickListeners() {
    //val info = CustomOnClickListener
    //binding.container5km.setOnClickListener(info)

    */
/*binding.container5km.setOnClickListener(object : View.OnClickListener{
        override fun onClick(v: View?) {
            launchRunningRecordScreen("5km")
        }
    })*//*


    binding.container5km.setOnClickListener{
        // val ans1 = binding.textView5kHeading
        //changeText(ans1)
        launchRunningRecordScreen("5km")}
    binding.container10km.setOnClickListener{launchRunningRecordScreen("10km")}
    binding.containerHalfmarathon.setOnClickListener{launchRunningRecordScreen("Half Marathon")}
    binding.containerMarathon.setOnClickListener{launchRunningRecordScreen("Marathon")}
}

private fun launchRunningRecordScreen(distance: String) {
    //context //italisized means its a variable part of our parent class
    //because we are in a fragment rather than an Activity, we use Context instead
    val intent = Intent(context,EditRunningRecordActivity::class.java) //move from this fragment to another
    intent.putExtra("Distance", distance)
    startActivity(intent)

    //if 5km is clicked, then we send 5km string
}

fun changeText(stringword: String) {
    binding.textView5kHeading.text = stringword

    //binding.textView5kHeading.text = newINFO
    //binding.textView5kHeading.text = "hello"
    //val changes = intent.getStringExtra("Distance")

}

}
*/
