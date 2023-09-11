package com.rakutentest.android.domain.repository

import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.data.model.dataRemote.response.Buybox
import com.rakutentest.android.data.model.dataRemote.response.Entry
import com.rakutentest.android.data.model.dataRemote.response.GlobalRating
import com.rakutentest.android.data.model.dataRemote.response.Image
import com.rakutentest.android.data.model.dataRemote.response.ImagesUrls
import com.rakutentest.android.data.model.dataRemote.response.Product
import com.rakutentest.android.data.model.dataRemote.response.ProductDetails
import com.rakutentest.android.data.model.dataRemote.response.ProductList
import com.rakutentest.android.data.model.dataRemote.response.Seller
import com.rakutentest.android.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProductRepository: ProductRepository {

    //This variable help us to simulate
    // our network error
    private var shouldReturnProductListNetworkError = false
    private var shouldReturnProductDetailsNetworkError = false

    // we build our first fake buy box result
    val fakeBuyBoxResult1 = Buybox(
        salePrice = 689.99f,
        advertType = "NEW",
        advertQuality = "NEW",
        saleCrossedPrice = 859f,
        salePercentDiscount = 19,
        isRefurbished = false
    )

    // we build our second fake buy box result
    val fakeBuyBoxResult2 = Buybox(
        salePrice = 475.95f,
        advertType = "NEW",
        advertQuality = "NEW",
        saleCrossedPrice = 759f,
        salePercentDiscount = 37,
        isRefurbished = false
    )

    // we build our first fake product result
    val fakeProductResult1 = Product(
        id = 6035914280,
        newBestPrice = 689.99f,
        usedBestPrice = 640f,
        headline = "Samsung Galaxy S21 5G 128 Go Double SIM Violet",
        reviewsAverageNote = 4.627659574468085,
        nbReviews = 94,
        categoryRef = 194695,
        imagesUrls = listOf(
            "https://fr.shopping.rakuten.com/photo/1673299896.jpg"
        ),
        buybox = fakeBuyBoxResult1
    )

    // we build our second fake product result
    val fakeProductResult2 = Product(
        id = 5504650604,
        newBestPrice = 475.95f,
        usedBestPrice = 415f,
        headline = "Samsung Galaxy S20 FE 5G 128 Go 6.7 pouces Bleu Double Sim",
        reviewsAverageNote = 4.570707070707071,
        nbReviews = 198,
        categoryRef = 194695,
        imagesUrls = listOf(
            "https://fr.shopping.rakuten.com/photo/1511640339.jpg"
        ),
        buybox = fakeBuyBoxResult2
    )

    // we build our  fake product list result
    val fakeProductListResult = ProductList(
        totalResultProductsCount = 10000,
        resultProductsCount = 2,
        pageNumber = 1,
        title = "Samsung",
        maxPageNumber = 30,
        maxProductsPerPage = 2,
        products = listOf(fakeProductResult1, fakeProductResult2)
    )

    //we build our fake seller
    val fakeSeller = Seller(
        id = 11773507564,
        login = "Pixel-Tech"
    )

    //we build our fake global rating
    val fakeGlobalRating = GlobalRating(
        score = 4.6,
        nbReviews = 94
    )

    //we build our first fake entries
    val fakeEntry1 = Entry(
        size = "ORIGINAL",
        url = "https://fr.shopping.rakuten.com/photo/1673299896.jpg"
    )

    val fakeEntry2 = Entry(
        size = "SMALL",
        url = "https://fr.shopping.rakuten.com/photo/1673299896_S.jpg"
    )

    val fakeEntry3 = Entry(
        size = "MEDIUM",
        url = "https://fr.shopping.rakuten.com/photo/1673299896_M.jpg"
    )

    val fakeEntry4 = Entry(
        size = "LARGE",
        url = "https://fr.shopping.rakuten.com/photo/1673299896_L.jpg"
    )

    val fakeImagesUrls = ImagesUrls(
        entry = listOf(
            fakeEntry1,
            fakeEntry2,
            fakeEntry3,
            fakeEntry4
        )
    )
    //we build our fake Images retrieve
    val fakeImage= Image(
        imagesUrls = fakeImagesUrls,
        id = 1673299899
    )


    // Here we build our fake product details element
    val fakeProductDetails = ProductDetails(
        productId = 6035914280,
        salePrice = 689.99f,
        newBestPrice = 689.99f,
        usedBestPrice = 640f,
        seller = fakeSeller,
        quality = "NEW",
        type = "NEW",
        sellerComment = "Carte SIM unique, version US, compatible avec les opérateurs français. Avant l'expédition, nous vérifierons " +
                "si le téléphone est en bon état et fournirons un ensemble complet d'accessoires compatibles " +
                "français (y compris les prises de charge européennes) + film de protection et coque de téléphone. Remarque: " +
                "Expédition depuis la Chine, une garantie de 9 à 19 jours ouvrables / un an est fournie.",
        headline = "Samsung Galaxy S21 5G 128 Go Double SIM Violet",
        description = "<li class=\\\"sub\\\"><span class=\\\"label\\\">Fabricant&nbsp;: </span>" +
                "<em class=\\\"value\\\"><a class=\\\"ft_link\\\" href=\\\"/s/samsung\\\">Samsung</a></em></li>    " +
                "<li class=\\\"sub\\\"><span class=\\\"label\\\">Référence fabricant&nbsp;: </span><em class=\\\"value\\\">SM-G991BZVDEUB - SM-G991BZVDEUH</em></li><li class=\\\"sub\\\"><span class=\\\"label\\\">Poids&nbsp;: </span><em class=\\\"value\\\">169</em></li><li class=\\\"sub\\\"><span class=\\\"label\\\">Interface sans fil&nbsp;: </span><em class=\\\"value\\\">NFC</em></li>\\r\\n\\r\\n<p>Produit soumis à la Rémunération Pour Copie Privée.&nbsp;<a href=\\\"javascript:void PM.BT.ubw('http',58,47,47,'www',46,'culturecommunication',46,'gouv',46,'fr',47,'Politiques-ministerielles',47,'Propriete-litteraire-et-artistique',47,'Commission-pour-la-remuneration-de-la-copie-privee',47,'Notice-explicative-pour-les-acquereurs-de-supports')\\\">En savoir plus</a></p>\\r\\n<p><a href=\\\"javascript:void PM.BT.ubw('http',58,47,47,'www',46,'culturecommunication',46,'gouv',46,'fr',47,'Politiques-ministerielles',47,'Propriete-litteraire-et-artistique',47,'Commission-pour-la-remuneration-de-la-copie-privee',47,'Questions-pratiques',47,'Les-montants-de-la-Remuneration-pour-Copie-Privee')\\\">Voir le montant de la Rémunération Pour Copie Privée</a></p>",
        categories = listOf(
            "Tel-PDA",
            "Telephones-mobiles"
        ),
        globalRating = fakeGlobalRating,
        images = listOf(
            fakeImage,
            fakeImage,
            fakeImage,
            fakeImage,
            fakeImage
        )
    )

    val productRoomResult1 = ProductRoom(
        id = fakeProductResult1.id,
        newBestPrice = fakeProductResult1.newBestPrice,
        usedBestPrice = fakeProductResult1.usedBestPrice,
        headline = fakeProductResult1.headline,
        reviewsAverageNote = fakeProductResult1.reviewsAverageNote,
        nbReviews = fakeProductResult1.nbReviews,
        categoryRef = fakeProductResult1.categoryRef,
        imagesUrls = fakeProductResult1.imagesUrls
    )

    val productRoomResult2 = ProductRoom(
        id = fakeProductResult2.id,
        newBestPrice = fakeProductResult2.newBestPrice,
        usedBestPrice = fakeProductResult2.usedBestPrice,
        headline = fakeProductResult2.headline,
        reviewsAverageNote = fakeProductResult2.reviewsAverageNote,
        nbReviews = fakeProductResult2.nbReviews,
        categoryRef = fakeProductResult2.categoryRef,
        imagesUrls = fakeProductResult2.imagesUrls
    )

    //we simulate the add and remove in our product table
    val productRooms = mutableListOf<ProductRoom>()

    override suspend fun getProducts(keyWord: String): Resource<ProductList> {
        //here we return our
        // products fake data
        return if (shouldReturnProductListNetworkError) {
            Resource.Error("Error", null)
        } else {
            Resource.Success(fakeProductListResult)
        }
    }

    override suspend fun getProductDetails(id: Long): Resource<ProductDetails> {
       return if (shouldReturnProductDetailsNetworkError) {
            Resource.Error("Error", null)
        } else {
            Resource.Success(fakeProductDetails)
        }
    }

    override fun getLocalProducts(): Flow<List<ProductRoom>> {
        //we retrieve some fake Product Room
        return flow {
            emit(
                listOf(
                    productRoomResult1,
                    productRoomResult2
                )
            ) }
    }

    override suspend fun deleteProducts() {
        productRooms.removeAll(productRooms)
    }

    override suspend fun insertProduct(product: ProductRoom) {
        TODO("Not yet implemented")
    }
}