package com.example.chaebi.ui.main.cloth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.chaebi.R
import com.example.chaebi.data.model.cloth
import com.example.chaebi.databinding.FragmentClothBinding
import com.example.chaebi.ui.main.MainActivity
import com.example.chaebi.ui.main.cloth.adapter.ItemAdapter
import com.example.chaebi.ui.main.weather.adapter.WeatherAdapter


class ClothFragment : Fragment() {

    private var _binding: FragmentClothBinding? = null
    private val binding: FragmentClothBinding
        get() = requireNotNull(_binding) {"바인딩 객체를 생성해주세요."}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClothBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapterlist = (activity as MainActivity).getlist()
        //리사이클러뷰
        val clothAdapter = ItemAdapter(requireContext())
        binding.rvCloth1.adapter= clothAdapter
        clothAdapter.setFriendList(adapterlist)

        var adapterlist2 = (activity as MainActivity).getshirtlist()
        val clothAdapter2 = ItemAdapter(requireContext())
        binding.rvCloth2.adapter= clothAdapter2
        clothAdapter2.setFriendList(adapterlist2)

        var adapterlist3 = (activity as MainActivity).getpantlist()
        val clothAdapter3 = ItemAdapter(requireContext())
        binding.rvCloth3.adapter= clothAdapter3
        clothAdapter2.setFriendList(adapterlist3)

        binding.btPlus.setOnClickListener {

            var selectcloth = 0
            var clothname = ""
            var clothnumber = ""

            // Dialog만들기
            val mDialogView = LayoutInflater.from(activity as MainActivity).inflate(R.layout.activity_popup, null)

            val mBuilder = AlertDialog.Builder(activity as MainActivity)
                .setView(mDialogView)


            val mAlertDialog = mBuilder.show()

            val et_cloth_name = mDialogView.findViewById<EditText>(R.id.et_name)
            val et_cloth_number = mDialogView.findViewById<EditText>(R.id.et_number)
            val next = mDialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.bt_register)

            next.setOnClickListener {

                clothname = et_cloth_name.text.toString()
                clothnumber = et_cloth_number.text.toString()
                mAlertDialog.dismiss()

                val mDialogView2 = LayoutInflater.from(activity as MainActivity).inflate(R.layout.activity_popupselect, null)

                val mBuilder2 = AlertDialog.Builder(activity as MainActivity)
                    .setView(mDialogView2)


                val mAlertDialog2 = mBuilder2.show()

                val cloth1 = mDialogView2.findViewById<ImageView>(R.id.iv_cloth1)
                val cloth2 = mDialogView2.findViewById<ImageView>(R.id.iv_cloth2)
                val cloth3 = mDialogView2.findViewById<ImageView>(R.id.iv_cloth3)
                val bt_next = mDialogView2.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.bt_register2)

                cloth1.setOnClickListener {
                    selectcloth = 1
                    cloth1.setImageResource(R.drawable.iv_cloth1_sel)
                    cloth2.setImageResource(R.drawable.iv_cloth2)
                    cloth3.setImageResource(R.drawable.iv_cloth3)
                }
                cloth2.setOnClickListener {
                    selectcloth = 2
                    cloth1.setImageResource(R.drawable.iv_cloth1)
                    cloth2.setImageResource(R.drawable.iv_cloth2_sel)
                    cloth3.setImageResource(R.drawable.iv_cloth3)
                }
                cloth3.setOnClickListener {
                    selectcloth = 3
                    cloth1.setImageResource(R.drawable.iv_cloth1)
                    cloth2.setImageResource(R.drawable.iv_cloth2)
                    cloth3.setImageResource(R.drawable.iv_cloth3_sel)
                }
                bt_next.setOnClickListener {
                    mAlertDialog2.dismiss()

                    var selectitm = cloth()
                    selectitm.name = clothname
                    selectitm.hottest = clothnumber.toInt()
                    selectitm.select = selectcloth

                    (activity as MainActivity).putlist(clothname, clothnumber.toInt(), selectcloth)

                    var adapterlist = (activity as MainActivity).getlist()
                    //리사이클러뷰
                    val clothAdapter = ItemAdapter(requireContext())
                    binding.rvCloth1.adapter= clothAdapter
                    clothAdapter.setFriendList(adapterlist)

                    var adapterlist2 = (activity as MainActivity).getshirtlist()
                    val clothAdapter2 = ItemAdapter(requireContext())
                    binding.rvCloth2.adapter= clothAdapter2
                    clothAdapter2.setFriendList(adapterlist2)

                    var adapterlist3 = (activity as MainActivity).getpantlist()
                    val clothAdapter3 = ItemAdapter(requireContext())
                    binding.rvCloth3.adapter= clothAdapter3
                    clothAdapter2.setFriendList(adapterlist3)




                }

            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}