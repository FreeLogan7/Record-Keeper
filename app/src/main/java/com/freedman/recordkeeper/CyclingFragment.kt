package com.freedman.recordkeeper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.freedman.recordkeeper.databinding.FragmentCyclingBinding

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickers()

    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val cyclingPreferences = requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)
        binding.textViewLongestRideTime.text = cyclingPreferences.getString("$RECORD_LONGEST_RIDE record", null)
        binding.textViewLongestRideDate.text = cyclingPreferences.getString("$RECORD_LONGEST_RIDE date", null)
        binding.textViewBestAverageSpeedTime.text = cyclingPreferences.getString("$RECORD_BEST_AVERAGE_SPEED record", null)
        binding.textViewBestAverageSpeedDate.text = cyclingPreferences.getString("$RECORD_BEST_AVERAGE_SPEED date", null)
        binding.textViewBiggestClimbTime.text = cyclingPreferences.getString("$RECORD_BIGGEST_CLIMB record", null)
        binding.textViewBiggestClimbDate.text = cyclingPreferences.getString("$RECORD_BIGGEST_CLIMB date", null)
    }

    private fun setupClickers() {
        binding.containerLongestRide.setOnClickListener {launchCyclingRecordScreen(getString(R.string.Cycling_1), "Distance")}
        binding.containerBiggestClimb.setOnClickListener {launchCyclingRecordScreen(getString(R.string.Cycling_2), "Height")}
        binding.containerBestAverageSpeed.setOnClickListener{launchCyclingRecordScreen(getString(R.string.Cycling_3), "Average Speed")}
    }

    private fun launchCyclingRecordScreen(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(record, FILENAME, recordFieldHint))
        startActivity(intent)
    }

    companion object{
        const val FILENAME = "cycling"
        const val RECORD_LONGEST_RIDE = "Longest Ride"
        const val RECORD_BEST_AVERAGE_SPEED = "Best Average Speed"
        const val RECORD_BIGGEST_CLIMB = "Biggest Climb"
    }


}
