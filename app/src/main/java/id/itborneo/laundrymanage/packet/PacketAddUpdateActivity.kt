package id.itborneo.laundrymanage.packet

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.packet.PacketActivity.Companion.EXTRA_PACKET
import kotlinx.android.synthetic.main.activity_packet_add_update.*

class PacketAddUpdateActivity : AppCompatActivity() {

    private var isAddData = true
    private var data: PacketModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packet_add_update)

        retrieveData()
        setupActionbar()
        buttonListener()
    }

    private fun buttonListener() {

    }

    private fun retrieveData() {
        data = intent.getParcelableExtra(EXTRA_PACKET)
        if (data != null) {
            isAddData = false
        }
        updateField()
    }

    private fun updateField() {
        etName.setText(data?.name)
        etPrice.setText(data?.price)
        etNote.setText(data?.note)

        btnAddUpdate.text = "Update Paket"
        btnAddUpdate.icon = ContextCompat.getDrawable(this, R.drawable.ic_update)
    }

    //mengatur toolbar
    private fun setupActionbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = if (isAddData) {
            "Tambah Paket"
        } else {
            "Update paket"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //action ketika tombol back ditekan di toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}