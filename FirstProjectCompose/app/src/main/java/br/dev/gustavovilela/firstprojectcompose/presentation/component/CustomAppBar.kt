package br.dev.gustavovilela.firstprojectcompose.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.dev.gustavovilela.firstprojectcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "profile picture"
                )
                Text(stringResource(id = R.string.title))
            }
        },
        navigationIcon = {
            IconButton({}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "menu items"
                )
            }
        },
        actions = {
//                IconButton(onClick = {}) {
//                    Icon(
//                        imageVector = Icons.Filled.AddCircle,
//                        contentDescription = "settingd"
//                    )
//                }
//                IconButton(onClick = {}) {
//                    Icon(
//                        imageVector = Icons.Filled.Call,
//                        contentDescription = "phone call"
//                    )
//                }
//                IconButton(onClick = {}) {
//                    Icon(
//                        imageVector = Icons.Filled.MoreVert,
//                        contentDescription = "more options"
//                    )
//                }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primaryContainer)
    )
}