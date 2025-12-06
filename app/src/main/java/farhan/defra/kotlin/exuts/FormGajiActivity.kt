package farhan.defra.kotlin.exuts

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import farhan.defra.kotlin.R
import java.util.Calendar

class FormGajiActivity: AppCompatActivity() {

    private lateinit var tvTanggal: TextView
    private lateinit var tvJam: TextView
    private lateinit var spJenisGolongan: Spinner
    private val listGolongan = ArrayList<HashMap<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_gaji)

        tvTanggal = findViewById(R.id.tvTanggal)
        tvJam = findViewById(R.id.tvJam)
        spJenisGolongan = findViewById(R.id.spJenisGolongan)

        val etNIP = findViewById<EditText>(R.id.etNIP1)
        val etNama = findViewById<EditText>(R.id.etNama)
        val chkUangMakan = findViewById<CheckBox>(R.id.chkUangMakan)
        val etJlhUangMakan = findViewById<EditText>(R.id.etJlhUangMakan)
        val chkLembur = findViewById<CheckBox>(R.id.chkLembur)
        val etJlhLembur = findViewById<EditText>(R.id.etJlhLembur)
        val chkDinasLuar = findViewById<CheckBox>(R.id.chkDinasLuar)
        val etJlhDinasLuar = findViewById<EditText>(R.id.etJlhDinasLuar)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.list_golongan_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spJenisGolongan.adapter = adapter

        tvTanggal.setOnClickListener { showDatePicker() }
        tvJam.setOnClickListener { showTimePicker() }

        btnSimpan.setOnClickListener {
            listGolongan.clear()

            val intent = Intent(this, HasilFormGajiActivity::class.java)
            intent.putExtra("NIP", etNIP.text.toString())
            intent.putExtra("NamaPegawai", etNama.text.toString())
            intent.putExtra("Tanggal", tvTanggal.text.toString())
            intent.putExtra("Jam", tvJam.text.toString())
            intent.putExtra("Golongan", spJenisGolongan.selectedItem.toString())

            if (chkUangMakan.isChecked) {
                val item = HashMap<String, String>()
                item["keterangan"] = "Uang Makan"
                item["jumlah"] = etJlhUangMakan.text.toString()
                item["nilai"] = "15000"
                listGolongan.add(item)
            }

            if (chkLembur.isChecked) {
                val item = HashMap<String, String>()
                item["keterangan"] = "Lembur"
                item["jumlah"] = etJlhLembur.text.toString()
                item["nilai"] = "25000"
                listGolongan.add(item)
            }

            if (chkDinasLuar.isChecked) {
                val item = HashMap<String, String>()
                item["keterangan"] = "Dinas Luar"
                item["jumlah"] = etJlhDinasLuar.text.toString()
                item["nilai"] = "30000"
                listGolongan.add(item)
            }

            intent.putExtra("listGolongan", listGolongan)
            startActivity(intent)
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvTanggal.text = date
            },
            year, month, day
        )

        datePicker.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val jam = String.format("%02d:%02d", selectedHour, selectedMinute)
                tvJam.text = jam
            },
            hour, minute, true
        )

        timePicker.show()
    }
}
