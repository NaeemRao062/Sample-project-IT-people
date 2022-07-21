package com.itpeople.sample.mvvm.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.itpeople.sample.mvvm.R
import com.itpeople.sample.mvvm.data.model.ResultsItem
import com.itpeople.sample.mvvm.ui.adapter.MainAdapter
import com.itpeople.sample.mvvm.ui.view.activity.MainActivity
import com.itpeople.sample.mvvm.ui.viewmodel.MainViewModel
import com.itpeople.sample.mvvm.utilities.DateUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var resultItem: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true);

        if (arguments != null) {
            // The getPrivacyPolicyLink() method will be created automatically.
            resultItem = arguments?.getString("resultItem").toString()

            var item : ResultsItem = Gson().fromJson(resultItem,ResultsItem::class.java)

            (activity as AppCompatActivity?)!!.supportActionBar!!.title = item.name?.first+" "+item.name?.last
            tvCity.text = "City "+item.location?.city
            tvCountry.text = "Country "+item.location?.country
            tvDate.text = "Date "+ DateUtility.changeFormat(""+ DateUtility.DATE_FORMAT_ZONE,""+ DateUtility.DATE_ONLY_FORMAT,item.registered?.date)
            tvAge.text = item.dob?.age
            tvDob.text = "DOB "+DateUtility.changeFormat(""+ DateUtility.DATE_FORMAT_ZONE,""+ DateUtility.DATE_ONLY_FORMAT,item.dob?.date)
            tvEmail.text = "Email "+item.email
            tvPostalCode.text = "Postal Code "+item.location?.postcode
            tvState.text = "State "+item.location?.state

            Glide.with(requireContext())
                .load(item.picture?.large)
                .into(ivUserImage)
        }
        setupUI()
        setupObserver()

    }


    private fun setupObserver() {

    }

    private fun setupUI() {

    }




}