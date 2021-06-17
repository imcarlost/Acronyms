package dev.carlos.acronyms.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dev.carlos.acronyms.R
import dev.carlos.acronyms.viewmodel.NavigationViewmodel
import dev.carlos.core.extensions.observeNonNull
import dev.carlos.core.navigation.NavigationRouter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.main_fragment_container) }
    private val navRouter: NavigationRouter by inject()
    private val viewModel: NavigationViewmodel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        navController.setGraph(R.navigation.main_navigation)
        navRouter.setOnNavigationEvent {
            viewModel.onNavigationEvent(it)
        }
        viewModel.navigate.observeNonNull(this) { pair ->
            // Pair.first is a Navigation Id
            // Pair.second is a Bundle
            navController.navigate(pair.first, pair.second)
        }

        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}
