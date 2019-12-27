package ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.bmi.Bmi
import ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.bmr.Bmr
import ve.com.mariomendoza.livelowcarb.ui.fragments.gadgetsfragment.whr.Whr

class GadgetsViewModel : ViewModel() {

    fun goToBmi(context: Context?) {
        val i = Intent(context, Bmi::class.java)
        context?.startActivity(i)
    }

    fun goToBmr(context: Context?) {
        val i = Intent(context, Bmr::class.java)
        context?.startActivity(i)
    }

    fun goToWhr(context: Context?) {
        val i = Intent(context, Whr::class.java)
        context?.startActivity(i)
    }

}