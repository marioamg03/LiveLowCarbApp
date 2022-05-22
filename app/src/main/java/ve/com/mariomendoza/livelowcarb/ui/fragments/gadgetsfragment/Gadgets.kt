package ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import ve.com.mariomendoza.livelowcarb.R
import ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.bmi.Bmi
import ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.bmr.Bmr
import ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.whr.Whr

class Gadgets : Fragment() {

    private var goToBmi: ImageButton? = null
    private var goToBmr: ImageButton? = null
    private var goToWhr: ImageButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_gadgets, container, false)
        configView(rootView)
        return rootView
    }

    private fun configView(rootView: View) {
        goToBmi = rootView.findViewById(R.id.btn_go_to_bmi)
        goToBmr = rootView.findViewById(R.id.btn_go_to_bmr)
        goToWhr = rootView.findViewById(R.id.btn_go_to_whr)

        goToBmi?.setOnClickListener { goToBmi() }
        goToBmr?.setOnClickListener { goToBmr() }
        goToWhr?.setOnClickListener { goToWhr() }
    }

    fun goToBmi() {
        val i = Intent(context, Bmi::class.java)
        startActivity(i)
    }

    fun goToBmr() {
        val i = Intent(context, Bmr::class.java)
        startActivity(i)
    }

    fun goToWhr() {
        val i = Intent(context, Whr::class.java)
        startActivity(i)
    }
}