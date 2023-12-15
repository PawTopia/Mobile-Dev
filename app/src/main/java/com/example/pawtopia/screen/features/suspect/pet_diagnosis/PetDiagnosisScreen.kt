package com.example.pawtopia.screen.features.suspect.pet_diagnosis

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pawtopia.common.component.CustomSearchBar

@Composable
fun PetDiagnosisScreen(
    navigateToSuspect: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PetDiagnosisViewModel = hiltViewModel(),
) {

    val checkedState = remember {
        mutableStateOf(false)
    }
    var items by remember {
        mutableStateOf(
            (1..20).map {
                ListItem(
                    title = "Item $it",
                    isSelected = false
                )
            }
        )
    }
    var query by remember { mutableStateOf("") }
    val symptom by viewModel.symptom.collectAsStateWithLifecycle()

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
                text = "Please tell us which symptom describe your pet’s condition",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            CustomSearchBar(
                query = query, onQueryChange = {
                    query = it
                },
                placeholderText = "Cari gejala hewan peliharaan anda"
            )
            LazyColumn() {
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
                                    items = items.mapIndexed { j, item ->
                                        if (i == j) {
                                            item.copy(isSelected = !item.isSelected)
                                        } else item
                                    }
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = items[i].title)
                            Checkbox(
                                checked = items[i].isSelected,
                                onCheckedChange = { isSelected ->
                                    items = items.toMutableList().also {
                                        it[i] = it[i].copy(isSelected = isSelected)
                                    }
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


        ElevatedButton(
            onClick = { navigateToSuspect() },
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

data class ListItem(
    val title: String,
    val isSelected: Boolean
)

@Preview(showBackground = true)
@Composable
fun Preview() {
    PetDiagnosisScreen(navigateToSuspect = {})
}