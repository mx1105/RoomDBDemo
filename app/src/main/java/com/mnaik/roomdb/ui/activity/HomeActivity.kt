package com.mnaik.roomdb.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mnaik.roomdb.R
import com.mnaik.roomdb.databinding.ActivityHomeBinding
import com.mnaik.roomdb.model.Device
import com.mnaik.roomdb.ui.adapter.DeviceListAdapter
import com.mnaik.roomdb.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var mDataBinding: ActivityHomeBinding
    private val mViewModel: HomeViewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var mAdapter: DeviceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mDataBinding.rvDeviceList.adapter = mAdapter
        initializeListener()
        initializeObserver()
    }

    private fun initializeObserver() {
        mViewModel.allDevices.observe(this@HomeActivity) {
            mAdapter.addData(it)
            mDataBinding.tvNoRecord.isVisible = it.isNullOrEmpty()
        }
    }

    private fun initializeListener() {
        mDataBinding.btAdd.setOnClickListener {
            displayBottomSheet()
        }
        mAdapter.addListener { device -> mViewModel.removeDevice(device) }
    }

    private fun displayBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(R.layout.add_device)
        val saveTxt = bottomSheetDialog.findViewById<TextView>(R.id.save_txt)
        val name = bottomSheetDialog.findViewById<EditText>(R.id.et_device_name)
        val description = bottomSheetDialog.findViewById<EditText>(R.id.et_device_description)

        saveTxt!!.setOnClickListener {
            currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
            if (name?.text.isNullOrEmpty()) {
                Toast.makeText(this@HomeActivity, "Please Enter device name", Toast.LENGTH_SHORT)
                    .show()
            } else if (description?.text.isNullOrEmpty()) {
                Toast.makeText(
                    this@HomeActivity, "Please Enter device description", Toast.LENGTH_SHORT
                ).show()
            } else {
                mViewModel.addDevice(
                    Device(
                        id = 0,
                        deviceName = name?.text.toString(),
                        description = description?.text.toString()
                    )
                )
                bottomSheetDialog.dismiss()
            }

        }
        bottomSheetDialog.show()
    }
}