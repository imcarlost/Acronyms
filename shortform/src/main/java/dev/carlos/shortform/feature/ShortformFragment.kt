package dev.carlos.shortform.feature

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.carlos.core.domain.network.RequestError
import dev.carlos.core.domain.network.RequestState
import dev.carlos.core.extensions.gone
import dev.carlos.core.extensions.observeNonNull
import dev.carlos.core.extensions.visible
import dev.carlos.shortform.R
import dev.carlos.shortform.data.models.ShortformModel
import dev.carlos.shortform.databinding.FragmentShortformDefinitionBinding
import dev.carlos.shortform.viewmodels.ShortformViewmodel
import dev.carlos.shortform.widgets.LongformAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShortformFragment : Fragment() {
    private val viewModel: ShortformViewmodel by viewModel()
    private val listAdapter by lazy { LongformAdapter() }
    private lateinit var binding: FragmentShortformDefinitionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_shortform_definition, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBinding(view)
        setRecycler()
        setObservers()
    }

    private fun setBinding(view: View) {
        binding = FragmentShortformDefinitionBinding.bind(view)
    }

    private fun setObservers() {
        viewModel.acronymDefinition.observeNonNull(this) {
            handleResult(it)
        }

        binding.shortformDefinitionSearchField.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    onSearch()
                    return true
                }
                return false
            }
        })
    }

    private fun onSearch() {
        fetchAcronym(binding.shortformDefinitionSearchField.text.toString())
        binding.shortformDefinitionRecycler.requestFocus()
    }

    private fun fetchAcronym(acronym: String) {
        viewModel.fetchAcronymDefinition(acronym)
    }

    private fun handleResult(state: RequestState) {
        when (state) {
            is RequestState.Success<*> -> handleSuccess(state.data as ShortformModel)
            is RequestState.Empty -> handleEmpty()
            is RequestState.Error -> handleError(state.type)
            is RequestState.Loading -> handleLoading()
        }
    }

    private fun handleLoading() {
        binding.shortformDefinitionLoading.visible()
        binding.shortformDefinitionError.gone()
    }

    private fun handleSuccess(shortform: ShortformModel) {
        binding.shortformDefinitionError.gone()
        binding.shortformDefinitionLoading.gone()
        listAdapter.addAll(shortform.results)
    }

    private fun handleEmpty() {
        binding.shortformDefinitionErrorLabel.text = getString(R.string.shortform_definition_no_definition)
        binding.shortformDefinitionError.visible()
        binding.shortformDefinitionLoading.gone()
    }

    private fun handleError(error: RequestError) {
        binding.shortformDefinitionErrorLabel.text = getString(error.message)
        binding.shortformDefinitionError.visible()
        binding.shortformDefinitionLoading.gone()
    }

    private fun setRecycler() {
        binding.shortformDefinitionRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter.apply {
                onItemClick = {
                    Toast.makeText(context, it.value, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}