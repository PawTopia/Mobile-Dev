package com.example.pawtopia.screen.features.clinic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pawtopia.R
import com.example.pawtopia.common.component.ClinicItem
import com.example.pawtopia.common.component.CustomSearchBar
import com.example.pawtopia.common.component.OutlinedBackButton
import com.example.pawtopia.common.state.Resource

@Composable
fun FindClinicScreen(
    navigateToDetailClinic: (String, Int) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FindClinicViewModel = hiltViewModel()
) {
    val query by viewModel.query

    viewModel.result.collectAsStateWithLifecycle(initialValue = Resource.Loading).value.let { resource ->
        when (resource) {
            is Resource.Loading -> {
                viewModel.getAllClinic()
            }

            is Resource.Error -> {
                Text(text = resource.error)
            }

            is Resource.Success -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = modifier
                        .fillMaxSize()
                        .padding(20.dp, 20.dp, 20.dp, 0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedBackButton(onClick = navigateUp)
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "logo",
                            modifier = Modifier
                                .size(100.dp)
                                .weight(1f)
                        )
                        Spacer(Modifier.width(40.dp))
                    }
                    Text(
                        text = "Temukan Lokasi Klinik",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    CustomSearchBar(
                        query = query,
                        onQueryChange = viewModel::searchClinic,
                        placeholderText = "Temukan lokasi klinik"
                    )

                    LazyColumn {
                        items(resource.data) {
                            ClinicItem(
                                name = it.name,
                                description = it.desc,
                                rating = it.rating,
                                onClick = { navigateToDetailClinic(it.name, it.id) }
                            )
                        }
                    }
                }
            }
            else -> {}
        }
    }


}

