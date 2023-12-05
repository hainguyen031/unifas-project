import {configureStore} from '@reduxjs/toolkit'
import cartReducer from '../feature/cart/cartSlice'
import productsReducer from '../feature/product/productsSlice'
import categoryReducer from '../feature/category/categorySlice'
import subCategoryReducer from '../feature/subCategory/subCategorySlice'
import userReducer from '../feature/user/userSlice'
import addressReducer from "../feature/address/addressSlice";
import locationReducer from "../feature/location/locationSlice"


export const store = configureStore({
    reducer:{
        cart : cartReducer,
        products: productsReducer,
        userAccount: userReducer,
        category : categoryReducer,
        subCategory: subCategoryReducer,
        address : addressReducer,
        location : locationReducer
    },
})