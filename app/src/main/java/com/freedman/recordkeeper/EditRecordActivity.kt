package com.freedman.recordkeeper

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.freedman.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable
import java.util.Date

const val INTENT_EXTRA_SCREEN_DATA = "screen_data"

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    private val screenData: ScreenData by lazy {
        intent.getSerializableExtra(
            INTENT_EXTRA_SCREEN_DATA
        ) as ScreenData
    }
    private lateinit var wheretoSave: String
    private lateinit var heading: String
    private lateinit var hint: String
    private val sharedPref: SharedPreferences by lazy {
        this.getSharedPreferences(
            wheretoSave, Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVariables()
        setInfo()
        setupUI()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setInfo() {
        binding.editTextRecord.setText(sharedPref.getString("$heading record", null))
        binding.editTextDate.setText(sharedPref.getString("$heading date", null))
    }

    private fun setVariables() {
        wheretoSave = screenData.save
        heading = screenData.record
        hint = screenData.hint
    }

    private fun setupUI() {
        title = "${heading} Record"

        binding.textInputRecord.hint = hint

        binding.buttonSaveInfo.setOnClickListener {
            sharedPref.edit {
                putString("$heading record", binding.editTextRecord.text.toString())
                putString("$heading date", binding.editTextDate.text.toString())
            }
            finish()
        }

        binding.buttonClear.setOnClickListener {
            sharedPref.edit {
                remove("$heading record")
                remove("$heading date")
            }
            finish()

        }
    }


    data class ScreenData(val record: String, val save: String, val hint: String) : Serializable


    /*
    //variables
        private lateinit var binding: ActivityEditRecordBinding

        private val screenData: ScreenData by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("screen_data", ScreenData::class.java) as ScreenData
            } else {
                intent.getSerializableExtra("screen_data") as ScreenData
            }
        }
        private val recordPreferences: SharedPreferences by lazy {
            getSharedPreferences(
                "running",
                Context.MODE_PRIVATE
            )
        }
        private val record by lazy { intent.getStringExtra("screen_data") }


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityEditRecordBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupUI()
            displayRecord()
        }


        private fun setupUI() {
            title = "$record Record"
            //binding.textInputRecord.hint = screenData.recordFieldHint

            binding.buttonSaveInfo.setOnClickListener {
                saveRecord()
                finish()
            }

            binding.buttonClear.setOnClickListener {
                clearRecord()
                finish()
            }
        }

        private fun displayRecord() {
            binding.editTextRecord.setText(recordPreferences.getString("${screenData.record} record", null))
            binding.editTextDate.setText(recordPreferences.getString("${screenData.record} date", null))
        }



        private fun saveRecord() {
            val record = binding.editTextRecord.text.toString()
            val date = binding.editTextDate.text.toString()

            recordPreferences.edit {
                putString("${this@EditRecordActivity.screenData.record} record", record) //do i need to add the @?
                putString("${this@EditRecordActivity.screenData.record} date", date)
            }
        }

        private fun clearRecord() {
            recordPreferences.edit {
                remove("${screenData.record} record")
                remove("${screenData.record} date")
            }

        }

        data class ScreenData(
            val record: String,
            val sharedPreferencesName: String,
            val recordFieldHint: String
        ) : Serializable*/


    //THINGS I WANT TO DO
    //OBTAIN THE INFORMATION FROM THE PREVIOUS ACTIVITY
    //MAIN ACTIVITY - RUNS FRAGMENT -> RUNS THIS ACTIVITY

    //FRAGMENT SENDS INFO HERE
    //record
    //MAYBE FRAGMENT CAN ALSO SEND SHARED INFO

    //THIS FILE TAKES IN THE INFORMATION SUCH AS RECORD NAME, RECORD, DATE

    //FILLS INFO INTO EDIT FIELDS

    //SAVES INFO HERE


    // can be used to set Toolbar Title

}


//


//


//

//below is old view style, can try if it works later

/*

<EditText
android:id="@+id/text_view_edit_heading"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:fontFamily="sans-serif-light"
android:text="@string/edit_me"
android:textColor="@color/purple"
android:layout_marginTop="200dp"
android:textSize="20sp"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
android:autofillHints="Edit me" />

<TextView
android:id="@+id/text_view_edit_time"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:fontFamily="sans-serif-light"
android:layout_marginTop="200dp"
android:text="@string/edit_time"
android:textColor="@color/dark_grey"
android:textSize="20sp"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintTop_toTopOf="parent" />

<TextView
android:id="@+id/text_view_edit_date"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_marginTop="200dp"
android:fontFamily="sans-serif-light"
android:text="@string/edit_date"
android:textColor="@color/light_grey"
android:textSize="14sp"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintTop_toBottomOf="@id/text_view_edit_time" />




<View
android:background="@color/light_grey"
android:layout_height="2dp"
android:layout_width="match_parent"
app:layout_constraintTop_toBottomOf="@id/text_view_edit_date"/>*/
