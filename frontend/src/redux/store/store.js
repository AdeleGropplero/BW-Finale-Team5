import { combineReducers, configureStore } from "@reduxjs/toolkit";
import utenteReducer from "../reducers/utenteReducer";
import customerReducer from "../reducers/clienteReducer";

const rootReducer = combineReducers({
  utente: utenteReducer,
  customer: customerReducer
});

const store = configureStore({
  reducer: rootReducer
});

export default store;
