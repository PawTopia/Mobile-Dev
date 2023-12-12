package com.example.pawtopia.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pawtopia.common.component.ClinicItem
import com.example.pawtopia.common.component.TopBar
import com.example.pawtopia.data.model.clinicList

@Composable
fun SavedScreen(
    navigateToDetailClinic: () -> Unit,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column {
            TopBar(
                title = "Klinik Favorit",
                onClick = navigateUp,
                modifier = Modifier.padding(20.dp, 20.dp, 12.dp, 12.dp)
            )
            LazyColumn(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(clinicList) {
                    ClinicItem(name = it.name, onClick = navigateToDetailClinic)
                }
            }
        }
    }
}
