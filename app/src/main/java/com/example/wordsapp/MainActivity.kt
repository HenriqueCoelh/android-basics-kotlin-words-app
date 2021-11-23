/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Define o LinearLayoutManager do recyclerview
        chooseLayout()
    }


    private fun chooseLayout(){
        if (isLinearLayoutManager){
            recyclerView.layoutManager = LinearLayoutManager(this)
        }else{
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        // Defina o drawable para o ícone do menu com base em qual LayoutManager está atualmente em uso

        // Uma cláusula if pode ser usada no lado direito de uma atribuição se todos os caminhos retornarem um valor.
        // O código a seguir é equivalente a
        // if (isLinearLayoutManager)
        // menu.icon = ContextCompat.getDrawable (this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable (this, R.drawable.ic_linear_layout)
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        // Chama o código para definir o ícone com base no LinearLayoutManager do RecyclerView
        setIcon(layoutButton)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Define isLinearLayoutManager (um booleano) com o valor oposto
                isLinearLayoutManager = !isLinearLayoutManager
                // Define layout e ícone
                chooseLayout()
                setIcon(item)

                return true
            }
            // Caso contrário, não faça nada e use o tratamento de evento principal

            // quando as cláusulas exigem que todos os caminhos possíveis sejam explicados explicitamente,
            // por exemplo, os casos verdadeiro e falso se o valor for um booleano,
            // ou outro para capturar todos os casos não tratados.
            else -> super.onOptionsItemSelected(item)
        }
    }




}
