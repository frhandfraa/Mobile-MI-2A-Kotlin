package farhan.defra.kotlin.Tugas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import farhan.defra.kotlin.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.*
import android.content.Intent
import java.util.Calendar

class FormPesananActivity : AppCompatActivity() {

    private lateinit var tvTanggal: TextView
    private lateinit var tvJam: TextView
    private lateinit var spJenisPelanggan: Spinner
    private val listMinuman = ArrayList<HashMap<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pesanan)

        tvTanggal = findViewById(R.id.tvTanggal)
        tvJam = findViewById(R.id.tvJam)
        spJenisPelanggan = findViewById(R.id.spJenisPelanggan)

        val etNoMeja = findViewById<EditText>(R.id.etNoMeja)
        val etNamaPelanggan = findViewById<EditText>(R.id.etNamaPelanggan)
        val chkMangga = findViewById<CheckBox>(R.id.chkMangga)
        val etJlhMangga = findViewById<EditText>(R.id.etJlhMangga)
        val chkAlpukat = findViewById<CheckBox>(R.id.chkAlpukat)
        val etJlhAlpukat = findViewById<EditText>(R.id.etJlhAlpukat)
        val chkJeruk = findViewById<CheckBox>(R.id.chkJeruk)
        val etJlhJeruk = findViewById<EditText>(R.id.etJlhJeruk)
        val chkTeh = findViewById<CheckBox>(R.id.chkTeh)
        val etJlhTeh = findViewById<EditText>(R.id.etJlhTeh)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.jenis_pelanggan_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spJenisPelanggan.adapter = adapter

        tvTanggal.setOnClickListener { showDatePicker() }

        tvJam.setOnClickListener { showTimePicker() }

        btnSimpan.setOnClickListener {
            listMinuman.clear()
            val intent = Intent(this, HasilPesananActivity::class.java)
            intent.putExtra("NoMeja", etNoMeja.text.toString())
            intent.putExtra("NamaPelanggan", etNamaPelanggan.text.toString())
            intent.putExtra("Tanggal", tvTanggal.text.toString())
            intent.putExtra("Jam", tvJam.text.toString())
            intent.putExtra("JenisPelanggan", spJenisPelanggan.selectedItem.toString())

            if (chkAlpukat.isChecked) {
                val item = HashMap<String, String>()
                item["menu"] = "Jus Pokat"
                item["qty"] = etJlhAlpukat.text.toString()
                item["harga"] = "15000"
                listMinuman.add(item)
            }
            if (chkMangga.isChecked) {
                val item = HashMap<String, String>()
                item["menu"] = "Jus Mangga"
                item["qty"] = etJlhMangga.text.toString()
                item["harga"] = "12000"
                listMinuman.add(item)
            }
            if (chkJeruk.isChecked) {
                val item = HashMap<String, String>()
                item["menu"] = "Jus Jeruk"
                item["qty"] = etJlhJeruk.text.toString()
                item["harga"] = "10000"
                listMinuman.add(item)
            }
            if (chkTeh.isChecked) {
                val item = HashMap<String, String>()
                item["menu"] = "Teh Manis"
                item["qty"] = etJlhTeh.text.toString()
                item["harga"] = "5000"
                listMinuman.add(item)
            }


            intent.putExtra("pesananList", listMinuman)
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