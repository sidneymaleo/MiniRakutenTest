package com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.product

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rakutentest.android.R
import com.rakutentest.android.data.model.dataRemote.response.enums.ProductEnum

/**
 * This UI help us to display our star
 * dynamically
 */
@Composable
fun ProductStarHandle(score: Double, nbReviews: Int) {

    Row {

        /**
         * Here we build our star notation,
         * the idea here is first to set up
         * the integer part and then complete
         * the decomal part
         */
        /**
         * Here we build our star notation,
         * the idea here is first to set up
         * the integer part and then complete
         * the decomal part
         */
        var countValue = score.toInt()
        for (i in 1..countValue) {
            Icon(
                Icons.Outlined.Star,
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                tint = colorResource(R.color.yellow400)
            )
        }

        // We retrieve the decimal part
        val decimalPartValue = (score - countValue)
        /**
         * We test if it is greater
         * than or equal to 0.5
         * to add the half star
         */
        /**
         * We test if it is greater
         * than or equal to 0.5
         * to add the half star
         */
        if (decimalPartValue >= 0.5) {
            Icon(
                Icons.Outlined.StarHalf,
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                tint = colorResource(R.color.yellow400)
            )
            //we increment countValue
            countValue++
        }

        val stayValue = 5 - countValue
        /**
         * If the remainder is greater than 0,
         * the remaining stars are added
         */
        /**
         * If the remainder is greater than 0,
         * the remaining stars are added
         */
        if (stayValue > 0) {
            for (i in 1..stayValue) {
                Icon(
                    Icons.Outlined.StarOutline,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    tint = colorResource(R.color.yellow400)
                )
            }
        }

        Text(
            text = "$nbReviews " + stringResource(R.string.notice),
            fontSize = 10.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

/**
 * Here we display our product price
 */
@Composable
fun ProductPrice(
    advertType: String,
    newBestPrice: Float,
    usedBestPrice: Float
) {

    // here we test our advert Type
    // for display the good informations
    if ((advertType).equals(ProductEnum.NEW.name)) {
        Row {
            Text(
                text = "$newBestPrice €",
                color = Color.Red,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.new_value),
                color = Color.Red,
                fontSize = 15.sp,
            )
        }
    } else if ((advertType).equals(ProductEnum.USED.name)) {
        Row {
            Text(
                text = stringResource(R.string.occasion),
                fontSize = 15.sp,
            )

            Text(
                text = "$usedBestPrice €",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

/**
 * This function help us to display our image
 */
@Composable
fun getProductImage(image: String): Painter {
    return rememberAsyncImagePainter(image)
}