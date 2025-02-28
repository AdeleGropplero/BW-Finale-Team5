import { combineReducers, configureStore } from "@reduxjs/toolkit";
import utenteReducer from "../reducers/utenteReducer";

const rootReducer = combineReducers({
  utente: utenteReducer
});

const store = configureStore({
  reducer: rootReducer
});

export default store;
