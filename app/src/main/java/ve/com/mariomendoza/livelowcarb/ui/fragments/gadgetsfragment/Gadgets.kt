package ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ve.com.mariomendoza.livelowcarb.R

class Gadgets : Fragment() {

    private var viewModel: GadgetsViewModel? = null
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
        viewModel = ViewModelProviders.of(this).get(GadgetsViewModel::class.java)

        goToBmi = rootView.findViewById(R.id.btn_go_to_bmi)
        goToBmr = rootView.findViewById(R.id.btn_go_to_bmr)
        goToWhr = rootView.findViewById(R.id.btn_go_to_whr)

        goToBmi?.setOnClickListener { viewModel!!.goToBmi(context) }
        goToBmr?.setOnClickListener { viewModel!!.goToBmr(context) }
        goToWhr?.setOnClickListener { viewModel!!.goToWhr(context) }
    }
}