package com.example.experiments.ui.fragment.addNote

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.example.experiments.R
import com.example.experiments.base.BaseFragment
import com.example.experiments.databinding.FragmentAddNoteBinding
import com.example.experiments.model.NoteModel
import com.example.experiments.ui.App


class AddNote : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {

    @SuppressLint("SetTextI18n")
    override fun setupObserver() {
        super.setupObserver()
        if (arguments != null) {
            binding.edTitle.setText(arguments?.getString("title"))
            binding.edDescription.setText(arguments?.getString("description").toString())
            binding.btnSaveNote.text = "change"
        }
    }

    override fun setupUI() {
        binding.btnSaveNote.setOnClickListener {
            val id = arguments?.getInt("position")
            val title = binding.edTitle.text.toString()
            val description = binding.edDescription.text.toString()
            if (arguments != null) {
                App.db.noteDao()!!.upDateNote(
                    NoteModel(
                        id = id,
                        title = title,
                        description = description))

            } else {
                App.db.noteDao()!!.addNote(
                    NoteModel(
                        title = title,
                        description = description))
            }
            controller.navigate(R.id.noteFragment)
        }
    }
}

