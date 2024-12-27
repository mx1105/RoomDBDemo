package com.mnaik.roomdb.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mnaik.roomdb.databinding.ItemDeviceListBinding
import com.mnaik.roomdb.model.Device
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

/**
 * Created by Monil Naik on 27-12-2024.
 */
@Module
@InstallIn(ActivityComponent::class)
class DeviceListAdapter @Inject constructor() :
    RecyclerView.Adapter<DeviceListAdapter.DeviceListViewHolder>() {
    private val mDataList = ArrayList<Device>()
    private var listener: OnItemClickListener? = null

    inner class DeviceListViewHolder(val mDataBinding: ItemDeviceListBinding) :
        RecyclerView.ViewHolder(mDataBinding.root) {
        fun bind(data: Device) {
            mDataBinding.item = data
            mDataBinding.ivRemove.setOnClickListener {
                listener?.delete(data)
            }
            mDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDeviceListBinding.inflate(inflater, parent, false)
        return DeviceListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: DeviceListViewHolder, position: Int) {
        val card = mDataList[holder.adapterPosition]
        holder.bind(card)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(
        mCompanyList: List<Device>
    ) {
        mDataList.clear()
        if (mCompanyList.isNotEmpty()) mDataList.addAll(mCompanyList)
        notifyDataSetChanged()
    }

    fun addListener(listener: OnItemClickListener){
        this.listener = listener
    }
}

fun interface OnItemClickListener {
    fun delete(device: Device)
}