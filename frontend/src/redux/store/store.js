import { combineReducers, configureStore } from "@reduxjs/toolkit";
import utenteReducer from "../reducers/utenteReducer";
import customerReducer from "../reducers/clienteReducer";
import fatturaReducer from "../reducers/fatturaReducer";
import loginReducer from "../reducers/loginReducer";

const rootReducer = combineReducers({
  utente: utenteReducer,
  customer: customerReducer,
  fattura: fatturaReducer,
  login: loginReducer
});

const store = configureStore({
  reducer: rootReducer
});

export default store;
