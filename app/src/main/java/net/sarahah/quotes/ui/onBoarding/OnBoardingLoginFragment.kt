package net.sarahah.quotes.ui.onBoarding

import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.postDelayed
import net.sarahah.quotes.R
import net.sarahah.quotes.base.BaseSupportFragment
import net.sarahah.quotes.ui.bottom_tabs.QuotesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class OnBoardingLoginFragment : BaseSupportFragment(R.layout.fragment_on_boarding_login) {

    override val viewModel by viewModel<QuotesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationVisibility = View.GONE

        val point = Point()
        requireActivity().windowManager.defaultDisplay.getSize(point)
        val translation: Float = (point.y / 5).toFloat()
        requireView().postDelayed(100) {
            view.findViewById<ImageView>(R.id.splash_logo).animate()
                .translationYBy(translation * -1)
                .setDuration(1150)
                .setInterpolator(DecelerateInterpolator())
                .withStartAction {
                    view.postDelayed({
                                     navController.navigate(R.id.quotesFragment)
                    }, 1500)
                }
                .start()
        }

    }

}