package com.appsv.revisecontactapp.presentation.screen

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.appsv.revisecontactapp.presentation.ContactState
import com.appsv.revisecontactapp.presentation.ContactViewModel
import com.appsv.revisecontactapp.presentation.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: ContactViewModel,
    state: ContactState
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contacts") },
                actions = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Sort,
                        contentDescription = "sort",
                        modifier = Modifier.clickable {
                            viewModel.changeIsSorting()
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.AddEdit.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            LazyColumn {
                items(state.contacts) { contact ->
                    val bitmap = contact.image?.let {
                        BitmapFactory.decodeByteArray(it, 0, it.size)
                    }?.asImageBitmap()

                    contactCard(
                        id = contact.id,
                        name = contact.name,
                        phoneNumber = contact.phoneNumber,
                        email = contact.email,
                        imageByteArray = contact.image,
                        image = bitmap,
                        dateOfCreation = contact.dateOfCreation,
                        viewModel = viewModel,
                        state = state,
                        navController = navController
                    )

                }
            }
        }
    }

}


@Composable
fun contactCard(
    id: Int,
    name: String,
    phoneNumber: String,
    email: String,
    imageByteArray: ByteArray?,
    image: ImageBitmap?,
    dateOfCreation: Long,
    viewModel: ContactViewModel,
    state: ContactState,
    navController: NavHostController


) {

    val context = LocalContext.current
    Card(
        onClick = {
            state.id.value = id
            state.name.value = name
            state.phoneNumber.value = phoneNumber
            state.email.value = email
            state.image.value = imageByteArray
            state.dateOfCreation.value = dateOfCreation
            navController.navigate(Routes.AddEdit.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)

        ) {
            if (image != null) {
                Image(
                    bitmap = image,
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)

                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "image",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onPrimaryContainer)
                        .padding(16.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = phoneNumber,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = email,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                IconButton(
                    onClick = {
                        state.id.value = id
                        state.name.value = name
                        state.phoneNumber.value = phoneNumber
                        state.email.value = email
                        state.image.value = imageByteArray
                        state.dateOfCreation.value = dateOfCreation
                        viewModel.DeleteContact()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))
                IconButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_CALL)
                        intent.data = Uri.parse("tel:$phoneNumber")
                        context.startActivity(intent)
                    }
                ){
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "call",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}
