import {configureStore} from '@reduxjs/toolkit'
import counterReducer from '../feature/counter/counterSlice'
import userReducer from '../feature/userSlice'

export const store = configureStore({
  reducer: {
    counter: counterReducer,
    userAccount: userReducer,
  },
});