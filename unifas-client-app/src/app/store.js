import {configureStore} from '@reduxjs/toolkit'
import userReducer from '../feature/userSlice'
export const store = configureStore({
    reducer:{
        userAccount: userReducer,
    },
})