package dk.easj.anbo.collectwordsviewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dk.easj.anbo.collectwordsviewmodel.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val wordsViewModel: WordsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordsViewModel.words.observe(viewLifecycleOwner) { words: List<String> ->
            if (words.isEmpty()) {
                binding.textViewMessage.text = "No words"
            } else {
                binding.textViewMessage.text = ""
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                binding.recyclerView.adapter =
                    MyAdapter(words) { position -> onListItemClick(position) }
            }
        }

        binding.buttonAdd.setOnClickListener {
            val word = binding.editTextWord.text.trim().toString()
            wordsViewModel.add(word)

        }
    }

    private fun onListItemClick(position: Int) {
        Log.d("APPLE", wordsViewModel[position])
        Toast.makeText(activity, "You clicked " + wordsViewModel[position], Toast.LENGTH_SHORT).show()
        wordsViewModel.remove(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}