package com.example.pawtopia.screen.features.suspect.pet_diagnosis

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pawtopia.common.component.CustomSearchBar
import com.example.pawtopia.common.state.Resource
import com.example.pawtopia.data.model.Symptom

@Composable
fun PetDiagnosisScreen(
    navigateToSuspect: (prediction: String, description: String, treatment: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PetDiagnosisViewModel = hiltViewModel(),
) {
    var query by remember { mutableStateOf("") }
    val symptom by viewModel.symptom.collectAsStateWithLifecycle()
    val predict by viewModel.predict.collectAsStateWithLifecycle()
//    var items by remember { mutableStateOf(emptyList<Symptom>()) }
//    val listGejala = listOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    when (val predictData = predict) {
        is Resource.Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            navigateToSuspect(predictData.data.prediction, predictData.data.data.description, predictData.data.data.treatment)
        }

        is Resource.Error -> {
            Toast.makeText(LocalContext.current, "Error Predict", Toast.LENGTH_SHORT).show()
        }

        else -> {}
    }

    when (val symptomData = symptom) {
        is Resource.Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            var items by remember { mutableStateOf(symptomData.data.data) }

            PetDiagnosisContent(
                query = query,
                onQueryChange = { query = it },
                listSymptom = items,
                onSymptomChange = { items = it },
                onSubmitClick = {
                    val selectedItems = items.mapIndexed { _, data ->
                        if (data.isSelected) 1 else 0
                    }
                    viewModel.postSymptom(selectedItems.joinToString(","))
                },
                modifier = modifier
            )
        }
        is Resource.Error -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Error Symptom")
            }
        }

        else -> {}
    }


}

@Composable
fun PetDiagnosisContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listSymptom: List<Symptom>,
    onSymptomChange: (List<Symptom>) -> Unit,
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = "Beritahu kami gejala yang yang dialami hewan peliharaan anda",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            CustomSearchBar(
                query = query,
                onQueryChange = onQueryChange,
                placeholderText = "Cari gejala hewan peliharaan anda"
            )
            SymptomList(items = listSymptom, onChange = onSymptomChange)
        }
        ElevatedButton(
            onClick = {
                onSubmitClick()
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Suspect Penyakit", style = MaterialTheme.typography.headlineSmall)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun SymptomList(
    items: List<Symptom>,
    onChange: (List<Symptom>) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }
        items(items.size) { i ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(
                    width = Dp.Hairline,
                    color = MaterialTheme.colorScheme.outline
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onChange(items.mapIndexed { j, item ->
                                if (i == j) {
                                    item.copy(isSelected = !item.isSelected)
                                } else item
                            })
                        }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = items[i].name)
                    Checkbox(
                        checked = items[i].isSelected,
                        onCheckedChange = { isSelected ->
                            onChange(items.toMutableList().also {
                                it[i] = it[i].copy(isSelected = isSelected)
                            })
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.secondary,
                            checkmarkColor = Color.White
                        )
                    )
                }
            }
        }
    }
}